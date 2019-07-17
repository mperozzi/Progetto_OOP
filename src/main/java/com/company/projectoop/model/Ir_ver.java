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
public class Ir_ver {

    public Ir_ver() {
    }

    private float ir_ver;

    public float getIr_ver() {
        return ir_ver;
    }

    public void setIr_ver(float ir_ver) {
        this.ir_ver = ir_ver;
    }
}
