package com.example.arek.attranslate_ver10;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;

/**
 * Created by Arek on 2014-12-09.
 */
public class TranslatorAsyncTask extends AsyncTask<String,Void,String> {

    HttpClient httpClient;
    HttpGet httpRequest;
    HttpResponse httpResponse;
    String translatedData;

    public TranslatorAsyncTask() {
        httpClient = new DefaultHttpClient();
        httpRequest = new HttpGet();
    }

    @Override
    protected String doInBackground(String... params) {
        try {

            //pass built request to the parameter
            httpRequest.setURI(new URI(params[0]));

            //get the response on the request
            httpResponse = httpClient.execute(httpRequest);

            //formatting response to appriopriate String object
            BufferedReader input = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            StringBuffer sBuffer = new StringBuffer();

            String read = new String("");
            String space = System.getProperty("line.separator");
            int httpResponseCode = Integer.parseInt(input.readLine());

            if(httpResponseCode == 0)
            {
                while((read = input.readLine()) != null)
                {
                    sBuffer.append(read + space);
                }
            }

            //Load data from buffer
            translatedData = sBuffer.toString();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return translatedData;
    }
}
