package com.company.projectoop.parsing;

//segue importazione delle librerie necessarie
import org.json.simple.*;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.FileAlreadyExistsException;
import java.lang.Object;

@Component
@Repository
@Service("Parsejson_Impl")

//classe per il parse del JSON
public class Parsejson_Impl implements Parsejson{

    private static String url = "http://data.europa.eu/euodp/data/api/3/action/package_show?id=european-structural-investment-funds-esif-2014-2020-achievements";
    private static String FolderPath = "C:/Users/Matteo/Desktop/prova.csv";
    private static String urlD;

    public static void Read_json_from_URL() {

        try {

            URLConnection openConnection = new URL(url).openConnection();
            openConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
            InputStream in = openConnection.getInputStream();

            String data = "";
            String line;

            try {
                InputStreamReader inR = new InputStreamReader(in);
                BufferedReader buf = new BufferedReader(inR);

                while ((line = buf.readLine()) != null) {
                    data += line;
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
                    urlD = (String) o1.get("url");
                    if (format.contains("CSV")) {
                        Download_csv_FromURL(urlD);
                    }
                }
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void Download_csv_FromURL(String URLText) {
        try {
            ReadableByteChannel readChannel = Channels.newChannel(new URL(URLText).openStream());
            FileOutputStream fileOS = new FileOutputStream(FolderPath);
            FileChannel writeChannel = fileOS.getChannel();
            writeChannel.transferFrom(readChannel, 0, Long.MAX_VALUE);

        } catch (MalformedURLException e) {
            System.out.println("URL errato");

        } catch (FileAlreadyExistsException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
