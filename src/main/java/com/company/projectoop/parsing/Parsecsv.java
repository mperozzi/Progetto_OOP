package com.company.projectoop.parsing;

import com.company.projectoop.table.Riga_metadata;
import com.company.projectoop.table.Riga_tabella;

import java.util.ArrayList;
import java.util.List;

/**
 * Interfaccia contenente i metodi che verranno implementati in Parsecsv_Impl
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

    /**
     * Metodo che implementa i filtri logici e restituisce la lista delle occorrenze filtrate
     * @param fieldName1
     * @param value1
     * @param operator
     * @param fieldName2
     * @param value2
     * @return
     * @throws Exception
     */
    ArrayList<Riga_tabella> Logical_filter(String fieldName1, String value1, String operator, String fieldName2, String value2) throws Exception;

}
