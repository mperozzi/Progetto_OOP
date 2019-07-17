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
public class Priority_code {

    public Priority_code() {
    }

    private String priority_code;

    public String getPriority_code() {
        return priority_code;
    }

    public void setPriority_code(String priority_code) {
        this.priority_code = priority_code;
    }
}
