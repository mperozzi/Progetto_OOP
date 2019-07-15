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
public class Visualize_by_to {

    public Visualize_by_to() {
    }

    private String visualize_by_to;

    public String getVisualize_by_to() {
        return visualize_by_to;
    }

    public void setVisualize_by_to(String visualize_by_to) {
        this.visualize_by_to = visualize_by_to;
    }
}
