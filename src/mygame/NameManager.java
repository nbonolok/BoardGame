package mygame;
import java.io.File;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class NameManager implements Serializable
{
     
    protected   String FOLDER ="user/";
    protected  final String folder ="user";
    protected  final String EXT =".dat";
    
    protected  String fname ="tempUser";
    private String player1 ="";
    private String player2 ="";
    private String cust_player2="";
    
    private Hashtable myObject;
    private Hashtable c_state=null;
    private Object obj;
    MsgClass errDialog =new MsgClass();
  
  
  public NameManager()
	{ 
	       myObject= new Hashtable();
		   createMemUser();
		}
 
   private void createMemUser()
   {
        // Create one directory
     try {
          if(!(new File(folder).exists()))
          {
          boolean success = (new File(folder)).mkdir();
          if (success) {
          //System.out.println("Directory: " + folder + " created");
          }
          if(!success)
          {
             FOLDER=""; 
           }
         } 
       }
     catch (Exception e){
      //System.err.println("Error: " + e.getMessage());
    }

   }
  
   private void savePlayerNames(String player1,String player2,String cust_p2)
        {
            
             myObject.put("player1",new String(player1));
             myObject.put("player2",new String(player2));
             myObject.put("cust_player2",new String(cust_p2));
            }
        
        
       
    
    public void saveGameNames(String py1,String py2,String cpy2)
	{
       
     savePlayerNames(py1,py2,cpy2);
	  try
	  {
	  String strfile =fname+EXT;  
          File f =new File(FOLDER+strfile);
          
          FileOutputStream f_out = new 
       FileOutputStream (FOLDER+strfile);
       

    
           ObjectOutputStream obj_out = new
           ObjectOutputStream (f_out);

      
          obj_out.writeObject (myObject);
         }
       
        catch(MalformedURLException e)
        {
            //System.out.println(e);
          }
        catch(IOException e)
             {
             	//System.out.println("msg1 "+e.getMessage());
             	} 
        catch(NullPointerException e)
        {
            //System.out.println("null "+ e.getMessage());
         }
      
       }
        public String getPlayer1Name()
        {
        	return player1;
        	}
        public String getPlayer2Name()
        {
        	return player2;
        	}
        public String getPlayer2CName()
        {
          return cust_player2;
         }
        public void getPlayersFromFile()
	   {
		try
		{
                    
		 FileInputStream f_in = new
         FileInputStream (FOLDER+fname+EXT);

        // Read object using ObjectInputStream.
          ObjectInputStream obj_in = new ObjectInputStream (f_in);
          // Read an object.
             obj = obj_in.readObject ();
   
          // Is the object that you read in.
          if (obj instanceof Hashtable)
              {
                //validGamefile =true;
                // Cast object
                c_state = (Hashtable)obj;
                this.player1 =(String)c_state.get("player1");
                this.cust_player2=(String)c_state.get("cust_player2");
                this.player2=(String)c_state.get("player2");
               
                
              }
             if(!(obj instanceof Hashtable))
               {
                 // validGamefile =false;
                  //System.out.println("Saved game is corrupt..");
                  //boardGame.resetGame();
               }
             
             obj_in.close();
           }
           catch(IOException e)
             {
               //System.out.println(e.getMessage());
             //errDialog.getGameFileCorrupted();	
              }
           catch(ClassNotFoundException e)
             {
                //System.out.println(e.getMessage());
                //add dialog for invalid game file.
               //errDialog.getGameFileCorrupted();
              }
           catch(Exception e)
           {
               //errDialog.getGameFileCorrupted();
           	//System.out.println(e.getMessage());
           	}  		
	}
       
    /*public static void main(String [] args)
    {
    	NameManager nm = new NameManager(); //creating folder
    	nm.saveGameNames("Senwelo","Bonolo"); //writting to file
    	nm.getPlayersFromFile(); //reading from file
    	System.out.println(nm.getPlayer1Name()+" "+nm.getPlayer2Name());
    	}*/
}