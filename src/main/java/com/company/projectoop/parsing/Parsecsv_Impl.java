package com.company.projectoop.parsing;

import com.company.projectoop.table.Riga_metadata;
import com.company.projectoop.table.Riga_tabella;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
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

    private static String namefile = "fileprova.csv ";

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
                            String[] s3 = s[i].split("\"");
                            if (Float.isNaN(Float.parseFloat(s3[1]))) break;
                            else {
                                r.setVer(Float.parseFloat(s3[1]));
                            }
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
                        case 7:
                            r.setInd_code(s[i]);
                            break;
                        case 8:
                            r.setInd_group_code(s[i]);
                            break;
                        case 9:
                            r.setIndicator_short_name(s[i]);
                            break;
                        case 10:
                            r.setIndicator_long_name(s[i]);
                            break;
                        case 11:
                            String[] s11 = s[i].split("\"");
                            if (Float.isNaN(Float.parseFloat(s11[1]))) break;
                            else {
                                r.setTo(Float.parseFloat(s11[1]));
                            }
                            break;
                        case 12:
                            r.setTo_short(s[i]);
                            break;
                        case 13:
                            r.setPriority_code(s[i]);
                            break;
                        case 14:
                            r.setInvestment_priority(s[i]);
                            break;
                        case 15:
                            r.setMeasure_code(s[i]);
                            break;
                        case 16:
                            r.setFocus_area_code(s[i]);
                            break;
                        case 17:
                            String[] s17 = s[i].split("\"");
                            if (Float.isNaN(Float.parseFloat(s17[1]))) break;
                            else {
                                r.setTarget_value(Float.parseFloat(s17[1]));
                            }
                            break;
                        case 18:
                            r.setMeasurement_unit(s[i]);
                            break;
                        case 19:
                            String[] s19 = s[i].split("\"");
                            if (Float.isNaN(Float.parseFloat(s19[1]))) break;
                            else {
                                r.setNominator(Float.parseFloat(s19[1]));
                            }
                            break;
                        case 20:
                            r.setDenominator(s[i]);
                            break;
                        case 21:
                            r.setIs_division(s[i]);
                            break;
                        case 22:
                            String[] s22 = s[i].split("\"");
                            r.setNominator(Float.parseFloat(s22[1]));
                            break;
                        case 23:
                            r.setIr_ver(s[i]);
                            break;
                        case 24:
                            r.setForecast_value(s[i]);
                            break;
                        case 25:
                            r.setForecast_nominator(s[i]);
                            break;
                        case 26:
                            r.setForecast_denominator(s[i]);
                            break;
                        case 27:
                            r.setImplemented_value(s[i]);
                            break;
                        case 28:
                            r.setImplemented_nominator(s[i]);
                            break;
                        case 29:
                            r.setImplemented_denominator(s[i]);
                            break;
                        case 30:
                            r.setVisualize_by_to(s[i]);
                            break;
                        case 31:
                            r.setVisualize_by_ms(s[i]);
                            break;
                        case 32:
                            r.setMs_op_coverage(s[i]);
                            break;
                        case 33:
                            r.setFinance_coverage(s[i]);
                            break;
                        case 34:
                            r.setInd_group_name(s[i]);
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

    /**
     * Metodo che calcola la media dei valori all'interno di un campo value del dataset
     * @param value
     * @return
     * @throws Exception
     */
    public static float avgValue(String value) throws Exception{
        float sum = 0;
        int n = 0;

        switch (value) {
            case "nominator": {
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getNominator() != 0) {
                        sum += data.get(i).getNominator();
                        n++;
                    }
                }
            } break;

            case "target_value": {
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getTarget_value() != 0) {
                        sum += data.get(i).getTarget_value();
                        n++;
                    }
                }
            } break;

            case "to": {
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getTo() != 0) {
                        sum += data.get(i).getTo();
                        n++;
                    }
                }
            } break;

            case "ver": {
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getVer() != 0) {
                        sum += data.get(i).getVer();
                        n++;
                    }
                }
            } break;

            default: throw new Exception();
        }

        float avg = sum/n;
        return avg;
    }

    /**
     * Metodo che restituisce il valore minimo tra tutti quelli relativi al campo value del dataset
     * @param value
     * @return
     * @throws Exception
     */
    public static float minValue(String value) throws Exception{
        float min = 0;

        switch (value) {
            case "nominator": {
                min = data.get(0).getNominator();
                for (int i = 1; i < data.size(); i++) {
                    if (data.get(i).getNominator() < min && data.get(i).getNominator() != 0)
                        min = data.get(i).getNominator();
                }
            } break;

            case "target_value": {
                min = data.get(0).getTarget_value();
                for (int i = 1; i < data.size(); i++) {
                    if (data.get(i).getTarget_value() < min && data.get(i).getTarget_value() != 0)
                        min = data.get(i).getTarget_value();
                }
            } break;

            case "to": {
                min = data.get(0).getTo();
                for (int i = 1; i < data.size(); i++) {
                    if (data.get(i).getTo() < min && data.get(i).getTo() != 0)
                        min = data.get(i).getTo();
                }
            } break;

            case "ver": {
                min = data.get(0).getVer();
                for (int i = 1; i < data.size(); i++) {
                    if (data.get(i).getVer() < min && data.get(i).getVer() != 0)
                        min = data.get(i).getVer();
                }
            } break;

            default: throw new Exception();
        }

        return min;
    }


    /**
     * Metodo che restituisce il valore massimo tra tutti quelli relativi al campo value del dataset
     * @param value
     * @return
     * @throws Exception
     */
    public static float maxValue(String value) throws Exception{
        float max = 0;

        switch (value) {
            case "nominator": {
                max = data.get(0).getNominator();
                for (int i = 1; i < data.size(); i++) {
                    if (data.get(i).getNominator() > max)
                        max = data.get(i).getNominator();
                }
            } break;

            case "target_value": {
                max = data.get(0).getTarget_value();
                for (int i = 1; i < data.size(); i++) {
                    if (data.get(i).getTarget_value() > max)
                        max = data.get(i).getTarget_value();
                }
            } break;

            case "to": {
                max = data.get(0).getTo();
                for (int i = 1; i < data.size(); i++) {
                    if (data.get(i).getTo() > max)
                        max = data.get(i).getTo();
                }
            } break;

            case "ver": {
                max = data.get(0).getVer();
                for (int i = 1; i < data.size(); i++) {
                    if (data.get(i).getVer() > max)
                        max = data.get(i).getVer();
                }
            } break;

            default: throw new Exception();
        }

        return max;
    }

    /**
     * Metodo che calcola la somma dei valori di un campo value all'interno del dataset
     * @param value
     * @return
     * @throws Exception
     */
    public static float sumValue(String value) throws Exception{
        float sum = 0;

        switch (value) {
            case "nominator": {
                for (int i = 0; i < data.size(); i++) {
                    sum += data.get(i).getNominator();
                }
            } break;

            case "target_value": {
                for (int i = 0; i < data.size(); i++) {
                    sum += data.get(i).getTarget_value();
                }
            } break;

            case "to": {
                for (int i = 0; i < data.size(); i++) {
                    sum += data.get(i).getTo();
                }
            } break;

            case "ver": {
                for (int i = 0; i < data.size(); i++) {
                    sum += data.get(i).getVer();
                }
            } break;

            default: throw new Exception();
        }

        return sum;
    }

    /**
     * Metodo che calcola la deviazione standard dei valori all'interno di un campo del dataset
     * @param value
     * @return
     * @throws Exception
     */
    public static double devstdValue (String value) throws Exception{
        double c = 0.0;
        double n = 0;
        double a = avgValue(value);

        switch (value) {
            case "nominator": {
                for (Riga_tabella d: data) {
                    if(d.getNominator() == 0) {
                        continue;
                    } else {
                        n += Math.pow((d.getNominator() - a), 2);
                        c++;
                    }
                }
            } break;

            case "target_value": {
                for (Riga_tabella d: data) {
                    if(d.getTarget_value() == 0) {
                        continue;
                    } else {
                        n += Math.pow((d.getTarget_value() - a), 2);
                        c++;
                    }
                }
            } break;

            case "to": {
                for (Riga_tabella d: data) {
                    if(d.getTo() == 0) {
                        continue;
                    } else {
                        n += Math.pow((d.getTo() - a), 2);
                        c++;
                    }
                }
            } break;

            case "ver": {
                for (Riga_tabella d: data) {
                    if(d.getVer() == 0) {
                        continue;
                    } else {
                        n += Math.pow((d.getVer() - a), 2);
                        c++;
                    }
                }
            } break;

            default: throw new Exception();
        }

        double devstdV = Math.sqrt(n/c);
        return devstdV;
    }


    /**
     * Metodo che calcola il numero di valori presenti in un determinato campo value
     * @param value
     * @return
     * @throws Exception
     */
    public static int countValue(String value) throws Exception{
        int count = 0;

        switch (value) {
            case "nominator": {
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getNominator() != 0)
                        count++;
                }
            } break;

            case "target_value": {
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getTarget_value() != 0)
                        count++;
                }
            } break;

            case "to": {
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getTo() != 0)
                        count++;
                }
            } break;

            case "ver": {
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getVer() != 0)
                        count++;
                }
            } break;

            default: throw new Exception();
        }

        return count;
    }
}




