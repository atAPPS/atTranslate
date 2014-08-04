package com.example.atranslate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;

public class Translator extends AsyncTask<String, Void, String> {

	private String data = new String("");
	private String check = new String();
	String lang_src = new String("lwa_pl");
	
	@Override
	protected String doInBackground(String... params) {
		try {
			HttpClient httpclient = new DefaultHttpClient();

			String web = new String("http://www.worldlingo.com/S000.1/api?wl_data=" + params[0] + "&wl_srclang=" + lang_src + "&wl_trglang=de&wl_password=secret");
			
			URI website = new URI(web);
			
			HttpGet request = new HttpGet();
			request.setURI(website);
			HttpResponse response = httpclient.execute(request);
			
			BufferedReader input = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer sBuffer = new StringBuffer();
			
			String read = new String("");
			String space = System.getProperty("line.separator");
			check = input.readLine();
			int error = Integer.parseInt(check);
			
			//Check if request is successful
			if(error == 0) 
			{
				while((read = input.readLine()) != null)
				{
						sBuffer.append(read + space);
				}
			}
			
			//Load data from buffer
			data = sBuffer.toString();
		}
	 catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

}
