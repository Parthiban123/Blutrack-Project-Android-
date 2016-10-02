package com.example.pair;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Test extends Activity {
	
	TextView t1;
	TextView t2;
	TextView t3;
	TextView t4;
	TextView t5;
	TextView t6;
	String value;
	String val;
	LinearLayout ll;
	BluetoothAdapter myBluetoothAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,        
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		value = getIntent().getExtras().getString("val");
		val = getIntent().getExtras().getString("val1");

		setContentView(R.layout.test);
		
		myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        
		t4 = (TextView)findViewById(R.id.t4);
        t1 = (TextView)findViewById(R.id.t1);
        t2 = (TextView)findViewById(R.id.t2);
        t3 = (TextView)findViewById(R.id.t3);
        t5 = (TextView)findViewById(R.id.t5);
        t6 = (TextView)findViewById(R.id.t6);
        ll = (LinearLayout)findViewById(R.id.ll1);
        
        if(val.equalsIgnoreCase("laptop\n"+value))
		{
			ll.setBackgroundResource(R.drawable.laptop);
		}
		else if(val.equalsIgnoreCase("child\n"+value))
		{
			ll.setBackgroundResource(R.drawable.baby);
		}
		else if(val.equalsIgnoreCase("luggage\n"+value))
		{
			ll.setBackgroundResource(R.drawable.lug);
		}
		else if(val.equalsIgnoreCase("car key\n"+value))
		{
			ll.setBackgroundResource(R.drawable.key);
		}
		else
		{
			ll.setBackgroundResource(R.drawable.tile);
		}
        IntentFilter filter1 = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        IntentFilter filter2 = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        this.registerReceiver(myBluetoothReceiver, filter1);
        this.registerReceiver(myBluetoothReceiver, filter2);
        myBluetoothAdapter.cancelDiscovery();
        if(!myBluetoothAdapter.isDiscovering())
        {
        	myBluetoothAdapter.startDiscovery();
        }
        
	}

private final BroadcastReceiver myBluetoothReceiver = new BroadcastReceiver()
{
	@Override
	public void onReceive(Context context,Intent intent)
	{
		String action = intent.getAction();
		if(BluetoothDevice.ACTION_FOUND.equals(action))
		{
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            String add = (String)device.getAddress();
            if(add.equals(value))
            {
                short RSSI = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI,Short.MIN_VALUE);
                byte rs = (byte)RSSI;
                if(rs>-60)
                {
                	t4.setBackgroundColor((Integer)Color.parseColor("#00ff00"));
                	t1.setBackgroundColor((Integer)Color.parseColor("#00ff00"));
                	t2.setBackgroundColor((Integer)Color.parseColor("#00ff00"));
                	t3.setBackgroundColor((Integer)Color.parseColor("#00ff00"));
                	t5.setBackgroundColor((Integer)Color.parseColor("#00ff00"));
                	t6.setBackgroundColor((Integer)Color.parseColor("#00ff00"));

                }
                else if(RSSI>-65&&RSSI<-59)
                {
                	t1.setBackgroundColor((Integer)Color.parseColor("#ff0000"));
                	t2.setBackgroundColor((Integer)Color.parseColor("#00ff00"));
                	t3.setBackgroundColor((Integer)Color.parseColor("#00ff00"));
                	t4.setBackgroundColor((Integer)Color.parseColor("#00ff00"));
                	t5.setBackgroundColor((Integer)Color.parseColor("#00ff00"));
                	t6.setBackgroundColor((Integer)Color.parseColor("#00ff00"));

  
                }
                else if(RSSI>-72&&RSSI<-64)
                {
                	t1.setBackgroundColor((Integer)Color.parseColor("#ff0000"));
                	t2.setBackgroundColor((Integer)Color.parseColor("#ff0000"));
                	t3.setBackgroundColor((Integer)Color.parseColor("#00ff00"));
                	t4.setBackgroundColor((Integer)Color.parseColor("#00ff00"));
                	t5.setBackgroundColor((Integer)Color.parseColor("#00ff00"));
                	t6.setBackgroundColor((Integer)Color.parseColor("#00ff00"));

  
                }

                else if(RSSI>-80&&RSSI<-71)
                {
                	t1.setBackgroundColor((Integer)Color.parseColor("#ff0000"));
                	t2.setBackgroundColor((Integer)Color.parseColor("#ff0000"));
                	t3.setBackgroundColor((Integer)Color.parseColor("#ff0000"));
                	t4.setBackgroundColor((Integer)Color.parseColor("#00ff00"));
                	t5.setBackgroundColor((Integer)Color.parseColor("#00ff00"));
                	t6.setBackgroundColor((Integer)Color.parseColor("#00ff00"));
	
                }
                else if(RSSI>-88&&RSSI<-79)
                {
                	t1.setBackgroundColor((Integer)Color.parseColor("#ff0000"));
                	t2.setBackgroundColor((Integer)Color.parseColor("#ff0000"));
                	t3.setBackgroundColor((Integer)Color.parseColor("#ff0000"));
                	t4.setBackgroundColor((Integer)Color.parseColor("#ff0000"));
                	t5.setBackgroundColor((Integer)Color.parseColor("#00ff00"));
                	t6.setBackgroundColor((Integer)Color.parseColor("#00ff00"));
	
                }
                else if(RSSI>-100&&RSSI<-87)
                {
                	t1.setBackgroundColor((Integer)Color.parseColor("#ff0000"));
                	t2.setBackgroundColor((Integer)Color.parseColor("#ff0000"));
                	t3.setBackgroundColor((Integer)Color.parseColor("#ff0000"));
                	t4.setBackgroundColor((Integer)Color.parseColor("#ff0000"));
                	t5.setBackgroundColor((Integer)Color.parseColor("#ff0000"));
                	t6.setBackgroundColor((Integer)Color.parseColor("#00ff00"));

  
                }

                else
                {
                	t1.setBackgroundColor((Integer)Color.parseColor("#ff0000"));
                	t2.setBackgroundColor((Integer)Color.parseColor("#ff0000"));
                	t3.setBackgroundColor((Integer)Color.parseColor("#ff0000"));
                	t4.setBackgroundColor((Integer)Color.parseColor("#ff0000"));
                	t5.setBackgroundColor((Integer)Color.parseColor("#ff0000"));
                	t6.setBackgroundColor((Integer)Color.parseColor("#ff0000"));

                }
                myBluetoothAdapter.cancelDiscovery();
                

            }
		}
		if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action))
		{
			myBluetoothAdapter.startDiscovery();
		}

	}
};

@Override
public void onDestroy()
{
	myBluetoothAdapter.cancelDiscovery();
	Intent in1 = new Intent(getApplicationContext(), MainActivity.class);
	startActivity(in1);
	super.onDestroy();
}
}