package com.company.projectoop.model;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public class Target_value {

    private String target_value;

    public Target_value() {}

    public String getTarget_value() {
        return target_value;
    }

    public void setTarget_value(String target_value) {
        this.target_value = target_value;
    }

}
