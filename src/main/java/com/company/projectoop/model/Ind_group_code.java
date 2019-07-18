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
public class Ind_group_code {

    public Ind_group_code() {
    }

    private String ind_group_code;

    public String getInd_group_code() {
        return ind_group_code;
    }

    public void setInd_group_code(String ind_group_code) {
        this.ind_group_code = ind_group_code;
    }
}
