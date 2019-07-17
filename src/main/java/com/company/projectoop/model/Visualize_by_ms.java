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
public class Visualize_by_ms {

    public Visualize_by_ms() {
    }

    private String visualize_by_ms;

    public String getVisualize_by_ms() {
        return visualize_by_ms;
    }

    public void setVisualize_by_ms(String visualize_by_ms) {
        this.visualize_by_ms = visualize_by_ms;
    }
}
