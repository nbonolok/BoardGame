package mygame;
/**
 *Used for scanning all point that belongs to all rows
 * in the game.The following points
 * <br/>Row1 points 1,9,17
 * <br/>Row2 points 2,10,18
 * <br/>Row3 points 3,11,19
 * <br/>Row4 points 4,12,20
 * <br/>Row5 points 5,13,21
 * <br/>Row6 points 6,14,22
 * <br/>Row7 points 7,15,23
 * <br/>Row8 points 8,16,24
 * @author BKNyoni
 * @version 0.0.1
 */
public class AIRowScan
{
    /**
     *Any row point in the game board
     */
    public int rowpoint = 0;
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
         * Class Constructor that does not recieve parameter
         */
        public AIRowScan()
	{
		
		}
	 /* Set the target row point were the mouse was clicked
         * @param  p point
         * @return -
         */
    private void setRowPoint(int p)
	{
		this.rowpoint = p;
	}
	
	
        /**
         * Get the target row point were the mouse was clicked
         * @return rowpoint  -last clicked point which is of {@link integer}.
         */
    public int getRowPoint()
	{
		return rowpoint;
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
      * Gets a row of caps in a SET in an array format
      * @return ScanPoints - row SET
      */
     public int[] getRowSet()
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
     /**
      * This is used for clearing or resetting a boolean CAPTURE FLAG.
      */
     public void ResetCaptureFlag()
     {
     	this.CAPTURE_FLAG=false;
     	}
     /**
      * Scan all middle nodes points for a SET,then sets ScanPoints array with
      * current points location only if a SET is achieved.
      * @param pt -point int
      * @param py -player int
      * @return boolean - this will be set if a CAP SET was found.
      */
     private boolean ScanRowMiddlePoint(int pt,int py)
     {
       	//local variables
		setRowPoint(pt);
		setPlayer(py);
		int mpa    =getRowPoint();
		int mpb  =mpa;
		validCode  =0;
		PreviousPoint1=0;
		NextPoint1=0;
		
		ScanPoints = new int[4]; 
		if(mpa ==2 || mpa ==4 || mpa ==6|| mpa ==10 || mpa ==12|| 
		  mpa ==14 || mpa ==18 || mpa ==20|| mpa ==22)
		  {
		    PreviousPoint1= mpa-1;
		    NextPoint1=mpa+1;
		      
		      ScanPoints[1] =mpa; 
		      
		      
		      if(CAP_LAYOUT[PreviousPoint1] !=0  && CAP_LAYOUT[NextPoint1] !=0 &&CAP_LAYOUT[mpa] !=0)
		        {
		        
		       validCode = CAP_LAYOUT[PreviousPoint1] + CAP_LAYOUT[NextPoint1] + CAP_LAYOUT[mpa];
		       ScanPoints[2] =PreviousPoint1;
		       ScanPoints[3] =NextPoint1;
		       return RowConfirmation(validCode);	
	  	       }
		  	}
        if(mpb ==8 || mpb ==16 || mpb ==24)
		  {
		    PreviousPoint1= mpb-1;
		    NextPoint1=mpb-7;
		      ScanPoints[1] =mpb; 
		      
		      	
		      	if(CAP_LAYOUT[PreviousPoint1] !=0  && CAP_LAYOUT[NextPoint1] !=0 &&CAP_LAYOUT[mpb] !=0)
		           {
		           validCode = CAP_LAYOUT[PreviousPoint1] + CAP_LAYOUT[NextPoint1] + CAP_LAYOUT[mpb];
		            ScanPoints[2] =PreviousPoint1;
		            ScanPoints[3] =NextPoint1; 
		           return RowConfirmation(validCode);
		           } 	
     	
     	}
        return false;
     }
      /**
      * Scan all Corner nodes points for a SET,then sets ScanPoints array with
      * current points location only if a SET is achieved.
      * @param pt -point int
      * @param py -player int
      * @return boolean - this will be set if a CAP SET was found.
      */
     private boolean ScanRowCornerPoint(int pt,int py)
     {
       		//local variables
		setRowPoint(pt);
		setPlayer(py);
		int cpa    =getRowPoint();
		int cpb    =cpa;
		int cpc    =cpa;
		validCode  =0;
		
		PreviousPoint1=0;
		PreviousPoint2=0;
		  
		NextPoint1=0;
		NextPoint2=0;
		
		ScanPoints = new int[4];
		
		//scan CPA scanA and scanB
		 if(cpa==3 || cpa==5 || cpa==11 || cpa==13 || cpa==19 || cpa==21)
		   {
		   	  
		   	  	PreviousPoint1=cpa-1;
		   	  	PreviousPoint2=cpa-2;
		   	  	ScanPoints[1] =cpa; 
		        NextPoint1=cpa+1;
		        NextPoint2=cpa+2;
		        
		        if(CAP_LAYOUT[PreviousPoint1] !=0  && CAP_LAYOUT[PreviousPoint2] !=0 &&CAP_LAYOUT[cpa] !=0
		           && CAP_LAYOUT[PreviousPoint1] ==getPlayer()  && CAP_LAYOUT[PreviousPoint2] ==getPlayer() &&CAP_LAYOUT[cpa] ==getPlayer() )
		           
		           {
		           	validCode = CAP_LAYOUT[PreviousPoint1] + CAP_LAYOUT[PreviousPoint2] + CAP_LAYOUT[cpa];
	                ScanPoints[2] =PreviousPoint1;
		            ScanPoints[3] =PreviousPoint2; 
		            //System.out.println("Debug 1Row");
	                return RowConfirmation(validCode);
	                }	        
		        
		        if(CAP_LAYOUT[NextPoint1] !=0  && CAP_LAYOUT[NextPoint2] !=0 &&CAP_LAYOUT[cpa] !=0
		           && CAP_LAYOUT[NextPoint1] ==getPlayer()  && CAP_LAYOUT[NextPoint2] ==getPlayer() &&CAP_LAYOUT[cpa] ==getPlayer() )
		           
		           {
		           	 validCode = CAP_LAYOUT[NextPoint1] + CAP_LAYOUT[NextPoint2] + CAP_LAYOUT[cpa];
		           	 	
	                ScanPoints[2] =NextPoint1;
		            ScanPoints[3] =NextPoint2; 
		                //System.out.println("Debug 2Row");
	                return RowConfirmation(validCode);
		            }
		             
		   	}  	//scan CPA scanA and scanB ends 
		   	
		   //scan CPB,scanA and scanB 
		    if(cpb==1 || cpb==9 || cpb==17)
		       {
		   	  
		   	  	PreviousPoint1=cpb+7;
		   	  	PreviousPoint2=PreviousPoint1-1;
		   	  	ScanPoints[1] =cpb; 
		        NextPoint1=cpb+1;
		        NextPoint2=cpb+2;
		        
		        if(CAP_LAYOUT[PreviousPoint1] !=0  && CAP_LAYOUT[PreviousPoint2] !=0 &&CAP_LAYOUT[cpb] !=0
		        && CAP_LAYOUT[PreviousPoint1] ==getPlayer()  && CAP_LAYOUT[PreviousPoint2] ==getPlayer() &&CAP_LAYOUT[cpb] ==getPlayer())
		           {
		           	validCode = CAP_LAYOUT[PreviousPoint1] + CAP_LAYOUT[PreviousPoint2] + CAP_LAYOUT[cpb];
	                ScanPoints[2] =PreviousPoint1;
		            ScanPoints[3] =PreviousPoint2;   
	                return RowConfirmation(validCode);
	                }	        
		        
		        if(CAP_LAYOUT[NextPoint1] !=0  && CAP_LAYOUT[NextPoint2] !=0 &&CAP_LAYOUT[cpb] !=0
		           && CAP_LAYOUT[NextPoint1] ==getPlayer() && CAP_LAYOUT[NextPoint2] ==getPlayer() && CAP_LAYOUT[cpb] ==getPlayer())
		           {
		           	 validCode = CAP_LAYOUT[NextPoint1] + CAP_LAYOUT[NextPoint2] + CAP_LAYOUT[cpb];
		           	 	
	                ScanPoints[2] =NextPoint1;
		            ScanPoints[3] =NextPoint2;   
	                return RowConfirmation(validCode);
		            }
		             
		   	}//scan CPB,scanA and scanB  ends 
		   	
		   //scan CPC ,scanA and scanB
		   if(cpc==7 || cpc==15 || cpc==23)
		       {
		   	  
		   	  	PreviousPoint1=cpc-1;
		   	  	PreviousPoint2=cpc-2;
		   	  	ScanPoints[1] =cpc; 
		        NextPoint1=cpc+1;
		        NextPoint2=cpc-6;
		        
		        if(CAP_LAYOUT[PreviousPoint1] !=0  && CAP_LAYOUT[PreviousPoint2] !=0 &&CAP_LAYOUT[cpc] !=0
		         && CAP_LAYOUT[PreviousPoint1] ==getPlayer()  && CAP_LAYOUT[PreviousPoint2] ==getPlayer() &&CAP_LAYOUT[cpc] ==getPlayer())
		           {
		           	validCode = CAP_LAYOUT[PreviousPoint1] + CAP_LAYOUT[PreviousPoint2] + CAP_LAYOUT[cpc];
	                ScanPoints[2] =PreviousPoint1;
		            ScanPoints[3] =PreviousPoint2;   
	                return RowConfirmation(validCode);
	                
	                }	        
		        
		        if(CAP_LAYOUT[NextPoint1] !=0  && CAP_LAYOUT[NextPoint2] !=0 &&CAP_LAYOUT[cpc] !=0
		          && CAP_LAYOUT[NextPoint1] ==getPlayer()  && CAP_LAYOUT[NextPoint2] ==getPlayer() &&CAP_LAYOUT[cpc] ==getPlayer())
		          
		           {
		           	 validCode = CAP_LAYOUT[NextPoint1] + CAP_LAYOUT[NextPoint2] + CAP_LAYOUT[cpc];
		           	 	
	                ScanPoints[2] =NextPoint1;
		            ScanPoints[3] =NextPoint2;   
	                return RowConfirmation(validCode);
					         	 
		           	
		            }
		             
		   	}//scan CPC ,scanA and scanB ends
		    	 	
     	return false;
     	}
     /** Used to check sets if all three point belong to the same player
	 * Player 1 has a total cap weight of 3
	 * Player 2 has a total cap weight of 6
         * @param v_code  this a validation code.
	 */
        private boolean RowConfirmation(int v_code)
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
         *A general row scan for any row set that can be archived based on the
         * current point and player.
         * @param pt point
         * @param py player
         * @return boolean
         */
        public boolean ScanRows(int pt,int py)
		{
			//handle rows
			 
			if(pt ==2 ||pt ==4 ||pt ==6 ||pt ==8 ||pt ==10 ||
			   pt ==12 ||pt ==14 ||pt ==16 ||pt ==18||pt ==20 ||pt ==22 ||pt ==24)
			   {
			   	 return ScanRowMiddlePoint(pt,py);
			   	  
			   	 }
			if(pt ==3 || pt==5 || pt==11 || pt==13 || pt==19 || pt==21 ||
			   pt==1 ||  pt==9 || pt==17 || pt==7  || pt==15 || pt==23)
			   {
			   	return  ScanRowCornerPoint(pt,py);
			   }   	 
			    	   	 
			return false;
			}		 
	}