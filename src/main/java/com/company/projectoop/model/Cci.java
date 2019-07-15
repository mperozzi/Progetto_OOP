package com.company.projectoop.model;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public class Cci {

    private String cci;

    //costruttore della classe
    public Cci() { }

    public String getCci() {
        return cci;
    }

    public void setCci(String cci) {
        this.cci = cci;
    }
}
