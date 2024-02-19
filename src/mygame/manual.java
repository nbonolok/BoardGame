package mygame;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;

import java.net.URL;
import java.io.IOException;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.JDialog;


import javax.swing.JFrame;
//import java.lang.System.*;

/**
 *
 * @author BKNyoni
 */
public class manual extends JDialog
{
private String GAME_NAME ="Mhele Manual"; 
final private String LANG_MANUAL_PATH="manual/";  
private String lang;
private JScrollPane scrollPane;
board boardGame;	
/**
 *
 * @param frame
 * @param boardGame
 */
public manual(JFrame frame,board boardGame)
	 {
           super(frame);
           this.boardGame = boardGame; 
           lang= boardGame.getlanguage();
	    setTitle(GAME_NAME);
	    setSize(780,600);
		setTitle(GAME_NAME);
			setResizable(false);
		 setIconImage(new ImageIcon(new board().imgHandler.getGameBoard()).getImage());
	  		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	         setLocationRelativeTo( null );
		setManualPane();
		setContentPane(scrollPane);
		}
	
	
/**
 *
 */
public void setManualPane()
	{
	       
                String str=null;
                URL path=null;
                try {
                 path = this.getClass().getResource(LANG_MANUAL_PATH+lang+"/index.html");
                
                }
                 catch(Exception e)
	          {
                     
	   	  System.exit(-1);
	   	 }
                 JEditorPane jep = new JEditorPane();
                 
		 jep.setEditable(false);
                 jep.setDoubleBuffered(true);
                 jep.addHyperlinkListener(new LinkFollower(jep));
		 
		 try
		 {
		   jep.setPage(path);
		   }
		 
		 catch(IOException e)
		 {
		 	System.err.println(e);
		   }
		 	
		scrollPane =new JScrollPane(jep);
		 }
		
	 class  LinkFollower implements HyperlinkListener
	{
		private JEditorPane pane;
		
		public LinkFollower(JEditorPane pane)
		{
			this.pane =pane;
                        
			}
        @Override
		public void hyperlinkUpdate(HyperlinkEvent evt)
		{
			if(evt.getEventType()== HyperlinkEvent.EventType.ACTIVATED)
			{
				try
				{
					pane.setPage(evt.getURL());
                                        pane.setCursor(boardGame.getGameCursor());
				}
				catch(Exception e)
				{
					}
			}
		}
		
		
		
		
		
			 
		}
	}
