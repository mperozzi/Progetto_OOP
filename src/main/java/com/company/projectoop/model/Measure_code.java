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
public class Measure_code {

    public Measure_code() {
    }

    private String measure_code;

    public String getMeasure_code() {
        return measure_code;
    }

    public void setMeasure_code(String measure_code) {
        this.measure_code = measure_code;
    }
}
