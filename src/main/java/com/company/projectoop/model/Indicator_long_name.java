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
public class Indicator_long_name {

    public Indicator_long_name() {
    }

    private String indicator_long_name;

    public String getIndicator_long_name() {
        return indicator_long_name;
    }

    public void setIndicator_long_name(String indicator_long_name) {
        this.indicator_long_name = indicator_long_name;
    }
}
