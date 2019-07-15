package com.company.projectoop.table;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Classe che implementa la struttura base per andare ad immagazzinare i metadati
 * @author Matteo Perozzi
 * @author Ettore Zamponi
 */

@Component
@Repository
public class Riga_metadata {

    /**
     * Costruttore della classe
     */
    public Riga_metadata() {
    }

    //nome che identifica il campo della tabella del dataset
    private String alias;
    //variabile che identifica il tipo di dato
    private String type;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
