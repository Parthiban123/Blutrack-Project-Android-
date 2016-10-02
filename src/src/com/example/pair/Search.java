package com.example.pair;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

public class Search extends Activity {
	LinearLayout lt;
	PowerManager p;
	WakeLock w;
	MediaPlayer mp;
	Button bt;
	String value;
	String val;
	@SuppressWarnings("deprecation")
	@SuppressLint("ServiceCast") @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,        
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	
		value = getIntent().getExtras().getString("val");
		val = getIntent().getExtras().getString("val1");

		Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
		
		// Vibrate for 500 milliseconds
		v.vibrate(500);
		v.vibrate(500);
		mp = MediaPlayer.create(this, R.raw.red);
		
		
		setContentView(R.layout.search);
		lt = (LinearLayout) findViewById(R.id.layout);
		bt = (Button)findViewById(R.id.track);
		lt.setBackgroundColor(Color.RED);
		p = (PowerManager) getSystemService(Context.POWER_SERVICE);
		w = p.newWakeLock((PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP),"TAG");
		w.acquire();
		if(!mp.isPlaying())
		{
		mp.start();
		mp.setLooping(true);
		}


        //Create the timer object which will run the desired operation on a schedule or at a given time
        Timer timer = new Timer();

        MyTimer mt = new MyTimer();

        timer.schedule(mt, 20, 20);
        
        bt.setOnClickListener(new View.OnClickListener() {
       	 
            @Override
            public void onClick(View v) {
            	
            	mp.stop();
            	Intent in1 = new Intent(getApplicationContext(), Test.class);
         		in1.putExtra("val",value);
         		in1.putExtra("val1",val);
          		startActivity(in1);
            	
            }
});
    }
	
	

    class MyTimer extends TimerTask {

        public void run() {                       

            runOnUiThread(new Runnable() {

                public void run() {
                    Random rand = new Random();
                    lt.setBackgroundColor(Color.argb(255, rand.nextInt(256), rand.nextInt(256), rand.nextInt(256) ));
                }
            });
        }

    }
    public void stop(View view)
    {
    	mp.stop();
    	this.finish();
    }
   @Override
    public void onDestroy()
    {
	   	super.onDestroy();
	   	if(mp.isPlaying())
	   		mp.stop();
	   	mp.release();
	   	
    }
}