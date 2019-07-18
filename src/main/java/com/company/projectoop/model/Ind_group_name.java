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
public class Ind_group_name {

    public Ind_group_name() {
    }

    String ind_group_name;

    public String getInd_group_name() {
        return ind_group_name;
    }

    public void setInd_group_name(String ind_group_name) {
        this.ind_group_name = ind_group_name;
    }
}
