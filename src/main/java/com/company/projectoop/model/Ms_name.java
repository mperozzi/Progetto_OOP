package com.company.projectoop.model;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Classe che identifica un campo della tabella
 *
 * @author Matteo Perozzi
 * @author Ettore Zamponi
 */

@Component
@Repository
public class Ms_name {

    private String ms_name;

    //costruttore della classe
    public Ms_name() {
    }

    //getter
    public String getMs_name() {
        return ms_name;
    }

    //setter
    public void setMs_name(String ms_name) {
        this.ms_name = ms_name;
    }

}
