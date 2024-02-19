package mygame;

import java.io.File;
/**
 *This is a super class for the savegame and loadgame which is used for setting
 * up the storage environment and file type.
 * @author BKNyoni
 */
public class GameManager
{
    /**
     * Game storage path
     */
    protected   String FOLDER ="memcard/";
  /**
   * This is where all Mhele games are stored.
   */
  protected  final String folder ="memcard";
  /**
   *This the file type of the saved games.
   */
  protected  final String EXT =".mhl";
  /**
   * The name of the game which will be saved or loaded.
   */
  protected  String fname;
  /**
   * Class constructor which will also check the create or use the storage
   * location.
   */
  public GameManager()
	{
            createMemCard();
		}
   /**
    * Checks if the storage area exist and uses it.if it does not exist the
    * method will create a new location.
    * @exception Exception  Any exception that may occur during storage
    * validation
    */
   private void createMemCard()
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
   /**
    * sets the game name from user.
    * @param fn filename,This is the game name.
    */
   public void setFileName(String fn)
	{
		this.fname=fn+EXT;
		}
   /**
    * Get filename which is used in game saving/loading.
    * @return fname filename.
    */
   public String getFileName()
	{
		return this.fname;
		}		
	}

