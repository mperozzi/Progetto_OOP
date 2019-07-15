package com.company.projectoop.model;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public class Investment_priority {

    private String investment_priority;

    public Investment_priority() {}

    public String getInvestment_priority() {
        return investment_priority;
    }

    public void setInvestment_priority(String investment_priority) {
        this.investment_priority = investment_priority;
    }
}
