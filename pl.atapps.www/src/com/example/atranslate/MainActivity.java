package com.example.atranslate;

import java.util.concurrent.ExecutionException;

import android.support.v7.app.ActionBarActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements Handler.Callback {

	private Handler handler = new Handler(this);
	
	private EditText e_search;
	private TextView tv_text;
	private Button b_trl;
	private Spinner sp_1;
	private Spinner sp_2;
	
	private String[] src_langs = {"Polish", "Deutsch", "Spanish"};
	private String[] trl_langs = {"English","Deutsch","Spanish"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		e_search = (EditText) findViewById(R.id.edit1);
		
		b_trl = (Button) findViewById(R.id.button2);
		
		tv_text = (TextView) findViewById(R.id.textView2);
		
		sp_1 = (Spinner) findViewById(R.id.spinner1);
		sp_2 = (Spinner) findViewById(R.id.spinner2);
		
		ArrayAdapter<String> sp1_adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,trl_langs);
		ArrayAdapter<String> sp2_adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,src_langs);
		sp_1.setAdapter(sp1_adapter);
		sp_2.setAdapter(sp2_adapter);
		
		
		b_trl.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Translator trl = new Translator();
				String rec;
				try {
					rec = trl.execute(e_search.getText().toString()).get();
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
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean handleMessage(Message msg) {
		
		String odbior = msg.getData().getString("okon");
		tv_text = (TextView) findViewById(R.id.textView2);
		tv_text.setText(odbior);
		
		return false;
	}
}
