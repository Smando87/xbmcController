package com.smando.soft.xbmccontroller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


import android.content.Intent;
import android.os.Handler;
import android.os.Messenger;
import android.util.Log;

public class Connessione extends Thread {
	
	private Interprete interprete;
	private Socket sock;
	private DataInputStream in;
	private DataOutputStream out;
	
	
	private String autenticazione;	
	private Logger log;
	private Messenger messenger;
	private String comando;
	private Handler parentHandler;


	
	
	
	/**
	 * @param autenticazione
	 * @param log
	 * @param comando
	 * @param parentHandler
	 */
	public Connessione(String autenticazione, String comando, Logger log, 
			Handler parentHandler) {
		super();
		
		interprete = new Interprete(parentHandler,log);
		this.autenticazione = autenticazione;
		this.log = log;
		this.comando = comando;
		this.parentHandler = parentHandler;
	}

	public Connessione(String autenticazione, String comando, Logger log) {
		interprete = new Interprete(log);
		this.comando = comando;
		this.autenticazione = autenticazione;
		this.log=log;

	}

	public Connessione(String comando) {
		interprete = new Interprete();
		this.comando = comando;
		this.autenticazione = "";
		this.log=log;

	}
	
	public Connessione(String comando, Handler parentHandler) {
		interprete = new Interprete(parentHandler);
		this.comando = comando;
		this.autenticazione = "";
		this.parentHandler = parentHandler;
	}

	public Connessione(String autenticazione, String comando, Messenger m) {
		interprete = new Interprete(m);
		this.comando = comando;
		this.autenticazione = autenticazione;
		messenger = m;

	}

	
	/*public void setHandler(Handler hand) {
		interprete.setHandler(hand);

	}*/


	@Override
	public void run() {
		this.setName("Connessione"+this.getId());
		esegui();
	}

	public void eseguiNoThread() {

		esegui();
	}

	private void esegui() {
		connetti();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		send(autenticazione + comando);
		leggi();

	}

	private void connetti() {
		sock = null;
		try {
			sock = new Socket(EV.IP, 9021);

			if (sock.isConnected()) {
				in = new DataInputStream(sock.getInputStream());
				out = new DataOutputStream(sock.getOutputStream());
				

			}
		} catch (IOException e) {
			log.debugStackTraceToScreen(e);
			e.printStackTrace();
		}

	}

	private void leggi() {
		String read = "";

		String result;
		try {
			read = in.readUTF();			
			//log.debug( "Il server dice: " + read, this.getClass(), Logger.INFO);
			result = interprete.decode(read);
			if (result.length() > 0)
				out.writeUTF(result);
			sock.close();
		} catch (Exception e) {
			//log.debugStackTraceToScreen(e);
			e.printStackTrace();
		}

	}

	private boolean send(String cmd) {
		boolean result = false;
		Log.i(EV.LOGTAG, "Invio " + cmd + " ");
		try {
			connetti();
			out.writeUTF(cmd);
			result = true;
		} catch (IOException e) {
			log.debugStackTraceToScreen(e);
			e.printStackTrace();
			Log.e(EV.LOGTAG, "Invio " + cmd + " " + e.getLocalizedMessage());
			return result;
		}
		return result;
	}

}
