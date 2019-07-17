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
public class To_short {
    public To_short() {
    }

    private String to_short;

    public String getTo_short() {
        return to_short;
    }

    public void setTo_short(String to_short) {
        this.to_short = to_short;
    }
}
