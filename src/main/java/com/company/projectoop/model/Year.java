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
public class Year {

    private String year;

    public Year() {}

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
