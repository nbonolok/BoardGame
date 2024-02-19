package mygame;
//import javax.swing.JOptionPane;
//import java.util.Vector;
/**
 *
 * @author BKNyoni
 */
public class shifting
{
	//variables
	private final int [][] xycords;
        /**
         *
         */
        public  int [] temp ;
        /**
         *
         */
        public  int [] nextXy;
        /**
         *
         */
        public  int source=0;
        /**
         *
         */
        public  int PLAYER=1;
	private int [] CAP_LAYOUT= new int[25];
	//constructor
        /**
         *
         * @param cords
         */
        public shifting(int [][] cords)
		{
		  xycords = cords;
		  temp = new int[3];
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
		 
        /**
         *
         * @param src
         */
        public void setSource(int src)
	    {
		   this.source=src;
		  }
		
		
                /**
                 *
                 * @return
                 */
                public int getSource()
		    {
		return this.source;
		}
		
		
		
                /**
                 *
                 * @return
                 */
                public int [] getMoves()
		    {
		return nextXy;
		}	  
	 
                /**
                 *
                 * @param index
                 * @return
                 */
                public int moveright(int index)
	 {
		 	 if(index == 8 || index==16 || index==24)
		 	   {
		 	   	index =index-7;
		 	   	return index;
		 	   	}
	 	 return index+1;
	 	 }
	 	 
	 	 
         /**
          *
          * @param index
          * @return
          */
         public int moveleft(int index)
	 {
	    if(index == 1 || index==9 || index==17)
	 	   {
	 	   	index =index+7;
	 	   	return index;
	 	   	}
	 	  
	 		return index-1;
	 	}	 	
		
		
         /**
          *
          * @param index
          * @return
          */
         public int moveOutWards(int index)
	 {
	    if(index <17)
	    {
	     index = index+8;
	     return index;
	     }
	 	return index;
	 	}
	 	
         /**
          *
          * @param index
          * @return
          */
         public int moveInWards(int index)
	 {
	 	if(index >8)
	 	  {
	 	  	index =index-8;
	 	  	return index;
	 	  	}
	 	return index;
	 	 }	 	
         /**
          *
          * @param current
          * @param capLayer
          * @return
          */
         public int[] checkValidMoves(int current,int [] capLayer)
	   { 
	    
	    
	     int [] nextXytemp={0};
	     if(current>=9 && current <=16)
	        {
	        nextXytemp =new int[4];
	         }
	        else if(current<9 || current >16)
	        {
	        	 nextXytemp =new int[3];
	         } 
	     setSource(current);
	     setCapLayout(capLayer);
	     nextXy =new int[4];
	     
	     nextXy[0]=moveright(current);
	     nextXy[1]=moveleft(current);
	     nextXy[2]=moveInWards(current);
	     nextXy[3]=moveOutWards(current);
	     
	     int cposition =getSource(); 
	     int cin =0;
	     for(int p=0; p<nextXy.length; ++p)
	        {
	        	if(nextXy[p] !=cposition && CAP_LAYOUT[nextXy[p]] ==0)
	        	  {
	        	  	 
	        	  	 nextXytemp[cin]= nextXy[p];
	        	  	 cin++;
	        	  	 }
	        	}
	   return nextXytemp; 
	   }
         /**
          *
          * @param current
          * @param capLayer
          * @return
          */
         public int []  validMoves(int current,int [] capLayer)
	   {
	   	int [] moves;
	   	int [] tempstore= checkValidMoves(current,capLayer);
	   	int size=0;
	   
	   	for(int x=0; x<tempstore.length; ++x)
	   	   {
	   	   	 if(tempstore[x] !=0)
	   	   	    {
	   	   	    	 size+=1;
	   	   	      }
	   	   	}
	   	   	
	   	 moves = new int[size];
	   	 for(int y =0; y< tempstore.length; ++y)
	   	    {
	   	    	if(tempstore[y] !=0) 
	   	    	moves[y] =tempstore[y];
	   	    } 
	   	return moves;
	   	}
	   	
           /**
            *
            * @return
            */
           public int [] getEmptySlots()
		  	  {
		  		int [] onBoardCap=new int[25];
		  		int arrSize=0;
		  		int counter=0; 
		  		 for(int i=1; i<CAP_LAYOUT.length; ++i)
                    {
                      if(CAP_LAYOUT[i]==0)
                      {
                      	onBoardCap[counter]=i;
                      	arrSize+=1;
                      	counter+=1;
         	           }
         	        }
         	        int [] arr=new int[arrSize];
         	        
         	        for(int i=0; i<arr.length; ++i)
         	        {
         	        	if(onBoardCap[i]!=0)
         	        	arr[i]=onBoardCap[i];
         	        	
         	        	}
         	        return arr; 
		  		}
           /**
            *
            * @param capLayer
            * @return
            */
           public int [] validMovesRoaming(int [] capLayer)
		 {
		 	this.setCapLayout(capLayer);
		    int [] freeloc=this.getEmptySlots();
		          
		 	
		 	 return freeloc;
		 	} 		
		  		
	 
	}
	