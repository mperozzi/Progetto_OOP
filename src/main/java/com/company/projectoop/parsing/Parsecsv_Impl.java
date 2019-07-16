package com.company.projectoop.parsing;

import com.company.projectoop.table.Riga_metadata;
import com.company.projectoop.table.Riga_tabella;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
                        case 3: {
                            String[] s1 = s[i].split("\"");
                            if (Float.isNaN(Float.parseFloat(s1[1]))) break;
                            else {
                                r.setVer(Float.parseFloat(s1[1]));
                            }
                            break;
                        }
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

    /**
     * Filtri Logici --> Filtri implementati: and, or, not
     * @param fieldName1
     * @param value1
     * @param operator
     * @param fieldName2
     * @param value2
     * @return
     * @throws Exception
     */
    @Override
    public ArrayList<Riga_tabella> Logical_filter(String fieldName1, String value1, String operator, String fieldName2, String value2) throws Exception {

        //inizializzazione delle strutture dati d'appoggio utilizzate
        List<Riga_tabella> list = new ArrayList<>();
        List<String> ls = new ArrayList<>();
        List<String> ls2 = new ArrayList<>();

        switch (operator) {
            case "and": {
                try {
                    //se i due campi secondari sono vuoti viene lanciata una eccezione
                    if (fieldName2.isEmpty() || value2.isEmpty())
                        throw new Exception();

                    Method method1;
                    Method method2;
                    String s_filter1;
                    String s_filter2;

                    for (Riga_tabella riga : data) {

                        //richiamo dei metodi richiesti dall'utente su cui applicare il filtro
                        method1 = riga.getClass().getMethod("get" + fieldName1.substring(0, 1).toUpperCase() + fieldName1.substring(1), null);

                        //assegnazione delle variabili per applicare il filtro in base al tipo di dato
                        if (Float.class.isInstance(method1.invoke(riga))) {
                            s_filter1 = Float.toString((float) method1.invoke(riga));
                            ls.add(s_filter1);
                        } else if (Integer.class.isInstance(method1.invoke(riga))) {
                            s_filter1 = Integer.toString((int) method1.invoke(riga));
                            ls.add(s_filter1);
                        } else {
                            s_filter1 = (String) method1.invoke(riga);
                            ls.add(s_filter1);
                        }

                        method2 = riga.getClass().getMethod("get" + fieldName2.substring(0, 1).toUpperCase() + fieldName2.substring(1), null);
                        if (Float.class.isInstance(method2.invoke(riga))) {
                            s_filter2 = Float.toString((float) method2.invoke(riga));
                            ls2.add(s_filter2);
                        } else if (Integer.class.isInstance(method2.invoke(riga))) {
                            s_filter2 = Integer.toString((int) method2.invoke(riga));
                            ls2.add(s_filter2);
                        } else {
                            s_filter2 = (String) method2.invoke(riga);
                            ls.add(s_filter2);
                        }
                    }

                    //inserimento degli elementi della tabella che rispettano i criteri specificati dal filtro nella lista
                    for (int j = 0; j < ls.size(); j++) {
                        if (ls.get(j).equals(value1) && ls2.get(j).equals(value2)) {
                            Riga_tabella riga;
                            riga = data.get(j);
                            list.add(riga);
                        }
                    }

                } catch (NoSuchMethodException e) {
                    e.printStackTrace();

                } catch (SecurityException e) {
                    e.printStackTrace();

                } catch (IllegalAccessException e) {
                    e.printStackTrace();

                } catch (IllegalArgumentException e) {
                    e.printStackTrace();

                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            break;

            case "or": {
                try {
                    //se i due campi secondari sono vuoti viene lanciata una eccezione
                    if (fieldName2.isEmpty() || value2.isEmpty())
                        throw new Exception();

                    Method method1;
                    Method method2;
                    String s_filter1;
                    String s_filter2;

                    for (Riga_tabella riga : data) {

                        //richiamo dei metodi richiesti dall'utente su cui applicare il filtro
                        method1 = riga.getClass().getMethod("get" + fieldName1.substring(0, 1).toUpperCase() + fieldName1.substring(1), null);

                        //assegnazione delle variabili per applicare il filtro in base al tipo di dato
                        if (Float.class.isInstance(method1.invoke(riga))) {
                            s_filter1 = Float.toString((float) method1.invoke(riga));
                            ls.add(s_filter1);
                        } else if (Integer.class.isInstance(method1.invoke(riga))) {
                            s_filter1 = Integer.toString((int) method1.invoke(riga));
                            ls.add(s_filter1);
                        } else {
                            s_filter1 = (String) method1.invoke(riga);
                            ls.add(s_filter1);
                        }

                        method2 = riga.getClass().getMethod("get" + fieldName2.substring(0, 1).toUpperCase() + fieldName2.substring(1), null);
                        if (Float.class.isInstance(method2.invoke(riga))) {
                            s_filter2 = Float.toString((float) method2.invoke(riga));
                            ls2.add(s_filter2);
                        } else if (Integer.class.isInstance(method2.invoke(riga))) {
                            s_filter2 = Integer.toString((int) method2.invoke(riga));
                            ls2.add(s_filter2);
                        } else {
                            s_filter2 = (String) method2.invoke(riga);
                            ls.add(s_filter2);
                        }
                    }

                    //inserimento degli elementi della tabella che rispettano i criteri specificati dal filtro nella lista
                    for (int j = 0; j < ls.size(); j++) {
                        if (ls.get(j).equals(value1) | ls2.get(j).equals(value2)) {
                            Riga_tabella riga;
                            riga = data.get(j);
                            list.add(riga);
                        }
                    }

                } catch (NoSuchMethodException e) {
                    e.printStackTrace();

                } catch (SecurityException e) {
                    e.printStackTrace();

                } catch (IllegalAccessException e) {
                    e.printStackTrace();

                } catch (IllegalArgumentException e) {
                    e.printStackTrace();

                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            } break;

            case "not": {
                try {

                    Method method1;
                    String s_filter1;

                    for (Riga_tabella riga : data) {

                        //richiamo del metodo richiesto dall'utente su cui applicare il filtro
                        method1 = riga.getClass().getMethod("get" + fieldName1.substring(0, 1).toUpperCase() + fieldName1.substring(1), null);

                        //assegnazione della variabile per applicare il filtro in base al tipo di dato
                        if (Float.class.isInstance(method1.invoke(riga))) {
                            s_filter1 = Float.toString((float) method1.invoke(riga));
                            ls.add(s_filter1);
                        } else if (Integer.class.isInstance(method1.invoke(riga))) {
                            s_filter1 = Integer.toString((int) method1.invoke(riga));
                            ls.add(s_filter1);
                        } else {
                            s_filter1 = (String) method1.invoke(riga);
                            ls.add(s_filter1);
                        }
                    }

                    for (int j = 0; j < ls.size(); j++) {
                        if (!ls.get(j).equals(value1)) {
                            Riga_tabella riga;
                            riga = data.get(j);
                            list.add(riga);
                        }
                    }

                } catch (NoSuchMethodException e) {
                    e.printStackTrace();

                } catch (SecurityException e) {
                    e.printStackTrace();

                } catch (IllegalAccessException e) {
                    e.printStackTrace();

                } catch (IllegalArgumentException e) {
                    e.printStackTrace();

                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            } break;

            default:
                throw new Exception();
        }

        return (ArrayList<Riga_tabella>) list;
    }
}



