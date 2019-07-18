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
public class Is_division {

    private String is_division;

    public Is_division() {
    }

    public String getIs_division() {
        return is_division;
    }

    public void setIs_division(String is_division) {
        this.is_division = is_division;
    }
}
