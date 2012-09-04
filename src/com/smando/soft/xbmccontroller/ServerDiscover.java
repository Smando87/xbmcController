package com.smando.soft.xbmccontroller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.logging.Level;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

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
		// TODO add your handling code here:
		//ip = InetAddress.getLocalHost().getHostAddress();
		//ip = InetAddress.getByName("localhost").getHostAddress();
		 StringBuilder IFCONFIG=new StringBuilder();
		    try {
		        for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
		            NetworkInterface intf = en.nextElement();
		            for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
		                InetAddress inetAddress = enumIpAddr.nextElement();
		                if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress() && inetAddress.isSiteLocalAddress()) {
		                IFCONFIG.append(inetAddress.getHostAddress().toString()+"\n");
		                }
		            }
		        }
		    } catch (SocketException ex) {
		        Log.e("LOG_TAG", ex.toString());
		    }
	    ip=IFCONFIG.toString();
		
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
				start.connect(addr, 500);

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
