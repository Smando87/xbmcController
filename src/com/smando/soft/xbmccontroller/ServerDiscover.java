package com.smando.soft.xbmccontroller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * 
 * @author sdelprete
 */
public class ServerDiscover extends Thread {
	// String serverIp;
	Logger log;
	Handler handler;
	private DataInputStream in;
	private DataOutputStream out;
	boolean trovato;

	/**
	 * @param log
	 * @param handler
	 */
	public ServerDiscover(Logger log, Handler handler) {
		this.log = log;
		this.handler = handler;
	}

	@Override
	public void run() {
		ServerSocket server;
		Socket connessione;

		String ip = null;
		try {
			// TODO add your handling code here:
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			log.printStackTrace(ex);
		}
		int index = ip.indexOf(".");
		index = ip.indexOf(".", index + 1);
		index = ip.indexOf(".", index + 1);
		ip = ip.substring(0, index + 1);
		
		Socket start = new Socket();
		InetSocketAddress addr = null;
		int incremento = 100 / 254;
		int value = 0;

		for (int i = 1; i < 254; i++) {
			try {
				value += incremento;
				addr = new InetSocketAddress(ip + i, 9021);
				start.connect(addr, 50);

				if (start.isConnected()) {
					
					start.close();
					trovato=true;
					break;

				}
			} catch (IOException ex) {
				start = new Socket();
			}
			
		}
		if(trovato){
			Message msg = handler.obtainMessage();
			Bundle b = new Bundle();
			b.putString("ipserver", addr.toString());
			msg.setData(b);
			handler.sendMessage(msg);
			
		}
			

	}

}
