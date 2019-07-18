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
public class Ms_op_coverage {

    private String ms_op_coverage;

    public Ms_op_coverage() {
    }

    public String getMs_op_coverage() {
        return ms_op_coverage;
    }

    public void setMs_op_coverage(String ms_op_coverage) {
        this.ms_op_coverage = ms_op_coverage;
    }

}
