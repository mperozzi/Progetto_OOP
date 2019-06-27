package com.company;

//segue importazione delle librerie necessarie

import netscape.javascript.JSObject;

import org.json.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import javax.lang.model.type.NullType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

//classe per il parse del JSON
public class Parsejson {

    public Parsejson () {
    }

    String url = "http://data.europa.eu/euodp/data/api/3/action/package_show?id=european-structural-investment-funds-esif-2014-2020-achievements";

    public void Read_json_from_URL() {
		try {
        URLConnection openConnection = new URL(url).openConnection();
        openConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
        InputStream in = openConnection.getInputStream();

        String data = "";
        String line = "";
        try {
            InputStreamReader inR = new InputStreamReader( in );
            BufferedReader buf = new BufferedReader( inR );

            while ( ( line = buf.readLine() ) != null ) {
                data+= line;
                System.out.println( line );
            }
        } finally {
            in.close();
        }
        JSONObject obj = new JSONObject(url);
        //JSONObject obj = (JSONObject) JSONValue.parseWithException(data);
        JSONObject objI = (JSONObject) (obj.get("result"));
        JSONArray objA = (JSONArray) (objI.get("resources"));

        System.out.println( "OK" );

    } catch (IOException e) {
        e.printStackTrace();

    } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void download(String url, String fileName) throws Exception {
        try (InputStream in = URI.create(url).toURL().openStream()) {
            Files.copy(in, Paths.get(fileName));
        }
    }

}
