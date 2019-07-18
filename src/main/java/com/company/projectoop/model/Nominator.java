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
public class Nominator {

    private int nominator;

    public Nominator() {
    }

    public int getNominator() {
        return nominator;
    }

    public void setNominator(int nominator) {
        this.nominator = nominator;
    }

}
