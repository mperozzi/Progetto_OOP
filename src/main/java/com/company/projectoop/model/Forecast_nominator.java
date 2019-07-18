package com.company.projectoop.model;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Classe che identifica un campo della tabella
 *
 * @author Matteo Perozzi
 * @author Ettore Zamponi
 */

@Repository
@Component
public class Forecast_nominator {

    public Forecast_nominator() {
    }

    private String forecast_nominator;

    public String getForecast_nominator() {
        return forecast_nominator;
    }

    public void setForecast_nominator(String forecast_nominator) {
        this.forecast_nominator = forecast_nominator;
    }
}
