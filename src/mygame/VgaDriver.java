package mygame;
/**
 * .
 *
 * @author Bonolo K Nyoni 
 * @version 1.00 09/09/23
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author BKNyoni
 */
public class VgaDriver
{
	GraphicsEnvironment graphicsEnvironment ;
    GraphicsDevice graphicsDevice;
    DisplayMode originalDisplayMode;
    final int MODE = 19;  //800x600
    JWindow window; 
	JFrame frame;
	//constructor
        /**
         *
         * @param fr
         */
        public VgaDriver(JFrame fr)
	{
		 loadVgaDriver();
		 frame = fr;
		 
       
		}
		
		
        /**
         * 
         */
        public void loadVgaDriver()
	 {
	  graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
      graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
      DisplayMode displayModes[] =    graphicsDevice.getDisplayModes();
      originalDisplayMode = graphicsDevice.getDisplayMode();
      window = new JWindow(); 
       window.add(new board());
	 	
	 	try {
              if(graphicsDevice.isFullScreenSupported()) 
                  {
                   graphicsDevice.setFullScreenWindow(window);
                   //Thread.sleep(1000);
                   }
                
      
               DisplayMode displayMode = displayModes[MODE];
               
               if(graphicsDevice.isDisplayChangeSupported()) 
                  {
                   graphicsDevice.setDisplayMode(displayMode);
                   
                   }
             }
             catch(Exception eg){
             	
             	JOptionPane.showMessageDialog(null,"Display Graphics error\n"+eg.getMessage(),
             	"Hardware Error",JOptionPane.ERROR_MESSAGE);

             	}
	 	
	 	
	 	}	
         /**
          *
          */
         public void resetResolution()
	 {
	 	 graphicsDevice.setDisplayMode(originalDisplayMode);
         graphicsDevice.setFullScreenWindow(null);
         try{Thread.sleep(1000);
         }
         catch(Exception eg)
         {
         	 
         	}
	 	 }
	 	 	
   		 
	}