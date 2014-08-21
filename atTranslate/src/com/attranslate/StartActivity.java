package com.attranslate;

import java.util.Timer;
import java.util.TimerTask;

import com.attranslate.R;


import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;



public class StartActivity extends ActionBarActivity {

	Timer timer;
	MyTimerTask myTimerTask;
	
	
	public void makeNewActivity(){
		Intent i = new Intent(this,MenuActivity.class);
		startActivity(i);
		onStop();
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    	
    	if (timer != null){
    		timer.cancel();
    	}
    	
    	timer = new Timer();
    	myTimerTask = new MyTimerTask();
    	
    	timer.schedule(myTimerTask, 4000);
    	
    }
    
    public class MyTimerTask extends TimerTask{


    	@Override
    	public void run() {
    		makeNewActivity();
    	}

    }
    
}
