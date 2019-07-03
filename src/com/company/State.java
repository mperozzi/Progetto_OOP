package com.company;

public class State {

    private String ms;
    private String ms_name;
    private String visualize_by_ms;

    //costruttore della classe
    public State () {}

    public String getMs() {
        return ms;
    }

    public String getMs_name() {
        return ms_name;
    }

    public String getVisualize_by_ms() {
        return visualize_by_ms;
    }

    public void setMs(String ms) {
        this.ms = ms;
    }

    public void setMs_name(String ms_name) {
        this.ms_name = ms_name;
    }

    public void setVisualize_by_ms(String visualize_by_ms) {
        this.visualize_by_ms = visualize_by_ms;
    }
}
