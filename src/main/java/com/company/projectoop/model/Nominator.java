package com.company.projectoop.model;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public class Nominator {

    private float nominator;

    public Nominator() {}

    public float getNominator() {
        return nominator;
    }

    public void setNominator(float nominator) {
        this.nominator = nominator;
    }

}
