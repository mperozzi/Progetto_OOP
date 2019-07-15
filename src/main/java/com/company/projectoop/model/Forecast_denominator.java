package com.company.projectoop.model;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public class Forecast_denominator {

    public Forecast_denominator() {
    }

    private String forecast_denominator;

    public String getForecast_denominator() {
        return forecast_denominator;
    }

    public void setForecast_denominator(String forecast_denominator) {
        this.forecast_denominator = forecast_denominator;
    }
}
