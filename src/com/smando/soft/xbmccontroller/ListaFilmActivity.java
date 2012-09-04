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
import android.widget.ListView;


public class ListaFilmActivity extends Activity {
	/** Called when the activity is first created. */

	ListView lv;
	String[] listaFilm;
	Intent controller;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listafilm);
		controller=new Intent(this,ControllerActivity.class);
		//lv=(ListView)this.findViewById(id.listViewFilm);
		
		String lista=this.getIntent().getStringExtra("lista");
		
		listaFilm=lista.split("\n");
		
		ArrayList<ListaFilmItem> filmlist = new ArrayList<ListaFilmItem>();
		
		for(int i =0; i< listaFilm.length; i++){
			
			filmlist.add(new ListaFilmItem(listaFilm[i]));
			
		}
		
		ListaFilmArrayAdapter adapt = new ListaFilmArrayAdapter(
				getApplicationContext(), R.layout.listafilm_item,
				filmlist);
		lv = (ListView) findViewById(R.id.listViewFilm);
		lv.setAdapter(adapt);
		lv.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				//ListaFilmItem item=(ListaFilmItem) lv.getSelectedItem();
				String titolo=listaFilm[arg2];
				Connessione c=new Connessione("InviaFilm;"+titolo);
				c.start();
				startActivity(controller);
			}			
		});
		

	}

	


}