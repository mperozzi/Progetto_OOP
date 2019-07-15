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
public class Indicator_short_name {

    public Indicator_short_name() {
    }

    private String indicator_short_name;

    public String getIndicator_short_name() {
        return indicator_short_name;
    }

    public void setIndicator_short_name(String indicator_short_name) {
        this.indicator_short_name = indicator_short_name;
    }
}
