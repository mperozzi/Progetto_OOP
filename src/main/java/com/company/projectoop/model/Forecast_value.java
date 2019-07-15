package com.company.projectoop.model;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public class Forecast_value {

    public Forecast_value() {
    }

    private String forecast_value;

    public String getForecast_value() {
        return forecast_value;
    }

    public void setForecast_value(String forecast_value) {
        this.forecast_value = forecast_value;
    }
}
