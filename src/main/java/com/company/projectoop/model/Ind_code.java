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
public class Ind_code {

    private String ind_code;

    public Ind_code() {
    }

    public String getInd_code() {
        return ind_code;
    }

    public void setInd_code(String ind_code) {
        this.ind_code = ind_code;
    }

}
