package com.company;

import java.io.*;

public class Parsecsv {

    private PrintWriter pw;

    public Parsecsv() {
        pw = new PrintWriter(System.out, true);
    }

    public void Read_File (String namefile) {

        BufferedReader reader = null;

        if (namefile.length() < 1) {
            pw.println("Specificare correttamente un nome di un file");
            return;
        }

        //apro il file e leggo liea per linea --> Fino alla fine del file
        try {
            reader = new BufferedReader(new FileReader(namefile));
            String line = null;
            do {
                line = reader.readLine();
                pw.println(line);

            } while(line != null);

            pw.println("Fine lettura file");

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        finally {
            try {
                if (reader != null) reader.close();
            } catch (IOException e) {
                pw.println("Errore di chiusura");
            }
        }
    }
}

