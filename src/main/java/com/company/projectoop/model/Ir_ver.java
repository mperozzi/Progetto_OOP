package com.company.projectoop.model;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public class Ir_ver {

    public Ir_ver() {
    }

    private String ir_ver;

    public String getIr_ver() {
        return ir_ver;
    }

    public void setIr_ver(String ir_ver) {
        this.ir_ver = ir_ver;
    }
}
