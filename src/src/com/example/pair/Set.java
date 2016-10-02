package com.example.pair;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Set extends Activity {

		Button b1;
		Button b2;
		String value;
		BluetoothSocket socket;
		BluetoothAdapter myBluetoothAdapter;
		LinearLayout ll;
		String val;
	
		@Override
		public void onCreate(Bundle bundle)
		{
			super.onCreate(bundle);
			value = getIntent().getExtras().getString("val1");
			val = getIntent().getExtras().getString("ex");
			setContentView(R.layout.set);

			b1 = (Button)findViewById(R.id.button1);
			b2 = (Button)findViewById(R.id.button2);
			ll = (LinearLayout)findViewById(R.id.ll);
			myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
			ll = (LinearLayout)findViewById(R.id.ll);
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
			b1.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Toast.makeText(getApplicationContext(),"Safety Activated",Toast.LENGTH_SHORT).show();
					BluetoothDevice bd = myBluetoothAdapter.getRemoteDevice(value);

	            try {
	            	Method m = bd.getClass().getMethod("createRfcommSocket", new Class[] {int.class});
	                socket = (BluetoothSocket) m.invoke(bd, 1);
	            	socket.connect();
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            } catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

					finish();
				}
			});
			
			b2.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent in1 = new Intent(getApplicationContext(), Test.class);
	         		in1.putExtra("val",value);
	         		in1.putExtra("val1",val);
	          		startActivity(in1);
				}
			});
			
		}
}
