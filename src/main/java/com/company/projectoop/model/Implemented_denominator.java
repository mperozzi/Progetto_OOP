package com.company.projectoop.model;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Classe che identifica un campo della tabella
 *
 * @author Matteo Perozzi
 * @author Ettore Zamponi
 */

@Repository
@Component
public class Implemented_denominator {

    public Implemented_denominator() {
    }

    private String implemented_denominator;

    public String getImplemented_denominator() {
        return implemented_denominator;
    }

    public void setImplemented_denominator(String implemented_denominator) {
        this.implemented_denominator = implemented_denominator;
    }
}
