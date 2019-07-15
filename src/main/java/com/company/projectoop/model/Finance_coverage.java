package com.company.projectoop.model;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public class Finance_coverage {

    public Finance_coverage() {
    }

    private String finance_coverage;

    public String getFinance_coverage() {
        return finance_coverage;
    }

    public void setFinance_coverage(String finance_coverage) {
        this.finance_coverage = finance_coverage;
    }
}
