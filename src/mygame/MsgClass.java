package mygame;

import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
/**
 * The Message Class is a handler for all the user prompts that must be
 * displayed in game dialogs.MsgClass depends mostly on the current set langauge.
 * @author BKNyoni
 */
public class MsgClass
{
	String langTitleGR,langTitleGE,langTitleIM;
	String langPlayer,langCow;
	String langCaptureCowMsg_AI,langNotCaptureMsg_AI,langInvalidMoveMsg;
	String langStartGameMsg,langWinnerMsg,langDrawGameMsg;
	String langMovesDone,langMovesBlocked;
        String langChangeGameMode,langTitleCM;
        String langRoamingMsg,langTitleRM;
        String langfileCorrupted,langTitleFileCP;
	Font gameMenuFont= new Font("Comic Sans MS",Font.ITALIC,18);
	board boardGame;
        /**
         * Class Constructor
         */
       voice me = new voice(voice.VOICE_KEVIN_16);
	public void MsgClass()
	{
	 
	}
        public void setBoard(board boardGame)
        {
            this.boardGame =boardGame;
        }

  	/**
         * This method sets the current langauge of all the game dialogs including
         * the game prompts.
         * @param lang lanagauge
         */
  	public void setLangDialog(String lang)
	{
	    //set to english
            if(lang == null ? "EN" == null : lang.equals("EN"))
		   {
		   	 langTitleGR="Game Rules ";
		   	 langTitleIM="Invalid Move ";
		   	 langTitleGE=" Game End ";
                         langTitleCM="Move Cows";
                         langTitleRM="Roaming Mode";
                         langTitleFileCP="Invalid Game File";
                         langfileCorrupted="The file which was seleted is corrupted or invalid Mhele\ngame file.";        
		   	 langPlayer=" Player ";
		   	 langCow="Cow ";
		   	 
		   	 langCaptureCowMsg_AI="Capture a Cow that belongs to ";
		   	 langNotCaptureMsg_AI=" You cannot capture a Kgomo in "+
                                  " a set while there \n are others available ";
		   	 langInvalidMoveMsg=" Please rethink your move";
		   	 langStartGameMsg=" start the game by clicking on any\n node on the Mhele board ";
     	 	 langWinnerMsg="A player that won this round is ";
     	 	 langDrawGameMsg="Draw,two bulls in a Kraal ";
     	 	 langMovesDone=",You finish all moves ";
     	 	 langMovesBlocked=":You cant move,So you lose this Game.\nOpponent wins this Round";
     	 	 langChangeGameMode="Now you have to move cows inside the board by clicking on the\n"+
                                     "Cow and then clicking on the next empty node";
     	 	 langRoamingMsg=",You can now jump or roam around when moving a Cow";
                }
                //set to setswana
     	 	if(lang == null ? "TN" == null : lang.equals("TN"))
     	 	{
     	 	langTitleGR=" Molao Wa Tshameko ";
     	 	langTitleIM=" Tshameko e dibobo ";
     	 	langTitleGE=" Bokhutlô jwa Motshameko ";
                langTitleCM="Tshameka Dikgomo mo sakeng";
                langTitleRM="Kgomo e ka Kgarakgatsêga";
     	 	langPlayer=" Modisa ";
     	 	langCow=" Kgomo ";
     	 	
                 langTitleFileCP="Motshameko";
                         langfileCorrupted="Gona le bothata ka feile ya Mhele.";        
		   	
     	 	
     	 	 langCaptureCowMsg_AI="Jaa Kgomo ya ";
		   	 langNotCaptureMsg_AI="Molao wa Tshameko ga o letle gore o je\n Kgomo e e mo tshageng tse dingwe di le teng.";
		   	 langInvalidMoveMsg=",Ga o a Tshameka sentle leka gape.";
		   	 langStartGameMsg=" Tlhopa lefelo mo Mheleng la go baya Kgomo ka go \ntobetsa mo go lone";
     	 	 langWinnerMsg=" Motshameko o gapilwe ke ";
     	 	 langDrawGameMsg=" Pôô pedi sakeng,Ga gona mofenye.";
     	 	 langMovesDone=" O latlhegelwa ke Motshameko ka o gatologile ga ntse.";
     	 	 langMovesBlocked=" O latlhegelwa ke Motshameko,ka dikgomo tsa gago di thibilwe.";
     	 	 langChangeGameMode="Tlhopa kgomo e o batlang go e sutisa ka go tsirima mo go yone.\n"+
                                     "Gotswa foo, o bo o tsirima mo konopong e mabapi e go senaga kgomo.";
                 langRoamingMsg=",jaanong Kgomo e ka Kgarakgatsêga\n mo sakeng fa o Tshameka.";
                }
		   	
		}
		      
         private String getDATPlayers(String ppointer)
         {
           NameManager nm = new NameManager(); //creating folder
    	   nm.getPlayersFromFile(); //reading from file
           if(ppointer == null ? "1" == null : ppointer.equals("1"))
             {
                return nm.getPlayer1Name();
              }
           if(ppointer == null ? "2" == null : ppointer.equals("2")){
               String p2=nm.getPlayer2CName();
               if(p2 == null ? "" == null : p2.equals("")){
               return nm.getPlayer2Name();
               }
               if(p2 == null ? "" != null : !p2.equals("")){
                 return p2;
               }
             }
           return "Player";
         }
        public void VoiceHandler(){
          Thread t_voice = new Thread(me);
          if(!t_voice.isAlive()){
             t_voice.start();
          }
        }
        /**
         * Displays a message to player to capture a opponent cow.
         * @param player current player
         */
        public void getCaptureCowMsg_AI(String  player)
        {
          
            JOptionPane.showMessageDialog(null,langCaptureCowMsg_AI+getDATPlayers(player),
	 langTitleGR,JOptionPane.WARNING_MESSAGE,new ImageIcon(this.getClass().getResource("res/images/info.png")));
          me.addWords(langCaptureCowMsg_AI+getDATPlayers(player));
          this.VoiceHandler();
        }
 
        /**
         * Displays a dialog prompt for illegal cow capture move.
         */
      public void getNotCaptureMsg_AI()
       {
           JOptionPane.showMessageDialog(null, langNotCaptureMsg_AI, langTitleGR,
                   JOptionPane.WARNING_MESSAGE, new ImageIcon(this.getClass().getResource("res/images/info.png")));
      me.addWords(langNotCaptureMsg_AI);
          this.VoiceHandler();
      }
		

        /**
         * Displays a dialog prompt for invalid move that was made by player.
         * @param C_PLAYER current player
         */
        public void getInvalidMoveMsg(int C_PLAYER)
	 {
	 	
                 JOptionPane.showMessageDialog(null,getPlayerByCname(C_PLAYER)+
	     langInvalidMoveMsg,langTitleIM,JOptionPane.WARNING_MESSAGE,new ImageIcon(this.getClass().getResource("res/images/info.png")));
          me.addWords(getPlayerByCname(C_PLAYER)+langInvalidMoveMsg);
          this.VoiceHandler();
        }
	
        /**
         * Displays a game start prompt so that a player can start playing a
         * new game.
         * @param C_PLAYER current player.
         */
        public void getStartGameMsg(int C_PLAYER)
	  {
            String plyName = getPlayer1Name();
            //System.out.println(plyName);
            if(plyName.equals("unknown"))
            {
	   JOptionPane.showMessageDialog(null,langPlayer+""+Integer.toString(C_PLAYER)+
	   langStartGameMsg,langTitleGR,JOptionPane.INFORMATION_MESSAGE,new ImageIcon(this.getClass().getResource("res/images/Cow.png")));   	                	                	                  	       
            }
           else if(!plyName.equals("unknown"))
            {
	   JOptionPane.showMessageDialog(null,plyName+
	   langStartGameMsg,langTitleGR,JOptionPane.INFORMATION_MESSAGE,new ImageIcon(this.getClass().getResource("res/images/Cow.png")));   	                	                	                  	       
            }
            }
        /**
         * Displays a game winner message to the current player
         * @param C_PLAYER current player
         */
        public void getWinnerMsg(int C_PLAYER)
	{
	 /* JOptionPane.showMessageDialog(null,langWinnerMsg+Integer.toString(C_PLAYER),langTitleGE,
	   	       JOptionPane.INFORMATION_MESSAGE,new ImageIcon(this.getClass().getResource("res/images/Cow.png")));
	 */
            JOptionPane.showMessageDialog(null,langWinnerMsg+getPlayerByCname(C_PLAYER),langTitleGE,
	   	       JOptionPane.INFORMATION_MESSAGE,new ImageIcon(this.getClass().getResource("res/images/Cow.png")));
        }
        /**
         * Displays a game draw prompt
         */
        public void getDrawGameMsg()
	{
	   JOptionPane.showMessageDialog(null,langDrawGameMsg,langTitleGE,
	   	  	 JOptionPane.INFORMATION_MESSAGE,new ImageIcon(this.getClass().getResource("res/images/Cow.png")));
	   	  	
		}
        /**
         * Display the status of the moves for the current player.
         * @param C_PLAYER current player
         */
	public void getMovesDone(int C_PLAYER)
	{
	  
          JOptionPane.showMessageDialog(null,langWinnerMsg+getPlayerByCname(C_PLAYER)+
	  langMovesDone,langTitleGE,
	  JOptionPane.INFORMATION_MESSAGE,new ImageIcon(this.getClass().getResource("res/images/Cow.png")));
		}
        /**
         * This is called only when the current player cannot moved or is blocked
         * by the opponent.
         * @param C_PLAYER current player
         */
	public void getMovesBlocked(int C_PLAYER)
	{
	/*	 JOptionPane.showMessageDialog(null,langPlayer+""+Integer.toString(C_PLAYER)+
	  langMovesBlocked,langTitleGR,JOptionPane.INFORMATION_MESSAGE,new ImageIcon(this.getClass().getResource("res/images/info.png")));
          */
          JOptionPane.showMessageDialog(null,getPlayerByCname(C_PLAYER)+
	  langMovesBlocked,langTitleGR,JOptionPane.INFORMATION_MESSAGE,new ImageIcon(this.getClass().getResource("res/images/info.png")));

        }

        /**
         * This method displays an alert prompt to the player to indicate that
         * the game mode has change.
         */
        public void getChangeModeGameMsg()
        {
           JOptionPane.showMessageDialog(null,langChangeGameMode,langTitleCM,
	  JOptionPane.INFORMATION_MESSAGE,new ImageIcon(this.getClass().getResource("res/images/Cow.png")));
	 }
        /**
         * This method alerts the current player about the cap roaming mode 
         * activation.Only if the player has three caps on the board.
         * @param C_PLAYER current player
         */
        public void getRoamingMsg(int C_PLAYER)
        {
          /*JOptionPane.showMessageDialog(null,langPlayer+""+Integer.toString(C_PLAYER)+
	  langRoamingMsg,langTitleRM,JOptionPane.INFORMATION_MESSAGE,new ImageIcon(this.getClass().getResource("res/images/info.png")));
	    */
          JOptionPane.showMessageDialog(null,getPlayerByCname(C_PLAYER)+
	  langRoamingMsg,langTitleRM,JOptionPane.INFORMATION_MESSAGE,new ImageIcon(this.getClass().getResource("res/images/info.png")));
           me.addWords(getPlayerByCname(C_PLAYER)+langRoamingMsg);
          this.VoiceHandler();
          }
        /**
         * This used by the load game class to indicate to the player that the Mhele
         * saved file is corrupt.
         */
        public void getGameFileCorrupted()
        {
         JOptionPane.showMessageDialog(null,langfileCorrupted,langTitleFileCP,
         JOptionPane.INFORMATION_MESSAGE,new ImageIcon(this.getClass().getResource("res/images/info.png")));
	     
        }
        public String getPlayer1Name()
        {
          if(boardGame.cust_player1 !=null &&
            (boardGame.cust_player1 == null ? "" != null : !boardGame.cust_player1.equals("")))
            {
               //System.out.println("debugging name 1");
               return boardGame.cust_player1;
             }
          if(boardGame.cust_player1 ==null &&
            (boardGame.cust_player1 == null ? "" == null : boardGame.cust_player1.equals("")))
            {
               //System.out.println("debugging name 2");
               return boardGame.lang_Player;
            }
           //System.out.println("debugging name 3");
          return boardGame.lang_Player;
        }
           public String getPlayer2Name()
            {
            return boardGame.lang_Player2;
            }
           public String getPlayerByCname(int py)
           {
             if(py == 1)
               {
                  return getPlayer1Name();
                }
             else if (py==2)
               {
                 return getPlayer2Name();
               }
               return "O";
            }
          public void saveNamesToFileDAT()
          {
               NameManager nm = new NameManager(); //creating folder
               String cstp2=boardGame.cust_player2;
               if(cstp2==null){
                 cstp2="";
               }
    	       nm.saveGameNames(getPlayer1Name(),getPlayer2Name(),cstp2);
            }
	}