package com.company;

//segue importazione delle librerie necessarie
import org.json.simple.*;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import javax.lang.model.type.NullType;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.lang.Object;
import java.util.Scanner;

//classe per il parse del JSON
public class Parsejson {

    public Parsejson () {
    }

    private String url;
    private boolean flag = false;
    private String FolderPath;
    private String urlD;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setFolderPath(String folderPath) {
        FolderPath = folderPath;
    }

    public void Read_json_from_URL() {

        try {
            URLConnection openConnection = new URL(url).openConnection();
            openConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
            InputStream in = openConnection.getInputStream();

            String data = "";
            String line = "";

            try {
                InputStreamReader inR = new InputStreamReader(in);
                BufferedReader buf = new BufferedReader(inR);

                while ((line = buf.readLine()) != null) {
                    data += line;
                    System.out.println(line);
                }
            } finally {
                in.close();
            }

            JSONObject obj = (JSONObject) JSONValue.parseWithException(data);
            JSONObject objI = (JSONObject) (obj.get("result"));
            JSONArray objA = (JSONArray) (objI.get("resources"));

            for (Object o : objA) {
                if (o instanceof JSONObject) {
                    JSONObject o1 = (JSONObject) o;
                    String format = (String) o1.get("format");
                    this.urlD = (String) o1.get("url");
                    System.out.println(format + " | " + urlD);
                    if (format.contains("CSV") || format.contains("csv")) {
                        Download_csv_FromURL(urlD);
                        System.out.println("Print" + urlD);
                    }
                }
            }
            System.out.println("OK");

        } catch (IOException | ParseException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Download_csv_FromURL(String URLText) {
        do {
            try {
                ReadableByteChannel readChannel = Channels.newChannel(new URL(URLText).openStream());
                FileOutputStream fileOS = new FileOutputStream(this.FolderPath);
                FileChannel writeChannel = fileOS.getChannel();
                writeChannel.transferFrom(readChannel, 0, Long.MAX_VALUE);
                System.out.println("File creato");

                if (writeChannel != null) {
                    this.flag = true;
                } else this.flag = false;

            } catch (FileAlreadyExistsException e) {
                e.printStackTrace();
                System.out.println("Il file esiste già! Indicare un altro nome");

            } catch (FileNotFoundException e) {
                System.out.println(e);
                System.out.println("Inserire un corretto percorso del file");
                Scanner input = new Scanner(System.in);
                this.FolderPath = input.nextLine();

            } catch (MalformedURLException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } while (flag == false);
    }
}
