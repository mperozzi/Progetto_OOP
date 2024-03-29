package com.company.projectoop.controller;

import com.company.projectoop.parsing.Parsecsv_Impl;
import com.company.projectoop.table.Riga_tabella;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Controller dell'applicazione
 *
 * @author Matteo Perozzi
 * @author Ettore Zamponi
 */

@RestController
public class MainController {


    Parsecsv_Impl p = new Parsecsv_Impl();

    /**
     * Presentazione. Response che si ottiene appena si accede al localhost e ad ogni rotta sbagliata
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<Object> EachOtherRoot() {
        return
                new ResponseEntity<>("Questi sono i root path che possono essere impostati:" + "\n" +
                        "/getalldata --> Per restituire un JSON con tutti i dati organizzati" + "\n" +
                        "/getmetadata --> Per restituire un JSON con i metadati" + "\n" +
                        "/logicalfilter/{operator}?<parametri e valori> --> Per applicare filtri logici" + "\n" +
                        "/conditionalfilter/{operator}?<parametri e valori> --> Per applicare i filtri condizionali" + "\n" +
                        "/stats/{field} --> Per ottenere le statistiche complete di un determinato campo" + "\n" +
                        "/singlestats/{field}/{statistica desiderata} ---> Per ottenere la singola statistica di un determinato campo" + "\n" +
                        "/get/{field} --> Per ottenere i valori di un determinato campo della tabella" + "\n" +
                        "/countocc/{field}/{valore del campo} -->  Per contare quante occorrenze di un determinato valore all'interno di un campo ci sono"
                        , HttpStatus.OK);
    }

    /**
     * Ottenere tutti i dati
     *
     * @return --> lista di tutti i dati del dataset organizzati
     */
    @GetMapping(value = "/getalldata")
    public ArrayList<Riga_tabella> getAllData() {
        return (ArrayList) p.getData();
    }

    /**
     * Ottenere i metadata
     *
     * @return --> lista dei metadati
     */
    @GetMapping(value = "/getmetadata")
    public ArrayList<Object> getMetaData() {
        return (ArrayList) p.getMetadata();
    }

    /**
     * Filtro logico --> Applicabile a tutti i campi del dataset.
     * Operatori --> $and, $or, $not
     *
     * @param operator --> operatore logico in base al quale applicare il filtro
     * @param field1   --> primo campo sul quale applicare il filtro
     * @param value1   --> valore desiderato da filtrare relativo al campo 1
     * @param field2   --> secondo campo sul quale applicare il filtro
     * @param value2   --> valore desiderato da filtrare relativo al campo 2
     * @return lista degli elementi del dataset che rispettano il criterio stabilito dal filtro
     * @throws Exception
     */
    @RequestMapping(value = "/logicalfilter/{operator}", method = RequestMethod.GET)
    public ArrayList<Riga_tabella> Lfilter(@PathVariable String operator,
                                           @RequestParam(name = "field1") String field1,
                                           @RequestParam(name = "value1") String value1,
                                           @RequestParam(name = "field2", defaultValue = "") String field2,
                                           @RequestParam(name = "value2", defaultValue = "") String value2) throws Exception {

        return p.Logical_filter(operator, field1, value1, field2, value2);
    }

    /**
     * Metodo che restituisce il valore di tutte le statistiche sul parametro field
     * Applicabile solo a campi di tipo numerico
     *
     * @param field --> campo sul quale effettuare le statistiche
     * @return --> JSON contenente le statistiche complete
     * @throws Exception
     */
    @RequestMapping(value = "/stats/{field}", method = RequestMethod.GET)
    public JSONObject Stats(@PathVariable String field) throws Exception {
        JSONObject add = new JSONObject();
        try {
            add.put("field: ", field);
        } catch (Exception e) {
            throw new Exception();
        }
        try {
            add.put("sum: ", Parsecsv_Impl.sumValue(field.toLowerCase()));
        } catch (Exception e) {
            throw new Exception("Impossibile calcolare somma");
        }
        try {
            add.put("min: ", Parsecsv_Impl.minValue(field.toLowerCase()));
        } catch (Exception e) {
            throw new Exception("Impossibile individuare minimo");
        }
        try {
            add.put("max: ", Parsecsv_Impl.maxValue(field.toLowerCase()));
        } catch (Exception e) {
            throw new Exception("Impossibile individuare massimo");
        }
        try {
            add.put("avg: ", Parsecsv_Impl.avgValue(field.toLowerCase()));
        } catch (Exception e) {
            throw new Exception("Impossibile calcolare media");
        }
        try {
            add.put("devstd: ", Parsecsv_Impl.devstdValue(field.toLowerCase()));
        } catch (Exception e) {
            throw new Exception("Impossibile calcolare deviazione standard");
        }
        try {
            add.put("count: ", Parsecsv_Impl.countValue(field.toLowerCase()));
        } catch (Exception e) {
            throw new Exception("Impossibile contare elementi diversi da 0");
        }
        return add;
    }

    /**
     * Statistica - Restituisce la singola statistica in base al tipo di root specificato
     * Applicabile solo a campi di tipo numerico
     * sum --> Somma dei valori del campo field all'interno del dataset
     * max --> Individua il massimo tra i valori del campo field all'interno del dataset
     * min --> Individua il minimo tra i valori del campo field all'interno del dataset
     * avg --> Effettua la media dei valori del campo field all'interno del dataset
     * count --> Conta quanti record sono non nulli all'interno del campo scelto
     * devstd --> Calcola la deviazione standard dei valori all'interno del campo del datataset
     *
     * @param field --> campo sul quale applicare la statistica
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/singlestats/{field}/{choise}", method = RequestMethod.GET)
    public JSONObject Singlestat(@PathVariable String field, @PathVariable String choise) throws Exception {
        JSONObject add = new JSONObject();
        try {
            switch (choise) {
                case "sum": {
                    add.put("field: ", field);
                    add.put("sum: ", Parsecsv_Impl.sumValue(field.toLowerCase()));
                }
                break;

                case "max": {
                    add.put("field: ", field);
                    add.put("max: ", Parsecsv_Impl.maxValue(field.toLowerCase()));
                }
                break;

                case "min": {
                    add.put("field: ", field);
                    add.put("min: ", Parsecsv_Impl.minValue(field.toLowerCase()));
                }
                break;

                case "avg": {
                    add.put("field: ", field);
                    add.put("avg: ", Parsecsv_Impl.avgValue(field.toLowerCase()));
                }
                break;

                case "devstd": {
                    add.put("field: ", field);
                    add.put("devstd: ", Parsecsv_Impl.devstdValue(field.toLowerCase()));
                }
                break;

                case "count": {
                    add.put("field: ", field);
                    add.put("count: ", Parsecsv_Impl.countValue(field.toLowerCase()));
                }
                break;
            }

        } catch (Exception e) {
            throw new Exception();
        }

        return add;
    }

    /**
     * Statistica - Effettua il conteggio di quante occorrenze ci sono di un valore all'interno
     * di un determinato campo del dataset
     *
     * @param field --> campo sul quale applicare la statistica
     * @param value --> valore del quale se ne vogliono contare le occorrenze
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/countocc/{field}/{value}", method = RequestMethod.GET)
    public JSONObject CountOccorrenze(@PathVariable String field, @PathVariable String value) throws Exception {
        JSONObject add = new JSONObject();

        try {
            add.put("field: ", field);
            add.put("countocc: ", Parsecsv_Impl.countOcc(field, value));
        } catch (Exception e) {
            throw new Exception();
        }

        return add;
    }

    /**
     * Filtro condizionale --> Applicabile solo ai campi numerici
     * Operatori --> $gt (maggiore), $gte (maggiore e uguale), $lt(minore), $lte (minore e uguale), $bt (compreso tra)
     *
     * @param operator --> operatore
     * @param field    --> campo sul quale applicare il filtro
     * @param value    --> valore da filtrare
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/conditionalfilter/{operator}", method = RequestMethod.GET)
    public ArrayList<Riga_tabella> Cfilter(@PathVariable String operator,
                                           @RequestParam(name = "field") String field,
                                           @RequestParam(name = "value", defaultValue = "0") String value,
                                           @RequestParam(name = "value2", defaultValue = "0") String value2) throws Exception {
        return p.Conditional_filter(operator, field, value, value2);
    }

    /**
     * Ottenere i valori di un determinato campo del dataset
     *
     * @return --> lista dei dati del campo
     */
    @GetMapping(value = "/get/{field}")
    public ArrayList<Object> getfield(@PathVariable String field) throws Exception {
        return p.Returnfield(field);
    }

}
