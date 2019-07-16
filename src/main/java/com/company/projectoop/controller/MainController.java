package com.company.projectoop.controller;

import com.company.projectoop.parsing.Parsecsv_Impl;
import com.company.projectoop.table.Riga_tabella;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

/**
 * Controller dell'applicazione Spring
 * @author Matteo Perozzi
 * @author Ettore Zamponi
 */

@RestController
public class MainController {


    Parsecsv_Impl p = new Parsecsv_Impl();

    /**
     * Presentazione. Response che si ottiene appena si accede al localhost
     * @return
     */
    @RequestMapping(value = "")
    public ResponseEntity<Object> Start() {
        return
            new ResponseEntity<>("Questi sono i root path che puoi impostare:" + "\n" +
                    "/getalldata --> Per restituire un JSON con tutti i dati organizzati" + "\n" +
                    "/getmetadata --> Per restituire un JSON con i metadati" + "\n" +
                    "/logicalfilter --> Per applicare filtri logici",
                    HttpStatus.OK);
    }

    /**
     * Ottenere tutti i dati
     * @return --> lista di tutti i dati del dataset organizzati
     */
    @RequestMapping(value = "/getalldata")
    public ArrayList<Riga_tabella> getAllData() {
        return (ArrayList) p.getData();
    }

    /**
     * Ottenere i metadata
     * @return --> lista dei metadati
     */
    @RequestMapping(value = "/getmetadata")
    public ArrayList<Object> getMetaData() {
        return (ArrayList) p.getMetadata();
    }

    /**
     * Filtro logico --> Applicabile a tutti i campi del dataset.
     * Operatori --> and, or, not
     * @param operator --> operatore logico in base al quale applicare il filtro
     * @param field1 --> primo campo sul quale applicare il filtro
     * @param value1 --> valore desiderato da filtrare relativo al campo 1
     * @param field2 --> secondo campo sul quale applicare il filtro
     * @param value2 --> valore desiderato da filtrare relativo al campo 2
     * @return lista degli elementi del dataset che rispettano il criterio stabilito dal filtro
     * @throws Exception
     */
    @RequestMapping(value = "/logicalfilter", method = RequestMethod.GET)
    public ArrayList<Riga_tabella> Lfilter(
            @RequestParam("operator") String operator,
            @RequestParam("field1") String field1,
            @RequestParam("value1") String value1,
            @RequestParam(name = "field2", defaultValue = "") String field2,
            @RequestParam(name = "value2", defaultValue = "") String value2
            ) throws Exception {

        return p.Logical_filter(field1, value1, operator, field2, value2);
    }

    /**
     * Metodo che restituisce il valore di tutte le statistiche sul parametro field
     * Applicabile solo a metodi int e float
     * @param field --> campo sul quale effettuare le statistiche
     * @return --> JSON contenente le statistiche complete
     * @throws Exception
     */
    @RequestMapping (value = "/stats", method = RequestMethod.GET)
    public String Stats (@RequestParam("field") String field) throws Exception
    {
        JSONObject add = new JSONObject();
        try {

            add.put("field: ",field);
            add.put("sum: ", Parsecsv_Impl.sumValue(field.toLowerCase()));
            add.put("min: ", Parsecsv_Impl.minValue(field.toLowerCase()));
            add.put("max: ", Parsecsv_Impl.maxValue(field.toLowerCase()));
            add.put("avg: ", Parsecsv_Impl.avgValue(field.toLowerCase()));
            add.put("devstd: ", Parsecsv_Impl.devstdValue(field.toLowerCase()));
            add.put("count: ", Parsecsv_Impl.countValue(field.toLowerCase()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return add.toString();
    }

    /**
     * Statistica - Effettua la somma dei valori del campo field all'interno del dataset
     * @param field --> campo sul quale applicare la statistica
     * @return --> somma
     * @throws Exception
     */
    @RequestMapping (value = "/stats/sum", method = RequestMethod.GET)
    public String sumValue (@RequestParam("field") String field) throws Exception
    {
        JSONObject add = new JSONObject();
        try {
            //calcoliamo la somma e la mettiamo nel JSON
            add.put("field: ",field);
            add.put("sum: ", Parsecsv_Impl.sumValue(field.toLowerCase()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return add.toString();
    }

    /**
     * Statistica - Effettua la media dei valori del campo field all'interno del dataset
     * @param field --> campo sul quale applicare la statistica
     * @return --> media
     * @throws Exception
     */
    @RequestMapping (value = "/stats/avg", method = RequestMethod.GET)
    public String avgValue (@RequestParam("field") String field) throws Exception
    {
        JSONObject add = new JSONObject();
        try {
            //calcoliamo la media e la mettiamo nel JSON
            add.put("field: ",field);
            add.put("avg: ", Parsecsv_Impl.avgValue(field.toLowerCase()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return add.toString();
    }

    /**
     * Statistica - Individua il minimo tra i valori del campo field all'interno del dataset
     * @param field --> campo sul quale applicare la statistica
     * @return --> valore minimo
     * @throws Exception
     */
    @RequestMapping (value = "/stats/min", method = RequestMethod.GET)
    public String minValue (@RequestParam("field") String field) throws Exception
    {
        JSONObject add = new JSONObject();
        try {
            //calcoliamo il valore minimo e lo mettiamo nel JSON
            add.put("field: ",field);
            add.put("min: ", Parsecsv_Impl.minValue(field.toLowerCase()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return add.toString();
    }

    /**
     * Statistica - Individua il massimo tra i valori del campo field all'interno del dataset
     * @param field --> campo sul quale applicare la statistica
     * @return --> valore massimo
     * @throws Exception
     */
    @RequestMapping (value = "/stats/max", method = RequestMethod.GET)
    public String maxValue (@RequestParam("field") String field) throws Exception
    {
        JSONObject add = new JSONObject();
        try {
            //calcoliamo il valore massimo e lo mettiamo nel JSON
            add.put("field: ",field);
            add.put("max: ", Parsecsv_Impl.maxValue(field.toLowerCase()));
        } catch (Exception e) {
           e.printStackTrace();
        }
        return add.toString();
    }

    /**
     * Statistica - Conta quanti record sono non nulli all'interno del campo scelto
     * @param field --> campo sul quale applicare la statistica
     * @return --> contatore
     * @throws Exception
     */
    @RequestMapping (value = "/stats/count", method = RequestMethod.GET)
    public String countValue (@RequestParam("field") String field) throws Exception
    {
        JSONObject add = new JSONObject();
        try {
            add.put("field: ",field);
            add.put("count", Parsecsv_Impl.countValue(field.toLowerCase()));
        } catch (Exception e) {
           e.printStackTrace();
        }
        return add.toString();
    }

    /**
     * Statistica - Calcola la deviazione standard dei valori all'interno del campo del datataset
     * @param field --> campo sul quale applicare la statistica
     * @return --> deviazione standard
     * @throws Exception
     */
    @RequestMapping (value = "/stats/devstd", method = RequestMethod.GET)
    public String devstd (@RequestParam("field") String field) throws Exception
    {
        JSONObject add = new JSONObject();
        try {
            add.put("field: ",field);
            add.put("count", Parsecsv_Impl.devstdValue(field));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return add.toString();
    }

}
