package com.company.projectoop.model;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public class To {
    private String to;

    //costruttore della classe
    public To() {}

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

}
