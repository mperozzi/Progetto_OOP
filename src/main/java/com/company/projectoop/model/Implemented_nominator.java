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
public class Implemented_nominator {

    public Implemented_nominator() {
    }

    private String implemented_nominator;

    public String getImplemented_nominator() {
        return implemented_nominator;
    }

    public void setImplemented_nominator(String implemented_nominator) {
        this.implemented_nominator = implemented_nominator;
    }
}
