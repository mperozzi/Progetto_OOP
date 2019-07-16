package com.company.projectoop.model;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public class To {

    private float to;

    //costruttore della classe
    public To() {}

    public float getTo() {
        return to;
    }

    public void setTo(float to) {
        this.to = to;
    }

}
