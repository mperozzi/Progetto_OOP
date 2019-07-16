package com.company.projectoop.model;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public class Target_value {

    private float target_value;

    public Target_value() {}

    public float getTarget_value() {
        return target_value;
    }

    public void setTarget_value(float target_value) {
        this.target_value = target_value;
    }

}
