package com.company.projectoop.model;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Classe che identifica un campo della tabella
 * @author Matteo Perozzi
 * @author Ettore Zamponi
 */

@Component
@Repository
public class Ver {

    private String ver;

    //costruttore della classe
    public Ver() {}

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }
}
