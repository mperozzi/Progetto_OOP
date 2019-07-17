package com.company.projectoop.table;

import com.company.projectoop.model.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Classe che implementa la struttura base di una riga della tabella del dataset per immagazzinare
 * i dati estrapolati dal CSV.
 * @author Matteo Perozzi
 * @author Ettore Zamponi
 */

@Component
@Repository
public class Riga_tabella {

    private Ms ms;
    private Ms_name ms_name;
    private Cci cci;
    private Ver ver;
    private Title title;
    private Fund fund;
    private Indicator_type_code indicator_type_code;
    private Ind_code ind_code;
    private Ind_group_code ind_group_code;
    private Indicator_short_name indicator_short_name;
    private Indicator_long_name indicator_long_name;
    private To to;
    private To_short to_short;
    private Priority_code priority_code;
    private Investment_priority investment_priority;
    private Measure_code measure_code;
    private Focus_area_code focus_area_code;
    private Target_value target_value;
    private Measurement_unit measurement_unit;
    private Nominator nominator;
    private Denominator denominator;
    private Is_division is_division;
    private Year year;
    private Ir_ver ir_ver;
    private Forecast_value forecast_value;
    private Forecast_nominator forecast_nominator;
    private Forecast_denominator forecast_denominator;
    private Implemented_value implemented_value;
    private Implemented_nominator implemented_nominator;
    private Implemented_denominator implemented_denominator;
    private Visualize_by_to visualize_by_to;
    private Visualize_by_ms visualize_by_ms;
    private Ms_op_coverage ms_op_coverage;
    private Finance_coverage finance_coverage;
    private Ind_group_name ind_group_name;


    /**
     * Costruttore della classe -> Inizializza lo stato interno dell'oggetto in maniera tale che ad ogni istanza
     * l'oggetto puo essere utilizzato subito
     */
    public Riga_tabella() {
        ms = new Ms();
        ms_name = new Ms_name();
        cci = new Cci();
        ver = new Ver();
        title = new Title();
        fund = new Fund();
        indicator_type_code = new Indicator_type_code();
        ind_code = new Ind_code();
        ind_group_code = new Ind_group_code();
        indicator_short_name = new Indicator_short_name();
        indicator_long_name = new Indicator_long_name();
        to = new To();
        to_short = new To_short();
        priority_code = new Priority_code();
        investment_priority = new Investment_priority();
        measure_code = new Measure_code();
        focus_area_code = new Focus_area_code();
        target_value = new Target_value();
        measurement_unit = new Measurement_unit();
        nominator = new Nominator();
        denominator = new Denominator();
        is_division = new Is_division();
        year = new Year();
        ir_ver = new Ir_ver();
        forecast_value = new Forecast_value();
        forecast_nominator = new Forecast_nominator();
        forecast_denominator = new Forecast_denominator();
        implemented_value = new Implemented_value();
        implemented_nominator = new Implemented_nominator();
        implemented_denominator = new Implemented_denominator();
        visualize_by_to = new Visualize_by_to();
        visualize_by_ms = new Visualize_by_ms();
        ms_op_coverage = new Ms_op_coverage();
        finance_coverage = new Finance_coverage();
        ind_group_name = new Ind_group_name();
    }

    /**
     * getter dei vari campi della riga della tabella
     * @return
     *
     * setter dei vari campi della riga della tabella
     */

    public String getMs() {
        return ms.getMs();
    }

    public void setMs(String ms) {
        this.ms.setMs(ms);
    }

    public String getMs_name() {
        return ms_name.getMs_name();
    }

    public void setMs_name(String ms_name) {
        this.ms_name.setMs_name(ms_name);
    }

    public String getCci() {
        return cci.getCci();
    }

    public void setCci(String cci) {
        this.cci.setCci(cci);
    }

    public float getVer() {
        return ver.getVer();
    }

    public void setVer(float ver) {
        this.ver.setVer(ver);
    }

    public String getTitle() {
        return title.getTitle();
    }

    public void setTitle(String title) {
        this.title.setTitle(title);
    }

    public String getFund() {
        return fund.getFund();
    }

    public void setFund(String fund) {
        this.fund.setFund(fund);
    }

    public String getIndicator_type_code() {
        return indicator_type_code.getIndicator_type_code();
    }

    public void setIndicator_type_code(String indicator_type_code) {
        this.indicator_type_code.setIndicator_type_code(indicator_type_code);
    }

    public String getInd_code() {
        return ind_code.getInd_code();
    }

    public void setInd_code (String ind_code) {
        this.ind_code.setInd_code(ind_code);
    }

    public String getInd_group_code() {
        return ind_group_code.getInd_group_code();
    }

    public void setInd_group_code(String ind_group_code) {
        this.ind_group_code.setInd_group_code(ind_group_code);
    }

    public String getIndicator_short_name() {
        return indicator_short_name.getIndicator_short_name();
    }

    public void setIndicator_short_name(String indicator_short_name) {
        this.indicator_short_name.setIndicator_short_name(indicator_short_name);
    }

    public String getIndicator_long_name() {
        return indicator_long_name.getIndicator_long_name();
    }

    public void setIndicator_long_name(String indicator_long_name) {
        this.indicator_long_name.setIndicator_long_name(indicator_long_name);
    }

    public int getTo() {
        return to.getTo();
    }

    public void setTo(int to) {
        this.to.setTo(to);
    }

    public String getTo_short() {
        return to_short.getTo_short();
    }

    public void setTo_short(String to_short) {
        this.to_short.setTo_short(to_short);
    }

    public String getPriority_code() {
        return priority_code.getPriority_code();
    }

    public void setPriority_code(String priority_code) {
        this.priority_code.setPriority_code(priority_code);
    }

    public String getInvestment_priority() {
        return investment_priority.getInvestment_priority();
    }

    public void setInvestment_priority(String investment_priority) {
        this.investment_priority.setInvestment_priority(investment_priority);
    }

    public String getMeasure_code() {
        return measure_code.getMeasure_code();
    }

    public void setMeasure_code(String measure_code) {
        this.measure_code.setMeasure_code(measure_code);
    }

    public String getFocus_area_code() {
        return focus_area_code.getFocus_area_code();
    }

    public void setFocus_area_code(String focus_area_code) {
        this.focus_area_code.setFocus_area_code(focus_area_code);
    }

    public int getTarget_value() {
        return target_value.getTarget_value();
    }

    public void setTarget_value(int target_value) {
        this.target_value.setTarget_value(target_value);
    }

    public String getMeasurement_unit() {
        return measurement_unit.getMeasurement_unit();
    }

    public void setMeasurement_unit(String measurement_unit) {
        this.measurement_unit.setMeasurement_unit(measurement_unit);
    }

    public int getNominator() {
        return nominator.getNominator();
    }

    public void setNominator(int nominator) {
        this.nominator.setNominator(nominator);
    }

    public String getDenominator() {
        return denominator.getDenominator();
    }

    public void setDenominator(String denominator) {
        this.denominator.setDenominator(denominator);
    }

    public String getIs_division() {
        return is_division.getIs_division();
    }

    public void setIs_division(String is_division) {
        this.is_division.setIs_division(is_division);
    }

    public int getYear() {
        return year.getYear();
    }

    public void setYear(int year) {
        this.year.setYear(year);
    }

    public float getIr_ver() {
        return ir_ver.getIr_ver();
    }

    public void setIr_ver(float ir_ver) {
        this.ir_ver.setIr_ver(ir_ver);
    }

    public String getForecast_value() {
        return forecast_value.getForecast_value();
    }

    public void setForecast_value(String forecast_value) {
        this.forecast_value.setForecast_value(forecast_value);
    }

    public String getForecast_nominator() {
        return forecast_nominator.getForecast_nominator();
    }

    public void setForecast_nominator(String forecast_nominator) {
        this.forecast_nominator.setForecast_nominator(forecast_nominator);
    }

    public String getForecast_denominator() {
        return forecast_denominator.getForecast_denominator();
    }

    public void setForecast_denominator(String forecast_denominator) {
        this.forecast_denominator.setForecast_denominator(forecast_denominator);
    }

    public String getImplemented_value() {
        return implemented_value.getImplemented_value();
    }

    public void setImplemented_value(String implemented_value) {
        this.implemented_value.setImplemented_value(implemented_value);
    }

    public String getImplemented_nominator() {
        return implemented_nominator.getImplemented_nominator();
    }

    public void setImplemented_nominator(String implemented_nominator) {
        this.implemented_nominator.setImplemented_nominator(implemented_nominator);
    }

    public String getImplemented_denominator() {
        return implemented_denominator.getImplemented_denominator();
    }

    public void setImplemented_denominator(String implemented_denominator) {
        this.implemented_denominator.setImplemented_denominator(implemented_denominator);
    }

    public String getVisualize_by_to() {
        return visualize_by_to.getVisualize_by_to();
    }

    public void setVisualize_by_to(String visualize_by_to) {
        this.visualize_by_to.setVisualize_by_to(visualize_by_to);
    }

    public String getVisualize_by_ms() {
        return visualize_by_ms.getVisualize_by_ms();
    }

    public void setVisualize_by_ms(String visualize_by_ms) {
        this.visualize_by_ms.setVisualize_by_ms(visualize_by_ms);
    }

    public String getMs_op_coverage() {
        return ms_op_coverage.getMs_op_coverage();
    }

    public void setMs_op_coverage(String ms_op_coverage) {
        this.ms_op_coverage.setMs_op_coverage(ms_op_coverage);
    }

    public String getFinance_coverage() {
        return finance_coverage.getFinance_coverage();
    }

    public void setFinance_coverage(String finance_coverage) {
        this.finance_coverage.setFinance_coverage(finance_coverage);
    }

    public String getInd_group_name() {
        return ind_group_name.getInd_group_name();
    }

    public void setInd_group_name(String ind_group_name) {
        this.ind_group_name.setInd_group_name(ind_group_name);
    }
}

