package com.company;

public class Riga_tabella {

    private State my_state;
    private Code_Cci my_cci;
    private Ver my_ver;
    private Title my_title;
    private Fund my_fund;
    private Code my_code;
    private Group my_group;
    private Indicator_name my_indicator_name;
    private To my_to;
    private Investment my_investment;
    private Value my_value;
    private Unit my_measure_unit;
    private Nominator my_nom;
    private Denominator my_denom;
    private Div my_is_div;
    private Reference_Year my_year;
    private Coverage my_coverage;

    //Costruttore della classe -> Inizializza lo stato interno dell'oggetto in maniera tale che ad ogni istanza l'oggetto puo essere utilizzato subito
    Riga_tabella() {
        my_state = new State();
        my_cci = new Code_Cci();
        my_ver = new Ver();
        my_title = new Title();
        my_fund = new Fund();
        my_code = new Code();
        my_group = new Group();
        my_indicator_name = new Indicator_name();
        my_to = new To();
        my_investment = new Investment();
        my_value = new Value();
        my_measure_unit = new Unit();
        my_nom = new Nominator();
        my_denom = new Denominator();
        my_is_div = new Div();
        my_year = new Reference_Year();
        my_coverage = new Coverage();
    }

    public State getMy_state() {
        return my_state;
    }

    public void setMy_state(State my_state) {
        this.my_state = my_state;
    }

    public Code_Cci getMy_cci() {
        return my_cci;
    }

    public void setMy_cci(Code_Cci my_cci) {
        this.my_cci = my_cci;
    }

    public Ver getMy_ver() {
        return my_ver;
    }

    public void setMy_ver(Ver my_ver) {
        this.my_ver = my_ver;
    }

    public Title getMy_title() {
        return my_title;
    }

    public void setMy_title(Title my_title) {
        this.my_title = my_title;
    }

    public Fund getMy_fund() {
        return my_fund;
    }

    public void setMy_fund(Fund my_fund) {
        this.my_fund = my_fund;
    }

    public Code getMy_code() {
        return my_code;
    }

    public void setMy_code(Code my_code) {
        this.my_code = my_code;
    }

    public Group getMy_group() {
        return my_group;
    }

    public void setMy_group(Group my_group) {
        this.my_group = my_group;
    }

    public Indicator_name getMy_indicator_name() {
        return my_indicator_name;
    }

    public void setMy_indicator_name(Indicator_name my_indicator_name) {
        this.my_indicator_name = my_indicator_name;
    }

    public To getMy_to() {
        return my_to;
    }

    public void setMy_to(To my_to) {
        this.my_to = my_to;
    }

    public Investment getMy_investment() {
        return my_investment;
    }

    public void setMy_investment(Investment my_investment) {
        this.my_investment = my_investment;
    }

    public Value getMy_value() {
        return my_value;
    }

    public void setMy_value(Value my_value) {
        this.my_value = my_value;
    }

    public Unit getMy_measure_unit() {
        return my_measure_unit;
    }

    public void setMy_measure_unit(Unit my_measure_unit) {
        this.my_measure_unit = my_measure_unit;
    }

    public Nominator getMy_nom() {
        return my_nom;
    }

    public void setMy_nom(Nominator my_nom) {
        this.my_nom = my_nom;
    }

    public Denominator getMy_denom() {
        return my_denom;
    }

    public void setMy_denom(Denominator my_denom) {
        this.my_denom = my_denom;
    }

    public Div getMy_is_div() {
        return my_is_div;
    }

    public void setMy_is_div(Div my_is_div) {
        this.my_is_div = my_is_div;
    }

    public Reference_Year getMy_year() {
        return my_year;
    }

    public void setMy_year(Reference_Year my_year) {
        this.my_year = my_year;
    }

    public Coverage getMy_coverage() {
        return my_coverage;
    }

    public void setMy_coverage(Coverage my_coverage) {
        this.my_coverage = my_coverage;
    }







}
