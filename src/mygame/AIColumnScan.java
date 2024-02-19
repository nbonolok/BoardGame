/** This is a traditional strategic board game,which is played by
 *  two players designed and written in java.
 */
package mygame;


/**
 *Used for scanning all point that belongs to all rings 1-3
 * column direction in the game.The following points
 * <br/>Ring1 points 1-8
 * <br/>Ring2 points 9-16
 * <br/>Ring3 points 17-24
 * @author BKNyoni
 * @version 0.0.1
 */
public class AIColumnScan
{
    
    /**
     *This is any column point on the board<br/>
     *
     */
    public int columnpoint = 0;

       
    /**
     *flag used for checking a column cap SET.
     * TRUE = three in a row
     * False = No SET row made
     */
    public boolean CAPTURE_FLAG = false;

        
        /**
         * All the XY coordinates on the board nodes used for boundary check.
         */
        protected final int [][] xycords={{0},{0}};
	/**
         * All the points to be scanned in a column.
         */
        private int [] ScanPoints ={0};
        /**Points used for getting the next destination by addition.
        */
	private int NextPoint1,NextPoint2;
        /**Points used for getting the next destination by substraction.
         */
	private int PreviousPoint1,PreviousPoint2;
        /**
         *This variable is used for validating a complete SET
         */
	private int validCode;
        /**
         * An array that stores all the game cap location for both players
         * and empty slots
         */
	private int [] CAP_LAYOUT= new int[25];
        /**stores the current player
         */
	private int player;
	
	
        /**
         *class constructor,does not recieve any parameter
         */
        public AIColumnScan()
	{
	  
		}
	
        /* Set the target column point were the mouse was clicked
         * @param  p point
         * @return -
         */
	private void setColumnPoint(int p)
	{
		this.columnpoint = p;
	}
	
	
        /**
         * Get the target column point were the mouse was clicked
         * @return columnpoint  -last clicked point which is of {@link integer}.
         */
        public int getColumnPoint()
	{
		return columnpoint;
		}
		
	
        /**
         * Set positions were the caps are located on the board,Also empty
         * slots are reserved in this array
         * @param clayout - CAP LAYOUT
         */
        public void setCapLayout(int [] clayout)
     {
     	CAP_LAYOUT = clayout;
     	}

     /** Set the current player of the game
      * @param currentplayer  -this can be 1 or 2.
      * @return -
      */
     private void setPlayer(int currentplayer)
     {
     	player =currentplayer;
     	}
     	
     /**
      * Get current player that had moved or played
      * @return player - 1 or 2
      */
     public int getPlayer()
      {
      	return player;
      	}
     	
     /**
      * This is used for clearing or resetting a boolean CAPTURE FLAG.
      */
     public void ResetCaptureFlag()
     {
     	this.CAPTURE_FLAG=false;
     	}
     	 	
     /**
      * Gets a column of caps in a SET in an array format
      * @return ScanPoints - column SET
      */
     public int[] getColumnSet()
     {
     	return ScanPoints;
     	}
      	 
     /**
      * Gets the current CAPTURE FLAG status
      * @return CAPTURE_FLAG - {@link boolean}
      */
     public boolean getCaptureFlag()
     {
     	return CAPTURE_FLAG;
     } 			
     
     /**scan any point in ring 1 using column point
      *by targered point pt and current player 
      *@return boolean
      *
      */			 
	private  boolean ScanColumnRing1(int pt,int py)
	{
		//local variables
		setColumnPoint(pt);
		setPlayer(py);
		int csr1   =getColumnPoint();
		validCode  =0;
		NextPoint1 =0;
		NextPoint2 =0;
		
		ScanPoints = new int[4];  
		if(csr1>8)
		   {
		    CAPTURE_FLAG=false;
		    return false;
		    }
		  if(csr1 >0 && csr1< 9)
		    {
		      NextPoint1 = csr1 +8;
		      NextPoint2 = csr1 +16;
		      ScanPoints[1] =csr1; 
		     
		      if(CAP_LAYOUT[NextPoint1] !=0  && CAP_LAYOUT[NextPoint2] !=0 &&CAP_LAYOUT[csr1] !=0)
		        {
		        validCode = CAP_LAYOUT[NextPoint1] + CAP_LAYOUT[NextPoint2] + CAP_LAYOUT[csr1];
		          ScanPoints[2] =NextPoint1;
		          ScanPoints[3] =NextPoint2;
		         }
		      return ColumnConfirmation(validCode);
		    	}
		   
          return false;
		}
		
     /**scan any point in ring 2 using column point
      * by targered point pt and current player
      * @return boolean -true or false
      *
      */	
	private  boolean ScanColumnRing2(int pt,int py)
	{
		//local variables
		setColumnPoint(pt);
		setPlayer(py);
		int csr2   =getColumnPoint();
		validCode  =0;
		PreviousPoint1=0;
		NextPoint2 =0;
		
		ScanPoints = new int[4];  
		
		  if(csr2 >8 && csr2< 17)
		    {
		      PreviousPoint1 = csr2 -8;
		      NextPoint2 = csr2 +8;
		      
		      ScanPoints[1] =csr2; 
		      		      
		      if(CAP_LAYOUT[PreviousPoint1] !=0  && CAP_LAYOUT[NextPoint2] !=0 &&CAP_LAYOUT[csr2] !=0)
		         {
		          validCode = CAP_LAYOUT[PreviousPoint1] + CAP_LAYOUT[NextPoint2] + CAP_LAYOUT[csr2];
		          ScanPoints[2] =PreviousPoint1;
            	  ScanPoints[3] =NextPoint2;
 
		           return ColumnConfirmation(validCode);
		          }   	
		    	
		    	}
		   return false;
		}
     /**scan any point in ring 3 using column point
      *by targered point pt and current player 
      *@return boolean true or false
      *
      */		
	private  boolean ScanColumnRing3(int pt,int py)
	{
		//local variables
		setColumnPoint(pt);
		setPlayer(py);
		int csr3   =getColumnPoint();
		validCode  =0;
		PreviousPoint1=0;
		PreviousPoint2=0;
		
		ScanPoints = new int[4];  
		
		  if(csr3 >16 && csr3<25)
		    {
		      PreviousPoint1 = csr3 -8;
		      PreviousPoint2 = csr3 -16;
		      
		      ScanPoints[1] =csr3; 
		      
		      
		      if(CAP_LAYOUT[PreviousPoint1] !=0  && CAP_LAYOUT[PreviousPoint2] !=0 &&CAP_LAYOUT[csr3] !=0)
		         {
		         validCode = CAP_LAYOUT[PreviousPoint1] + CAP_LAYOUT[PreviousPoint2] + CAP_LAYOUT[csr3];
		          ScanPoints[2] =PreviousPoint1;
		          ScanPoints[3] =PreviousPoint2;
		         return ColumnConfirmation(validCode);
		    	 }
		    	}
		   return false;
		}
	
	/** Used to check sets if all three point belong to the same player
	 * Player 1 has a total cap weight of 3
	 * Player 2 has a total cap weight of 6
         * @param v_code  this a validation code.
	 */	
	private boolean ColumnConfirmation(int v_code)
        {
        	validCode = v_code;
	        if(validCode == 3 &&  getPlayer() == 1)
	            {
	             CAPTURE_FLAG= true;
	              return true;
	             }
	         if(validCode == 6 && getPlayer() == 2)
	              {
	           	   CAPTURE_FLAG =true;
	                return true;
	         	   }	
        	   return false;
        	}		
	
	/**
         * General column scan controller
	 * determines which ring to scan based on target point pt
	 * and current player py
         *
         * @param pt  point
         * @param py  player
         * @return boolean - true or false
         */
        public boolean ScanColumnRing(int pt,int py)
	{
		//handle ring 1
		if(pt ==1 ||pt ==2 ||pt ==3 ||pt ==4 
		   ||pt ==5 ||pt ==6 ||pt ==7 ||pt ==8)
		   {
		   	 return ScanColumnRing1(pt,py);
		   	  
		   	 }
		  //handle ring 2 	 
		 if(pt ==9 ||pt ==10 ||pt ==11 ||pt ==12 
		   ||pt ==13 ||pt ==14 ||pt ==15 ||pt ==16)
		   {
		   	 return ScanColumnRing2(pt,py);
		   	 }
		  //handle ring 3 	 
		 if(pt ==17 ||pt ==18 ||pt ==19 ||pt ==20 
		   ||pt ==21 ||pt ==22 ||pt ==23 ||pt ==24)
		   {
		   	 return ScanColumnRing3(pt,py);
		   	 }  	   	 
		return false;
		}
	}