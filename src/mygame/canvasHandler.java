package mygame;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;
import java.awt.color.ColorSpace;

/**
 *
 * @author BKNyoni
 */
public class canvasHandler
{
	
	Image player1,player2,boardImg,dockImg,tempImg;
	Image CapturedByPlayer1,CapturedByPlayer2,backgroundImg;
        String IMAGE_PATH_STR ="res/images/";
       

	
        /**
         *
         * @param img_board
         * @param img_p1
         * @param img_p2
         */
        public canvasHandler(String img_board,String img_p1,String img_p2)
		{
		  setGameBoard(img_board);
		  setPlayer1Img(img_p1);
		  setPlayer2Img(img_p2);
			}
        /**
         *
         */
        public canvasHandler()
			{
			
			}		
		
                /**
                 *
                 * @param img
                 */
                public  void setGameBoard(String img)
		{
		  try
		  {
                      //System.out.println(img);
		  
                  ImageIcon ii =
	                new ImageIcon(this.getClass().getResource(img));
	        boardImg = ii.getImage();
			}
			catch(NullPointerException e)
			{
			 JOptionPane.showMessageDialog(null,"Mhele Board image not found.","Game Resource Error",
             JOptionPane.ERROR_MESSAGE);
               System.exit(1);
				}
			}
        /**
         *
         * @return
         */
        public Image getGameBoard()
		{
			return this.boardImg;
			}
		

        /**
         *
         * @param img
         */
        public void setGameDockPanel(String img)
		{
		  try
		  {
		  
		  ImageIcon ii =
	                new ImageIcon(this.getClass().getResource(img));
	        dockImg= ii.getImage();
			}
			catch(NullPointerException e)
			{
			 JOptionPane.showMessageDialog(null,"Mhele Dock image not found.","Game Resource Error",
             JOptionPane.ERROR_MESSAGE);
               System.exit(1);
				}
			}
       /**
        *
        * @return
        */
       public Image getGameDockPanel()
		{
			return this.dockImg;
			}
		
       /**
        *
        * @param img
        */
       public void setGameBackground(String img)
		{
		  try
		  {
		  
		  ImageIcon ii =
	                new ImageIcon(this.getClass().getResource(img));
	        backgroundImg= ii.getImage();
			}
			catch(NullPointerException e)
			{
			 JOptionPane.showMessageDialog(null,"Mhele Background image not found.","Game Resource Error",
             JOptionPane.ERROR_MESSAGE);
               System.exit(1);
				}
			}
        /**
         *
         * @return
         */
        public Image getGameBackground()
		{
			return this.backgroundImg;
			}
		
 
        
        /**
         *
         * @param img
         */
        public void setPlayer1Img(String img)
		   {
		   try
		   {
		   	
		  ImageIcon ii =
	                new ImageIcon(this.getClass().getResource(img));
	        player1 = ii.getImage();
			}
			catch(NullPointerException e)
			{
			 JOptionPane.showMessageDialog(null,"Game piece 1 image not found.","Game Resource Error",
             JOptionPane.ERROR_MESSAGE);
               System.exit(1);
				}
			}
		
        /**
         *
         * @return
         */
        public Image getPlayer1Img()
		{
			return this.player1;
			} 	
	
	
        /**
         *
         * @param img
         */
        public void setPlayer2Img(String img)
		   {
		  try
		  {
		   	ImageIcon ii =
	                new ImageIcon(this.getClass().getResource(img));
	        player2 = ii.getImage();
			}
			catch(NullPointerException e)
			{
			 JOptionPane.showMessageDialog(null,"Game piece 2 image not found.","Game Resource Error",
             JOptionPane.ERROR_MESSAGE);
               System.exit(1);
				}
			}
		
        /**
         *
         * @return
         */
        public Image getPlayer2Img()
		{
			return this.player2;
			}
        /**
         *
         * @param img
         * @return
         */
        public Image getGrayImg(String img)
     {
     	BufferedImage imgGrey =null;
     	 try {
              imgGrey = ImageIO.read(new File(img));
             } 
             catch (IOException e) 
             {}
           ColorSpace cs =ColorSpace.getInstance(ColorSpace.CS_GRAY);
           ColorConvertOp op=new ColorConvertOp(cs,null);
           imgGrey =op.filter(imgGrey,null);   
     return imgGrey;
     }			 	
	
    /**
     *
     * @param img
     * @return
     */
    public Image tempImghandler(String img)
		   {
		   	
		   try
		   {
		   	
		  ImageIcon ii =
	                new ImageIcon(this.getClass().getResource(img));
	         tempImg = ii.getImage();
	         return tempImg;
			}
			catch(NullPointerException e)
			{
			 JOptionPane.showMessageDialog(null,"Game piece 1 image not found.","Game Resource Error",
             JOptionPane.ERROR_MESSAGE);
               System.exit(1);
				}
			return tempImg;	
			}
			
						
	}