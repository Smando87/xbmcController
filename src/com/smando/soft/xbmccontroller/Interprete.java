package com.smando.soft.xbmccontroller;

import java.util.LinkedList;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 * @author Simone Del Prete
 */
public class Interprete {
	
	private Handler handler;
	
	private Messenger messenger;
	
	private Logger log;
	
	


	/**
	 * @param handler
	 * @param log
	 */
	public Interprete(Handler handler, Logger log) {
		
		this.handler = handler;
		this.log = log;
	}


	
	public Interprete(Handler handler) {
		super();
		this.handler = handler;
	}



	public Interprete() {
		
	}

	/**
	 * @param log
	 */
	public Interprete(Logger log) {
		this.log = log;
	}

	

	/**
	 * @param messenger
	 * @param log
	 */
	public Interprete(Messenger messenger, Logger log) {
		
		this.messenger = messenger;
		this.log = log;
	}



	public Interprete(Messenger m) {
		messenger = m;

	}



	public String decode(String cmd) {
		String command = cmd.substring(0, cmd.indexOf(";"));
		if(command.equals("lista_film")){
			Message msg = handler.obtainMessage();
			Bundle b = new Bundle();
			b.putString("lista_film", cmd.substring(cmd.indexOf(";")+1));
			msg.setData(b);
			handler.sendMessage(msg);
		}
		return "";
	}

	

	@SuppressWarnings("unused")
	private String[] getParamArray(String cmd) {
		LinkedList<String> array = new LinkedList<String>();
		while (cmd.contains(";")) {
			array.add(cmd.substring(0, cmd.indexOf(";")));
			cmd = cmd.substring(cmd.indexOf(";") + 1);
		}
		array.add(cmd);

		return array.toArray(new String[0]);
	}
}