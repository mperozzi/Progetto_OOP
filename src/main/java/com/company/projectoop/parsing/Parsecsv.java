package com.company.projectoop.parsing;

import com.company.projectoop.table.Riga_metadata;
import com.company.projectoop.table.Riga_tabella;

import java.util.List;

/**
 * Interfaccia contenente i metodi che verranno implementati in Parsecsv_Impl
 *
 * @author Matteo Perozzi
 * @author Zamponi Ettore
 */

public interface Parsecsv {

    /**
     * Metodo che restituisce la lista contenente i metadati
     * @return
     */
    List<Riga_metadata> getMetadata();

    /**
     * Metodo che restituisce la lista contenente i dati completi
     * @return
     */
    List<Riga_tabella> getData();


}
