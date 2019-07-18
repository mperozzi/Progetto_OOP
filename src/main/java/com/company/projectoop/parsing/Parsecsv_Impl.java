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
 * Viene effettuato il parsing del CSV e i dati vengono immagazzinati in una lista.
 * Sono presenti i metodi per la restituzione all'utente dei dati e metadati.
 * Sono presenti i metodi che implementano i filtri.
 *
 * @author Matteo Perozzi
 * @author Ettore Zamponi
 */

@Component
@Repository
@Service("Parsecsv_Impl")

public class Parsecsv_Impl implements Parsecsv {

    private static String namefile = "dataset.csv ";


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
     *
     * @return
     */
    @Override
    public List<Riga_metadata> getMetadata() {
        return metadata;
    }


    /**
     * getter della lista: data
     *
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
     *
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
            } while (reader.ready());

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
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
     *
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
                            /**
                             * conversione valore da string a float e assegnazione all'interno del campo della riga
                             * solamente se la stringa rappresenta un numero
                             * */
                            String[] s3 = s[i].split("\"");
                            if (s3.length >= 2) {
                                if (Float.isNaN(Float.parseFloat(s3[1]))) r.setVer(0);
                                else r.setVer(Float.parseFloat(s3[1]));
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
                            /**
                             * conversione valore da string a int e assegnazione all'interno del campo della riga
                             * solamente se la stringa rappresenta un numero
                             * */
                            String[] s11 = s[i].split("\"");

                            if (s11.length >= 2 && !s11[1].isEmpty()) {
                                if (Float.isNaN(Float.parseFloat(s11[1]))) r.setTo(0);
                                else r.setTo((int) Float.parseFloat(s11[1]));
                            } else break;
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
                            if (s17.length >= 2 && !s17[1].isEmpty()) {
                                if (Float.isNaN((int) Float.parseFloat(s17[1]))) r.setTarget_value(0);
                                else r.setTarget_value((int) Float.parseFloat(s17[1]));
                            }
                            break;
                        case 18:
                            r.setMeasurement_unit(s[i]);
                            break;
                        case 19:
                            String[] s19 = s[i].split("\"");
                            if (s19.length >= 2 && !s19[1].isEmpty()) {
                                if (Float.isNaN(Float.parseFloat(s19[1]))) r.setNominator(0);
                                else r.setNominator((int) Float.parseFloat(s19[1]));
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
                            if (s22.length >= 2 && !s22[1].isEmpty()) {
                                if (Float.isNaN(Float.parseFloat(s22[1]))) r.setYear(0);
                                else r.setYear((int) Float.parseFloat(s22[1]));
                            }
                            break;
                        case 23:
                            String[] s23 = s[i].split("\"");
                            if (s23.length >= 2 && !s23[1].isEmpty()) {
                                if (Float.isNaN(Float.parseFloat(s23[1]))) r.setIr_ver(0);
                                else r.setIr_ver(Float.parseFloat(s23[1]));
                            }
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

            //aggiungo la riga alla lista
            list.add(r);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }


    /**
     * Metodo che popola la lista meta che contiene i metadati.
     * Inserisce il campo della tabella e il tipo di dato.
     *
     * @param line
     * @param list
     */
    private static void Popola_metadata(String line, List<Riga_metadata> list) {

        //separazione in base al separatore di campo della stringa che contiene l'intero insieme dei metadati
        String[] s = line.split(",");

        //assegnazione dei valori nei campi corrispondenti della struttura dei metadati
        for (int i = 0; i < s.length; i++) {
            Riga_metadata rm = new Riga_metadata();
            rm.setAlias(s[i]);
            switch (i) {
                case 3:
                    rm.setType("int");
                    break;
                case 11:
                    rm.setType("int");
                    break;
                case 17:
                    rm.setType("float");
                    break;
                case 19:
                    rm.setType("int");
                    break;
                case 22:
                    rm.setType("int");
                    break;
                case 23:
                    rm.setType("float");
                    break;
                default:
                    rm.setType("String");
                    break;
            }
            list.add(rm);
        }
    }


    /**
     * Filtri Logici --> Filtri implementati: $and, $or, $not
     *
     * @param fieldName1
     * @param value1
     * @param l_operator
     * @param fieldName2
     * @param value2
     * @return
     * @throws Exception
     */
    @Override
    public ArrayList<Riga_tabella> Logical_filter(String l_operator, String fieldName1, String value1, String fieldName2, String value2) throws Exception {

        //verifica del corretto inserimento dei parametri primari
        if (l_operator.isEmpty() || fieldName1.isEmpty() || value1.isEmpty())
            throw new Exception("Errore di inserimento parametri");

        //inizializzazione delle strutture dati d'appoggio
        List<Riga_tabella> list = new ArrayList<>();
        List<String> ls = new ArrayList<>();
        List<String> ls2 = new ArrayList<>();

        switch (l_operator) {
            case "$and": {
                try {
                    //verifica del corretto inserimento dei parametri secondari
                    if (fieldName2.isEmpty() || value2.isEmpty())
                        throw new Exception("Immettere correttamente tutti i campi");

                    Method method1;
                    Method method2;
                    String s_filter1;
                    String s_filter2;

                    for (Riga_tabella riga : data) {

                        //richiamo dei metodi richiesti dall'utente su cui applicare il filtro
                        method1 = riga.getClass().getMethod("get" + fieldName1.substring(0, 1).toUpperCase() + fieldName1.substring(1), null);
                        s_filter1 = method1.invoke(riga).toString();

                        method2 = riga.getClass().getMethod("get" + fieldName2.substring(0, 1).toUpperCase() + fieldName2.substring(1), null);
                        s_filter2 = method2.invoke(riga).toString();

                        if (s_filter1.equals(value1) && s_filter2.equals(value2))
                            list.add(riga);
                    }

                    //inserimento degli elementi della tabella che rispettano i criteri specificati dal filtro nella lista
                    for (int j = 0; j < ls.size(); j++) {
                        if (ls.get(j).equals(value1) && ls2.get(j).equals(value2)) {
                            Riga_tabella riga;
                            riga = data.get(j);
                            list.add(riga);
                        }
                    }
                //eccezione se non trova i campi corrispondenti nel dataset
                } catch (NoSuchMethodException e) {
                    throw new Exception("Inserire un campo corretto del dataset");

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

            case "$or": {
                try {
                    //verifica del corretto inserimento dei parametri secondari
                    if (fieldName2.isEmpty() || value2.isEmpty())
                        throw new Exception();

                    Method method1;
                    Method method2;
                    String s_filter1;
                    String s_filter2;

                    for (Riga_tabella riga : data) {

                        //richiamo dei metodi richiesti dall'utente su cui applicare il filtro
                        method1 = riga.getClass().getMethod("get" + fieldName1.substring(0, 1).toUpperCase() + fieldName1.substring(1), null);
                        s_filter1 = method1.invoke(riga).toString();

                        method2 = riga.getClass().getMethod("get" + fieldName2.substring(0, 1).toUpperCase() + fieldName2.substring(1), null);
                        s_filter2 = method2.invoke(riga).toString();

                        if (s_filter1.equals(value1) || s_filter2.equals(value2))
                            list.add(riga);
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
                    throw new Exception("Inserire un campo corretto del dataset");

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

            case "$not": {
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

                    //inserimento nella lista degli elementi che rispettano il criterio stabilito dal filtro
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
                    throw new Exception("Inserire un campo corretto del dataset");

                } catch (IllegalAccessException e) {
                    e.printStackTrace();

                } catch (IllegalArgumentException e) {
                    e.printStackTrace();

                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            break;

            default:
                throw new Exception();
        }
        return (ArrayList<Riga_tabella>) list;
    }


    /**
     * Metodo che calcola la media dei valori all'interno di un campo value del dataset
     *
     * @param value
     * @return
     * @throws Exception
     */
    public static float avgValue(String value) throws Exception {
        float sum = 0;
        int n = 0;

        //in base al tipo di campo scelto, si calcola la media dei valori presenti nella tabella richiamandoli con il getter
        switch (value) {
            case "nominator": {
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getNominator() != 0) {
                        sum += data.get(i).getNominator();
                        n++;
                    }
                }
            }
            break;

            case "target_value": {
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getTarget_value() != 0) {
                        sum += data.get(i).getTarget_value();
                        n++;
                    }
                }
            }
            break;

            case "to": {
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getTo() != 0) {
                        sum += data.get(i).getTo();
                        n++;
                    }
                }
            }
            break;

            case "ver": {
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getVer() != 0) {
                        sum += data.get(i).getVer();
                        n++;
                    }
                }
            }
            break;

            case "ir_ver": {
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getIr_ver() != 0) {
                        sum += data.get(i).getIr_ver();
                        n++;
                    }
                }
            }
            break;

            //se il campo scelto non è di tipo numerico si lancia l'eccezione
            default:
                throw new Exception("Inserire un campo che prevede valori numerici o il nime corretto del campo");
        }

        float avg = sum / n;
        return avg;
    }


    /**
     * Metodo che restituisce il valore minimo tra tutti quelli relativi al campo value del dataset
     *
     * @param value
     * @return
     * @throws Exception
     */
    public static float minValue(String value) throws Exception {
        float min = 0;

        //in base al tipo di campo scelto, si cerca il valore minimo tra valori presenti nella tabella richiamandoli con il getter
        switch (value) {
            case "nominator": {
                min = data.get(0).getNominator();
                for (int i = 1; i < data.size(); i++) {
                    if (data.get(i).getNominator() < min && data.get(i).getNominator() != 0)
                        min = data.get(i).getNominator();
                }
            }
            break;

            case "target_value": {
                min = data.get(0).getTarget_value();
                for (int i = 1; i < data.size(); i++) {
                    if (data.get(i).getTarget_value() < min && data.get(i).getTarget_value() != 0)
                        min = data.get(i).getTarget_value();
                }
            }
            break;

            case "to": {
                min = data.get(0).getTo();
                for (int i = 1; i < data.size(); i++) {
                    if (data.get(i).getTo() < min && data.get(i).getTo() != 0)
                        min = data.get(i).getTo();
                }
            }
            break;

            case "ver": {
                min = data.get(0).getVer();
                for (int i = 1; i < data.size(); i++) {
                    if (data.get(i).getVer() < min && data.get(i).getVer() != 0)
                        min = data.get(i).getVer();
                }
            }
            break;

            case "ir_ver": {
                min = data.get(0).getIr_ver();
                for (int i = 1; i < data.size(); i++) {
                    if (data.get(i).getIr_ver() < min && data.get(i).getIr_ver() != 0)
                        min = data.get(i).getIr_ver();
                }
            }
            break;

            //se il campo scelto non è di tipo numerico si lancia l'eccezione
            default:
                throw new Exception("Inserire un campo che prevede valori numerici o il nime corretto del campo");
        }

        return min;
    }


    /**
     * Metodo che restituisce il valore massimo tra tutti quelli relativi al campo value del dataset
     *
     * @param value
     * @return
     * @throws Exception
     */
    public static float maxValue(String value) throws Exception {
        float max = 0;

        //in base al tipo di campo scelto, si cerca il valore massimo tra valori presenti nella tabella richiamandoli con il getter
        switch (value) {
            case "nominator": {
                max = data.get(0).getNominator();
                for (int i = 1; i < data.size(); i++) {
                    if (data.get(i).getNominator() > max)
                        max = data.get(i).getNominator();
                }
            }
            break;

            case "target_value": {
                max = data.get(0).getTarget_value();
                for (int i = 1; i < data.size(); i++) {
                    if (data.get(i).getTarget_value() > max)
                        max = data.get(i).getTarget_value();
                }
            }
            break;

            case "to": {
                max = data.get(0).getTo();
                for (int i = 1; i < data.size(); i++) {
                    if (data.get(i).getTo() > max)
                        max = data.get(i).getTo();
                }
            }
            break;

            case "ver": {
                max = data.get(0).getVer();
                for (int i = 1; i < data.size(); i++) {
                    if (data.get(i).getVer() > max)
                        max = data.get(i).getVer();
                }
            }
            break;

            case "ir_ver": {
                max = data.get(0).getIr_ver();
                for (int i = 1; i < data.size(); i++) {
                    if (data.get(i).getIr_ver() > max)
                        max = data.get(i).getIr_ver();
                }
            }
            break;

            //se il campo scelto non è di tipo numerico si lancia l'eccezione
            default:
                throw new Exception("Inserire un campo che prevede valori numerici o il nime corretto del campo");
        }

        return max;
    }


    /**
     * Metodo che calcola la somma dei valori di un campo value all'interno del dataset
     *
     * @param value
     * @return
     * @throws Exception
     */
    public static float sumValue(String value) throws Exception {
        float sum = 0;

        //in base al tipo di campo scelto, si calcola la somma dei valori presenti nella tabella richiamandoli con il getter
        switch (value) {
            case "nominator": {
                for (int i = 0; i < data.size(); i++) {
                    sum += data.get(i).getNominator();
                }
            }
            break;

            case "target_value": {
                for (int i = 0; i < data.size(); i++) {
                    sum += data.get(i).getTarget_value();
                }
            }
            break;

            case "to": {
                for (int i = 0; i < data.size(); i++) {
                    sum += data.get(i).getTo();
                }
            }
            break;

            case "ver": {
                for (int i = 0; i < data.size(); i++) {
                    sum += data.get(i).getVer();
                }
            }
            break;

            case "ir_ver": {
                for (int i = 0; i < data.size(); i++) {
                    sum += data.get(i).getIr_ver();
                }
            }
            break;

            //se il campo scelto non è di tipo numerico si lancia l'eccezione
            default:
                throw new Exception("Inserire un campo che prevede valori numerici o il nime corretto del campo");
        }

        return sum;
    }


    /**
     * Metodo che calcola la deviazione standard dei valori all'interno di un campo del dataset
     *
     * @param value
     * @return
     * @throws Exception
     */
    public static double devstdValue(String value) throws Exception {
        double c = 0.0;
        double n = 0;
        double a = avgValue(value);

        //in base al tipo di campo scelto, si calcola la deviazione standard dei valori presenti nella tabella richiamandoli con il getter
        switch (value) {
            case "nominator": {
                for (Riga_tabella d : data) {
                    if (d.getNominator() == 0) {
                        continue;
                    } else {
                        n += Math.pow((d.getNominator() - a), 2);
                        c++;
                    }
                }
            }
            break;

            case "target_value": {
                for (Riga_tabella d : data) {
                    if (d.getTarget_value() == 0) {
                        continue;
                    } else {
                        n += Math.pow((d.getTarget_value() - a), 2);
                        c++;
                    }
                }
            }
            break;

            case "to": {
                for (Riga_tabella d : data) {
                    if (d.getTo() == 0) {
                        continue;
                    } else {
                        n += Math.pow((d.getTo() - a), 2);
                        c++;
                    }
                }
            }
            break;

            case "ver": {
                for (Riga_tabella d : data) {
                    if (d.getVer() == 0) {
                        continue;
                    } else {
                        n += Math.pow((d.getVer() - a), 2);
                        c++;
                    }
                }
            }
            break;

            case "ir_ver": {
                for (Riga_tabella d : data) {
                    if (d.getIr_ver() == 0) {
                        continue;
                    } else {
                        n += Math.pow((d.getIr_ver() - a), 2);
                        c++;
                    }
                }
            }
            break;

            //se il campo scelto non è di tipo numerico si lancia l'eccezione
            default:
                throw new Exception("Inserire un campo che prevede valori numerici o il nime corretto del campo");
        }

        double devstdV = Math.sqrt(n / c);
        return devstdV;
    }


    /**
     * Metodo che conta il numero di valori presenti in un determinato campo value
     *
     * @param value
     * @return
     * @throws Exception
     */
    public static int countValue(String value) throws Exception {
        int count = 0;

        //in base al tipo di campo scelto, si conta il numero dei valori non nulli presenti nella tabella richiamandoli con il getter
        switch (value) {
            case "nominator": {
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getNominator() != 0)
                        count++;
                }
            }
            break;

            case "target_value": {
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getTarget_value() != 0)
                        count++;
                }
            }
            break;

            case "to": {
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getTo() != 0)
                        count++;
                }
            }
            break;

            case "ver": {
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getVer() != 0)
                        count++;
                }
            }
            break;

            case "ir_ver": {
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getIr_ver() != 0)
                        count++;
                }
            }
            break;

            //se il campo scelto non è di tipo numerico si lancia l'eccezione
            default:
                throw new Exception("Inserire un campo che prevede valori numerici o il nime corretto del campo");
        }

        return count;
    }


    /**
     * Metodo che effettua il conteggio di quante occorrenze ci sono di un valore
     * all'interno di un determinato campo del dataset
     *
     * @param fieldName
     * @param value
     * @return
     */
    public static int countOcc(String fieldName, String value) throws Exception {
        if (fieldName.isEmpty() || value.isEmpty()) throw new Exception("Inserire un campo e/o un valore");

        Method method1;
        String s_filter1;
        int counter = 0;
        try {

            for (Riga_tabella riga : data) {

                //richiamo del metodo richiesto dall'utente su cui applicare il filtro
                method1 = riga.getClass().getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), null);

                //assegnazione della variabile per applicare il filtro in base al tipo di dato
                if (Float.class.isInstance(method1.invoke(riga))) {
                    s_filter1 = Float.toString((float) method1.invoke(riga));
                    if (s_filter1.equals(value)) {
                        counter++;
                    } else break;

                } else if (Integer.class.isInstance(method1.invoke(riga))) {
                    s_filter1 = Integer.toString((int) method1.invoke(riga));
                    if (s_filter1.equals(value)) {
                        counter++;
                    } else break;

                } else {
                    s_filter1 = (String) method1.invoke(riga);
                    if (s_filter1.equals(value)) {
                        counter++;
                    } else break;
                }
            }
        } catch (NoSuchMethodException e) {
            throw new Exception("Inserire un campo corretto del dataset");

        } catch (SecurityException e) {
            e.printStackTrace();

        } catch (IllegalAccessException e) {
            e.printStackTrace();

        } catch (IllegalArgumentException e) {
            e.printStackTrace();

        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return counter;
    }


    /**
     * Filtri Condizionali --> Filtri implementati: $gt (maggiore), $gte (maggiore e uguale), $lt(minore), $lte (minore e uguale), $bt (compreso tra)
     *
     * @param c_operator
     * @param fieldName
     * @param value
     * @param value2
     * @return
     * @throws Exception
     */
    public ArrayList<Riga_tabella> Conditional_filter(String c_operator, String fieldName, String value, String value2) throws Exception {

        List<Riga_tabella> list = new ArrayList<>();

        //verifica del corretto inserimento dei parametri
        if (c_operator.isEmpty() | fieldName.isEmpty() | value.isEmpty() | value2.isEmpty())
            throw new Exception("Errore di inserimento parametri");

        else {

            switch (c_operator) {
                case "$gt": {
                    try {

                        if ((!fieldName.equals("") && !value.equals("0") && value2.equals("0")) | (!fieldName.equals("") && !value.equals("0") && !value2.equals("0"))) {

                            Method method;
                            float fvalue = 0;
                            int ivalue = 0;

                            for (Riga_tabella riga : data) {

                                //richiamo del metodo richiesto dall'utente su cui applicare il filtro
                                method = riga.getClass().getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), null);

                                //assegnazione della variabile per applicare il filtro in base al tipo di dato

                                if (Float.class.isInstance(method.invoke(riga))) {
                                    float valuemod = Float.parseFloat(value);
                                    fvalue = ((float) method.invoke(riga));

                                    if (fvalue > valuemod) {
                                        list.add(riga);
                                    }

                                } else if (Integer.class.isInstance(method.invoke(riga))) {
                                    ivalue = ((int) method.invoke(riga));

                                    int valuemod = Integer.parseInt(value);
                                    ivalue = ((int) method.invoke(riga));

                                    if (ivalue > valuemod) {
                                        list.add(riga);
                                    }
                                } else if (String.class.isInstance(method.invoke(riga)))
                                    throw new Exception("Inserire un campo che contenga valori numerici");
                            }
                        } else if (value.equals("0") && !value2.equals("0")) {
                            Method method;
                            float fvalue = 0;
                            int ivalue = 0;

                            for (Riga_tabella riga : data) {

                                //richiamo del metodo richiesto dall'utente su cui applicare il filtro
                                method = riga.getClass().getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), null);

                                //assegnazione della variabile per applicare il filtro in base al tipo di dato

                                if (Float.class.isInstance(method.invoke(riga))) {
                                    float valuemod = Float.parseFloat(value2);
                                    fvalue = ((float) method.invoke(riga));

                                    if (fvalue > valuemod) {
                                        list.add(riga);
                                    }

                                } else if (Integer.class.isInstance(method.invoke(riga))) {
                                    ivalue = ((int) method.invoke(riga));
                                    int valuemod = Integer.parseInt(value2);
                                    ivalue = ((int) method.invoke(riga));

                                    if (ivalue > valuemod) {
                                        list.add(riga);
                                    }
                                } else if (String.class.isInstance(method.invoke(riga)))
                                    throw new Exception("Inserire un campo che contenga valori numerici");
                            }

                        }
                    } catch (NoSuchMethodException e) {
                        throw new Exception("Inserire un campo corretto del dataset");

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

                case "$gte": {
                    try {

                        if ((!fieldName.equals("") && !value.equals("0") && value2.equals("0")) | (!fieldName.equals("") && !value.equals("0") && !value2.equals("0"))) {

                            Method method;
                            float fvalue = 0;
                            int ivalue = 0;

                            for (Riga_tabella riga : data) {

                                //richiamo del metodo richiesto dall'utente su cui applicare il filtro
                                method = riga.getClass().getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), null);

                                //assegnazione della variabile per applicare il filtro in base al tipo di dato

                                if (Float.class.isInstance(method.invoke(riga))) {
                                    float valuemod = Float.parseFloat(value);
                                    fvalue = ((float) method.invoke(riga));

                                    if (fvalue >= valuemod) {
                                        list.add(riga);
                                    }

                                } else if (Integer.class.isInstance(method.invoke(riga))) {
                                    ivalue = ((int) method.invoke(riga));

                                    int valuemod = Integer.parseInt(value);
                                    ivalue = ((int) method.invoke(riga));

                                    if (ivalue >= valuemod) {
                                        list.add(riga);
                                    }
                                } else if (String.class.isInstance(method.invoke(riga)))
                                    throw new Exception("Inserire un campo che contenga valori numerici");
                            }
                        } else if (value.equals("0") && !value2.equals("0")) {
                            Method method;
                            float fvalue = 0;
                            int ivalue = 0;

                            for (Riga_tabella riga : data) {

                                //richiamo del metodo richiesto dall'utente su cui applicare il filtro
                                method = riga.getClass().getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), null);

                                //assegnazione della variabile per applicare il filtro in base al tipo di dato

                                if (Float.class.isInstance(method.invoke(riga))) {
                                    float valuemod = Float.parseFloat(value2);
                                    fvalue = ((float) method.invoke(riga));

                                    if (fvalue >= valuemod) {
                                        list.add(riga);
                                    }

                                } else if (Integer.class.isInstance(method.invoke(riga))) {
                                    ivalue = ((int) method.invoke(riga));
                                    int valuemod = Integer.parseInt(value2);
                                    ivalue = ((int) method.invoke(riga));

                                    if (ivalue >= valuemod) {
                                        list.add(riga);
                                    }
                                } else if (String.class.isInstance(method.invoke(riga)))
                                    throw new Exception("Inserire un campo che contenga valori numerici");
                            }

                        }
                    } catch (NoSuchMethodException e) {
                        throw new Exception("Inserire un campo corretto del dataset");

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

                case "$lt": {
                    try {

                        if ((!fieldName.equals("") && !value.equals("0") && value2.equals("0")) | (!fieldName.equals("") && !value.equals("0") && !value2.equals("0"))) {

                            Method method;
                            float fvalue = 0;
                            int ivalue = 0;

                            for (Riga_tabella riga : data) {

                                //richiamo del metodo richiesto dall'utente su cui applicare il filtro
                                method = riga.getClass().getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), null);

                                //assegnazione della variabile per applicare il filtro in base al tipo di dato

                                if (Float.class.isInstance(method.invoke(riga))) {
                                    float valuemod = Float.parseFloat(value);
                                    fvalue = ((float) method.invoke(riga));

                                    if (fvalue < valuemod) {
                                        list.add(riga);
                                    }

                                } else if (Integer.class.isInstance(method.invoke(riga))) {
                                    ivalue = ((int) method.invoke(riga));

                                    int valuemod = Integer.parseInt(value);
                                    ivalue = ((int) method.invoke(riga));

                                    if (ivalue < valuemod) {
                                        list.add(riga);
                                    }
                                } else if (String.class.isInstance(method.invoke(riga)))
                                    throw new Exception("Inserire un campo che contenga valori numerici");
                            }
                        } else if (value.equals("0") && !value2.equals("0")) {

                            Method method;
                            float fvalue = 0;
                            int ivalue = 0;

                            for (Riga_tabella riga : data) {

                                //richiamo del metodo richiesto dall'utente su cui applicare il filtro
                                method = riga.getClass().getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), null);

                                //assegnazione della variabile per applicare il filtro in base al tipo di dato

                                if (Float.class.isInstance(method.invoke(riga))) {
                                    float valuemod = Float.parseFloat(value2);
                                    fvalue = ((float) method.invoke(riga));

                                    if (fvalue < valuemod) {
                                        list.add(riga);
                                    }

                                } else if (Integer.class.isInstance(method.invoke(riga))) {
                                    ivalue = ((int) method.invoke(riga));
                                    int valuemod = Integer.parseInt(value2);
                                    ivalue = ((int) method.invoke(riga));

                                    if (ivalue < valuemod) {
                                        list.add(riga);
                                    }
                                } else if (String.class.isInstance(method.invoke(riga)))
                                    throw new Exception("Inserire un campo che contenga valori numerici");
                            }

                        }
                    } catch (NoSuchMethodException e) {
                        throw new Exception("Inserire un campo corretto del dataset");

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

                case "$lte": {
                    try {

                        if ((!fieldName.equals("") && !value.equals("0") && value2.equals("0")) | (!fieldName.equals("") && !value.equals("0") && !value2.equals("0"))) {

                            Method method;
                            float fvalue = 0;
                            int ivalue = 0;

                            for (Riga_tabella riga : data) {

                                //richiamo del metodo richiesto dall'utente su cui applicare il filtro
                                method = riga.getClass().getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), null);

                                //assegnazione della variabile per applicare il filtro in base al tipo di dato

                                if (Float.class.isInstance(method.invoke(riga))) {
                                    float valuemod = Float.parseFloat(value);
                                    fvalue = ((float) method.invoke(riga));

                                    if (fvalue <= valuemod) {
                                        list.add(riga);
                                    }

                                } else if (Integer.class.isInstance(method.invoke(riga))) {
                                    ivalue = ((int) method.invoke(riga));

                                    int valuemod = Integer.parseInt(value);
                                    ivalue = ((int) method.invoke(riga));

                                    if (ivalue <= valuemod) {
                                        list.add(riga);
                                    }
                                } else if (String.class.isInstance(method.invoke(riga)))
                                    throw new Exception("Inserire un campo che contenga valori numerici");
                            }
                        } else if (value.equals("0") && !value2.equals("0")) {
                            Method method;
                            float fvalue = 0;
                            int ivalue = 0;

                            for (Riga_tabella riga : data) {

                                //richiamo del metodo richiesto dall'utente su cui applicare il filtro
                                method = riga.getClass().getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), null);

                                //assegnazione della variabile per applicare il filtro in base al tipo di dato

                                if (Float.class.isInstance(method.invoke(riga))) {
                                    float valuemod = Float.parseFloat(value2);
                                    fvalue = ((float) method.invoke(riga));

                                    if (fvalue <= valuemod) {
                                        list.add(riga);
                                    }

                                } else if (Integer.class.isInstance(method.invoke(riga))) {
                                    ivalue = ((int) method.invoke(riga));
                                    int valuemod = Integer.parseInt(value2);
                                    ivalue = ((int) method.invoke(riga));

                                    if (ivalue <= valuemod) {
                                        list.add(riga);
                                    }
                                } else if (String.class.isInstance(method.invoke(riga)))
                                    throw new Exception("Inserire un campo che contenga valori numerici");
                            }
                        }
                    } catch (NoSuchMethodException e) {
                        throw new Exception("Inserire un campo corretto del dataset");

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

                case "$bt": {
                    try {

                        if ((!fieldName.equals("") && !value.equals("0") && value2.equals("0")) | (!fieldName.equals("") && !value.equals("0") && !value2.equals("0"))) {

                            Method method;
                            float fvalue = 0;
                            int ivalue = 0;

                            for (Riga_tabella riga : data) {

                                //richiamo del metodo richiesto dall'utente su cui applicare il filtro
                                method = riga.getClass().getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), null);

                                //assegnazione della variabile per applicare il filtro in base al tipo di dato

                                if (Float.class.isInstance(method.invoke(riga))) {
                                    float valuemod = Float.parseFloat(value);
                                    fvalue = ((float) method.invoke(riga));
                                    float valuemod2 = Float.parseFloat(value2);
                                    if (valuemod > valuemod2) {
                                        if ((ivalue < valuemod) && (ivalue > valuemod2)) {
                                            list.add(riga);
                                        }
                                    } else if ((ivalue > valuemod) && (ivalue < valuemod2)) {
                                        list.add(riga);
                                    }

                                } else if (Integer.class.isInstance(method.invoke(riga))) {
                                    ivalue = ((int) method.invoke(riga));
                                    int valuemod = Integer.parseInt(value);
                                    int valuemod2 = Integer.parseInt(value2);
                                    ivalue = ((int) method.invoke(riga));

                                    if (valuemod > valuemod2) {
                                        if ((ivalue < valuemod) && (ivalue > valuemod2)) {
                                            list.add(riga);
                                        }
                                    } else if ((ivalue > valuemod) && (ivalue < valuemod2)) {
                                        list.add(riga);
                                    }

                                } else if (String.class.isInstance(method.invoke(riga)))
                                    throw new Exception("Inserire un campo che contenga valori numerici");
                            }
                        }
                    } catch (NoSuchMethodException e) {
                        throw new Exception("Inserire un campo corretto del dataset");

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

                default:
                    break;
            }
        }

        return (ArrayList) list;

    }

    /**
     * Metodo che restituisce i  valori di un determinato campo della dataset
     *
     * @param field
     * @return
     * @throws Exception
     */
    public ArrayList<Object> Returnfield(String field) throws Exception {

        ArrayList<Object> list = new ArrayList<>();

        try {
            for (Riga_tabella riga : data) {
                Method method = riga.getClass().getMethod("get" + field.substring(0, 1).toUpperCase() + field.substring(1), null);
                String s = method.invoke(riga).toString();
                list.add(s);
            }

        } catch (NoSuchMethodException e) {
            throw new Exception("Inserire un campo corretto del dataset");

        } catch (IllegalAccessException e) {
            e.printStackTrace();

        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return list;
    }
}



