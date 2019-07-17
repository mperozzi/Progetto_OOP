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
public class Target_value {

    private int target_value;

    public Target_value() {}

    public int getTarget_value() {
        return target_value;
    }

    public void setTarget_value(int target_value) {
        this.target_value = target_value;
    }

}
