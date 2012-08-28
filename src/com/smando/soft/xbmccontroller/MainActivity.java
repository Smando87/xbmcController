package com.smando.soft.xbmccontroller;


import com.smando.soft.xbmccontroller.R.id;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity {

	Button close;
	Button film;
	Button start;
	TextView editText;
	TextView ip;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        editText=(TextView)this.findViewById(id.editText);
        ip=(TextView)this.findViewById(id.editTextIP);
        ip.setText("192.168.1.5");
        start=(Button)this.findViewById(id.buttonStart) ;
        start.setOnClickListener(new OnClickListener(){

			public void onClick(View arg0){
				EV.IP=(String) ip.getText().toString();
				Connessione c=new Connessione("esegui;vlc");
				c.start();
				
			}

			});
        close=(Button)this.findViewById(id.Chiudi);
        close.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				EV.IP=(String) ip.getText().toString();
				Connessione c=new Connessione("esegui;killall vlc");
				c.start();
			}
        	
        });
        film=(Button)this.findViewById(id.film);
        film.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				EV.IP=(String) ip.getText().toString();
				Connessione c=new Connessione("lista_film;",new MyHandler());
				c.start();
			}
        	
        });

    }

    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    private class MyHandler extends Handler {
    	@Override
    	public void handleMessage(Message msg) {
    		Bundle bundle = msg.getData();

    		if (bundle.containsKey("lista_film")) {
    			editText.setText(bundle.getString("lista_film"));
    		}
    	}
    }
}
