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
public class Implemented_value {

    public Implemented_value() {
    }

    private String implemented_value;

    public String getImplemented_value() {
        return implemented_value;
    }

    public void setImplemented_value(String implemented_value) {
        this.implemented_value = implemented_value;
    }
}
