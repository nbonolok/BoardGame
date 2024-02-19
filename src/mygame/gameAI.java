package mygame;
//import java.util.Arrays;
//import java.util.List;
import java.util.Vector;
//import javax.swing.JOptionPane;
/**
 *
 * @author BKNyoni
 */
public class gameAI
{
 private Vector Vsets;
 private int gameSets;
 private int freeCows;
 private final int [][] xycords={{0},{0}}; //dummy array 
 
 private final int [][] gameSetsList ={{1,2,3},{3,4,5},{5,6,7},
	                                   {7,8,1},{9,10,11},{11,12,13},
	                                   {13,14,15},{15,16,9},{17,18,19},
	                                   {19,20,21},{21,22,23},{23,24,17},
	                                   {1,9,17},{2,10,18},{3,11,19},
	                                   {4,12,20},{5,13,21},{6,14,22},
	                                   {7,15,23},{8,16,24}}; 
 
 //18/01/2010
private final int [][]nextNodes2D={{0},{2,8,9},{1,3,10},{2,4,11},{3,5,12},
		                       {4,6,13},{5,7,14},{6,8,15},{1,7,16},
		                       {1,10,16,17},{2,9,11,18},{3,10,12,19},
		                       {4,11,13,20},{5,12,14,21},{6,13,15,22},
		                       {7,14,16,23},{8,9,15,24},{9,18,24},{10,17,19},
		                       {11,18,20},{12,19,21},{13,20,22},{14,21,23},
		                       {15,22,24},{16,17,23} 
		                          };
//18/01/2010
private  int [] pPosition;
	                                    
	                                                        
	private boolean ROW_FLAG   =false;
	private boolean COLUMN_FLAG=false;
	private boolean CAPTURE_FLAG=false;
        private boolean TEMP_FLAG = false;
	
	int CAPS_CAPTURED_BY_PLAYER2=0;
    int [] CAPS_LOCATION_PLAYER2 =new int[12];
    
    int CAPS_CAPTURED_BY_PLAYER1=0;
	int [] CAPS_LOCATION_PLAYER1 =new int[12];
	
	private int [] CAP_LAYOUT= new int[25];
	
	
	int currentpoint =0;
	private int player;
	private int opponent;
	
	private String [] PlayerNames={null,"2","1"};
	
	 
	 //Objects 
       AIColumnScan AIColumnScanObj = new AIColumnScan();
       AIRowScan    AIRowScanObj    = new AIRowScan();
       ruleManager rulesObj=new ruleManager();
       MsgClass  gameMsgObj= new MsgClass();
	
        /**
         *
         */
        public gameAI()
	{
	
		}
        /**
         *
         * @param l
         */
        public void setlanguage(String l)
        {
     	
     	gameMsgObj.setLangDialog(l);
     	}
            /**
             *
             * @param clayout
             */
            public void setCapLayout(int [] clayout)
			 {
			 	CAP_LAYOUT = clayout;
			  }
		  
            /**
             *
             * @return
             */
            public int [] getCapLayout()
			  {
				return CAP_LAYOUT;
				}
		 
		 private void setPlayer(int currentplayer)
			 {
			 	player =currentplayer;
			 	}
		 
                 /**
                  *
                  * @return
                  */
                 public int getPlayer()
		     {
		  	  return player;
		  	 }
		  private void setOpponent()
		  {
		  	if(getPlayer()==1)
		  	  {
		  	  	this.opponent =2;
		  	  	}
		  	 if(getPlayer()==2)
		  	  {
		  	  	this.opponent =1;
		  	    } 	
		  	}	 
		  	
                  /**
                   *
                   * @param p
                   */
                  public void setCurrentPoint(int p)
	     {
		   this.currentpoint = p;
	       }
	
                 /**
                  *
                  * @return
                  */
                 public int getCurrentPoint()
	{
		return currentpoint;
		}	
		  			
        /**
         *
         * @param pieceLayout
         * @param Cpoint
         * @param player
         */
        public void updateStatus(int [] pieceLayout,int Cpoint,int player)
			  {
			  	//if()
			  	setCapLayout(pieceLayout);
			  	setCurrentPoint(Cpoint);
			  	setPlayer(player);   
			  	}
                  /**
                   *
                   * @return
                   */
                  public boolean CheckMoveSet()
		  {
		          
		          AIColumnScanObj.setCapLayout(getCapLayout());
				  AIRowScanObj.setCapLayout(getCapLayout());
				  
				  COLUMN_FLAG=AIColumnScanObj.ScanColumnRing(getCurrentPoint(),getPlayer());
				  ROW_FLAG=AIRowScanObj.ScanRows(getCurrentPoint(),getPlayer());
			//System.out.println(getPlayer()+" Row set:"+ ROW_FLAG +" Column set:"+COLUMN_FLAG);
			
			 for(int i=1; i<=24; ++i)
             {
         	  //System.out.print(Integer.toString(CAP_LAYOUT[i])+" ");
         	  } 
			
			//System.out.println(" ");
			if(ROW_FLAG==true || COLUMN_FLAG==true)
			   {
			   	CAPTURE_FLAG=true;
			   	return CAPTURE_FLAG; 
			   	}
			 CAPTURE_FLAG =false;  		  	
		  	return CAPTURE_FLAG;
		  	}
                  /**
                   *
                   * @return
                   */
                  public boolean captureKgomo()
		  	{
		  	int c_position=getCurrentPoint();
		  	boolean setChecker=rulesObj.canCaputer(c_position);
		  	    //System.out.println(CAP_LAYOUT[c_position]);
		  		if(CAP_LAYOUT[c_position] !=0 && CAP_LAYOUT[c_position] !=getPlayer() && CAPTURE_FLAG && setChecker)
		  		  {
		  		  	CAP_LAYOUT[c_position] =0;
		  		  	updateDockStack();
		  		  	CAPTURE_FLAG=false;
		  		  	return CAPTURE_FLAG;
		  		  	 //check for three in a row 
		  		  	}
		  		 if(CAP_LAYOUT[c_position] !=0 && CAP_LAYOUT[c_position] !=getPlayer() && CAPTURE_FLAG && !setChecker)
		  		  {
		  		  	//JOptionPane.showMessageDialog(null,"You cannot capture a Kgomo in a set while there\n are others available","Capture Rule",
                    //JOptionPane.WARNING_MESSAGE);
                    gameMsgObj.getNotCaptureMsg_AI();
		  		  	}
		  		  	                     //!=
		  		 if(CAP_LAYOUT[c_position] ==0 || CAP_LAYOUT[c_position] ==getPlayer() && CAPTURE_FLAG)
		  		  {
		  		  	//JOptionPane.showMessageDialog(null,"Please capture "+PlayerNames[getPlayer()]+" Kgomo","Capture Rule",
                    //JOptionPane.WARNING_MESSAGE);
                     gameMsgObj.getCaptureCowMsg_AI(PlayerNames[getPlayer()]);
                     
		  		  	}
		  		  	
		  		
		  		  	CAPTURE_FLAG=true;
		  		  	return CAPTURE_FLAG;
		  	}	
                        /**
                         *
                         */
                        public void updateDockStack()
		  	{
		  		if(getPlayer()==1)
		  		   {
		  		   	  CAPS_CAPTURED_BY_PLAYER1=CAPS_CAPTURED_BY_PLAYER1+1;
		  		   		}
		  		if(getPlayer()==2)
		  		   {
		  		   	 CAPS_CAPTURED_BY_PLAYER2=CAPS_CAPTURED_BY_PLAYER2+1;
		  		   	  }   		
		  		}
		  		
		  		
                        /**
                         *
                         * @param cp1
                         */
                        public void setCAPS_CAPTURED_BY_PLAYER1(int cp1)
                        {
                            CAPS_CAPTURED_BY_PLAYER1 =cp1;
                           }
                        /**
                         *
                         * @param cp2
                         */
                        public void setCAPS_CAPTURED_BY_PLAYER2(int cp2)
                        {
                            CAPS_CAPTURED_BY_PLAYER2 =cp2;
                           }
                        /**
                         *
                         * @return
                         */
                        public int getCAPS_CAPTURED_BY_PLAYER1()
		  	{
		  		return CAPS_CAPTURED_BY_PLAYER1;
		  		}
		  		
		  			  	 
                        /**
                         *
                         * @return
                         */
                        public int getCAPS_CAPTURED_BY_PLAYER2()
		  	{
		  		return CAPS_CAPTURED_BY_PLAYER2;
		  		}
		  		
		  		
                        /**
                         *
                         */
                        public void resetCAPS_CAPTURED()
		  	{
		  	  CAPS_CAPTURED_BY_PLAYER1=0;
		  	  CAPS_CAPTURED_BY_PLAYER2=0;	
		  		}
		  		
		  		
                        /**
                         *
                         * @return
                         */
                        public int getPLAYER1_CAPS_BOARD()
		  	{
		  		int onBoardCap=0;
		  		
		  		 for(int i=1; i<CAP_LAYOUT.length; ++i)
                    {
                      if(CAP_LAYOUT[i]==1)
                        {
                       CAPS_LOCATION_PLAYER1[onBoardCap]=i;
                       onBoardCap+=1;
         	            
         	            }
         	        }
         	        return onBoardCap; 
		  		}
		  		
                        /**
                         *
                         * @return
                         */
                        public int getPLAYER2_CAPS_BOARD()
		  	  {
		  		int onBoardCap=0;
		  		 for(int i=1; i<CAP_LAYOUT.length; ++i)
                    {
                      if(CAP_LAYOUT[i]==2)
                      {
                        CAPS_LOCATION_PLAYER2[onBoardCap]=i;
                      	onBoardCap+=1;
                      	}
         	        }
         	        return onBoardCap; 
		  		}
		  		
                        /**
                         *
                         * @return
                         */
                        public int [] getCap1LocationOnBoard()
		  	  {
		  	  	return CAPS_LOCATION_PLAYER1; 
		  	  	}	
		  	  
                        /**
                         *
                         * @return
                         */
                        public int [] getCap2LocationOnBoard()
		  	  {
		  	  	return CAPS_LOCATION_PLAYER2; 
		  	  	}
		  	  	
                          /**
                           *
                           * @return
                           */
                          public int getEmptySlots()
              {
                    int onBoardCap=0;
                     for(int i=1; i<CAP_LAYOUT.length; ++i)
                    {
                      if(CAP_LAYOUT[i]==0)	
         	          onBoardCap+=1;
         	        }
         	        return onBoardCap; 
		}
             
		  		
		  	 		
		  		
/*if cows are less than 3 game Finished 
 *if cows are equls to 3 current player triggers Roaming Mode
 *if cows are all in a SET caputure any cow
 *if cows are in a SET but still others are not block SET and 
 *allow CAPTURE MODE
 *if player cannot move game ends 
 */
             /**
              *
              */
             public class ruleManager
{
	//constructor
      
                 /**
                  *
                  */
                 public ruleManager()
	{
		
		}
	//if all nodes are occupied by cows game must end.	
        /**
         *
         * @return
         */
        public boolean isBoardFull() //not sure
  	{
  		if(getEmptySlots() ==0)
  		   {
  		   	return true;
  		   	}
  		   if(getEmptySlots() !=0)
  		    {
  			return false;
  			 }
  		return false;	    	
  		}
  	
  	private void setGameSets(int set)
  	{
  		gameSets=set;
  		}
        /**
         *
         * @return
         */
        public int getGameSets()
  	{
  		return gameSets;
  		}
  	private void setSetsPoints(Vector v)
  	{
  		Vsets=v;
  		}
        /**
         *
         * @return
         */
        public Vector getSetsPoints()
  	{
  		return Vsets;
  		}				
        /**
         *
         * @return
         */
        public int getSetsCowsNumbers()
  	{
  		return Vsets.size();
  		}
  								
        /**
         *
         * @param py
         */
        public void PlayerCountRowSets(int py)
	{
	    //rows count
	    int cows =0;
	    gameSets =0;
	    Vsets = new Vector();
	    
	    for(int i=0; i<gameSetsList.length; ++i)
	       {
	       	 for(int j=0; j<3; ++j)
	       	    {
	       	    	
	       	    	
	       	    	if(CAP_LAYOUT[gameSetsList[i][j]]==py)
	       	    	  {
	       	    	    cows +=1;
	       	    	   // System.out.print(" "+gameSetsList[i][j]);
	       	    	    if(cows == 3)
	       	    	       {
	       	    	       	 for(int a=0; a<3; ++a)
	       	    	       	  {
	       	    	       	   if(!Vsets.contains(gameSetsList[i][a]))
	       	    	       	     {
	       	    	       	     	Vsets.add(gameSetsList[i][a]);
	       	    	       	     }
	       	    	       	   }
	       	    	       	
	       	    	        gameSets+=1;
	       	    	        cows =0;
	       	    	       
	       	    	       }
	       	    	    }
	       	    	
	       	     }
	       	     cows=0;
	       	     
	       	     //System.out.println();	
		      }
		      setSetsPoints(Vsets);
		      setGameSets(gameSets);
		       setPlayerFreeCows(py);
		}
        /**
         *
         * @param py
         */
        public void setPlayerFreeCows(int py)
	{
		if(py ==1)
		{
		 freeCows=getPLAYER1_CAPS_BOARD()-getSetsCowsNumbers();	
		 }
		if(py==2)
		{
		  freeCows=getPLAYER2_CAPS_BOARD()-getSetsCowsNumbers();	
		  }
				
		}
		
		
        /**
         *
         * @return
         */
        public int  getPlayerFreeCows()
    {
    	return freeCows;
    	}
    private boolean hasMovesValid(int num)
    {
    	if(num==0)
    	   return false;
    	if(num>0)
    	   return true;
    	return false;
    	}
    	//18/01/2010
    /**
     *
     * @param player
     * @return
     */
    public  int [] getCap_locations(int player)
		  	  {
		  		int [] CAPS_LOCATION=new int[12];
		  		int [] returnPositions;
		  		int onBoardCap=0;
		  		
		  		 for(int i=1; i<CAP_LAYOUT.length; ++i)
                    {
                      if(CAP_LAYOUT[i]==player)
                      {
                        CAPS_LOCATION[onBoardCap]=i;
                      	onBoardCap+=1;
                      	}
         	        }
         	        returnPositions = new int[onBoardCap];
         	        
         	        for(int i=0; i<returnPositions.length; ++i)
         	        {
         	        	returnPositions[i]=CAPS_LOCATION[i];
         	        	}
         	        
         	        return 	returnPositions; 
		  		}
	//18/01/2010
                /**
                 *
                 * @param player
                 * @param c_layout
                 * @return
                 */
                public boolean hasMoves(int player,int [] c_layout)
	{
	    //System.out.println("Player Moves Checker...");
            pPosition=getCap_locations(player);
		if(pPosition.length >1)
		  {
		  
		
		for(int x=0; x<pPosition.length; ++x)
				   {
				    for(int i=0; i< nextNodes2D[pPosition[x]].length; ++i)
					{
					 //System.out.print(pPosition[x]+" "+nextNodes2D[pPosition[x]][i]+" ");
					// System.out.println(CAP_LAYOUT[nextNodes2D[pPosition[x]][i]]);
					 if(CAP_LAYOUT[nextNodes2D[pPosition[x]][i]]==0)
					  {
					   // System.out.println("can move");
					  	return true;
					  	
					  	}
				     }
			    //System.out.println(" ");
			    }
			   // System.out.println("cant move");
			   return false;
			 }
			 return true; 
	}	
                /**
                 *
                 * @param position
                 * @return
                 */
                public boolean canCaputer(int position)
	{
		int playerCow=0;
		int cowsToCapture=0;
		boolean cowInSet = false;
		if(getPlayer()==1)
		   {
		   	playerCow =2;
		   	}
		 if(getPlayer()==2)
		 {
		 	playerCow=1;
		 	}
		 PlayerCountRowSets(playerCow);
		 
		 //do calculation on cow available
		 cowsToCapture=getPlayerFreeCows();
		 cowInSet=getSetsPoints().contains(position);
		 if(cowsToCapture==0 && cowInSet)
		    {
		    	return true;
		    	}
		 if(cowsToCapture>0 && cowInSet)
		    {
		    	return false;
		    	}
		  if(cowsToCapture>0 && !cowInSet)
		     {
		     	return true;
		     	}  	   	
		 	
		return false;
		} 
                /**
                 *
                 * @param value
                 * @return
                 */
                public int subBlockHumanPlayer(int value)
        {
            int putCowAt=0;
            for(int i=0; i< gameSetsList.length; ++i)
		   {
		   	 //System.out.print("["+i+"] "+CAP_LAYOUT[gameSetsList[i][0]]);
		   	 //System.out.print(CAP_LAYOUT[gameSetsList[i][1]]);
		   	 //System.out.println(CAP_LAYOUT[gameSetsList[i][2]]);
		   	 
		if(CAP_LAYOUT[gameSetsList[i][0]]==0 && CAP_LAYOUT[gameSetsList[i][1]]== value 
		   && CAP_LAYOUT[gameSetsList[i][2]]== value)
		   	   {
		   	   	
		   	   	putCowAt = gameSetsList[i][0];
		   	   	break;
		   	   	}
		if(CAP_LAYOUT[gameSetsList[i][0]]==value && CAP_LAYOUT[gameSetsList[i][1]]==0 
		  && CAP_LAYOUT[gameSetsList[i][2]]== value)
		   	   {
		   	   	putCowAt=gameSetsList[i][1];
		   	   	break;
		   	   	}
		if(CAP_LAYOUT[gameSetsList[i][0]]==value && CAP_LAYOUT[gameSetsList[i][1]]==value 
		   && CAP_LAYOUT[gameSetsList[i][2]]==0)
		   	   {
		   	   	putCowAt=gameSetsList[i][2];
		   	   	break;
		   	   	}
   	   	
   	        }
            return putCowAt;
        }
		
        /**
         *
         * @param py
         * @return
         */
        public int blockHumanPlayerSet(int py)
	 {
	   //System.out.println("Block set");
		//System.out.println("Set List: "+gameSetsList.length);
		//System.out.println("Set Layout: "+CAP_LAYOUT.length);
		
		int putCowAt=0;
                /*TO DO
                 *else buildset (later)
                */
                  putCowAt = subBlockHumanPlayer(2);
                  if(putCowAt==0)
                  { 
                       putCowAt = subBlockHumanPlayer(py);
                    }
                  return putCowAt;	
	 	}
        /**
         *
         * @param source
         * @param setLoc1
         * @param setLoc2
         * @return
         */
        public int subBuildset(int source,int setLoc1,int setLoc2) //,int [] layout)
         {
            //System.out.println("Next move Checker...");
            int pPos=source;
		if(pPos >0)
		  {
		  
		
		//for(int x=0; x<pPosition.length; ++x)
				   //{
				    for(int i=0; i< nextNodes2D[pPos].length; ++i)
					{
					 //System.out.print(pPos+" "+nextNodes2D[pPos][i]+" ");
					 //System.out.println(CAP_LAYOUT[nextNodes2D[pPos][i]]);
					 if(CAP_LAYOUT[nextNodes2D[pPos][i]]==2 && 
                                                 nextNodes2D[pPos][i] != setLoc1 && nextNodes2D[pPos][i] !=setLoc2)
					  {
					    return nextNodes2D[pPos][i];
					  	}
				     }
			    
			    //}
			    
			 }
			 
             return 0;
           }
        /**
         *
         * @return
         */
        public int BuildComputerSetMode1()
         {
             int [] temp= new int[12];
             int [] store;
             int count = 0;
             
             
             //System.out.println("Build set");
				
		int putCowAt=0;
		int value=2; 
		
		for(int i=0; i< gameSetsList.length; ++i)
		   {
		   	
		   	 
		if(CAP_LAYOUT[gameSetsList[i][0]]==0 && CAP_LAYOUT[gameSetsList[i][1]]== value 
		   && CAP_LAYOUT[gameSetsList[i][2]]== value)
		   	   {
		   //System.out.println("BCDM3:"+CAP_LAYOUT[gameSetsList[i][0]]+" "+CAP_LAYOUT[gameSetsList[i][1]]+" "
                                      //  +CAP_LAYOUT[gameSetsList[i][2]]);
                    //System.out.println("BCDM3_P:"+gameSetsList[i][0]+" "+gameSetsList[i][1]+" "
                                     //   +gameSetsList[i][2]);
                   
                                putCowAt = gameSetsList[i][0];
		   	   	//System.out.println("BCDM3_EMPTY_P:"+putCowAt);
                                int z= subBuildset(putCowAt,gameSetsList[i][1],gameSetsList[i][2]);
                               // System.out.println("BCDM3 move: "+z+" to "+putCowAt);
                                temp[count] = putCowAt;
                                ++count;
                                if(z !=0)
                                  {
                                    CAP_LAYOUT[z] =0;
                                    CAP_LAYOUT[putCowAt]=2;
                                    updateStatus(CAP_LAYOUT,putCowAt,2);
                                    setTempFlag(CheckMoveSet());
                                    return 1;
                                   }
                                }
		if(CAP_LAYOUT[gameSetsList[i][0]]==value && CAP_LAYOUT[gameSetsList[i][1]]==0 
		  && CAP_LAYOUT[gameSetsList[i][2]]== value)
		   	   {
		   	   	//System.out.println("BCDM5:"+CAP_LAYOUT[gameSetsList[i][0]]+" "+CAP_LAYOUT[gameSetsList[i][1]]+" "
                                  //      +CAP_LAYOUT[gameSetsList[i][2]]);
                                 //System.out.println("BCDM5_P:"+gameSetsList[i][0]+" "+gameSetsList[i][1]+" "
                                   //     +gameSetsList[i][2]);
                   
                                putCowAt = gameSetsList[i][1];
		   	   	//System.out.println("BCDM5_EMPTY_P:"+putCowAt);
                                 int z= subBuildset(putCowAt,gameSetsList[i][0],gameSetsList[i][2]);
                               // System.out.println("BCDM5 move: "+z+" to "+putCowAt);
                               
                                temp[count] = putCowAt;
                                ++count;
                                if(z !=0)
                                  {
                                    CAP_LAYOUT[z] =0;
                                    CAP_LAYOUT[putCowAt]=2;
                                    updateStatus(CAP_LAYOUT,putCowAt,2);
                                    setTempFlag(CheckMoveSet());
                                    return 1;
                                   }

		   	   	}
		if(CAP_LAYOUT[gameSetsList[i][0]]==value && CAP_LAYOUT[gameSetsList[i][1]]==value 
		   && CAP_LAYOUT[gameSetsList[i][2]]==0)
		   	   {
		   	   	//System.out.println("BCDM6:"+CAP_LAYOUT[gameSetsList[i][0]]+" "+CAP_LAYOUT[gameSetsList[i][1]]+" "
                                        //+CAP_LAYOUT[gameSetsList[i][2]]);
                                //System.out.println("BCDM6_P:"+gameSetsList[i][0]+" "+gameSetsList[i][1]+" "
                                //        +gameSetsList[i][2]);
                                
                   
                                putCowAt = gameSetsList[i][2];
		   	   	//System.out.println("BCDM6_EMPTY_P:"+putCowAt);
                                 int z= subBuildset(putCowAt,gameSetsList[i][0],gameSetsList[i][1]);
                                //System.out.println("BCDM6 move: "+z+" to "+putCowAt);
                               
                                temp[count] = putCowAt;
                                ++count;
                                if(z !=0)
                                  {
                                    CAP_LAYOUT[z] =0;
                                    CAP_LAYOUT[putCowAt]=2;
                                    updateStatus(CAP_LAYOUT,putCowAt,2);
                                    setTempFlag(CheckMoveSet());
                                    return 1;
                                   }

		   	   	}
   	   	
   	        }
   	      store=new int[count];
              //System.out.println("BCDM counter "+count);
             
	    return 0;  //not found now do random move.   
         }
       
    	private void setTempFlag(boolean fg)
        {
           TEMP_FLAG =fg;
        }
        /**
         *
         * @return
         */
        public boolean getTempFlag()
        {
            return TEMP_FLAG;
        }
    } //end of inner class
} //end of outside class 
	
				   	      
				   	     
				   	     
	
		