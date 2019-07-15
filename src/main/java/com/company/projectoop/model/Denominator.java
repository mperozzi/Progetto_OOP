package com.company.projectoop.model;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public class Denominator {

    private String denominator;

    public Denominator() {}

    public String getDenominator() {
        return denominator;
    }

    public void setDenominator(String denominator) {
        this.denominator = denominator;
    }

}
