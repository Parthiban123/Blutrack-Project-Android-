package com.example.pair;

import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

	ToggleButton tb;
	private Button bluetoothScan;
    private BluetoothAdapter myBluetoothAdapter;
    private ArrayAdapter<String> btArrayAdapter;
    private ListView listDevicesFound;
    ProgressDialog pd;
    TextToSpeech ttobj;
    String na;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(btArrayAdapter!=null)
        {
        	btArrayAdapter.clear();
        }
        tb = (ToggleButton) findViewById(R.id.toggleButton1);
        pd = new ProgressDialog(MainActivity.this);
        bluetoothScan= (Button)findViewById(R.id.btnScan);
        myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        listDevicesFound = (ListView)findViewById(R.id.devicesfound);
        btArrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1);        
        listDevicesFound.setAdapter(btArrayAdapter);
        if(!myBluetoothAdapter.isEnabled())
        {
        	myBluetoothAdapter.enable();
        }
        
        Intent i =new Intent(getApplicationContext(),MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        
        IntentFilter filter1 = new IntentFilter(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        this.registerReceiver(myBluetoothReceiver, filter1);
        IntentFilter filter2 = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        this.registerReceiver(myBluetoothReceiver, filter2);
        registerReceiver(myBluetoothReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));
        
        
        ttobj=new TextToSpeech(getApplicationContext(), 
        	      new TextToSpeech.OnInitListener() {
        	      @Override
        	      public void onInit(int status) {
        	         if(status != TextToSpeech.ERROR){
        	             ttobj.setLanguage(Locale.UK);
        	             ttobj.setPitch((float)10);
        	             ttobj.setSpeechRate((float)0.5);
        	                     	            }				
        	         }
        	      });
        tb.setOnClickListener(new View.OnClickListener() {
        	 
            @Override
            public void onClick(View v) {
            	
            	if(tb.isChecked()){
                // TODO Auto-generated method stub
                if (!myBluetoothAdapter.isEnabled()) {
                    Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(turnOn, 0);
                    Toast.makeText(getApplicationContext(),"Turned on"
                            ,Toast.LENGTH_LONG).show();
                }
                startService(new Intent(getApplicationContext(),MainActivity.class));
            
            }else{
            	myBluetoothAdapter.disable();
                Toast.makeText(getApplicationContext(),"Turned off" ,
                        Toast.LENGTH_LONG).show();
                stopService(new Intent(getApplicationContext(),MainActivity.class));

            }
            }
        });
        
        bluetoothScan.setOnClickListener(new View.OnClickListener() {
        	 
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                btArrayAdapter.clear();
                pd = ProgressDialog.show(MainActivity.this, "Scanning", "Scanning For Your Device",true);
                myBluetoothAdapter.startDiscovery();
            }
});
        

    
        	
        
        listDevicesFound.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                    long arg3) {
                // TODO Auto-generated method stub          

                myBluetoothAdapter.cancelDiscovery();
                String info = ((TextView) arg1).getText().toString();
                String add = info.substring(info.length()-17);
                na = (String)listDevicesFound.getItemAtPosition(arg2);
				Toast.makeText(getApplicationContext(),na,Toast.LENGTH_SHORT).show();

                Intent in1 = new Intent(getApplicationContext(), Set.class);
          		in1.putExtra("val1",add);
          		in1.putExtra("ex", na);
           		MainActivity.this.startActivity(in1);

               
            }
        });        
    }
    
    
    
    
    
    
    private final BroadcastReceiver myBluetoothReceiver = new BroadcastReceiver(){
    	 
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            String action = intent.getAction();
            if(BluetoothDevice.ACTION_FOUND.equals(action)) {
            	BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                
                btArrayAdapter.add((String)device.getName()+"\n"+device.getAddress());
                btArrayAdapter.notifyDataSetChanged();                
            }
            if(BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action))
            {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String toSpeak = "You are missing '"+(String)device.getName()+"'";
                Toast.makeText(getApplicationContext(), toSpeak, 
       	             Toast.LENGTH_SHORT).show();
                	ttobj.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                  	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
             
            			alertDialogBuilder.setTitle(toSpeak);
             
            			alertDialogBuilder
            				.setMessage(toSpeak)
            				.setCancelable(false)
            				.setPositiveButton("THANK YOU",new DialogInterface.OnClickListener() {
            					public void onClick(DialogInterface dialog,int id) {
            					dialog.cancel();
            					}
            				  });
            				           
            				AlertDialog alertDialog = alertDialogBuilder.create();
             
            				alertDialog.show();
                
           	
           		 Intent in = new Intent(context, Search.class);
           		 in.putExtra("val",(String)device.getAddress());
           		 in.putExtra("val1",na);
           		 
            		 startActivity(in);
            		 
				
				

            }
            if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action))
            {
            	pd.dismiss();
            }
            
        }
        };
             


        
    

   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
   
   @Override
   public void onDestroy()
   {
	   
	   super.onDestroy();
   }
    
}
