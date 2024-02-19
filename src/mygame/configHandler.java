package mygame;
import java.io.File; 
import java.util.Scanner;
/**
 *
 * @author BKNyoni
 */
public class configHandler
{
	String strlang;
	String [] comp_lang ={"EN","TN"};
	String [] comp_cap_no = {"3","6","9","12"};
	String [] comp_sound={"ON","OFF"};
	String [] syntax ={"LANGAUGE","SOUND","COW"};
	
	String [][] commands;
        /**
         *
         */
        public String lang;
        /**
         *
         */
        public boolean volume;
	private File file;
	private String line="";
        /**
         *
         */
        public int caps;
	private Scanner scanner;
	 
   
        /**
         *
         */
        public configHandler()
	{
		loadConfig();
		 }
		 
     
        /**
         *
         * @return
         */
        public String getLang()
    {
    	if(lang==null || (lang == null ? "" == null : lang.equals("")))
          this.lang="EN";
    	return this.lang;
    	
    }
        /**
         *
         * @return
         */
        public boolean getVolume()
    {
    	return this.volume;
    	}
    /**
     *
     * @return
     */
    public int getCaps()
    {
    	return this.caps;
    	}
    			 
	
    /**
     *
     */
    public void loadConfig()
	{
            intCommand("LANGAUGE","EN");
            intCommand("SOUND","ON");
            intCommand("COW","12");
        }
     
    private void intCommand(String syn,String command)
    {
       if(syn.contains(syntax[0]))
         {
         	String temp ="EN";
         	//System.out.println(syn+" "+ syntax[0]);
         	for(int i=0; i<comp_lang.length; ++i)
         	     {
         	     	//System.out.println("Debug inside "+comp_lang[i]);
         	     if(command.contains(comp_lang[i]))
         	        {
         	        	temp =comp_lang[i];
         	        	//System.out.println("Debug inside 2"+temp);
         	        	break;
         	        }
         	      }  
         	  setlangauge(temp);   
         	 }
        if(syn.contains(syntax[1]))
         {
           boolean temp =true;
           //System.out.println("debug sound");
         	for(int i=0; i<comp_sound.length; ++i)
         	     {
         	     //System.out.println("debug sound 1");	 
         	     if(command.contains(comp_sound[i]))
         	        {
         	        	//System.out.println("debug sound 2 "+ comp_sound[i]);
         	        	if(command.contains(comp_sound[0]))
         	        	   {
         	        	   	 //System.out.println("debug sound 3");
         	        	   	temp =true;
         	        	   	break;
         	        	   }
         	        	 if(command.contains(comp_sound[1]))  
         	        	   {
         	        	   	//System.out.println("debug sound 4");
         	        	   	temp =false;
         	        	   	break;
         	        	   }
         	        	
         	        	
         	        }
         	      }  
         	  setVolume(temp);   
            }
        
         if(syn.contains(syntax[2]))
         {
           String temp ="12";
           //System.out.println("debug caps");
         	for(int i=0; i<comp_cap_no.length; ++i)
         	     {
         	     //System.out.println("debug sound 1");	 
         	     if(command.contains(comp_cap_no[i]))
         	        {
         	        	//System.out.println("debug sound 2 "+ comp_sound[i]);
         	        	if(command.contains(comp_cap_no[i]))
         	        	   {
         	        	   	 //System.out.println("debug sound 3");
         	        	   	temp =comp_cap_no[i];
         	        	   	break;
         	        	   }
         	        	 
         	        	
         	        	
         	        }
         	      }  
         	  setCaps(temp); 
         	              }	 	
    	} 	 
    /**
     *
     * @param l
     * @return
     */
    public String setlangauge(String l)
	{
		this.lang =l;
		
        
          
        return lang;
    	}
	
        /**
         *
         * @param line
         * @return
         */
        public boolean setVolume(boolean line)
	{
    	volume=line;
    	return volume; 
		}
		
	private int setCaps(String n)
	{
        this.caps =Integer.parseInt(n);
        return this.caps;
		}
		
	  			 
	}