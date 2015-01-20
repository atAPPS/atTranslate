package com.attranslate;

import android.util.Log;

public class TransData {
	
	private String data="";
	private String src_lang="lwa_pl"; 
	private String trans_lang="en";

	
	private String request = new String();
	
	//JÊZYKI I W ODPOWIEDNIEJ KOLEJNOŒCI I SKRÓTY
	
	public String languages[] = {"German","English","Spanish","French","Italian","Japanese","Portuguese","Russian","Polish"};
	private String shortcuts[] = {"de","en","es","fr","it","ja","pt","ru","lwa_pl"};
	
	public void SetRequest()
	{
		request = BuildRequest();
	}
	
	public String GetRequest()
	{
		SetRequest();
		return request;
	}
	
	public String BuildRequest() // Budowa ca³ego adresu i zwrot pe³nego http://
	{
		String http = new String("http://www.worldlingo.com/S000.1/api?wl_data=" + data);
		http = http + "&wl_srclang=" + src_lang;
		http = http + "&wl_trglang=" + trans_lang;
		http = http + "&wl_password=secret";
		Log.i("ff",src_lang);
		Log.i("ff",trans_lang);
		return http;
	}
	
	public void SetSourceLanguage (int position)
	{
		switch (position)
		{
		case 0: src_lang = shortcuts[0]; break;
		case 1: src_lang = shortcuts[1]; break;
		case 2: src_lang = shortcuts[2]; break;
		case 3: src_lang = shortcuts[3]; break;
		case 4: src_lang = shortcuts[4]; break;
		case 5: src_lang = shortcuts[5]; break;
		case 6: src_lang = shortcuts[6]; break;
		case 7: src_lang = shortcuts[7]; break;
		case 8: src_lang = shortcuts[8]; break;
		}
	}
	
	public String getSourceLanguage()
	{
		return src_lang;
	}
	
	public void SetTransLanguage (int position)
	{
		switch (position)
		{
		case 0: trans_lang = shortcuts[0]; break;
		case 1: trans_lang = shortcuts[1]; break;
		case 2: trans_lang = shortcuts[2]; break;
		case 3: trans_lang = shortcuts[3]; break;
		case 4: trans_lang = shortcuts[4]; break;
		case 5: trans_lang = shortcuts[5]; break;
		case 6: trans_lang = shortcuts[6]; break;
		case 7: trans_lang = shortcuts[7]; break;
		case 8: trans_lang = shortcuts[8]; break;
		}
	}
	
	public String getTransLanguage()
	{
		return trans_lang;
	}
	
	public void SetData (String data)
	{
		this.data=data.replace(" ", "+");
	}
	
	public String GetData()
	{
		return data;
	}
}

