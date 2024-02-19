package mygame;
/*@programmer: BK Nyoni
 *@version:    0.1.0
 *
 */

import java.awt.BasicStroke;

import java.awt.Cursor;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Dimension;

import java.awt.Point;


import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;
import java.util.Vector;


/**
 * This is backbone of Mhele project.Graphics,game AI,sound ,cap shifting and
 * any class that this game uses is coordinated in by the board class.
 * @author BKNyoni
 */
public class board extends JPanel
  {
    final private String IMAGE_PATH_STR="res/images/";
    /**
     *A pointer to the theme folder.
     */
    final public String THEME_PATH_STR="res/images/themes/skins/";
    /**
     * All available Mhele theme that comes with the game
     */

    final public  String [] availableThemes={"Botswana","Cow","Giraffe","Leopard","Traditional"};
    public  String [] infoMsgThemes={"Botswana Vs HIV/AIDS","Youth against Alcohol","Arrive Alive,Speed Kills","Recycling Vs Pollution","Poaching is illegal"};
    public String campainMsg;
    /**
     *Background image for the Save dialog window
     */
    public String SaveDialogBackground; //saved
    /**
     * This is used for indexing or selecting available themes
     */
    public int THEME_POINTER =0;
    /**
     * temp storage for game sets during runtime
     */
    public Vector  vsets;
    Rectangle[] r =new Rectangle[25];
    
    Rectangle playArea = new Rectangle(30,30,550,490);
    Rectangle[] dockCapBoundries = new Rectangle[2];
    /**
     * A boundary around cap player images at the dock boundary
     */
    public int dock_cap_boundary = 1;  //0 inactivate; 1 boundary P1; 2 boundary P2
    /**
     * This is used as indicating the status of the drog mode.
     */
    public int drop_player_cap =0; //0 ready mode ; 1 drag mode
    /**
     *A pointer to a players images to be dragged in game Mode 0.
     */
    public int dragCapImage =0;
    /**
     *A pointer to a players images to be dragged in game Mode 0.
     */
    public int dragCapImageMode1=0;

    /**
     * Game rule timer that checks rule violation at a fixed duration
     */
    /**
     * AI logic for a virtual player is controlled or monited at fixed duration
     */
    /**
     *
     */
    protected Timer timerRules,timerVplayer,timerDemo;
    /**
     *
     */
    protected int Demovalue=0;
    /**
     *
     */
    public boolean PlayerMode = false; //false= p1 vs PC true=p1 vs p2
    /**
     *
     */
    public int gameLevel =0;
    /**
     *
     */
    public boolean ROAM_PLAYER1=false;
    /**
     *
     */
    public boolean ROAM_PLAYER1_MSG=false;
    
    /**
     *
     */
    public boolean ROAM_PLAYER2=false;
    /**
     *
     */
    public boolean ROAM_PLAYER2_MSG=false;
    
    /**
     *
     */
    public int  ROAM_COUNTER_MAX=12;
    /**
     *
     */
    public int  ROAM_COUNTER_P1=0;
    /**
     *
     */
    public int  ROAM_COUNTER_P2=0;
    
    private boolean DEBUG =true;
    /**
     *
     */
    public boolean ColumnFlag = false;
    /**
     *
     */
    public boolean RowFlag =false;
    
    /**
     *
     */
    public int dialogCounter=0;
    
    String DEBUG_STR_RECT="Board";
    String DEBUG_BOARD ="in Board";
    String DEBUG_STR_SRC="unknown"; 
    String DEBUG_STR_LOC="Unknown";
    String DEBUG_MOUSE ="Unknown";
    String NEXT_STR_POS="Moves:";
    
   
    int x,y;
    int xtemp,ytemp;
    int xboard,yboard;
    int Ms_count_Click =0;
    int POSITION =0;
    
    String PLAYER   ="PLAYER:";
    String lang_Player,lang_Player2;
    public String cust_player1=null;
    public  String cust_player2=null;
    public static String AI_PlayerName;
    String lang_capture_cow;
    int MD0_PLAYER=1;
    int C_PLAYER=1;
    int matrix_player=0; 
    int GAME_MODE=0;
    boolean CHANGE_MODE=false;
    int[] GROUP_MOVES={0};
    
    int PLAYER1_CAPS_DOCK_OPTION =0; 
    
    /**
     *
     */
    public int PLAYER1_CAPS_DOCK=12;     //saved
    /**
     *
     */
    public int PLAYER1_CAPS_BOARD=0;     //saved
    /**
     *
     */
    public int CAPS_CAPTURED_BY_PLAYER2=0; //saved
    
    /**
     *
     */
    public int PLAYER2_CAPS_DOCK=12;      //saved
    /**
     *
     */
    public int PLAYER2_CAPS_BOARD=0;      //saved
    /**
     *
     */
    public int CAPS_CAPTURED_BY_PLAYER1=0; //saved
    
    int START_POSITION =0;
    int END_POSITION=0;
    int TEMP_POSITION =0;
    
    boolean boardGameFlag,moveflag;
    boolean CAPTURE_FLAG = false; 
    
    final int PIECE_NUMBERS = 24;

  
     /* on decorations coordinates
      final int [][] xycords ={{0,183,286,378,378,380,286,184,185,122,285,454,456,455,283,112,118,
                                      25,285,536,536,536,285,25,25},
	                              {0,169,169,171,261,333,338,338,261,110,97,104,259,397,406,
                                      399,260,30,30,30,261,471,472,473,260}
	                              };*/
     //decorations coordinates
      final int [][] xycords ={{0,183,280,374,374,374,280,183,183,119,282,
	                             448,448,448,281,111,116,30,281,529,529,529,
	                              282,30,30},
	                              {0,162,162,162,247,316,324,324,249,104,92,98,248,380,387,
	                              381,247,30,30,30,249,450,450,450,248}
	                              };
    final int [][] capdockcords ={{600,690},{200,200}};
	  
   
	
	
	int [] CAP_LAYOUT={0,0,0,0,0,0,0,0,0,
	                     0,0,0,0,0,0,0,0,
	                     0,0,0,0,0,0,0,0};
	                                                   
        /**
         *
         */
        public Cursor cursor;
        private  mouseHandler gameMouseHandler;
        /**
          *
          */
         public int [] vPlayerLastMove={0,0,0,0}; //[0] location [1] current cap
                                                  //[2]caputer [3] current cap
         BasicStroke dashed;
        //===========================================
         /**
          *
          */
         /**
          *
          */
         /**
          *
          */
         /**
          *
          */
         /**
          *
          */
         public String SV_player1,SV_player2,SV_mheleBoard,SV_dock,SV_background;
         //==========================================
	
        shifting AImoves = new shifting(xycords); 
	gameAI    mheleAI = new gameAI();
	gameAI    rulesAI = new gameAI();
        canvasHandler imgHandler = new canvasHandler();
        soundHandler music = new  soundHandler();
        configHandler config = new configHandler();
      
        MsgClass  gameMsgObj= new MsgClass();

        /**
         *
         */
        public board()
        {
         this.intComponents();
         this.addGraphics();
         gameMsgObj.setBoard(this);
         gameMsgObj.saveNamesToFileDAT();
         }
        
    
    private void addGraphics()
    {
        SV_player1= IMAGE_PATH_STR+"Caps/set1a.png";
        SV_player2= IMAGE_PATH_STR+"Caps/set1b.png";
        SV_mheleBoard= THEME_PATH_STR+"Botswana.jpg";
        SV_dock= THEME_PATH_STR+"bwdock.jpg";
        SV_background= THEME_PATH_STR+"blue.jpg";
        campainMsg =infoMsgThemes[0];
        imgHandler.setPlayer1Img(SV_player1);
	imgHandler.setPlayer2Img(SV_player2);
        imgHandler.setGameBoard(SV_mheleBoard);
        imgHandler.setGameDockPanel(SV_dock);
        imgHandler.setGameBackground(SV_background);
        SaveDialogBackground="blue.jpg";
        THEME_POINTER =0;
    }
    /**
     *
     * @param myboard
     */
    public void updateThemes(String myboard)
    {
        String CAP_PATH = "Caps/";
       if(myboard == null ? availableThemes[0] == null : myboard.equals(availableThemes[0]))
         {
         THEME_POINTER =0;
         SV_dock=THEME_PATH_STR+"bwdock.jpg";
         imgHandler.setGameDockPanel(SV_dock); 
         SV_background=THEME_PATH_STR+"blue.jpg";
         imgHandler.setGameBackground(SV_background);
         
         //SaveDialogBackground ="blue.jpg";
         //add caps for the theme
         SV_player1=IMAGE_PATH_STR+CAP_PATH+"set1a.png";
         imgHandler.setPlayer1Img(SV_player1);
         SV_player2=IMAGE_PATH_STR+CAP_PATH+"set1b.png";
	imgHandler.setPlayer2Img(SV_player2);
        campainMsg=infoMsgThemes[0];
         }
        else if(myboard == null ? availableThemes[1] == null : myboard.equals(availableThemes[1]))
       {
         //setupCursor("whiteArrow.gif");
         THEME_POINTER=1;
         SV_dock=THEME_PATH_STR+"Cowdock.jpg";
         imgHandler.setGameDockPanel(SV_dock);
         SV_background=THEME_PATH_STR+"basket.jpg";
         imgHandler.setGameBackground(SV_background);
         //SaveDialogBackground ="basket.jpg";
         //add caps for the theme 
         SV_player1=IMAGE_PATH_STR+CAP_PATH+"set2a.png";
          imgHandler.setPlayer1Img(SV_player1);
          SV_player2=IMAGE_PATH_STR+CAP_PATH+"set2b.png";
	imgHandler.setPlayer2Img(SV_player2);
        campainMsg =infoMsgThemes[1];
        }
       else if((myboard == null ? availableThemes[2] == null : myboard.equals(availableThemes[2])) || (myboard == null ? availableThemes[3] == null : myboard.equals(availableThemes[3])))
         {
          SV_dock=THEME_PATH_STR+"dockpanel.jpg";
          imgHandler.setGameDockPanel(SV_dock);
          SV_background=THEME_PATH_STR+"green.jpg";
          imgHandler.setGameBackground(SV_background);
          
            if(myboard == null ? availableThemes[2] == null : myboard.equals(availableThemes[2]))
             {
               THEME_POINTER=2;
               SV_player1=IMAGE_PATH_STR+CAP_PATH+"set5a.png";
               imgHandler.setPlayer1Img(SV_player1);
               
               SV_player2=IMAGE_PATH_STR+CAP_PATH+"set5b.png";
	       imgHandler.setPlayer2Img(SV_player2);
               campainMsg =infoMsgThemes[2];
              }
            if(myboard == null ? availableThemes[3] == null : myboard.equals(availableThemes[3]))
            {
                THEME_POINTER=3;
                SV_player1=IMAGE_PATH_STR+CAP_PATH+"set4a.png";
                imgHandler.setPlayer1Img(SV_player1);
                SV_player2=IMAGE_PATH_STR+CAP_PATH+"set4b.png";
	        imgHandler.setPlayer2Img(SV_player2);
                campainMsg =infoMsgThemes[3];
             }
          }
        if(myboard == null ? availableThemes[4] == null : myboard.equals(availableThemes[4]))
       {
         THEME_POINTER=4;
         SV_dock=THEME_PATH_STR+"Tradock.jpg";
         imgHandler.setGameDockPanel(SV_dock);
         SV_background=THEME_PATH_STR+"green.jpg";
         imgHandler.setGameBackground(SV_background);
          //add caps for the theme 
         SV_player1=IMAGE_PATH_STR+CAP_PATH+"set3a.png";
          imgHandler.setPlayer1Img(SV_player1);
         SV_player2=IMAGE_PATH_STR+CAP_PATH+"set3b.png";
	imgHandler.setPlayer2Img(SV_player2);
        campainMsg =infoMsgThemes[4];
        }
       SV_mheleBoard=THEME_PATH_STR+myboard+".jpg";
       imgHandler.setGameBoard(SV_mheleBoard);
       
    }
    /**
     *
     * @param l
     */
    public void boardSaveNamesToFileDAT()
    {
         gameMsgObj.saveNamesToFileDAT();
     }
    public void setlanguage(String l)
     {
     	config.setlangauge(l);
     	gameMsgObj.setLangDialog(l);
     	mheleAI.setlanguage(l);
        gameMsgObj.saveNamesToFileDAT();
     	}
    /**
     *
     * @return
     */
    public String getlanguage()
     {
     	//System.out.println(config.getLang());
     	return config.getLang();
     	}
    
     /**
      *
      * @param cows
      */
     public void setGameTokens(int cows)
    {
        PLAYER1_CAPS_DOCK_OPTION = cows;
        if(PLAYER1_CAPS_DOCK_OPTION == 0)
        {
          PLAYER1_CAPS_DOCK_OPTION = 12;    
          }
       }
     /**
      *
      * @return
      */
     public int getGameTokens()
        {
        return PLAYER1_CAPS_DOCK_OPTION;
       }
     /**
      *
      */
     public void intComponents()
    {
      	 gameMouseHandler =new mouseHandler();
         setPreferredSize(new Dimension(800,600));
      	setSize(800,600);
           setBackground(Color.GREEN);//BLACK);
             
                setDoubleBuffered(true);
         
       
       	 addGameMouseEvents();
       	setPointsBoundary();
        setupGameCursor();
       	 setGameTokens(0);  
         music.ConfigSound(config.getVolume());
       	   
           PLAYER1_CAPS_DOCK =getGameTokens();
       	   PLAYER2_CAPS_DOCK=PLAYER1_CAPS_DOCK;
       	   setlangDock();
       	   setUpTimer(); //xman 
       	   //get player names for the AI class
           
            
    	}
     private void addGameMouseEvents()
     {
       this.addMouseListener(gameMouseHandler);  //new mouseHandler());
       this.addMouseMotionListener(gameMouseHandler);  //new mouseHandler());
     }
    /* private void removeGameMouseEvents()
     {
         this.removeMouseListener(gameMouseHandler);
         this.removeMouseMotionListener(gameMouseHandler);
     }*/
     /**
      *
      * @param md
      */
     public void setPlayerMode(boolean md)
    {
    	this.PlayerMode =md;
    	}
    /**
     *
     * @return
     */
    public boolean getPlayerMode()
    {
    	return this.PlayerMode; 
    	}		
    
    /**
     *
     */
    public void setUpTimer()
    {
         
             
         updatePlayerMode();
         timerRules = new Timer();
         timerRules.scheduleAtFixedRate(new rulesTimer(), 50, 100);
         
         //timerDemo = new Timer();
         //timerDemo.scheduleAtFixedRate(new DemoVersion(), 0, 10000);
      }
    /**
     *
     */
    public void updatePlayerMode()
     {
     
       boolean pmode= this.getPlayerMode();
      if(pmode==false)
            {
             timerVplayer = new Timer();
             timerVplayer.scheduleAtFixedRate(new virtualPlayer(), 100, 1000);
             PlayerMode=false;
             }
          if(pmode==true)
            {
             PlayerMode=true;
                 
          	 timerVplayer.cancel();
          	}	
     	}
     
     /**
      *
      */
     public void setupGameCursor()
    {
    //Get the default toolkit  
        Toolkit toolkit = Toolkit.getDefaultToolkit();  
        //Load an image for the cursor  
      Image image = toolkit.getImage(this.getClass().getResource("res/images/RedGlow.gif"));
    	Point hotSpot = new Point(0,0);  
    	cursor = toolkit.createCustomCursor(image, hotSpot, "RedGlow");
    	super.setCursor(cursor); 
    	if(image==null)
    	super.setCursor(new Cursor(Cursor.HAND_CURSOR));  
    	}
      
     //theme configure cursor
     /**
      *
      * @param cursorfile
      */
     public void setupCursor(String cursorfile)
        {
       //Get the default toolkit
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        //Load an image for the cursor
        Image image = toolkit.getImage(this.getClass().getResource("res/images/"+cursorfile));
    	Point hotSpot = new Point(0,0);
    	cursor = toolkit.createCustomCursor(image, hotSpot, "RedGlow");
    	super.setCursor(cursor);
    	if(image==null)
    	super.setCursor(new Cursor(Cursor.HAND_CURSOR));
      }
     /**
      *
      * @return
      */
     public Cursor getGameCursor()
      {
        return cursor;
     }		
     /**
      *
      */
     public void setPointsBoundary()
    {
        x = y = 30;
        //int adjustLoc =20;
         for(int i=1; i<=PIECE_NUMBERS; ++i)
           {
         r[i] =new Rectangle(xycords[0][i],xycords[1][i],45,45);
               
           }
         //add boundary for cap dock icons
         for(int i=0; i<dockCapBoundries.length; ++i)
            {
             dockCapBoundries[i]=new Rectangle(capdockcords[0][i],capdockcords[1][i],45,45);
             } 
             
    	}	
   
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        xboard=this.getWidth()-250;
        yboard=this.getHeight()-80;
        g2d.drawImage(imgHandler.getGameBackground(),0,0,800,600, this);
       
        g2d.drawImage(imgHandler.getGameBoard(),30,30,xboard,yboard,this);
       
        g2d.setColor(Color.BLACK);//WHITE);
        g2d.fillRect(580,30,185,yboard);
        //g2d.drawImage(imgHandler.tempImghandler("res/images/dockpanel.jpg"),580,30,200,yboard, this);
        g2d.drawImage(imgHandler.getGameDockPanel(),580,30,200,yboard, this);
       if(DEBUG){
        debug(g2d);	
        }
         	
         	 
        
        drawcaps(g2d);
        g2d.setColor(Color.GREEN); 
        g2d.drawRect(580,30,200,yboard);
        
   	    g2d.drawRect(30,30,xboard,yboard);
   	    
   	    
         g2d.setColor(Color.GREEN);
   	    g2d.drawRect(30,30,xboard,yboard);
   	    
   	   
   	    /*if(GAME_MODE==1)
   	      {
   	      	for(int z=1; z<r.length; ++z)
		   {
			 if(r[z].contains(x,y))
			  {
				g2d.drawRect(xycords[0][z],xycords[1][z],45,45);
	                     //45,45);
				break;
				}
             }

   	      }*/
   	    
   	    
   	    
   	    //600,250 690,250
   	    DrawDockPanel(g2d);
            DrawCapturedTarget(g2d,605,250,40);
            DrawCapturedTarget(g2d,695,250,40);
           DrawLastMove(g2d);
            DrawDragCapAtCursor(g2d);
             //
           Toolkit.getDefaultToolkit().sync();
        g.dispose();
        
         
        
    }
    /**
     *
     */
    public void upDateGameBoard()
    {
    	setlangDock();
    	}
    //new game load 
    /**
     * 
     */
    public void resetGame()
    {
     /*debug*/
    DEBUG_STR_RECT="Board";
    DEBUG_BOARD ="in Board";
    DEBUG_STR_SRC="unknown"; 
    DEBUG_STR_LOC="Unknown";
    DEBUG_MOUSE ="Unknown";
    NEXT_STR_POS="Moves:";
    //end of debug
    //addGameMouseEvents();
    Ms_count_Click =0;  
    POSITION =0;
    PLAYER   ="";
    C_PLAYER=1;
    MD0_PLAYER=1;
    GAME_MODE=0;
    dialogCounter=0;
    vPlayerLastMove[0]=0;
    vPlayerLastMove[1]=0;
    dragCapImageMode1=0;
     
    PLAYER1_CAPS_BOARD=0; //saved
    PLAYER2_CAPS_BOARD=0; //saved
    x=0;               //xx
    y=0;               //xx
    boardGameFlag=false;
    moveflag=false;
    ROAM_PLAYER1=false;
    ROAM_PLAYER1_MSG=false;
    
    ROAM_PLAYER2=false;
    ROAM_PLAYER2_MSG=false;
    CHANGE_MODE=false;
    ROAM_COUNTER_P1=0;
    ROAM_COUNTER_P2=0;
    //config.loadConfig();
    mheleAI.resetCAPS_CAPTURED();
    updatePlayerMode(); //18/01/2010
     //music.ConfigSound(config.getVolume());
     PLAYER1_CAPS_DOCK=getGameTokens(); //config.getCaps();
     PLAYER2_CAPS_DOCK=PLAYER1_CAPS_DOCK;
       	   setlangDock();
       	   
    CAPTURE_FLAG=false;
    drop_player_cap=0;
    dragCapImage=0;
    dock_cap_boundary=1;

    for(int rpoint=0; rpoint <=PIECE_NUMBERS; ++rpoint)
       {
       	 CAP_LAYOUT[rpoint]=0;
       	 }
     repaint();
        gameMsgObj.getStartGameMsg(C_PLAYER);
        gameMsgObj.saveNamesToFileDAT();
    }
    /**
     *
     * @param g2d
     * @param dx
     * @param dy
     * @param sc
     */
    public void DrawCapturedTarget(Graphics2D g2d,int dx,int dy,int sc)
    {
        int width =sc;
        int length=width;
        float dash1[] = {4.0f};
        dashed = new BasicStroke(3.0f, 
                                          BasicStroke.CAP_ROUND, 
                                          BasicStroke.JOIN_ROUND, 
                                          10.0f, dash1, 0.0f);
        g2d.setColor(Color.RED);
         g2d.setStroke(dashed);//new BasicStroke(2));
         g2d.drawRect(dx,dy,width,length);
         g2d.drawLine(dx,dy,dx+width-1,dy+length);
          g2d.drawLine(dx,dy+width,dx+width-1,dy);
         g2d.setColor(Color.BLACK);
         g2d.setStroke(new BasicStroke(1));
     }
    /**
     *
     * @param g2d
     */
    public void drawcaps(Graphics2D g2d)
    {
    	for(int cap=1; cap<CAP_LAYOUT.length; ++cap)
    	{
    		if(CAP_LAYOUT[cap] !=0)
    		  {
    		  
    		if(CAP_LAYOUT[cap]==1)
    		  {
    		       //g2d.drawImage(imgHandler.getPlayer1Img(), xycords[0][cap],xycords[1][cap],(int)(xboard*0.09),(int)(yboard*0.09), this);
    		     g2d.drawImage(imgHandler.getPlayer1Img(), xycords[0][cap],xycords[1][cap],50,50, this);
                  }
    		 if(CAP_LAYOUT[cap]==2)
    		    {
    		    //g2d.drawImage(imgHandler.getPlayer2Img(), xycords[0][cap],xycords[1][cap],(int)(xboard*0.09),(int)(yboard*0.09), this);	
    		    g2d.drawImage(imgHandler.getPlayer2Img(), xycords[0][cap],xycords[1][cap],50,50, this);
    		    
                   }
    		  } 
    	
    		}
       
    }
    /**
     *
     * @param g2d
     */
    public void DrawDockPanel(Graphics2D g2d)
    {
                                                //img1.jpg 600,250 690,250
        g2d.drawImage(imgHandler.tempImghandler("res/images/Cow1.png"),600,95,70,80,this);
        
        g2d.drawImage(imgHandler.getPlayer2Img(),600,250,(int)(xboard*0.09),(int)(yboard*0.09), this);
                                               //img2.jpg 
        g2d.drawImage(imgHandler.tempImghandler("res/images/Cow2.png"),690,95,70,80,this);
        g2d.drawImage(imgHandler.getPlayer1Img(),690,250,(int)(xboard*0.09),(int)(yboard*0.09), this);
       
        g2d.setColor(Color.WHITE);//BLACK);
        Font f = new Font("Comic Sans MS",Font.BOLD,12);
        g2d.setFont(f);
        g2d.drawString(campainMsg.toUpperCase(),590,320);

        if(cust_player1 !=null) {
            g2d.drawString( cust_player1,600,190);
            //AI_PlayerName=cust_player1;
           
            }
        if(cust_player1 ==null || (cust_player1 == null ? "" == null : cust_player1.equals("")) ) {
        g2d.drawString(lang_Player+" 1",600,190);//140);
        //g2d.drawRect(580,32,85,yboard-90);
        
        }
        g2d.drawImage(imgHandler.getPlayer1Img(),600,200,(int)(xboard*0.09),(int)(yboard*0.10), this);
        
         if(PlayerMode == true && cust_player2 !=null &&(cust_player2 == null ? "" != null : !cust_player2.equals("")))
           {
            lang_Player2 = cust_player2;
           }
        g2d.drawString(lang_Player2,680,190);
        //g2d.drawRect(670,32,80,yboard-90);
        g2d.drawImage(imgHandler.getPlayer2Img(),690,200,(int)(xboard*0.09),(int)(yboard*0.10), this);
         f = new Font("Comic Sans MS",Font.ITALIC,20);
        g2d.setFont(f);
	    g2d.drawString(Integer.toString(PLAYER1_CAPS_DOCK),650,230);
        g2d.drawString(Integer.toString(PLAYER2_CAPS_DOCK),740,230);
        
        CAPS_CAPTURED_BY_PLAYER1=mheleAI.getCAPS_CAPTURED_BY_PLAYER1();
        CAPS_CAPTURED_BY_PLAYER2 = mheleAI.getCAPS_CAPTURED_BY_PLAYER2();
        g2d.drawString(Integer.toString(CAPS_CAPTURED_BY_PLAYER1),650,280);//debug
        
        g2d.drawString(Integer.toString(CAPS_CAPTURED_BY_PLAYER2),740,280);
        DrawCurrentPlayer(g2d,590,450);
       
    	}
    
    /**
     *
     * @param g2d
     * @param loc_X
     * @param loc_Y
     */
    public void DrawCurrentPlayer(Graphics2D g2d,int loc_X,int loc_Y)
    {
     if(GAME_MODE==0)
           { 

             if(PlayerMode ==false && CAPTURE_FLAG ==true)
             { g2d.drawString(lang_capture_cow,loc_X,loc_Y); }
             
             else if(PlayerMode ==true && CAPTURE_FLAG==true) {
                 g2d.drawString(lang_capture_cow,loc_X,loc_Y);
             }

                
             else if(dock_cap_boundary==1)
                {
                 g2d.setColor(Color.YELLOW);
                 g2d.setStroke(new BasicStroke(3));
                 g2d.drawOval(604,200,(int)(xboard*0.07),(int)(yboard*0.09));
                 }
             else if(dock_cap_boundary==2)
                {
                 g2d.setColor(Color.GREEN);
                 g2d.setStroke(new BasicStroke(3));
                 g2d.drawOval(694,200,(int)(xboard*0.07),(int)(yboard*0.09));
                 }
                  }
           if (GAME_MODE==1)
           {
               if(PlayerMode ==false && C_PLAYER==2 && CAPTURE_FLAG==false)
                 { g2d.drawString(lang_Player2,loc_X,loc_Y); }
                 
               else if(PlayerMode == false && C_PLAYER==1 && CAPTURE_FLAG==false)
                 { g2d.drawString(PLAYER+C_PLAYER,loc_X,loc_Y); }
              
              else if(PlayerMode == false && CAPTURE_FLAG==true)
                 { g2d.drawString(lang_capture_cow,loc_X,loc_Y); }
 
             if(PlayerMode == true && CAPTURE_FLAG==false)
                 { g2d.drawString(PLAYER+C_PLAYER,loc_X,loc_Y); }
             
             else if(PlayerMode == true && CAPTURE_FLAG==true)
                 { g2d.drawString(lang_capture_cow,loc_X,loc_Y); }  
           }    
    }
    /**
     *
     * @param g2d
     */
    public void debug(Graphics2D g2d)
    {
         
         g2d.setColor(Color.GREEN);
         g2d.drawRect(x, y, 45,45);
        g2d.setColor(Color.RED);
        g2d.drawString("Main Board X:"+xboard+" Y:"+yboard,590,310);
        g2d.drawString("Mouse X:"+x+" Y:"+y,590,320);
        g2d.drawString(DEBUG_STR_LOC,590,330);
        g2d.drawString(DEBUG_STR_RECT,590,340);
        g2d.drawString(DEBUG_BOARD,590,350);
        g2d.drawString(DEBUG_MOUSE,590,360);
        g2d.drawString("AI Player:"+ MD0_PLAYER,590,370);
        g2d.drawString("Player: "+C_PLAYER,590,380);
        g2d.drawString("CAPTURE :"+CAPTURE_FLAG,590,390);
        g2d.setColor(Color.RED);
        for(int i=1; i<=PIECE_NUMBERS; ++i)
         {
         	
           g2d.drawRect(xycords[0][i],xycords[1][i],45,45);
           g2d.drawString("p:"+i,xycords[0][i],xycords[1][i]); 
          
          }
        for(int i=0; i<dockCapBoundries.length; ++i)
            {
             g2d.drawRect(capdockcords[0][i],capdockcords[1][i],45,45);
             g2d.drawString("p:"+i,capdockcords[0][i],capdockcords[1][i]); 
             } 
        
          g2d.drawString("GAME MODE: "+Integer.toString(GAME_MODE),590,410);
          int cp =6;
         for(int i=1; i<=PIECE_NUMBERS; ++i)
         {
         	g2d.drawString(Integer.toString(CAP_LAYOUT[i]),584+cp,400);
         	cp+=6;
          }   
   	   
       
    	}
    /**
     *
     * @param g2d
     */
    public void DrawLastMove(Graphics2D g2d)
    {
        int lastmoveAI = vPlayerLastMove[0]; 
        if(vPlayerLastMove[0] != 0 && vPlayerLastMove[1] !=0) 
        {
         g2d.setColor(Color.GREEN);
         g2d.setStroke(new BasicStroke(2));
         g2d.drawOval(xycords[0][lastmoveAI]+3,xycords[1][lastmoveAI]+1,44,44);
        }
     }
     	
    /**
     *
     */
    public void setlangDock()
     	 {
     	 	 
        if(getlanguage().toUpperCase() == null ? "EN" == null : getlanguage().toUpperCase().equals("EN"))
     	 	{
     	 	lang_Player="PLAYER ";
     	 	PLAYER=lang_Player;
                lang_Player2 ="PLAYER 2";
                lang_capture_cow="Capture Cow";
                  if(PlayerMode == false)
                  {
                     lang_Player2="COMPUTER";       
     	 	     }
                }
     	 	if(getlanguage().toUpperCase() == null ? "TN" == null : getlanguage().toUpperCase().equals("TN"))
     	 	{
     	 	lang_Player="MODISA";
     	 	PLAYER=lang_Player;
     	 	lang_Player2 ="MODISA 2";
                lang_capture_cow="Jaa Kgomo";
                if(PlayerMode == false)
                  {
                     lang_Player2="KHOMPUTARA";       
     	 	     }
                }
                //
     	}	
     /**
      *
      * @param g2d
      */
     public void DrawDragCapAtCursor(Graphics2D g2d)
     {
       int dxcap = x-25;
       int dycap = y-25;
       if(dragCapImage== 1 || dragCapImageMode1==1)
           {
    	   g2d.drawImage(imgHandler.getPlayer1Img(), dxcap,dycap,50,50, this);
           }
       if(dragCapImage==2 || dragCapImageMode1==2)
    	{
         g2d.drawImage(imgHandler.getPlayer2Img(),dxcap,dycap,50,50, this);
    	 }
       }
     

     /**
      *
      * @param e
      */
     public void CheckDockCapBoundaries(MouseEvent e)
        {
           //dock_cap_boundary = C_PLAYER;
            //System.out.println("cap dock player:"+PLAYER1_CAPS_DOCK);
            int xyplayer =0;
            for(int i=1; i<r.length; ++i) 
		{
	          if(r[i].contains(e.getX(),e.getY()))
	            {
                             xyplayer=i;
                            // System.out.println("cap checker loc:"+i);
                             break;
                    }
               }
            if(GAME_MODE==0 && CAPTURE_FLAG ==false && CAP_LAYOUT[xyplayer] ==0)
               {
                
                if(dock_cap_boundary==1 && drop_player_cap==0 && 
                  dockCapBoundries[0].contains(e.getX(),e.getY()) && PLAYER1_CAPS_DOCK >=0)
                  {
                    drop_player_cap=1;
                    dragCapImage=1;
                    PLAYER1_CAPS_DOCK-=1;
                    //System.out.println("cap block player 1->" + PLAYER1_CAPS_DOCK);
                    }
               if(dock_cap_boundary==2 && drop_player_cap==0 
                       && PLAYER2_CAPS_DOCK >=0 && dockCapBoundries[1].contains(e.getX(),e.getY())
                       && PlayerMode==true)
                     {
                       
                       drop_player_cap=1;
                       dragCapImage=2;
                       PLAYER2_CAPS_DOCK-=1;
                      // System.out.println("cap block player 2->"+ PLAYER2_CAPS_DOCK);

                     }
              //add AI for game mode 0
             }
        }   
     /**
      *
      * @param e
      *
      */
     public void CheckBoundaries(MouseEvent e)
	{
	         CheckDockCapBoundaries(e);  //debug
                  
		 if(playArea.contains(e.getX(),e.getY()))
		   {
		   	DEBUG_BOARD="in the Boundary";
		   	boardGameFlag =true;
		   	}
		  else if(!playArea.contains(e.getX(),e.getY()))
		   {
		   	DEBUG_BOARD="Out of Boundary"; 
		   	 boardGameFlag =false;
		   	} 	
		 for(int cxy=1; cxy<r.length; ++cxy) //compete this to cap checker
		   {
			 if(r[cxy].contains(e.getX(),e.getY()))
			  {
				
                               POSITION=cxy;
				DEBUG_STR_LOC ="Location:"+cxy;
				DEBUG_STR_RECT="RectX:"+r[cxy].getX() +" RectY:"+r[cxy].getY()+" WH"+r[cxy].getHeight();
				if( CAP_LAYOUT[POSITION] !=0)
				   {
				   	boardGameFlag=false;
				
			    	break;
				   }
				boardGameFlag=true;
                                
				break;
				}
			  if(!r[cxy].contains(e.getX(),e.getY()))
			    {
				DEBUG_STR_RECT ="location:Board ";
				boardGameFlag=false;
				}	
		    }

    }
    
     /**
      *
      * @param e
      */
     public void CheckBoundariesPoints(MouseEvent e)
	{
	 if(playArea.contains(e.getX(),e.getY()))
		   {
		   	DEBUG_BOARD="in the Boundary";
		   	boardGameFlag =true;
		   	}
		  else if(!playArea.contains(e.getX(),e.getY()))
		   {
		   	DEBUG_BOARD="Out of Boundary"; 
		   	 boardGameFlag =false;
		   	}
		   
		   
		   	for(int cxy=1; cxy<r.length; ++cxy)
		         {
			     if(r[cxy].contains(e.getX(),e.getY()) )
			        {
				       
				        // System.out.println(CAP_LAYOUT[cxy]+"L<->P "+C_PLAYER+" Mouse:"+Ms_count_Click);
				         POSITION=cxy;
				         DEBUG_STR_LOC ="Location:"+cxy;
				         DEBUG_STR_RECT="RectX:"+r[cxy].getX() +" RectY:"+r[cxy].getY()+" WH"+r[cxy].getHeight();
				         boardGameFlag=true;

				         break;
				        
				     }
				    
				    boardGameFlag =false;
				}
				if(!boardGameFlag)
				  {
				  	Ms_count_Click-=1;
				  	
				  	}
	  }
//18/01/2010
     /**
      *
      * @param player
      * @return
      */
     public boolean playerBlocked(int player)
	{
	  dialogCounter=+1;
         if(PLAYER1_CAPS_DOCK ==0 && PLAYER2_CAPS_DOCK==0)
           {
           rulesAI.rulesObj.PlayerCountRowSets(player);
	    boolean movement = rulesAI.rulesObj.hasMoves(player,CAP_LAYOUT);
	    int str_player =1;
	    //System.out.println("moves available "+ movement);
	    
	    if(movement==false)
	       {
	       	 if(!ROAM_PLAYER1 && player==1)
	       	   {
	       	    str_player= 1;
	       	    }
             if(!ROAM_PLAYER2 && player==2)
                {
                 	str_player= 2;
                	}
                 if(dialogCounter==1)
                 {
                 gameMsgObj.getMovesBlocked(str_player); 
                 resetGame();
                 //removeGameMouseEvents();
                 } 
	       	}
	       	return movement;
		}
         return true;
        }
 private class mouseHandler extends MouseAdapter
{
	
	public void MovetoBoard()
	{
	  	if(boardGameFlag)
			{
			
			Ms_count_Click++;
			NEXT_STR_POS="Next Moves:";
			if(PLAYER1_CAPS_DOCK ==0 || PLAYER1_CAPS_DOCK >0)
			{
			    //System.out.println("Mouse C1 "+ Ms_count_Click);
                            if(Ms_count_Click ==1)
				      {
				       //pathFindAnimation(new Point(30,30),new Point(200,200));
				       
                                       CAP_LAYOUT[POSITION]=1;
                                             if( drop_player_cap==0 && dragCapImage==0)
				   	     PLAYER1_CAPS_DOCK-=1;
				   	     music.addEffect();
				   	     C_PLAYER=1;
				   	     
				   	     MD0_PLAYER=2;
                                             dock_cap_boundary=2;
                                              drop_player_cap=0;
                                              dragCapImage=0;
				   	   repaint();
		               //timer.start();
				  	   
				       }
			 }
			 if(PLAYER2_CAPS_DOCK ==0 || PLAYER2_CAPS_DOCK >0)
			   {
				//System.out.println("Mouse C2 "+ Ms_count_Click);
                             if(Ms_count_Click ==2)
				       {
				       	
				       	  
				  	        CAP_LAYOUT[POSITION]=2;
                                                if( drop_player_cap==0 && dragCapImage==0)
				  	           PLAYER2_CAPS_DOCK-=1;
				  	       Ms_count_Click=0;
				  	      music.addEffect();
				  	      C_PLAYER=2;

				  	      
				  	     MD0_PLAYER=1;
                                             dock_cap_boundary=1;
                                               drop_player_cap=0;
                                               dragCapImage=0;
                                               
                                               repaint();

				  	    }
		  	    }
		  	    
		  	    
		  	    
		  	    
		  	  }  
		  	 	
	    	boardGameFlag =false;
           }
	public void MovetoBoardManager()
	{
	  if(CAPTURE_FLAG==false)
		   {
		    if(GAME_MODE==0)
		    MovetoBoard();
		    
		    
		    
		    mheleAI.updateStatus(CAP_LAYOUT,POSITION,C_PLAYER);
		    CAPTURE_FLAG=mheleAI.CheckMoveSet();
		     if(CAPTURE_FLAG==false)
		        {
		        	POSITION=0;
		        	//test fix
		        }
		      repaint();
		      
		    }
		  if(CAPTURE_FLAG)
		      {
		      	//timer.cancel();
		      	music.addCowEffect();
		      	mheleAI.setCurrentPoint(POSITION);
                        //System.out.println("Targer "+mheleAI.getCurrentPoint()+" "+C_PLAYER);
		      	CAPTURE_FLAG=mheleAI.captureKgomo();
		      	CAP_LAYOUT=mheleAI.getCapLayout();
		      	
		      	}
         
                      
                      
                     
		}
	public void MoveInBoard()
	{
		
		boolean checkdest=false;
		if(boardGameFlag)
			{
			
		
			NEXT_STR_POS="Next Moves:";
		
		        
		     switch(Ms_count_Click)
		        {
		            
		            case 1:
		             {
		               if(CAP_LAYOUT[POSITION]==1)
		               {
		                C_PLAYER=1;
		                START_POSITION=POSITION;
		                xtemp=x;
		                ytemp=y;
                                dragCapImageMode1=1;
                                CAP_LAYOUT[START_POSITION]=0;
		               // System.out.println("start point:"+START_POSITION);
		               playerBlocked(1);  //18/01/2010
		               break;
		                }
		                if(CAP_LAYOUT[POSITION]!=1)
		                {
		                	 START_POSITION=0;
		                	 END_POSITION=0;
		                	 C_PLAYER=1;
		                	 Ms_count_Click=0;
		                	 //JOptionPane.showMessageDialog(null,"PLAYER "+C_PLAYER+" please rethink your move","Invalid Move",JOptionPane.WARNING_MESSAGE);
		                	 gameMsgObj.getInvalidMoveMsg(C_PLAYER);
		                	 break;
		                	} 	   
				      }
				 case 2:
			         {
			         if(CAP_LAYOUT[POSITION]!=0)
	 	                  {
	 	                  	C_PLAYER=1;
                                         CAP_LAYOUT[START_POSITION]=1; //xx
	 	                  	START_POSITION=0;
	 	                  	END_POSITION=0;
                                         dragCapImageMode1=0; //15/04/2010
				            TEMP_POSITION=END_POSITION;
				            //JOptionPane.showMessageDialog(null,"PLAYER "+C_PLAYER+" please rethink your move","Invalid Move",JOptionPane.WARNING_MESSAGE);
		                	gameMsgObj.getInvalidMoveMsg(C_PLAYER);
		                	Ms_count_Click =0;
	 	                  	break;
	 	                  	}	
			         if(CAP_LAYOUT[POSITION]==0)
		               {
				     
                                     C_PLAYER=1;
				      END_POSITION=POSITION;
				      //System.out.println("end point:"+END_POSITION);
				      
				       GROUP_MOVES=AImoves.validMoves(START_POSITION,CAP_LAYOUT);
				      
				        if(ROAM_PLAYER1)
				           { GROUP_MOVES=AImoves.validMovesRoaming(CAP_LAYOUT);
				             ROAM_COUNTER_P1+=1;
				             }
				      
				      for(int i=0; i<GROUP_MOVES.length;++i)
	                      {
	 	                    //System.out.println(GROUP_MOVES[i]);
	 	                    if(GROUP_MOVES[i]==END_POSITION)
	 	                       {
	 	                       	 //System.out.println("Destination:"+END_POSITION);
	 	                       	 checkdest =true;
	 	                       	 dragCapImageMode1=0;
                                         CAP_LAYOUT[START_POSITION]=0;
	 	                       	 CAP_LAYOUT[END_POSITION]=1;
	 	                         //boardMoveCapture(); //add update position 
	 	                                               //update game AI
	 	                         mheleAI.updateStatus(CAP_LAYOUT,POSITION,C_PLAYER); //debugging
		                         CAPTURE_FLAG=mheleAI.CheckMoveSet();  //debugging
		                         
	 	                         START_POSITION=0;
	 	                         TEMP_POSITION=END_POSITION;
				                 END_POSITION=0;
	 	                       	 C_PLAYER = 2;
	 	                       	  MD0_PLAYER=2;
	 	                       	 break;
	 	                       	 }
	 	                       	 NEXT_STR_POS+=GROUP_MOVES[i]+",";
	 	                   }
	 	                    
	 	                    
	 	                    if(!checkdest)
				              {
                                        dragCapImageMode1=0;
                                        CAP_LAYOUT[START_POSITION]=1; //xx
				              MD0_PLAYER=1;	
				          	  C_PLAYER = 1;
				          	START_POSITION=0;
	 	                    END_POSITION=0;
	 	                    TEMP_POSITION=END_POSITION;
	 	                    Ms_count_Click=0;
	 	                    //JOptionPane.showMessageDialog(null,"PLAYER "+C_PLAYER+" please rethink your move","Invalid Move",JOptionPane.WARNING_MESSAGE);
		                  	 gameMsgObj.getInvalidMoveMsg(C_PLAYER);
				          	}
	 	                  }
	 	                  
				        
				  	   //Ms_count_Click=0;
				  	   break;
				  	   }
				case 3:
		             {
		             	if(CAP_LAYOUT[POSITION]==2)
		                  {
		                    C_PLAYER=2;
		                    START_POSITION=POSITION;
                                    CAP_LAYOUT[START_POSITION]=0;
		                    xtemp=x;
		                    ytemp=y;
                                    dragCapImageMode1=2;
		                   // System.out.println("start point:"+START_POSITION);
				            //testing
				             playerBlocked(2); //18/01/2010
				            break;
				          }
				         if(CAP_LAYOUT[POSITION]!=2)
		                   {
		                	 START_POSITION=0;
		                	 END_POSITION=0;
		                	 TEMP_POSITION=END_POSITION;
		                	 C_PLAYER=2;
		                	 Ms_count_Click=2;
		                	  //JOptionPane.showMessageDialog(null,"PLAYER "+C_PLAYER+" please rethink your move","Invalid Move",JOptionPane.WARNING_MESSAGE);
		                	  gameMsgObj.getInvalidMoveMsg(C_PLAYER);
		                	 break;
		                	} 
				       	   
				      }
				    case 4:
			         {
			         	 checkdest=false;
			          if(CAP_LAYOUT[POSITION]!=0)
	 	                  {
	 	                  	C_PLAYER=2;
                                        CAP_LAYOUT[START_POSITION]=2;
	 	                  	START_POSITION=0;
	 	                  	END_POSITION=0;
				            TEMP_POSITION=END_POSITION;
				            Ms_count_Click =2;
                                            dragCapImageMode1=0; //15/04/2010
				             //JOptionPane.showMessageDialog(null,"PLAYER "+C_PLAYER+" please rethink your move","Invalid Move",JOptionPane.WARNING_MESSAGE);
		                	gameMsgObj.getInvalidMoveMsg(C_PLAYER);
	 	                  	break;
	 	                  	}
	 	               if(CAP_LAYOUT[POSITION]==0)
	 	                  {   		
				       C_PLAYER=2;
				       END_POSITION=POSITION;
				    //  System.out.println("end point:"+END_POSITION);
				       
				       
				       GROUP_MOVES=AImoves.validMoves(START_POSITION,CAP_LAYOUT);
				       
				       if(ROAM_PLAYER2)
				           { GROUP_MOVES=AImoves.validMovesRoaming(CAP_LAYOUT);
				             ROAM_COUNTER_P2+=1;
				           }
				      
				       
				      for(int i=0; i<GROUP_MOVES.length;++i)
	                      {
	 	                   // System.out.println(GROUP_MOVES[i]);
	 	                    if(GROUP_MOVES[i]==END_POSITION)
	 	                       {
	 	                       	// System.out.println("correct destination"+END_POSITION);
	 	                       	 checkdest =true;
	 	                       	 CAP_LAYOUT[START_POSITION]=0;
	 	                       	 CAP_LAYOUT[END_POSITION]=2;
	 	                       	dragCapImageMode1=0;
	 	                       	 //boardMoveCapture();
	 	                       	 //boardMoveCapture(); //add update position 
	 	                                               //update game AI
	 	                         mheleAI.updateStatus(CAP_LAYOUT,POSITION,C_PLAYER); //debugging
		                         CAPTURE_FLAG=mheleAI.CheckMoveSet();  //debugging
	 	                       	 
	 	                       	 C_PLAYER =1;
	 	                       	 START_POSITION=0;
	 	                       	 TEMP_POSITION=END_POSITION;
	 	                       	 END_POSITION=0;
	 	                       	 Ms_count_Click=0;
	 	                       	 break;
	 	                       	 }
	 	                       	 NEXT_STR_POS+=GROUP_MOVES[i]+",";
	 	                   }
				        if(!checkdest)
				          {
				          dragCapImageMode1=0;
                                            C_PLAYER = 2;
                                            CAP_LAYOUT[START_POSITION]=2;
				          	START_POSITION=0;
	 	                    END_POSITION=0;
	 	                    TEMP_POSITION=END_POSITION;
	 	                    Ms_count_Click=2;      	 
				          	 //JOptionPane.showMessageDialog(null,"PLAYER "+C_PLAYER+" please rethink your move","Invalid Move",JOptionPane.WARNING_MESSAGE);
		                	gameMsgObj.getInvalidMoveMsg(C_PLAYER);
				          	}
				  	   //Ms_count_Click=0;
				  	   break;
				  	   }
				  	 } 
				  	//default:
				  	//Ms_count_Click =0;    
             
				 }
		  	    }
		  	   
		  	    boardGameFlag =false;
		  	    
	}
    public void MoveInBoardManager()
    {
      if(CAPTURE_FLAG==false)
		   {
		    if(GAME_MODE==1)
                    {
                        MoveInBoard();
                     }
		   
                    //System.out.println("PP_CAP "+TEMP_POSITION);
		    
		    	
    	}
    	if(CAPTURE_FLAG)
    	  {
    	  	 //System.out.println("must capture kgomo");
    	  	 //debug 
    	  	 music.addCowEffect();
		      	mheleAI.setCurrentPoint(POSITION);
		      	//System.out.println("Targer "+mheleAI.getCurrentPoint()+" "+C_PLAYER);
		      	CAPTURE_FLAG=mheleAI.captureKgomo();
		      	CAP_LAYOUT=mheleAI.getCapLayout();
    	  	 //debug ends
    	  	
    	  	}
    	  	
    
    	
    }
    public void boardMoveCapture()
    {
    	  mheleAI.updateStatus(CAP_LAYOUT,POSITION,C_PLAYER);
		  CAPTURE_FLAG=mheleAI.CheckMoveSet(); 
    	}				
	
			
	  // Handles the event of the user pressing down the mouse button.
        @Override
 	public void mousePressed(MouseEvent e)
	{
         x=e.getX()-((int)(xboard*0.08))/2; 
         y=e.getY()-((int)(xboard*0.08))/2;
         
         DEBUG_MOUSE="Mouse:pressed";
         updateLocation(e);
         repaint();
		}
	

        // Handles the event of a user dragging the mouse while holding down the mouse button.
        @Override
	public void mouseDragged(MouseEvent e)
	{
            x=e.getX()-((int)(xboard*0.08))/2; 
            y=e.getY()-((int)(xboard*0.08))/2;
       DEBUG_MOUSE="Mouse:dragged";
           
           
	   updateLocation(e);
	   
	  
	
	}

        // Handles the event of a user releasing the mouse button.
        @Override
	public void mouseReleased(MouseEvent e)
	 {
            x=e.getX();
	    y=e.getY();
         updateLocation(e);
         repaint();
         }

	// This method required by MouseListener.
        @Override
	public void mouseMoved(MouseEvent e){
		DEBUG_MOUSE="Mouse:moved";
		x=e.getX();
		y=e.getY();
		repaint();
		}

        // These methods are required by MouseMotionListener.
        @Override
	public void mouseClicked(MouseEvent e)
	  {
	    PLAYER1_CAPS_BOARD=mheleAI.getPLAYER1_CAPS_BOARD();
	    PLAYER2_CAPS_BOARD=mheleAI.getPLAYER2_CAPS_BOARD();

            if(PLAYER1_CAPS_DOCK ==0 && PLAYER2_CAPS_DOCK==0 && dragCapImage==0)
               {
                 
                 if(!CAPTURE_FLAG)
		      Ms_count_Click++;

		      //System.out.println("debug"+Ms_count_Click);
		      CheckBoundariesPoints(e);
		      MoveInBoardManager();
		      repaint();
              }
            if(PLAYER1_CAPS_DOCK != 0 || PLAYER2_CAPS_DOCK != 0 && GAME_MODE==0)
                   {
                    CheckBoundaries(e);
                    MovetoBoardManager();
		    repaint();
                 }
		 
             if(PLAYER1_CAPS_DOCK ==0 && PLAYER2_CAPS_DOCK==0 && dragCapImage==2)
               {
                CheckBoundaries(e);
                MovetoBoardManager();
		repaint();
                }
	      
	    
           





	    PLAYER1_CAPS_BOARD=mheleAI.getPLAYER1_CAPS_BOARD();
	    PLAYER2_CAPS_BOARD=mheleAI.getPLAYER2_CAPS_BOARD();
	    //System.out.println("On pieces "+ PLAYER1_CAPS_BOARD+" P1<->P2 "+ PLAYER2_CAPS_BOARD);


	     rulesAI.updateStatus(CAP_LAYOUT,POSITION,C_PLAYER);
	    rulesAI.rulesObj.PlayerCountRowSets(C_PLAYER);
             
             repaint();
	   }
        @Override
	public void mouseExited(MouseEvent e){}
        @Override
	public void mouseEntered(MouseEvent e){}
	public void updateLocation(MouseEvent e){
        
	     
	     if(boardGameFlag)
	     {
	       
	     x=e.getX()-((int)(xboard*0.08))/2; 
             y=e.getY()-((int)(xboard*0.08))/2;
              DEBUG_MOUSE="Mouse:update";
             boardGameFlag=false;
	     }
             repaint();
	    }

}
	
		
	
	//to be moved to gameAI
 /**
  *
  */
 public class rulesTimer extends TimerTask
	{
        @Override
	   public void run()
	   {
	   	RoamCounterChecker();
	   	PlayerHasFewCows();
	   	setRoamingMode();
	   	gameBoardFull();
                updateChangeMode();
                
            }
		public void updateChangeMode()
                {
                 if(PLAYER1_CAPS_DOCK==0 && PLAYER2_CAPS_DOCK==0 
                    && PLAYER1_CAPS_DOCK_OPTION !=3 && drop_player_cap==0)
                    {
                        if(CHANGE_MODE==false)
                        {
                             Ms_count_Click=0;
		             GAME_MODE=1;      //change to board movements
		             C_PLAYER=1;
                            gameMsgObj.getChangeModeGameMsg();
                            CHANGE_MODE=true;
                         }
                   }
                }
		//called if cows are less than three; 
           /**
            *
            */
           public void PlayerHasFewCows()
		{
		 if(PLAYER1_CAPS_DOCK==0)
	   	  {
	   	   if(mheleAI.getPLAYER1_CAPS_BOARD()<3 && GAME_MODE==1 && dragCapImageMode1==0)
	   	     {
	   	     //JOptionPane.showMessageDialog(null,"WINNER Player"+2,"GAME END",
	   	       //JOptionPane.INFORMATION_MESSAGE,new ImageIcon("Cow.png"));
             gameMsgObj.getWinnerMsg(2);
             resetGame();
             //removeGameMouseEvents();
	   	      	
	   	  	}}
	   	  if(PLAYER2_CAPS_DOCK==0)
	   	  {
	   	  	if(mheleAI.getPLAYER2_CAPS_BOARD()<3 && GAME_MODE ==1 && dragCapImageMode1==0)
	   	      {
	   	  	 //JOptionPane.showMessageDialog(null,"WINNER Player"+1,"GAME END",
	   	  	 //JOptionPane.INFORMATION_MESSAGE,new ImageIcon("Cow.png"));
              gameMsgObj.getWinnerMsg(1);
              resetGame();
	   	//removeGameMouseEvents();
	   	  	}
	   	 }	
		}
                /**
                 *
                 */
                public void gameBoardFull()
		{
		 if(PLAYER1_CAPS_DOCK==0 && PLAYER2_CAPS_DOCK==0 &&
		     mheleAI.rulesObj.isBoardFull())
	   	  {
	   	  
	   	     
	   	  	 
              gameMsgObj.getDrawGameMsg();
              resetGame();
	   	//removeGameMouseEvents();
	   	  	}
	   	 	
		}
            /**
             *
             */
            public void setRoamingMode()
		{
		  if(PLAYER1_CAPS_DOCK==0 ||PLAYER2_CAPS_DOCK==0)
	   	    {
	   	      if(mheleAI.getPLAYER1_CAPS_BOARD()==3 && dragCapImageMode1 ==0
                         && dragCapImage==0)
	   	        {
	   	     	   if( ROAM_PLAYER1_MSG == false)
                               {
                               gameMsgObj.getRoamingMsg(1);
                                ROAM_PLAYER1_MSG=true;
                              }
                            ROAM_PLAYER1=true;	
			   }
			  if(mheleAI.getPLAYER2_CAPS_BOARD()==3 && dragCapImageMode1==0
                             && dragCapImage==0)
	   	            {
                              if( ROAM_PLAYER2_MSG == false && PlayerMode==true)
                               {
                               gameMsgObj.getRoamingMsg(2);
                                ROAM_PLAYER2_MSG=true;
                              }
	   	     	      ROAM_PLAYER2=true;	
			   } 
		 }    
	 
	    }
                /**
                 *
                 */
          public void RoamCounterChecker()
	    {
	    	if(ROAM_COUNTER_P1>= ROAM_COUNTER_MAX)
	    	  {
	             gameMsgObj.getMovesDone(2);
                     resetGame();
                     //removeGameMouseEvents();
	    		}
	    	if(ROAM_COUNTER_P2>= ROAM_COUNTER_MAX)
	    	  {
	           gameMsgObj.getMovesDone(1);
                   resetGame();
                   //removeGameMouseEvents();
	    	   }	
	    	}
  }
  
 /**
  *
  */
 public class DemoVersion extends TimerTask
  {
        @Override
  	public void run()
  	{
  		Demovalue+=1;
  		if(Demovalue==4)
  		  {
  		  	//System.exit(0);
  		  	}
  		}
  	}
   //vitual player (machine player)
  /**
   *
   */
  public class virtualPlayer extends TimerTask
{
	 // boolean setChecker= mheleAI.rulesObj.canCaputer(c_position);
	 //unlock set for v player
        @Override
	 public void run()
	 {
	    matrix_player =1;
	    if(PlayerMode ==false)
            {
	    if(MD0_PLAYER==2 && GAME_MODE ==0)
	      {
	      	 if(!CAPTURE_FLAG)
	      	 {
	      	 POSITION=gen_location();
                  CAP_LAYOUT[POSITION] =2;
                  vPlayerLastMove[0]=POSITION;
                  vPlayerLastMove[1]=2;
                  
	          C_PLAYER=2;
	          
	          mheleAI.updateStatus(CAP_LAYOUT,POSITION,C_PLAYER);
	          playerBlocked(2); //18/01/2010
		      CAPTURE_FLAG=mheleAI.CheckMoveSet();
	              MD0_PLAYER=1;
	 	      PLAYER2_CAPS_DOCK-=1;
		      Ms_count_Click=0;
		      music.addEffect();
		      
              repaint();
              if(CAPTURE_FLAG)
                 {
                 POSITION=applySetRule();//gen_Oppenent_Loc();
                 vPlayerLastMove[0]=POSITION;
                 vPlayerLastMove[1]=2;
                 //System.out.println("Cx="+mheleAI.rulesObj.canCaputer(POSITION)); //debugging
                 music.addCowEffect();
		      	 mheleAI.setCurrentPoint(POSITION);
		      	
		          CAPTURE_FLAG=mheleAI.captureKgomo();
		        	CAP_LAYOUT=mheleAI.getCapLayout();
		      		//playerBlocked(2); //18/01/2010
                               
                                repaint();
                 	}
              }
                //resetting drag cap logic for player 1;
                 if(dock_cap_boundary==2 && drop_player_cap==0)
                 {
                   dock_cap_boundary=1;
                   drop_player_cap=0;
	 	   }
               }
	 	   if(!CAPTURE_FLAG)
	 	    {
	 	    
	 	   if(C_PLAYER==2 && GAME_MODE ==1)
	 	      {
	 	      if(mheleAI.getPLAYER2_CAPS_BOARD() > 2)	
                      {
                          if(!CAPTURE_FLAG)
	 	      	   {
	 	      	     //do advance move 
                              //if(gameLevel ==1)
                                // {
                                 gameLevel= mheleAI.rulesObj.BuildComputerSetMode1(); //19/01/2010
                                  
                                 repaint();
                                  CAPTURE_FLAG= mheleAI.rulesObj.getTempFlag();
                                  //}
                               //or this random move
                              if(gameLevel==0)
                              {
                               gen_virtualPlayer_Loc();
                                //gameLevel=1;
                                }
	 	      	   
	 	      	   }
	     if(CAPTURE_FLAG)
                 {
                 C_PLAYER=2;
                 	
                 POSITION=applySetRule();//gen_Oppenent_Loc();
                 vPlayerLastMove[0]=POSITION;
                  vPlayerLastMove[1]=2;
                  mheleAI.setCurrentPoint(POSITION);
                  //playerBlocked(C_PLAYER); //debugging
                   //System.out.println("Cx="+mheleAI.rulesObj.canCaputer(POSITION)); //debugging
		            CAPTURE_FLAG=mheleAI.captureKgomo();
		        	 CAP_LAYOUT=mheleAI.getCapLayout();
		      		
                 	
                 	
                 	 music.addCowEffect();
                 	repaint();
                 	}
                  CAPTURE_FLAG=false;	
                  Ms_count_Click=0;	
                  POSITION=0;	   
	 	      	  C_PLAYER=1;
	 	      	  MD0_PLAYER=1;
                           
                          repaint();
	 	      	}
	 	   }
                  }  
            }   }
        /**
         *
         */
        public void gen_virtualPlayer_Loc()
	{
	 boolean flagLoc= true;
		 int location =0; 
                  if(playerBlocked(2)) //18/01/2010
                  {
                      while(flagLoc)
		  {
		    
		    location= (int)(PIECE_NUMBERS*Math.random() + 1); 
		    if(CAP_LAYOUT[location]==0 ||CAP_LAYOUT[location]==1)
		      {
		      	flagLoc = true;
		      	}
		     if(CAP_LAYOUT[location]==2)
		       {
		       	C_PLAYER = 2; 
		       	 POSITION =location;
		       	  //==============================
		       	   GROUP_MOVES=AImoves.validMoves(POSITION,CAP_LAYOUT);
		       	    
		       	   if(mheleAI.getPLAYER2_CAPS_BOARD()==3)
				           { 
				          
				           GROUP_MOVES=AImoves.validMovesRoaming(CAP_LAYOUT);
				             ROAM_COUNTER_P2+=1;
				           }
				      for(int i=0; i<GROUP_MOVES.length;++i)
	                      {
	 	                   // System.out.println(GROUP_MOVES[i]);
	 	                    if(CAP_LAYOUT[GROUP_MOVES[i]]==0)
	 	                       {
	 	                      // 	 System.out.println("Destination:"+END_POSITION);
	 	                       	 flagLoc = false;
                                        CAP_LAYOUT[POSITION]=0;
                                         /*pathFindAnimation(new Point( xycords[0][POSITION],xycords[1][POSITION]),
                                         new Point(xycords[0][GROUP_MOVES[i]],xycords[1][GROUP_MOVES[i]]));
	 	                       	 */
	 	                       	 CAP_LAYOUT[GROUP_MOVES[i]]=2;
                                         location=GROUP_MOVES[i];
                                         vPlayerLastMove[0]=GROUP_MOVES[i];
                                         vPlayerLastMove[1]=2;
	 	                         //boardMoveCapture(); //add update position 
	 	                                               //update game AI
	 	                         mheleAI.updateStatus(CAP_LAYOUT,GROUP_MOVES[i],C_PLAYER); //debugging
		                         CAPTURE_FLAG=mheleAI.CheckMoveSet();  //debugging
		                         
	 	                         
	 	                       	 C_PLAYER = 2;
	 	                       	 repaint();
	 	                       	 break;
	 	                       	 }
	 	                       	 NEXT_STR_POS+=GROUP_MOVES[i]+",";
	 	                   }
		       	 
		       	 //===============================
		       	 //location;
                           vPlayerLastMove[0]=location;
                           vPlayerLastMove[1]=2;
                           repaint();
		       	 } 	
		 }
                }
		//return location;
                
	 } 	
        /**
         *
         * @return
         */

        public int  gen_Oppenent_Loc()
    {
    	boolean flagLoc= true;
		 
		 int location =0;
		  
		 while(flagLoc)
		  {
		    
		    location= (int)(PIECE_NUMBERS*Math.random() + 1); 
		   
		    if(CAP_LAYOUT[location]==0 ||CAP_LAYOUT[location]==2)
		      {
		      	flagLoc = true;
		      	}
		     if(CAP_LAYOUT[location]==1)
		       {
		       	 flagLoc = false;
		       	 return location;
		       	 } 	
		 }
		return location;
    	}
    	
    /**
     *
     * @return
     */
    public  int  gen_location()
	{
		boolean flagLoc= true;
		 int location =mheleAI.rulesObj.blockHumanPlayerSet(1);//0; 
		 
		 if(location==0)
		 {
		 while(flagLoc)
		  {
		    
		    location= (int)(PIECE_NUMBERS*Math.random() + 1); 
		    if(CAP_LAYOUT[location]==1 ||CAP_LAYOUT[location]==2)
		      {
		      	flagLoc = true;
		      	}
		     if(CAP_LAYOUT[location]==0)
		       {
		       	 flagLoc = false;
		       	 
		       	 return location;
		       	 } 	
		 }
		 }
		return location;
		}
    /**
     *
     * @return
     */
    public int applySetRule()
	{
		boolean passedRule = false;
		
		int pos =0;
		while(!passedRule)
		{
			 pos=gen_Oppenent_Loc();
			 passedRule=mheleAI.rulesObj.canCaputer(pos);
			}
		return pos;	
		}	 	
	}

   public void pathFindAnimation(Point sr,Point des)
      {
         Graphics g =getGraphics();

         //paintImmediately(playArea);
         setDoubleBuffered(true);
         Graphics2D g2d = (Graphics2D)g;
          //g2d.getPaint();

          Rectangle src =new Rectangle((int)sr.getX(),(int)sr.getY(),40,40);
          Rectangle dest =new Rectangle((int)des.getX(),(int)des.getY(),50,50);
  	  boolean flag =true;
          



  	while(flag) {

  	 if(dest.contains(src) || dest.intersects(src) ||
           src.getX()<=dest.getX() &&
           src.getY()==dest.getY())
            {
  	   	    flag =false;
  	   	    //continue;
  	   	    }
  	 if(src.getX() < dest.getX()){
  	    src=new Rectangle(((int)src.getX())+2,(int)src.getY(),40,40);

            }
  	 if(src.getX() > dest.getX()) {
  	     //src.setLocation(((int)src.getX())-1,src.getY());
              src=new Rectangle(((int)src.getX())-2,(int)src.getY(),40,40);
  	    }
  	  if(src.getY() < dest.getY()) {
  	     //src.setLocation(src.getX(),((int)src.getY())+1);
               src=new Rectangle(((int)src.getX()),(int)src.getY()+2,40,40);
  	    }
  	  if(src.getY() > dest.getY()) {
  	     //src.setLocation(src.getX(),((int)src.getY())-1);
              src=new Rectangle(((int)src.getX()),(int)src.getY()-2,40,40);
  	    }

          try{

             Thread.sleep(180);
             g2d.drawImage(imgHandler.getGameBoard(),30,30,xboard,yboard,this);
             drawcaps(g2d);
             g2d.drawImage(imgHandler.getPlayer2Img(),(int)src.getX(),(int)src.getY(),50,50,this);
             repaint();
           
  	  }catch(InterruptedException e){

          }
  	   }
         dest =null;
         src=null;
         Toolkit.getDefaultToolkit().sync();
         g.dispose();
  	}
}


	
	
	
	