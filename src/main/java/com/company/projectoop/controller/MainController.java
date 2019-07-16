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

    @RequestMapping(value = "")
    public ResponseEntity<Object> Start() {
        return
            new ResponseEntity<>("Questi sono i root path che puoi impostare:" + "\n" +
                    "/download --> Per effettuare il download del file CSV sul Desktop" + "\n" +
                    "/popolate --> Per riempire la struttura dati con i file all'interno del CSV" + "\n" +
                    "/getalldata --> Per restituire un JSON con tutti i dati organizzati" + "\n" +
                    "/getmetadata --> Per restituire un JSON con i metadati" + "\n" +
                    "/Lfilter --> Per applicare filtri logici",
                    HttpStatus.OK);
    }

    @RequestMapping(value = "/getalldata")
    public ArrayList<Riga_tabella> getAllData() {
        return (ArrayList) p.getData();
    }

    @RequestMapping(value = "/getmetadata")
    public ArrayList<Object> getMetaData() {
        return (ArrayList) p.getMetadata();
    }

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

    @RequestMapping (value = "/stats/sum", method = RequestMethod.GET)
    public String sumValue (@RequestParam("value") String value) throws Exception
    {
        JSONObject add = new JSONObject();
        try {
            //calcoliamo la somma e la mettiamo nel JSON
            add.put("sum", Parsecsv_Impl.sumValue(value.toLowerCase()));
        } catch (Exception e) {
            //...
        }
        return add.toString();
    }

    @RequestMapping (value = "/stats/avg", method = RequestMethod.GET)
    public String avgValue (@RequestParam("value") String value) throws Exception
    {
        JSONObject add = new JSONObject();
        try {
            //calcoliamo la somma e la mettiamo nel JSON
            add.put("avg", Parsecsv_Impl.avgValue(value.toLowerCase()));
        } catch (Exception e) {
           // ...
        }
        return add.toString();
    }

    @RequestMapping (value = "/stats/min", method = RequestMethod.GET)
    public String minValue (@RequestParam("value") String value) throws Exception
    {
        JSONObject add = new JSONObject();
        try {
            //calcoliamo la somma e la mettiamo nel JSON
            add.put("min", Parsecsv_Impl.minValue(value.toLowerCase()));
        } catch (Exception e) {
            //...
        }
        return add.toString();
    }

    @RequestMapping (value = "/stats/max", method = RequestMethod.GET)
    public String maxValue (@RequestParam("value") String value) throws Exception
    {
        JSONObject add = new JSONObject();
        try {
            //calcoliamo la somma e la mettiamo nel JSON
            add.put("max", Parsecsv_Impl.maxValue(value.toLowerCase()));
        } catch (Exception e) {
           // ...
        }
        return add.toString();
    }

    @RequestMapping (value = "/stats/count", method = RequestMethod.GET)
    public String countValue (@RequestParam("value") String value) throws Exception
    {
        JSONObject add = new JSONObject();
        try {
            //calcoliamo la somma e la mettiamo nel JSON
            add.put("count", Parsecsv_Impl.countValue(value.toLowerCase()));
        } catch (Exception e) {
           // ...
        }
        return add.toString();
    }

}
