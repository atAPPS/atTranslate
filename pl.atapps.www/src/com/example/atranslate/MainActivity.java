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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements Handler.Callback {

	private Handler handler = new Handler(this);
	
	EditText e_search;
	Button b_thread;
	TextView tv_text;
	Button b_AT;
	
	Runnable thr = new Runnable () {

		@Override
		public void run() {
			try {
				
				Bundle bun = new Bundle();
				bun.putString("okon", "Poszlo zajebiscie");
				
				Message mes = new Message();
				mes.setData(bun);
				handler.sendMessage(mes);
				
				Thread.sleep(5000);
				Log.i("Wys", "Poszlo z osobnego watku");
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		e_search = (EditText) findViewById(R.id.edit1);
		b_thread = (Button) findViewById(R.id.button1);
		
		OnClickListener l_listen = new OnClickListener () {

			@Override
			public void onClick(View v) {
			
				Toast.makeText(getApplicationContext(), "Wcisniety", Toast.LENGTH_LONG).show();	
			}
			
		};
		
		e_search.setOnClickListener(l_listen);
		
		b_thread.setOnClickListener(new OnClickListener (){

			@Override
			public void onClick(View v) {
				
				new Thread(thr).start();
			}
			
		});
		
		b_AT = (Button) findViewById(R.id.button2);
		tv_text = (TextView) findViewById(R.id.textView2);
		b_AT.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				async AT1 = new async();
				String rec;
				try {
					rec = AT1.execute().get();
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
