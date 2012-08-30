package com.smando.soft.xbmccontroller;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ListaFilmArrayAdapter extends ArrayAdapter<ListaFilmItem> {

	private static final String tag = "CountryArrayAdapter";
	private static final String ASSETS_DIR = "images/";
	private Context context;

	private ImageView itemIcon;
	private TextView titolo;
	
	private ProgressBar spinner1;
	private List<ListaFilmItem> menulist = new ArrayList<ListaFilmItem>();

	public ListaFilmArrayAdapter(Context context, int textViewResourceId,
			List<ListaFilmItem> objects) {
		super(context, textViewResourceId, objects);
		this.context = context;
		this.menulist = objects;
	}

	@Override
	public int getCount() {
		return this.menulist.size();
	}

	@Override
	public ListaFilmItem getItem(int index) {
		return this.menulist.get(index);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if (row == null) {
			// ROW INFLATION
			LayoutInflater inflater = (LayoutInflater) this.getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.listafilm_item, parent, false);
		}
		// Get item
		ListaFilmItem item = getItem(position);
		// itemIcon = (ImageView) row.findViewById(R.id.itemmenu_icon);
		titolo = (TextView) row.findViewById(R.id.textViewTitoloFilm);

		return row;
	}

}
