package com.influx.model;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Prabha on 13-12-2017.
 */

public class SimpleConfig {

    //URL Path
    public static String url_nowplaying_path="https://api.themoviedb.org/3/movie/now_playing?api_key=82c60abafaca46136c921c8885227236&language=en-US&page=1";
    public static String url_upcoming_path="https://api.themoviedb.org/3/movie/upcoming?api_key=82c60abafaca46136c921c8885227236&language=en-US&page=1";
    public static String url_toprated_path="https://api.themoviedb.org/3/movie/top_rated?api_key=82c60abafaca46136c921c8885227236&language=en-US&page=1";




    public static String getPostUrl(String url){
        InputStream inputStream = null;
        String result = "";
        try {

            HttpClient httpclient = new DefaultHttpClient();

            HttpPost httpPost = new HttpPost(url);

            String json = "";

            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("cinemaid", "0007");
            jsonObject.accumulate("countryid", "1");


            json = jsonObject.toString();


            StringEntity se = new StringEntity(json);

            //  set httpPost Entity
            httpPost.setEntity(se);

            // Set some headers to inform server about the type of the content
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            HttpResponse httpResponse = httpclient.execute(httpPost);

            inputStream = httpResponse.getEntity().getContent();

            if(inputStream != null) {
                result = convertInputStreamToString(inputStream);
            }


        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }


        return result;
    }


    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

}
