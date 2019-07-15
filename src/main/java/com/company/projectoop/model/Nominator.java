package com.company.projectoop.model;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public class Nominator {

    private String nominator;

    public Nominator() {}

    public String getNominator() {
        return nominator;
    }

    public void setNominator(String nominator) {
        this.nominator = nominator;
    }

}
