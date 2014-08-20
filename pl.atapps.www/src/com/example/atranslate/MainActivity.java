package com.example.atranslate;

import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity{

	private EditText e_search;
	private TextView tv_text;
	private Button b_trl;
	private Spinner sp_1;
	private Spinner sp_2;
	private ListView lv1;
	
	public TransData t_data = new TransData();
	
	private String[] words = {"word1","word2","word3"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		e_search = (EditText) findViewById(R.id.edit1);
		
		b_trl = (Button) findViewById(R.id.button2);
		
		tv_text = (TextView) findViewById(R.id.textView2);
		
		sp_1 = (Spinner) findViewById(R.id.spinner1);
		sp_2 = (Spinner) findViewById(R.id.spinner2);
		
		lv1 = (ListView) findViewById(R.id.listview1);
		
		ArrayAdapter<String> lv1_adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, words);
		lv1.setAdapter(lv1_adapter);
		
		ArrayAdapter<String> sp1_adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,t_data.languages);
		ArrayAdapter<String> sp2_adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,t_data.languages);
		
		sp_1.setAdapter(sp1_adapter);
		sp_2.setAdapter(sp2_adapter);
		
		// first listener with choosing source language
		
		sp_1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				position = sp_1.getSelectedItemPosition();
				t_data.SetSourceLanguage(position);
				Log.i("ff",t_data.getSourceLanguage());
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//second listener with translate language choice
		
		sp_2.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				position = sp_2.getSelectedItemPosition();
				t_data.SetTransLanguage(position);
				Log.i("ff",t_data.getTransLanguage());
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		b_trl.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				String rec;
				try {
					Translator trl = new Translator();
					t_data.SetData(e_search.getText().toString());
					rec = trl.execute(t_data.GetRequest()).get();
					tv_text.setText(rec);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			}
			
		});
			
	}
	
}
