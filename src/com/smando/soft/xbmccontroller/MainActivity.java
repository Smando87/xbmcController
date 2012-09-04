package com.smando.soft.xbmccontroller;


import com.smando.soft.xbmccontroller.R.id;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity {

	Button close;
	//Button Pausa;
	ProgressBar ProgressBarIP;
	ProgressBar ProgressBarLista;
	Button film;
	Button start;
	TextView ip;
	Logger log;
	Intent listaFilm;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        log=new Logger("/sdcard/","DiscoveryLog",this.getApplicationContext(),true);
        ServerDiscover sdiscover=new ServerDiscover(log,new MyHandler());
        sdiscover.start();
        ProgressBarIP=(ProgressBar)this.findViewById(id.progressBarIP);
        ProgressBarLista=(ProgressBar)this.findViewById(id.progressBarLista);
        ProgressBarLista.setVisibility(View.GONE);
        
        listaFilm=new Intent(this,ListaFilmActivity.class);
         
        ip=(TextView)this.findViewById(id.editTextIP);
   
        film=(Button)this.findViewById(id.film);
        film.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				ProgressBarLista.setVisibility(View.VISIBLE);
				EV.IP=(String) ip.getText().toString();
				Connessione c=new Connessione("lista_film;",new MyHandler());
				c.start();
			}
        	
        });
       /* Pausa=(Button)this.findViewById(id.Pausa);
        Pausa.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Connessione c=new Connessione("esegui;pause");
				c.start();
			}
        	
        });*/

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
    			listaFilm.putExtra("lista", bundle.getString("lista_film"));
    			ProgressBarLista.setVisibility(View.GONE);
    			startActivity(listaFilm);
    		}
    		
    		if (bundle.containsKey("ipserver")) {
    			String IPcorretto=bundle.getString("ipserver");
    			IPcorretto= IPcorretto.substring(1,IPcorretto.indexOf(":"));
    			ip.setText(IPcorretto);
    			ProgressBarIP.setVisibility(View.GONE);
    		
    		}
    		
    		
    	}
    }
}
