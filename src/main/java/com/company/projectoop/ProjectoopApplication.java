package com.company.projectoop;

import com.company.projectoop.parsing.Parsecsv_Impl;
import com.company.projectoop.parsing.Parsejson_Impl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principale dell'applicazione. All'avvio dell'app
 * viene scaricato il file CSV e viene popolata la struttura
 * dati contenente i dati all'interno.
 *
 * @author Matteo Perozzi
 * @author Ettore Zamponi
 */

@SpringBootApplication(scanBasePackages = {"com.company.projectoop"})

public class ProjectoopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectoopApplication.class, args);

        //operazioni di lettura del JSON e download del CSV da effettuare all'avvio
        Parsejson_Impl.Read_json_from_URL();
        Parsecsv_Impl.Popola_tabella();

    }

}
