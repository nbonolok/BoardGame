package mygame;
import java.io.File;
import java.net.MalformedURLException;

import java.util.Hashtable;

import java.io.IOException;
import java.io.FileOutputStream;

import java.io.ObjectOutputStream;

import java.io.Serializable;

/**
 *
 * @author BKNyoni
 */
public class saveGame extends GameManager implements Serializable
{

	private Hashtable myObject;
	//private String gameName;
        board boardGame;
        /**
         *
         * @param boardGame
         */
        public saveGame(board boardGame)
	{
	this.boardGame = boardGame;		
	myObject= new Hashtable();	
	}
        /**
         *
         * @param f
         * @return
         */
        public int checkSvFlag(boolean f)
        {
            int flag_sv =0;
             if(f ==true) { 
                flag_sv = 1; 
             }
             else if(f ==false) { 
                flag_sv =0; 
             }
             return  flag_sv;
         }
        /**
         *
         * @param roam_p1
         * @param roam_p1Msg
         * @param roam_p2
         * @param roam_p2Msg
         * @param Cflag
         * @param Rflag
         * @param roam_countP1
         * @param roam_countP2
         * @param dlogCounter
         */
        public void saveRoamingModeFlag(boolean roam_p1,boolean roam_p1Msg,boolean roam_p2,boolean roam_p2Msg,
                               boolean Cflag, boolean Rflag , int roam_countP1, int roam_countP2,int dlogCounter)
        {
            
            myObject.put("roam_p1_flag",new Integer(checkSvFlag(roam_p1)));
            myObject.put("roam_p2_flag",new Integer(checkSvFlag(roam_p2)));
            
            myObject.put("roam_p1Msg_flag",new Integer(checkSvFlag(roam_p1Msg)));
            myObject.put("roam_p2Msg_flag",new Integer(checkSvFlag(roam_p2Msg)));
            
            myObject.put("Cflag",new Integer(checkSvFlag(Cflag)));
            myObject.put("Rflag",new Integer(checkSvFlag(Rflag)));
            
            myObject.put("roam_countP1",new Integer(roam_countP1));
            myObject.put("roam_countP2",new Integer(roam_countP2));
            myObject.put("dlogCounter",new Integer(dlogCounter));
          }
        /**
         *
         */
        public void saveGameMode()
        {
            
          myObject.put("matrix_player",new Integer(boardGame.matrix_player)); 
          myObject.put("player_mode_flag",new Integer(checkSvFlag(boardGame.PlayerMode)));
          myObject.put("game_level",new Integer(boardGame.gameLevel));
          myObject.put("game_mode",new Integer(boardGame.GAME_MODE));
          myObject.put("chance_mode_flag",new Integer(checkSvFlag(boardGame.CHANGE_MODE)));
          myObject.put("capture_flag",new Integer(checkSvFlag(boardGame.CAPTURE_FLAG)));
          myObject.put("md0_player",new Integer(boardGame.MD0_PLAYER));
          //myObject.put("sets",new Vector(boardGame.vsets));
         }
        /**
         *
         * @param c_player
         * @param click_count
         * @param pos
         * @param s_pos
         * @param e_pos
         * @param temp_pos
         * @param board_flag
         * @param move_flag
         */
        public void savePlayerLocation(int c_player,int click_count,int pos,int s_pos,
                                       int e_pos,int temp_pos,boolean board_flag,boolean move_flag)
        {
           myObject.put("c_player",new Integer(c_player));
           myObject.put("click_count",new Integer(click_count));
           myObject.put("postion",new Integer(pos));
           myObject.put("s_pos",new Integer(s_pos));
           myObject.put("e_pos",new Integer(e_pos));
           myObject.put("temp_pos",new Integer(temp_pos));
           myObject.put("board_flag",new Integer(checkSvFlag(board_flag)));
           myObject.put("move_flag",new Integer(checkSvFlag(move_flag)));
           }
        
        /**
         *
         * @param ThemePointer
         * @param player1
         * @param player2
         * @param mheleBoard
         * @param dock
         * @param background
         * @param sound
         * @param lang
         */
        public void saveGameTheme(int ThemePointer,String player1,String player2,String mheleBoard,String dock,String background,boolean sound,String lang)
        {
          int soundInt =0;
           myObject.put("ThemePointer",new Integer(ThemePointer));
           myObject.put("player1",new String(player1)); 
           myObject.put("player2",new String(player2));
           myObject.put("mheleBoard",new String(mheleBoard));
           myObject.put("dock",new String(dock));
           myObject.put("background",new String(background));
           
           if(sound ==true) { soundInt = 1; }
           else if(sound ==false) { soundInt =0; }
           myObject.put("sound",new Integer(soundInt));
           
           myObject.put("lang",  new String(lang));
           
        }
        /**
         *
         * @param cows
         * @param cap_layoutIN
         */
        public void saveCapsInfo(int cows,int [] cap_layoutIN)
	{
		int [] cap_layout= cap_layoutIN;
		
        myObject.put("cows",  new Integer(cows));
        
        myObject.put("caps_dock_p1",new Integer(boardGame.PLAYER1_CAPS_DOCK));
        myObject.put("caps_board_p1",new Integer(boardGame.PLAYER1_CAPS_BOARD));
        myObject.put("caps_captured_p1",new Integer(boardGame.CAPS_CAPTURED_BY_PLAYER1));
        
        myObject.put("caps_dock_p2",new Integer(boardGame.PLAYER2_CAPS_DOCK));
        myObject.put("caps_board_p2",new Integer(boardGame.PLAYER2_CAPS_BOARD));
        myObject.put("caps_captured_p2",new Integer(boardGame.CAPS_CAPTURED_BY_PLAYER2));
        setCapLayout(cap_layout);
        }

        public void saveDragCapsInfo()
        {
         myObject.put("vPlayLastMove0",new Integer(boardGame.vPlayerLastMove[0]));
         myObject.put("vPlayLastMove1",new Integer(boardGame.vPlayerLastMove[1]));
         myObject.put("dock_cap_boundries",new Integer(boardGame.dock_cap_boundary));
         myObject.put("drop_player_cap",new Integer(boardGame.drop_player_cap));
         myObject.put("dragCapImage",new Integer(boardGame.dragCapImage));
         myObject.put("dragCapImageMode1",new Integer(boardGame.dragCapImageMode1));


        }
    private void  setCapLayout(int [] caps)
    {
      	for(int i=0; i<caps.length; ++i)
      	{
      	
         	String str = "position"+Integer.toString(i);
      	   myObject.put(str, new Integer(caps[i]));	
    	   }
    	}
      
    /**
     *
     * @param myGameName
     */
    public void saveGameWorld(String myGameName)
	{
           
	  try
	  {
	  String strfile =myGameName+EXT;  
          File f =new File(FOLDER+strfile);
          //System.out.println(f.getCanonicalPath());
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
}