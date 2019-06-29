package com.company;

public class Main {

    public static void main(String[] args) {
        Parsejson pj = new Parsejson();
        pj.setUrl("http://data.europa.eu/euodp/data/api/3/action/package_show?id=european-structural-investment-funds-esif-2014-2020-achievements");
        pj.setFolderPath("C:/Users/Matteo/Desktop/prova.csv");
        pj.Read_json_from_URL();
    }
}
