package com.company;

import java.util.*;

public class Tabella {

    private List<Riga_tabella> l;

    public Tabella() {
        l = new ArrayList<>();
    }

    public List<Riga_tabella> getL() {
        return l;
    }

    public void setL(List<Riga_tabella> l) {
        this.l = l;
    }

    //Restituisce la dimensione della lista (numero di righe della tabella)
    public int return_dim(){
        return this.l.size();
    }


}
