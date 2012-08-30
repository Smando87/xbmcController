package com.smando.soft.xbmccontroller;

import java.util.ArrayList;
import java.util.List;

import com.smando.soft.xbmccontroller.R.id;



import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;


public class ListaFilmActivity extends Activity {
	/** Called when the activity is first created. */

	ListView lv;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listafilm);
		
		//lv=(ListView)this.findViewById(id.listViewFilm);
		
		String lista=this.getIntent().getStringExtra("lista");
		
		String[] listaFilm=lista.split("\n");
		
		ArrayList<ListaFilmItem> filmlist = new ArrayList<ListaFilmItem>();
		
		for(int i =0; i< listaFilm.length; i++){
			
			filmlist.add(new ListaFilmItem(listaFilm[i]));
			
		}
		
		ListaFilmArrayAdapter adapt = new ListaFilmArrayAdapter(
				getApplicationContext(), R.layout.listafilm_item,
				filmlist);
		lv = (ListView) findViewById(R.id.listViewFilm);
		lv.setAdapter(adapt);
		
		

	}

	


}