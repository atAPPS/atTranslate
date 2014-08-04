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

public class async extends AsyncTask<Void, Void, String>{

	String data = new String();
	@Override
	protected String doInBackground(Void... params) {
		try {
			Log.i("ff","W¹tek z AsyncTask");
			Log.i("ff","Po zmianie");
			HttpClient httpclient = new DefaultHttpClient();
			URI website = new URI("http://httpbin.org/ip");
			HttpGet request = new HttpGet();
			request.setURI(website);
			HttpResponse response = httpclient.execute(request);
			
			BufferedReader input = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer sBufor = new StringBuffer();
			
			String przeczytane = new String("");
			String przerwa = System.getProperty("line.separator");
			while((przeczytane = input.readLine()) != null)
			{
				sBufor.append(przeczytane + przerwa);
			}
			data = sBufor.toString();
			
			Log.i("okon",response.getStatusLine().toString());
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

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}
	
}