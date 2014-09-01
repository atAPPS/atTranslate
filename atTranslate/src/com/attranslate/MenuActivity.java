package com.attranslate;

import com.attranslate.R;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class MenuActivity extends Activity {

	Button start_btn, end_btn, authors_btn, help_btn; 
	StartActivity app;
		
	public void makeNewActivity(int act){
		Intent main = new Intent(this,MainActivity.class);
		Intent help = new Intent(this,HelpActivity.class);
		Intent authors = new Intent(this,AuthorsActivity.class);
	
		switch(act){
		  case 1:
			  startActivity(main);
		    break;
		  case 2:
			  startActivity(help);
		    break;
		  case 3:
			  startActivity(authors);
		    break;
		  default:
			  Log.d("error", "Brak intencji");
		}	
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		start_btn = (Button)findViewById(R.id.button1);
		authors_btn = (Button)findViewById(R.id.button3);
		help_btn = (Button)findViewById(R.id.button2);
		end_btn = (Button)findViewById(R.id.button4);
		
		start_btn.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v){
				makeNewActivity(1);
			}
			
			
		});
		
		help_btn.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v){
				makeNewActivity(2);
			}
			
			
		});
		
		authors_btn.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v){
				makeNewActivity(3);
			}
			
			
		});
		
		end_btn.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v){
					Toast.makeText(getApplicationContext(), "Koñcze", Toast.LENGTH_SHORT).show();
			}
			
			
		});
		
		
		
	}


}


