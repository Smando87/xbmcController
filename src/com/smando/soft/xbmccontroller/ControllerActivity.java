package com.smando.soft.xbmccontroller;

import java.util.ArrayList;
import java.util.List;
import com.smando.soft.xbmccontroller.R.id;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;


public class ControllerActivity extends Activity {
	/** Called when the activity is first created. */
	Button pausa;
	Button play;
	Button ListaFilm;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.controller);
		
		pausa=(Button)this.findViewById(id.pausa);
        pausa.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Connessione c=new Connessione("esegui;pause");
				c.start();
			}
        	
        });
        play=(Button)this.findViewById(id.play);
        play.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Connessione c=new Connessione("esegui;play");
				c.start();
			}
        	
        });
        ListaFilm=(Button)this.findViewById(id.ListaFilm);
        ListaFilm.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Connessione c=new Connessione("esegui;quit");
				c.start();
				finish();
			}
        	
        });
		
		
	}

	


}