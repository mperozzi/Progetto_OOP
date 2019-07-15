package com.company.projectoop.controller;

import com.company.projectoop.parsing.Parsecsv_Impl;
import com.company.projectoop.table.Riga_tabella;
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
                    "/getmetadata --> Per restituire un JSON con i metadati",
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


}
