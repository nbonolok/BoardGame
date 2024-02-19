package mygame;
import java.awt.Color;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
class developer extends JDialog
 //JFrame 
{
	final private String GAME_NAME ="ABOUT MHELE"; 
    private String logostr=" Developed by BK Nyoni\nnbonolok@gmail.com"; 
	private ImageIcon logo=null;
	public developer(JFrame frame) 
	 {
	   super(frame);
	    setTitle(GAME_NAME);
	    logo = new ImageIcon(this.getClass().getResource("res/images/logo.gif"));
	    if(logo !=null)
	    add(new JLabel(logostr));
	    if(logo !=null)
	    add(new JLabel(logo));
	    
	    	
	    super.setBackground(Color.WHITE);
	    setSize(420,220);
		setTitle(GAME_NAME);
			setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    setLocationRelativeTo( null );
	setIconImage(new ImageIcon(new board().imgHandler.getGameBoard()).getImage());
	  		 
		}
		}