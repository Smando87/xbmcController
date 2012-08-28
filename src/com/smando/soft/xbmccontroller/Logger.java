package com.smando.soft.xbmccontroller;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.Date;
import java.util.GregorianCalendar;

import android.content.Context;
import android.widget.Toast;


/**
 *
 * @author Simone Del Prete
 * @since 19/11/2011
 * @mod 18/06/2012
 * @version 0.3
 *
 */
public class Logger {

	private PrintWriter logfile;
    private int lunghmaxstringhe=15;
    public static String INFO="INFO";
    public static String WARNING="WARNING";
    public static String ERROR="ERROR";
    private String libversion="0.2";
    private Context context;
    private boolean append;
    private String path, nomefile;
    public boolean DEBUG;
   
    
  
    
    /**
     * Costruttore 
     * @param append indica se i log vanno in coda al file o se ne viene genrato uno nuovo
     * @param path cartella relativa per il file di log, se "" verrà ignorata
     * @param nomefile nome del file di log
     *
     */
    public Logger(String path, String nomefile, Context c, boolean debug){
       File f;
       context=c;
       DEBUG=debug;
        try {
            if(path.length()>0)
                f=new File(path+"/"+nomefile);
            else
                f=new File("./"+nomefile);
            
            if(!f.exists())
                f.createNewFile();
            logfile = new PrintWriter(new BufferedWriter(new FileWriter(f,true)),true);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
        }
       // aggiungiRiga("Logger "+libversion+" avviato...", "Logger",INFO);
    }
 
    
    
    public void toScreen(String s,Class module,String TYPE){
        Toast.makeText(context, "["+getData()+" - "+module.getSimpleName()+" - "+TYPE+"] "+s, Toast.LENGTH_LONG).show();
        aggiungiRiga(s,module,TYPE);
    }
    
   
    public void debugToScreen(String s,Class module,String TYPE){
        if(DEBUG){
            toScreen(s,module,(TYPE+"-DEBUG"));
            aggiungiRiga(s,module,TYPE);
        }
    }
    
    public void debug(String s,Class module,String TYPE){
        if(DEBUG){
          
            aggiungiRiga(s,module,TYPE+"-DEBUG");
        }
    }
   
    
    public void debugStackTraceToScreen(Exception e){
    
        StackTraceElement[] st=e.getStackTrace();
        for(int i=0;i<st.length;i++){
            toScreen(st[i].toString(),e.getClass(),"ERROR");
            aggiungiRiga(st[i].toString(),e.getClass(),"ERROR");
        }
            
        
    }
    
    public void printStackTrace(Throwable e){
        
        StackTraceElement[] st=e.getStackTrace();
        for(int i=0;i<st.length;i++){
            toScreen(st[i].toString(),e.getClass(),"ERROR");
            aggiungiRiga(st[i].toString(),e.getClass(),"ERROR");
        }
            
        
    }
    
    public void printStackTrace(Thread t,Throwable e){
        
        StackTraceElement[] st=e.getStackTrace();
        toScreen("**ECCEZZIONE** "+t.getName(),e.getClass(),"ERROR");
        aggiungiRiga("**ECCEZZIONE** "+t.getName(),e.getClass(),"ERROR");
        for(int i=0;i<st.length;i++){
            toScreen(st[i].toString(),e.getClass(),"ERROR");
            aggiungiRiga(st[i].toString(),e.getClass(),"ERROR");
        }
            
        
    }
    
 
    /**
     *
     * @param attivadata
     * @param txt
     * @param classe
     * @param tipomess
     */
    public void aggiungiRiga(String s,Class module,String TYPE){
         logfile.println("["+getData()+" - "+module.getSimpleName()+" - "+TYPE+"] "+s+"");
         logfile.flush();
        
    }

    /**
     *
     * @return data e ora correnti
     */
    private String getData(){
       return new GregorianCalendar().getTime().toString();
    }

   
}
