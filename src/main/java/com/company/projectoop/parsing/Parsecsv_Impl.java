package com.company.projectoop.parsing;

import com.company.projectoop.model.Ms;
import com.company.projectoop.table.Riga_metadata;
import com.company.projectoop.table.Riga_tabella;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe che implementa i metodi descritti nell'intefaccia Parsecsv.
 * Viene scaricato il JSON dall'URL, se ne ricava il link per scaricare il file CSV
 * e poi viene effettuato il parsing del CSV.
 * I dati vengono immagazzinati in una lista.
 * @author Matteo Perozzi
 * @author Ettore Zamponi
 */

@Component
@Repository
@Service("Parsecsv_Impl")

public class Parsecsv_Impl implements Parsecsv {

    private static String namefile = "C:/Users/Matteo/Desktop/prova.csv";

    /**
     * ArrayList in cui vengono immagazzinati i dati completi letti dal CSV
     */
    public static List<Riga_tabella> data = new ArrayList<>();

    /**
     * ArrayList in cui vengono immagazzinati i metadati.
     */
    public static List<Riga_metadata> metadata = new ArrayList<>();

    /**
     * getter della lista: metadata.
     * @return
     */
    @Override
    public List<Riga_metadata> getMetadata() {
        return metadata;
    }

    /**
     * getter della lista: data
     * @return
     */
    @Override
    public List<Riga_tabella> getData() {
        return data;
    }

    /**
     * Metodo che legge il file mediante BufferedReader riga per riga e tramite l'ausilio
     * dei metodi Parse_file() e Popola_metadata() effettua il parsing del file CSV (precedemente scaricato tramite
     * l'utilizzo dei metodi della classe Parsejson_Impl).
     * I dati vengono immagazzinati nelle due liste di riferimento.
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static void Popola_tabella() {

        BufferedReader reader = null;

        //apro il file e leggo liea per linea --> Fino alla fine del file
        try {
            reader = new BufferedReader(new FileReader(namefile));
            String line = null;
            //legge la prima linea e popola la struttura dati che conterrà i metadati
            line = reader.readLine();
            Popola_metadata(line, metadata);
            do {
                line = reader.readLine();
                Parse_file(line, data);
                if (line == null) break;
            } while(reader.ready());

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        finally {
            //chiusura del flusso di lettura del file
            try {
                if (reader != null) reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //eliminazione dell'ultima riga della tabella che è null di fine riga e viene letto come stringa
        data.remove(data.size() - 1);
    }

    /**
     * Metodo privato che separa una stringa sulla base di un carattere specifico,
     * in questo caso, il separatore di campo, che è (,).
     * Le sottostringhe vengono immagazzinate in un array e poi assegnata al relativo
     * campo della tabella.
     * @param line
     * @param list
     * @throws NullPointerException
     */
    private static void Parse_file(String line, List<Riga_tabella> list) {

        try {
            Riga_tabella r = new Riga_tabella();

            //Split della stringa in base al separatore di campo
            String[] s = line.split(",");
            for (int i = 0; i < s.length; i++) {
                if (s[i] == null) break;
                else {
                    switch (i) {
                        case 0:
                            r.setMs(s[i]);
                            break;
                        case 1:
                            r.setMs_name(s[i]);
                            break;
                        case 2:
                            r.setCci(s[i]);
                            break;
                        case 3:
                            r.setVer(s[i]);
                            break;
                        case 4:
                            r.setTitle(s[i]);
                            break;
                        case 5:
                            r.setFund(s[i]);
                            break;
                        case 6:
                            r.setIndicator_type_code(s[i]);
                            break;
                        case 31:
                            r.setVisualize_by_ms(s[i]);
                            break;
                        default:
                            break;
                    }
                }
            }

            list.add(r);
        }

        catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo che popola la lista meta che contiene i metadati.
     * Inserisce il campo della tabella e il tipo di dato.
     * @param line
     * @param list
     */
    private static void Popola_metadata(String line, List<Riga_metadata> list) {
        String[] s = line.split(",");
        for (int i = 0; i < s.length; i++) {
            Riga_metadata rm = new Riga_metadata();
            rm.setAlias(s[i]);
            rm.setType("String");
            list.add(rm);
        }
    }
}



