package com.company;

import java.io.*;

public class Parsecsv {

    public PrintWriter pw;
    private String namefile;
    private Tabella tab;

    public Parsecsv(String namefile) {
        this.namefile = namefile;
        pw = new PrintWriter(System.out, true);
    }

    public void Read_File () {

        BufferedReader reader = null;

        if (namefile.length() < 1) {
            pw.println("Specificare correttamente un nome di un file");
            return;
        }

        //apro il file e leggo liea per linea --> Fino alla fine del file
        try {
            reader = new BufferedReader(new FileReader(namefile));
            String line = null;
            int i=1;
            do {
                line = reader.readLine();
                pw.println(line);
                Parse_file(line);

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

    //leggo il file riga per riga e separo le stringhe in base al separatore di campo (,)
    private void Parse_file(String line) {
        try {
            String[] s = line.split(",");
            for (int i = 0; i < s.length; i++) {
                if (s[i] == null) break;
                else pw.println(s[i]);
            }
        }

        catch (NullPointerException e) {
            pw.println("Fine file");
        }
    }
}

