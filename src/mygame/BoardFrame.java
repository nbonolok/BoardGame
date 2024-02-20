package mygame;
/**
 * This is the main class for the game.All the swing components are mounted to
 * this class.It has a menubar and a game panel.
 * @author: BK Nyoni
 *@version:    0.1.0
 *
 */

import java.awt.Color;
import java.awt.Font;
//events lib
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 //layout 
import java.awt.Container;

//gui components
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
//import javax.swing.JFileChooser;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;



public class BoardFrame extends JFrame implements ActionListener
{
    private int GAME_STATUS =0; //0=inactive 1=active
    final private String GAME_NAME ="MHELE";
    private JMenuBar menuBar;
    
    private JMenu menuGame;
    private JMenuItem  menuItemNew,menuItemLoad,menuItemSave,menuItemQuit,menuItemExit;
   
    private JMenu menuItemCows,menuOptions,menuPlayer;
    private JMenuItem  menuItemKraal,menuItem2Player,menuItemPlayerVsPC,menuItemPlayerName;
    private JMenuItem  menuItemCows3,menuItemCows6,menuItemCows9,menuItemCows12;
    
    private JMenu menu,menuHelp,menuItemSound,menuItemLangauge;
    
    private JRadioButtonMenuItem rbMenuItemSoundOn,rbMenuItemSoundOff; 
    
    private JMenuItem menuItem,menuItemManual,menuItemAbout;
    private board boardGame;
    private wallpaper wallPanel;
    private String lang_GAME,lang_NEW,lang_LOAD,lang_SAVE,lang_QUIT,lang_EXIT,lang_dialogQuitMsg;
    private String lang_OPTION,lang_OPLAYER,lang_OSOUND,lang_OSOUND_ON,lang_OSOUND_OFF,lang_LANG,lang_COWS,lang_KRAAL;
    private String lang_OnePlayer,lang_TwoPlayer,lang_PlayerName;
    private String lang_HELP,lang_MANUAL,lang_ABOUT;
    private String lang_dTitle,lang_dMsg,lang_dPlayer;
    /**
     *The current selected langauge.
     */
    public String lang;
    private String lang_dialogExitTitle,lang_dialogExitMsg;
    private String lang_menuItemCows3,lang_menuItemCows6,lang_menuItemCows9,lang_menuItemCows12;
    private String lang_ChangeCowsMsg,lang_ChangeCowsTitle;
    //JFileChooser fc;
    loadGame lg;
    VgaDriver vga =null; //not currently used.
	SplashWindow splw =null;
    public Container content_pane;
        /**
         *Class constructor- All the game swing components are initialized
         * in the constructor.
         */
        public BoardFrame()
	{
	
	    /*Will be added on a later stage*/
	     if(vga==null)
	       {
	       //vga = new VgaDriver(this);
	       }
	      if(splw ==null)
	      {
	      	//splw=new SplashWindow("res/images/gamelogo.jpg",this,2000);
	      	
	      	} 
	     content_pane = getContentPane ();
	        
	    boardGame = new board();
            wallPanel= new wallpaper();
	    lang =boardGame.getlanguage();
	    boardGame.setlanguage(lang);
	    langSetupMenu();
	    langSetupDialog();

	        setSize(710,600);
                //setSize(1024,800);
		setTitle(GAME_NAME);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setLocationRelativeTo( null );
            //setLocation(2,2);
	    setCursor( boardGame.getGameCursor());
	    setUndecorated(false); //TODO
	    
       	setIconImage(new ImageIcon(boardGame.imgHandler.getGameBoard()).getImage());	
       setGameMenu();
	

	    super.setJMenuBar(menuBar);
	    
	    //content_pane.add(boardGame);
	    content_pane.add(wallPanel);
            boardGame.setVisible(false);

		setVisible(true);
		setResizable(false);
		setDefaultLookAndFeelDecorated(true);
		}
	  	
		
        /**
         * game project main method.
         * @param args
         */
        public static void main(String [] args)
	 {
	     validgame vd =new validgame();
            // System.out.println(vd.getStatus());
                if(vd.getStatus()!=false)
              {
               BoardFrame bf=new BoardFrame();
              } 
	 }

     	 
        /**
         * Game Menubar,menuItems and look & feel for the game navigation is set
         * or add to the game JFrame in this method.
         */
        public void setGameMenu()
     {
     	 //Create the menu bar.
                        Font gameMenuFont= new Font("Comic Sans MS",Font.ITALIC,18);
                        Font gameMenuItemFont = new Font("Comic Sans MS",Font.BOLD,14);
                        Color colorMenuItem = Color.ORANGE;
                        Color colorMenu = Color.ORANGE;
			Color colorMenuItemBg = Color.BLACK;
                        menuBar = new JMenuBar();
			menuBar.setCursor( boardGame.getGameCursor());
                        menuBar.setBackground(Color.BLACK);
		
			
			//Game Menu.
			menuGame = new JMenu(lang_GAME);
                        
                        menuGame.setFont(gameMenuFont);
			menuGame.setForeground(colorMenu);
                        
                        menuGame.setMnemonic(KeyEvent.VK_G);
			menuGame.getAccessibleContext().setAccessibleDescription("View game Options");
			
			menuItemNew = new JMenuItem(lang_NEW,new ImageIcon(this.getClass().getResource("res/images/games.png")));
			menuItemNew.getAccessibleContext().setAccessibleDescription("Start A New Game");
			menuItemNew.setFont(gameMenuItemFont);
                        menuItemNew.setForeground(colorMenuItem);
                        menuItemNew.setBackground(colorMenuItemBg);
                        menuItemNew.addActionListener(this);
			
                        menuItemNew.setMnemonic(KeyEvent.VK_N);
		    menuGame.add(menuItemNew);
			
			menuItemLoad = new JMenuItem(lang_LOAD,new ImageIcon(this.getClass().getResource("res/images/load.png")));
			menuItemLoad.getAccessibleContext().setAccessibleDescription("load old Game");
			menuItemLoad.setFont(gameMenuItemFont);
                        menuItemLoad.setForeground(colorMenuItem);
                        menuItemLoad.setBackground(colorMenuItemBg);
                        menuItemLoad.addActionListener(this);
                        
			menuItemLoad.setEnabled(true);
            menuGame.add(menuItemLoad);
			
			menuItemSave = new JMenuItem(lang_SAVE,new ImageIcon(this.getClass().getResource("res/images/b_save.png")));
			menuItemSave.getAccessibleContext().setAccessibleDescription("Save Game");
			menuItemSave.setFont(gameMenuItemFont);
                        menuItemSave.setForeground(colorMenuItem);
                        menuItemSave.setBackground(colorMenuItemBg);
                        menuItemSave.addActionListener(this);
			
                        menuItemSave.setEnabled(false);
            menuGame.add(menuItemSave);

            menuGame.addSeparator();
		menuItemQuit = new JMenuItem(lang_QUIT,KeyEvent.VK_U);
			menuItemQuit.getAccessibleContext().setAccessibleDescription("Exit Game");
			menuItemQuit.setFont(gameMenuItemFont);
                        menuItemQuit.setForeground(colorMenuItem);
                        menuItemQuit.setBackground(colorMenuItemBg);
                        menuItemQuit.setActionCommand(lang_QUIT);
			menuItemQuit.addActionListener(this);
                        menuItemQuit.setEnabled(false);
                         //menuItemSave.setEnabled(false);
            menuGame.add(menuItemQuit);

			menuItemExit = new JMenuItem(lang_EXIT,KeyEvent.VK_X);
			menuItemExit.getAccessibleContext().setAccessibleDescription("Exit Game");
			menuItemExit.setFont(gameMenuItemFont);
                        menuItemExit.setForeground(colorMenuItem);
                        menuItemExit.setBackground(colorMenuItemBg);
                        menuGame.setActionCommand(lang_EXIT); 
			menuItemExit.addActionListener(this);
                       
            menuGame.add(menuItemExit);
			menuBar.add(menuGame);
			
			//options 
			menuOptions = new JMenu(lang_OPTION);
			menuOptions.setFont(gameMenuFont);
			menuOptions.setForeground(colorMenu);
                        menuOptions.setBackground(colorMenuItemBg);
			
                        menuItemKraal = new JMenuItem(lang_KRAAL);
			menuItemKraal.addActionListener(this);
                        menuItemKraal.setFont(gameMenuItemFont);
                        menuItemKraal.setForeground(colorMenuItem);
                        menuItemKraal.setBackground(colorMenuItemBg);
                        //menuItemKraal.setEnabled(true);
                        menuItemKraal.setEnabled(false);
                        menuOptions.add(menuItemKraal);
			
			menuOptions.addSeparator();
                        
                        menuPlayer = new JMenu(lang_OPLAYER);
                        
			menuPlayer.addActionListener(this);
			
                        menuPlayer.setFont(gameMenuItemFont);
                        menuPlayer.setForeground(colorMenuItem);
                        menuPlayer.setBackground(colorMenuItemBg);
                        

                 menuItemPlayerName = new JMenuItem(lang_PlayerName);
	    menuItemPlayerName.addActionListener(this);

            menuItemPlayerName.setFont(gameMenuItemFont);
            menuItemPlayerName.setForeground(colorMenuItem);
            menuItemPlayerName.setBackground(colorMenuItemBg);
            menuPlayer.add(menuItemPlayerName);

            
            menuItemPlayerVsPC = new JMenuItem(lang_OnePlayer);
	    menuItemPlayerVsPC.addActionListener(this);
	   
            menuItemPlayerVsPC.setFont(gameMenuItemFont);
            menuItemPlayerVsPC.setForeground(colorMenuItem);
            menuItemPlayerVsPC.setBackground(colorMenuItemBg);
            menuPlayer.add(menuItemPlayerVsPC);
            
            menuItem2Player = new JMenuItem(lang_TwoPlayer);
			menuItem2Player.addActionListener(this);
           

            menuItem2Player.setFont(gameMenuItemFont);
            menuItem2Player.setForeground(colorMenuItem);
            menuItem2Player.setBackground(colorMenuItemBg);
            
            menuPlayer.add(menuItem2Player);
            menuPlayer.setEnabled(false);
           
            
            
            menuOptions.add(menuPlayer);
			
			menuOptions.addSeparator();
			
			menuItemCows = new JMenu(lang_COWS);
			menuItemCows.addActionListener(this);
                        menuItemCows.setFont(gameMenuItemFont);
                        menuItemCows.setForeground(colorMenuItem);
                        menuItemCows.setBackground(colorMenuItemBg);
		
                        
                        //3 cows 
                      /*  menuItemCows3 = new JMenuItem(lang_menuItemCows3);
			menuItemCows3.addActionListener(this);
                        menuItemCows3.setFont(gameMenuItemFont);
                        menuItemCows3.setForeground(colorMenuItem);
                        menuItemCows3.setBackground(colorMenuItemBg);
            	       
                        menuItemCows.add(menuItemCows3);
                        */
                        //6 cows 
                        menuItemCows6 = new JMenuItem(lang_menuItemCows6);
			menuItemCows6.addActionListener(this);
                        menuItemCows6.setFont(gameMenuItemFont);
                        menuItemCows6.setForeground(colorMenuItem);
                        menuItemCows6.setBackground(colorMenuItemBg);
            	       
                        menuItemCows.add(menuItemCows6);
                        
                        //9 cows 
                        menuItemCows9 = new JMenuItem(lang_menuItemCows9);
			menuItemCows9.addActionListener(this);
                        menuItemCows9.setFont(gameMenuItemFont);
                        menuItemCows9.setForeground(colorMenuItem);
                        menuItemCows9.setBackground(colorMenuItemBg);
            	       
                        menuItemCows.add(menuItemCows9);
                        
                        //12 cows 
                        
                        menuItemCows12 = new JMenuItem(lang_menuItemCows12);
			menuItemCows12.addActionListener(this);
                        menuItemCows12.setFont(gameMenuItemFont);
                        menuItemCows12.setForeground(colorMenuItem);
                        menuItemCows12.setBackground(colorMenuItemBg);
            	       
                        menuItemCows.add(menuItemCows12);
            menuItemCows.setEnabled(false);
            menuOptions.add(menuItemCows);
			
			
			
			
			menuItemSound = new JMenu(lang_OSOUND);
			menuItemSound.addActionListener(this);
                        menuItemSound.setFont(gameMenuItemFont);
                        menuItemSound.setForeground(colorMenuItem);
                        menuItemSound.setBackground(colorMenuItemBg);
            
            ButtonGroup group = new ButtonGroup();

        rbMenuItemSoundOn = new JRadioButtonMenuItem(lang_OSOUND_ON);
        rbMenuItemSoundOn.setSelected(true);
        rbMenuItemSoundOn.setMnemonic(KeyEvent.VK_R);
                        rbMenuItemSoundOn.setFont(gameMenuItemFont);
                        rbMenuItemSoundOn.setForeground(colorMenuItem);
                        rbMenuItemSoundOn.setBackground(colorMenuItemBg);

        group.add(rbMenuItemSoundOn);
        rbMenuItemSoundOn.addActionListener(this);
        menuItemSound.add(rbMenuItemSoundOn);

        rbMenuItemSoundOff = new JRadioButtonMenuItem(lang_OSOUND_OFF);
        rbMenuItemSoundOff.setMnemonic(KeyEvent.VK_F);
        rbMenuItemSoundOff.setFont(gameMenuItemFont);
                        rbMenuItemSoundOff.setForeground(colorMenuItem);
                        rbMenuItemSoundOff.setBackground(colorMenuItemBg);
        group.add(rbMenuItemSoundOff);
        rbMenuItemSoundOff.addActionListener(this);
        menuItemSound.add(rbMenuItemSoundOff);
           
            menuOptions.add(menuItemSound);
			
			menuItemLangauge = new JMenu(lang_LANG);
			menuItemLangauge.addActionListener(this);
                        menuItemLangauge.setFont(gameMenuItemFont);
                        menuItemLangauge.setForeground(colorMenuItem);
                        menuItemLangauge.setBackground(colorMenuItemBg);
            	        
			menuItem = new JMenuItem("English");
			menuItem.addActionListener(this);
                        menuItem.setFont(gameMenuItemFont);
                        menuItem.setForeground(colorMenuItem);
                        menuItem.setBackground(colorMenuItemBg);
            	       
                        menuItemLangauge.add(menuItem);
            
            menuItem = new JMenuItem("Setswana");
			menuItem.addActionListener(this);
                        menuItem.setFont(gameMenuItemFont);
                        menuItem.setForeground(colorMenuItem);
                        menuItem.setBackground(colorMenuItemBg);
            	      
                        menuItemLangauge.add(menuItem);
            
            menuOptions.add(menuItemLangauge);
            
            
			menuBar.add(menuOptions);
			
			
			
			
			
			//Help Menu
			
			menuHelp = new JMenu(lang_HELP);
                        menuHelp.setFont(gameMenuFont);
			menuHelp.setForeground(colorMenu);
                        
                        
			menuHelp.setMnemonic(KeyEvent.VK_H);
			menuHelp.getAccessibleContext().setAccessibleDescription(
			        "View game Options");
			       
			       
			menuItemManual = new JMenuItem(lang_MANUAL,KeyEvent.VK_M);
			menuItemManual.addActionListener(this);
                        menuItemManual.setFont(gameMenuItemFont);
                        menuItemManual.setForeground(colorMenuItem);
                        menuItemManual.setBackground(colorMenuItemBg);
            	      
            menuHelp.add(menuItemManual);     
			
			menuItemAbout = new JMenuItem(lang_ABOUT,KeyEvent.VK_A);
			menuItemAbout.addActionListener(this);
                        menuItemAbout.setFont(gameMenuItemFont);
                        menuItemAbout.setForeground(colorMenuItem);
                        menuItemAbout.setBackground(colorMenuItemBg);
            	      
            menuHelp.add(menuItemAbout);
			
			menuBar.add(menuHelp);
			
		 	 }
     	 
    /**
     *This method handles all the game navigation events
     * @param ActionEvent - mouse click on the menuItem.
     */
    @Override
    public void actionPerformed(ActionEvent e)
     	 {
     	 	 JMenuItem source = (JMenuItem)(e.getSource());
     	 	String srcItem =  source.getText();
     	 	 
     	 	  //exit game
                if(lang_EXIT.equals(srcItem))
     	 	    {
                    langSetupDialog();
     	 	    int rep=JOptionPane.showConfirmDialog(null, 
                    lang_dialogExitMsg,lang_dialogExitTitle,JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE,new ImageIcon(this.getClass().getResource("res/images/faqs.png")));
                        if(rep==0)
                         {
                          System.exit(0);
     	 	          }
                   }
                //quit game
                if(lang_QUIT.equals(srcItem))
     	 	    {
                    langSetupDialog();
     	 	    int rep=JOptionPane.showConfirmDialog(null,
                    lang_dialogQuitMsg,lang_dialogExitTitle,JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE,new ImageIcon(this.getClass().getResource("res/images/faqs.png")));
                        if(rep==0 && boardGame.isVisible())
                         {
                          setSize(710,600);
                          boardGame.setVisible(false);
                           content_pane.remove(boardGame);
                            content_pane.add(wallPanel);
                            menuItemQuit.setEnabled(false);
                            menuItemSave.setEnabled(false);
                            disableCustMenus();
     	 	          }
                   }
                 //start a new game
     	 	 else if(lang_NEW.equals(srcItem))
     	 	    {
                      
     	 	   
                    setSize(800,600);
                       if(boardGame.isVisible()==false)
                           {
                            content_pane.remove(wallPanel);
                            content_pane.add(boardGame);
     	 	 	    boardGame.setVisible(true);
                            menuItemQuit.setEnabled(true);
                            menuItemSave.setEnabled(true);
     	 	          }
                        enableCustMenus();
                        boardGame.resetGame();
                    }
                 //load a saved game
     	 	 else if(lang_LOAD.equals(srcItem))
     	 	       {
     	 	 	 //System.out.println(lang_LOAD);

     	 	 	LoadForm lld = new LoadForm(boardGame,this);
                         lld.setVisible(true);
                        this.enableCustMenus();
                           
                           
                           
                 }
                //save game on store media.
     	 	 else if(lang_SAVE.equals(srcItem))
     	 	       {
                         SaveDialog sd=new SaveDialog(this,boardGame);
                         sd.setVisible(true);
     	 	      }

     	 	 else if(lang_PlayerName.equals(srcItem))
     	 	    {
     	 	 	 //System.out.println("player name change");
     	 	 	 UserName usrName = new UserName(boardGame);
                         usrName.setVisible(true);

     	 	     }
     	 	     
     	 	 /*
                  * set game mode to one player
                  */
                 else if(lang_OnePlayer.equals(srcItem))
     	 	    {
     	 	 	 
     	 	 	// System.out.println("player vs AI");
     	 	 	 boardGame.setPlayerMode(false);
                         boardGame.updatePlayerMode();
                         boardGame.upDateGameBoard();
                         UserName usrName = new UserName(boardGame);
                         usrName.setVisible(true);
                         boardGame.boardSaveNamesToFileDAT();
                        // }
                         //System.out.println("Player mode="+boardGame.getPlayerMode());
     	 	     }
                  /*
                   * set the game mode to verses mode.
                   */
     	 	   else if(lang_TwoPlayer.equals(srcItem))
     	 	    {
     	 	 	
     	 	 	 //System.out.println("player vs player");
     	 	 	  boardGame.setPlayerMode(true);
     	 	 	  boardGame.updatePlayerMode();
                          boardGame.upDateGameBoard();
     	 	 	  UserName usrName = new UserName(boardGame);
                          usrName.setVisible(true);
                          boardGame.boardSaveNamesToFileDAT();
     	 	 	 //System.out.println("Player mode="+boardGame.getPlayerMode());
     	 	     }

                 //activate game sound
     	 	 else if(lang_OSOUND_ON.equals(srcItem))
     	 	    {
     	 	 	// boardGame.setSound(false);
     	 	 	// System.out.println("sound on");
     	 	 		boardGame.music.ConfigSound(true);
     	 	     }
                  //deactivate sound
     	 	  else if(lang_OSOUND_OFF.equals(srcItem))
     	 	    {
     	 	 	// boardGame.setSound(false);
     	 	 	boardGame.music.ConfigSound(false);
     	 	 	 //System.out.println("sound off");
     	 	 	}   
              //set game langauge to English
             else if("English".equals(srcItem))
     	 	    {
     	 	 	
     	 	 	//boardGame.music.ConfigSound(false);
     	 	 	// System.out.println("langauge english");
     	 	 	 lang="EN";
     	 	 	 boardGame.setlanguage(lang);
     	 	 	 boardGame.upDateGameBoard();
     	 	 	  langSetupMenu();
     	 	 	 updateGameMenuTree();
     	 	 	}
                //set game langauge to Setswana
     	 	 else if("Setswana".equals(srcItem))
     	 	    {
     	 	 	  lang="TN";
     	 	 	  boardGame.setlanguage(lang);
     	 	 	   boardGame.upDateGameBoard();
     	 	 	//boardGame.music.ConfigSound(false);
     	 	 	 //System.out.println("langauge Setswana");
     	 	 	 langSetupMenu();
     	 	 	 updateGameMenuTree();
     	 	    	}
                     //customize the look and feel of the game.
                     else if(lang_KRAAL.equals(srcItem))
     	 	             {
                         
     	 	 	 themes thm = new themes(this,boardGame);
                         thm.setVisible(true);
                        
                     }
     	 	     //set the number of game caps to 3
                    /* else if(lang_menuItemCows3.equals(srcItem))
                        {
                         if(validateChangeToken() == true) {
                         System.out.println("Cows = 3");
                         boardGame.setGameTokens(3);
                         boardGame.resetGame();
                         }}*/
                    //set the number of game caps to 6
                   else if(lang_menuItemCows6.equals(srcItem))
                        {
                         if(validateChangeToken() == true) {
                         //System.out.println("Cows = 6");
                         boardGame.setGameTokens(6);
                         boardGame.resetGame();
                         }}
                   //set the number of game caps to 9
                   else if(lang_menuItemCows9.equals(srcItem))
                        {
                         if(validateChangeToken() ==true) {
                         //System.out.println("Cows = 9");
                         boardGame.setGameTokens(9);
                         boardGame.resetGame();
                         }
                         }
                   //set the number of game caps to 12
                   else if(lang_menuItemCows12.equals(srcItem))
                        {
                          if(validateChangeToken() ==true)
                          {
                          //System.out.println("Cows = 12");
                         boardGame.setGameTokens(12);
                         boardGame.resetGame();
                          }
                        }
                
                
                   //manual selection menuitem
                   else if(lang_MANUAL.equals(srcItem))
     	 	    {
     	 	       manual man = new manual(this,boardGame);
     	 	       man.setVisible(true);
     	 	     }
     	 	   else if(lang_ABOUT.equals(srcItem))
     	 	    {
     	 	     developer dinfo = new developer(new JFrame());
     	 	     dinfo.setVisible(true);
     	 	     //System.out.println("about");
     	 	     }
     	 	              
     	 	                   
     	 	}
          public void changeWorkSpaceByLoad()
          {
                langSetupMenu();
     	 	 	     updateGameMenuTree();
                             setSize(800,600);
                      if(boardGame.isVisible()==false)
                           {
                            content_pane.remove(wallPanel);
                            content_pane.add(boardGame);
     	 	 	    boardGame.setVisible(true);
                            menuItemQuit.setEnabled(true);
                            menuItemSave.setEnabled(true);
     	 	          }
                      boardGame.boardSaveNamesToFileDAT();
          }
          public void enableCustMenus()
          {
            menuPlayer.setEnabled(true);
            menuItemKraal.setEnabled(true);
            menuItemCows.setEnabled(true);
          }
          public void disableCustMenus()
          {
           menuPlayer.setEnabled(false);
           menuItemKraal.setEnabled(false);
           menuItemCows.setEnabled(false);
          }
         /**
          * This is a user confirmation handler before a user can change the
          * number of game CAP's used in Mhele board game.
          * @return boolean -true if response was YES and false if NO.
          */
         public boolean validateChangeToken()
         {
             langSetupDialog();
     	 	    int rep=JOptionPane.showConfirmDialog(null, 
                    lang_ChangeCowsMsg,lang_ChangeCowsTitle,JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE,new ImageIcon(this.getClass().getResource("res/images/faqs.png")));
                        if(rep==0)
                         {
                          return true;
     	 	          }
                    return false;
           }
         /**
          * This method set the menubar and menuItem langauge based on the
          * selected langauge.
          */
         public void langSetupMenu()
     	 {
     	 	if(lang.toUpperCase() == null ? "EN" == null : lang.toUpperCase().equals("EN"))
     	 	{
     	 	  lang_GAME="Game";
     	 	   lang_NEW="New Game";
     	 	   lang_LOAD="Load Game";
     	 	    lang_SAVE="Save Game";
                    lang_QUIT="Quit Game";
     	 	     lang_EXIT="Exit";
     	 	     
     	 	     lang_OPTION="Option";
     	 	     lang_OPLAYER="Player";
     	 	     lang_OnePlayer="One Player";
     	 	     lang_TwoPlayer="Two Players";
                     lang_PlayerName="Player Name";
     	 	     lang_OSOUND="Sound";
     	 	     lang_OSOUND_ON="On";
     	 	     lang_OSOUND_OFF="Off";
     	 	     lang_COWS="Cows";
                     
                     lang_menuItemCows3="Three";
                     lang_menuItemCows6="Six";
                     lang_menuItemCows9="Nine";
                     lang_menuItemCows12="Twelve";
                     
     	 	     lang_LANG="Langauge";
     	 	     lang_KRAAL="Game Board";
     	 	     
     	 	     lang_HELP="Help";
     	 	     lang_MANUAL="Game Manual";
     	 	     lang_ABOUT="About Mhele";
     	 	}
     	 	if(lang.toUpperCase() == null ? "TN" == null : lang.toUpperCase().equals("TN"))
     	 	{
     	 	  lang_GAME="Motshameko";
     	 	  lang_NEW="Simolola";
     	 	   lang_LOAD="Tlhatlhela";
     	 	    lang_SAVE="Boloka";
                    lang_QUIT="Tswala Losaka";
     	 	     lang_EXIT="Tswala Motshameko";
     	 	  
     	 	  lang_OPTION=  "Ithopele"; //"Ith�ph�l�";
     	 	  lang_OPLAYER="Modisa";
     	 	  lang_OnePlayer="Motshameki";
     	 	  lang_TwoPlayer="Batshameki";
                 lang_PlayerName="Maina a Badisa";
     	 	     
     	 	     lang_OSOUND="Modumo";
     	 	     
     	 	     lang_OSOUND_ON="Tsuba";
     	 	     lang_OSOUND_OFF="Tima";
     	 	     
     	 	     lang_COWS="Dikgomo";
                     lang_menuItemCows3="Tharo";
                     lang_menuItemCows6="Thataro";
                     lang_menuItemCows9="Bofera bongwe"; //"Bof�ra bongwe";
                     lang_menuItemCows12="Lesome le bobedi";//"Lesom� le bobedi";

     	 	     lang_KRAAL="Lesaka";
     	 	     lang_LANG="Puo";//"Pu�";
     	 	     
     	 	  lang_HELP="Thus�"; //"Thus�";
     	 	     lang_MANUAL="Bokana Ya Tshameko";
     	 	     lang_ABOUT="Ka Mhele";
     	 	     
     	 	}
     	 	}
         /**
          * This methods update the MenuBar and MenuItem to any swing change 
          * that was selected by the user
          */
         public void updateGameMenuTree()
     	 {
     	 		    
     	 	   
               // update game menu
     	 	   menuGame.setText(lang_GAME);
     	 	   menuItemNew.setText(lang_NEW);
     	 	   menuItemLoad.setText(lang_LOAD);
     	 	   menuItemSave.setText(lang_SAVE);
                    menuItemQuit.setText(lang_QUIT);
     	 	   menuItemExit.setText(lang_EXIT);
     	 	     
     	 	    
     	
     	 	     //update option menu
     	 	     menuOptions.setText(lang_OPTION);
     	 	     menuPlayer.setText(lang_OPLAYER);
                     menuItemPlayerName.setText(lang_PlayerName);
     	 	     menuItemSound.setText(lang_OSOUND);
     	 	     rbMenuItemSoundOn.setText(lang_OSOUND_ON);
     	 	     rbMenuItemSoundOff.setText(lang_OSOUND_OFF);
     	 	     menuItemCows.setText(lang_COWS);
                     //menuItemCows3.setText(lang_menuItemCows3);
                     menuItemCows6.setText(lang_menuItemCows6);
                     menuItemCows9.setText(lang_menuItemCows9);
                     menuItemCows12.setText(lang_menuItemCows12);
     	 	     menuItemLangauge.setText(lang_LANG);
     	 	     menuItemKraal.setText(lang_KRAAL);
     	 	     menuItemPlayerVsPC.setText(lang_OnePlayer); 
     	 	     menuItem2Player.setText(lang_TwoPlayer);
            
     	 	     
     	 	     
     	 	     
     	 	     //update help menu
     	 	     menuHelp.setText(lang_HELP);
     	 	     menuItemManual.setText(lang_MANUAL);
     	 	     menuItemAbout.setText(lang_ABOUT);
     	 		 SwingUtilities.updateComponentTreeUI(this);
		           
     	 	}	
         /**
          * This method sets the current selected game langauge for the dialog
          * windows.
          */
         public void langSetupDialog()
     	 {
     	 	if(lang.toUpperCase() == null ? "EN" == null : lang.toUpperCase().equals("EN"))
     	 	{
     	 	lang_dTitle="Game Rules";
     	 	lang_dMsg=" start the game by clicking on any\n node on the Mhele board";
     	 	lang_dPlayer="PLAYER ";
     	 	lang_dialogExitTitle ="Quit Game";
                lang_dialogExitMsg="Are you sure you want to exit Mhele?";
                lang_dialogQuitMsg="Are you sure you want to quit Mhele?";
                lang_ChangeCowsMsg="Changing the number of Tokens will restart the game.\nAre you sure you want to continue?";
                lang_ChangeCowsTitle="Change Cows";
                }
     	 	if(lang.toUpperCase() == null ? "TN" == null : lang.toUpperCase().equals("TN"))
     	 	{
     	 	lang_dTitle="Molao Wa Tshameko";
     	 	lang_dMsg=" Tlhopa lefelo mo Mheleng la go baya Kgomo ka go \ntobetsa mo go lone";
     	 	lang_dPlayer="Modisa ";
     	 	lang_dialogExitTitle ="Tswala Motshameko";
                lang_dialogExitMsg="A o batla go Tlhogela tshameko?";
                lang_dialogQuitMsg="A o batla go tswala  Motshameko?";
                lang_ChangeCowsMsg="A o batla go fetola palo ya dikgomo,\nKa go dira jalo go tla tshimola Motshameko?";
                lang_ChangeCowsTitle="Palo ya Dikgomo";
              
                }
     	}
        
	}