package com.company.projectoop.model;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Classe che identifica un campo della tabella
 * @author Matteo Perozzi
 * @author Ettore Zamponi
 */

@Repository
@Component
public class Indicator_type_code {

    private String indicator_type_code;

    public String getIndicator_type_code() {
        return indicator_type_code;
    }

    public void setIndicator_type_code(String indicator_type_code) {
        this.indicator_type_code = indicator_type_code;
    }

    public Indicator_type_code() {
    }


}
