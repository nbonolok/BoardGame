package mygame;
import java.util.Hashtable;

import java.io.IOException;

import java.io.FileInputStream;

import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 *
 * @author BKNyoni
 */
public class loadGame extends GameManager implements Serializable
{
    /**
     *
     */
    public boolean validGamefile;
	private Hashtable c_state=null;
        /**
         *
         */
        /**
         *
         */
        public int sound,cows;
        /**
         *
         */
        public String LD_lang;
	private Object obj;
        /**
         *
         */
        public int [] restoreCaps = new int[25];
	private String  LD_player1,LD_player2,LD_mheleBoard,LD_dock,LD_background;
        /**
         *
         */
        /**
         *
         */
        /**
         *
         */
        public int LD_PLAYER1_CAPS_DOCK,LD_PLAYER1_CAPS_BOARD,LD_CAPS_CAPTURED_BY_PLAYER1;
        /**
         *
         */
        /**
         *
         */
        /**
         *
         */
        public int LD_PLAYER2_CAPS_DOCK,LD_PLAYER2_CAPS_BOARD,LD_CAPS_CAPTURED_BY_PLAYER2;
        /**
         *
         */
        public int LD_themePointer;
        board boardGame;
        MsgClass errDialog =new MsgClass();
        /**
         *
         * @param boardGame
         */
        public loadGame(board boardGame)
	{
		this.boardGame = boardGame;
                //System.out.println("langauge "+ );
                errDialog.setLangDialog(boardGame.getlanguage());
	}
        
        
        /**
         *
         * @param num
         * @return
         */
        public boolean checkLdFlag(int num)
        {
            boolean flag =false;
             if(num ==1) { 
                flag = true; 
             }
             else if(num ==0) { 
                flag =false; 
             }
             return  flag;
         }
        /**
         *
         */
        public void LoadRoamingModeFlag()
        {
           Integer temp;
            
             temp=(Integer)c_state.get("roam_p1_flag");   
             boardGame.ROAM_PLAYER1=checkLdFlag((int)temp); 
            
             temp=(Integer)c_state.get("roam_p2_flag");   
             boardGame.ROAM_PLAYER2=checkLdFlag((int)temp); 
            
             temp=(Integer)c_state.get("roam_p1Msg_flag");   
             boardGame.ROAM_PLAYER1_MSG=checkLdFlag((int)temp); 
            
             temp=(Integer)c_state.get("roam_p2Msg_flag");   
             boardGame.ROAM_PLAYER2_MSG=checkLdFlag((int)temp);
            
             temp=(Integer)c_state.get("Cflag");   
             boardGame.ColumnFlag=checkLdFlag((int)temp);

             temp=(Integer)c_state.get("Rflag");   
             boardGame.RowFlag=checkLdFlag((int)temp);
            
             temp=(Integer)c_state.get("roam_countP1");   
             boardGame.ROAM_COUNTER_P1=(int)temp;
            
             temp=(Integer)c_state.get("roam_countP2");   
             boardGame.ROAM_COUNTER_P2=(int)temp;
            
             temp=(Integer)c_state.get("dlogCounter");   
             boardGame.dialogCounter=(int)temp;
            
          }
        /**
         *
         */
        public void LoadGameMode()
        {
          int temp;  
          
           temp=(Integer)c_state.get("matrix_player");   
           boardGame.matrix_player=(int)temp;
          
          temp=(Integer)c_state.get("player_mode_flag");   
          boardGame.PlayerMode=checkLdFlag((int)temp);
          
           temp=(Integer)c_state.get("game_level");   
           boardGame.gameLevel=(int)temp;
          
           temp=(Integer)c_state.get("game_mode");   
           boardGame.GAME_MODE=(int)temp;
          
          temp=(Integer)c_state.get("chance_mode_flag");   
          boardGame.CHANGE_MODE=checkLdFlag((int)temp);
          
          temp=(Integer)c_state.get("capture_flag");   
          boardGame.CAPTURE_FLAG=checkLdFlag((int)temp);
          
          temp=(Integer)c_state.get("md0_player");   
          boardGame.MD0_PLAYER=(int)temp;
         }
        /**
         *
         */
        public void LoadPlayerLocation()
        {
            int temp;
          
            temp=(Integer)c_state.get("c_player");   
           boardGame.C_PLAYER=(int)temp;
          
           temp=(Integer)c_state.get("click_count");   
           boardGame.Ms_count_Click=(int)temp;
           
           
           temp=(Integer)c_state.get("postion");   
           boardGame.POSITION=(int)temp;
           
           temp=(Integer)c_state.get("s_pos");   
           boardGame.START_POSITION=(int)temp;
           
           temp=(Integer)c_state.get("e_pos");   
           boardGame.END_POSITION=(int)temp;
           
           temp=(Integer)c_state.get("temp_pos");   
           boardGame.TEMP_POSITION=(int)temp;
           
            temp=(Integer)c_state.get("board_flag");   
          boardGame.boardGameFlag=checkLdFlag((int)temp);

           
            temp=(Integer)c_state.get("move_flag");   
          boardGame.moveflag=checkLdFlag((int)temp);
        }
        /**
         *
         */
        public void LoadGameCaps()
        {
         
              //player 1 caps
              Integer cdp1=(Integer)c_state.get("caps_dock_p1");
              this.LD_PLAYER1_CAPS_DOCK =(int)cdp1;
              
              Integer pcb1=(Integer)c_state.get("caps_board_p1");
              this.LD_PLAYER1_CAPS_BOARD =(int)pcb1;
               
              Integer ccp1=(Integer)c_state.get("caps_captured_p1");
              this.LD_CAPS_CAPTURED_BY_PLAYER1 =(int)ccp1;
               
              //player 2 caps
              Integer cdp2=(Integer)c_state.get("caps_dock_p2");
              this.LD_PLAYER2_CAPS_DOCK =(int)cdp2;
              
              Integer pcb2=(Integer)c_state.get("caps_board_p2");
              this.LD_PLAYER2_CAPS_BOARD =(int)pcb2;
               
              Integer ccp2=(Integer)c_state.get("caps_captured_p2");
              this.LD_CAPS_CAPTURED_BY_PLAYER2 =(int)ccp2;

              //end of caps collection
              
              
	     
              
              for(int i=0; i<restoreCaps.length; ++i)
                 {
                 String str = Integer.toString(i);	
                 Integer s = (Integer)c_state.get("position"+str);
                 restoreCaps[i] =(int)s;
                // System.out.print(restoreCaps[i]);
             }
             //for(int x=0; x< 25; ++x)
             // {
                       // System.out.print(restoreCaps[x]);
               //}
             //System.out.println("");
             LoadDragCapsInfo();
         }
        public void LoadDragCapsInfo()
        {
          int temp;
           temp=(Integer)c_state.get("vPlayLastMove0");
           boardGame.vPlayerLastMove[0]=(int)temp;

            temp=(Integer)c_state.get("vPlayLastMove1");
            boardGame.vPlayerLastMove[1]=(int)temp;

            temp=(Integer)c_state.get("dock_cap_boundries");
            boardGame.dock_cap_boundary=(int)temp;

            temp=(Integer)c_state.get("drop_player_cap");
            boardGame.drop_player_cap=(int)temp;

            temp=(Integer)c_state.get("dragCapImage");
            boardGame.dragCapImage=(int)temp;

            temp=(Integer)c_state.get("dragCapImageMode1");
            boardGame.dragCapImageMode1=(int)temp;
           }

        /**
         *
         */
        public void LoadGameTheme()
        {
           //load theme variables
             Integer tpointer=(Integer)c_state.get("ThemePointer");   
             this.LD_themePointer=(int)tpointer;   
             this.LD_player1 =(String)c_state.get("player1");
             this.LD_player2=(String)c_state.get("player2");
             this.LD_mheleBoard=(String)c_state.get("mheleBoard");
             this.LD_dock=(String)c_state.get("dock");
             this.LD_background=(String)c_state.get("background");
                
             //System.out.println(LD_themePointer+"\n"+LD_player1+"\n"+LD_player2+"\n"+LD_mheleBoard+"\n"+LD_dock+"\n"+LD_background);
               //end of theme variables 
                
             Integer sd=(Integer)c_state.get("sound");
             this.sound =(int)sd;
             
              Integer cow=(Integer)c_state.get("cows");
              this.cows =(int)cow;
               this.LD_lang=(String)c_state.get("lang");
	          
	           //System.out.println("sound ="+sound+"\ncows max= "+cows+"\nlang= "+LD_lang);
                
        }
        /**
         *
         * @param fname
         */
        public void getGameWorld(String fname)
	{
		try
		{
                    
		 FileInputStream f_in = new
         FileInputStream (FOLDER+fname);//getFileName()); //update this to dynamic list

        // Read object using ObjectInputStream.
          ObjectInputStream obj_in = new ObjectInputStream (f_in);
          // Read an object.
             obj = obj_in.readObject ();
   
          // Is the object that you read in.
          if (obj instanceof Hashtable)
              {
                validGamefile =true;
                // Cast object
                c_state = (Hashtable)obj;
             
                LoadRoamingModeFlag();
                LoadGameMode();
                LoadPlayerLocation();
                LoadGameCaps();
                LoadGameTheme();
               boardGame.mheleAI.updateStatus(restoreCaps,boardGame.POSITION,boardGame.C_PLAYER);
               boardGame.rulesAI.updateStatus(restoreCaps,boardGame.POSITION,boardGame.C_PLAYER);
              }
             if(!(obj instanceof Hashtable))
               {
                  validGamefile =false;
                  //System.out.println("Saved game is corrupt..");
                  //boardGame.resetGame();
               }
             
             obj_in.close();
           }
           catch(IOException e)
             {
               //System.out.println(e.getMessage());
             errDialog.getGameFileCorrupted();	
              }
           catch(ClassNotFoundException e)
             {
                //System.out.println(e.getMessage());
                //add dialog for invalid game file.
               errDialog.getGameFileCorrupted();
              }
           catch(Exception e)
           {
               errDialog.getGameFileCorrupted();
           	//System.out.println(e.getMessage());
           	}  		
	}
        
        
}