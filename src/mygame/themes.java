package mygame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
/**
 *
 * @author BKNyoni
 */
public class themes extends JDialog implements ActionListener
  
{
   final private String IMAGE_PATH_STR ="res/images/themes/";
   //final private String THEME_PATH_STR="themes/skins/";
   //private String choosen;
   private String GAME_NAME;  //="MHELE THEMES"; 
   private String GAME_BOARDS; // ="Boards";
   private String boardIMG = "Giraffe";
   private String boardIMG2 = "Leopard";
   private String boardIMG3  = "Cow";
   private String boardIMG4 = "Traditional";
   private String boardIMG5  = "Botswana";
   private String boardIMG_lang,boardIMG2_lang,boardIMG3_lang;
   private String boardIMG4_lang,boardIMG5_lang;
   private JLabel picture;
   
   board boardGame;
	
   /**
    *
    * @param frame
    * @param boardGame
    */
   public themes(JFrame frame,board boardGame)
	 {
            super(frame);
            this.boardGame = boardGame;
            setThemesLang();
	    setTitle(GAME_NAME);
	    super.setBackground(Color.BLACK);
	    setSize(343,220);
	    setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    setLocationRelativeTo( null );
           setIconImage(new ImageIcon(new board().imgHandler.getGameBoard()).getImage());
	    	  
            setDefaultLookAndFeelDecorated(true);
                 setCursor(new board().getGameCursor());
                  intOptions(); 
		}
           private void intOptions()
           {
               
            // Create the radio buttons.
        JRadioButton boardButton = new JRadioButton( boardIMG_lang);
        //boardButton.setMnemonic(KeyEvent.VK_G);
        boardButton.setActionCommand(boardIMG);
        //boardButton.setSelected(true);

        JRadioButton board2Button = new JRadioButton(boardIMG2_lang);
        //board2Button.setMnemonic(KeyEvent.VK_L);
        board2Button.setActionCommand(boardIMG2);

        JRadioButton board3Button = new JRadioButton(boardIMG3_lang);
        //board3Button.setMnemonic(KeyEvent.VK_C);
        board3Button.setActionCommand(boardIMG3);

         JRadioButton board4Button = new JRadioButton(boardIMG4_lang);
        //board4Button.setMnemonic(KeyEvent.VK_T);
        board4Button.setActionCommand(boardIMG4);
        
        JRadioButton board5Button = new JRadioButton(boardIMG5_lang);
        //board5Button.setMnemonic(KeyEvent.VK_B);
        board5Button.setActionCommand(boardIMG5);
        board5Button.setSelected(true);
       
       
        // Group the radio buttons.
        ButtonGroup group = new ButtonGroup();
        group.add(boardButton);
        group.add(board2Button);
        group.add(board3Button);
        group.add(board4Button);
        group.add(board5Button);
        // Set up the picture label
        picture = new JLabel(new ImageIcon(this.getClass().getResource(IMAGE_PATH_STR 
                                           + boardIMG5 
                                           + ".jpg")));

        // The preferred size is hard-coded to be the width of the
        // widest image and the height of the tallest image.
        // A real program would compute this.
        picture.setPreferredSize(new Dimension(201, 170));


        // Put the radio buttons in a column in a panel
         JPanel radioPanel = new JPanel();
         TitledBorder title = BorderFactory.createTitledBorder(GAME_BOARDS);
           radioPanel.setBorder(title);

         
        radioPanel.setLayout(new GridLayout(0, 1));
         radioPanel.add(board5Button);
         radioPanel.add(board3Button);
         radioPanel.add(boardButton);
         radioPanel.add(board2Button);
         radioPanel.add(board4Button);
       
         // Register a listener for the radio buttons.
       
        boardButton.addActionListener(this);
        board2Button.addActionListener(this);
        board3Button.addActionListener(this);
        board4Button.addActionListener(this);
        board5Button.addActionListener(this);
        
        setLayout(new BorderLayout());
        add(picture, BorderLayout.WEST);
        add(radioPanel, BorderLayout.CENTER);
       
       }
     
    @Override
    public void actionPerformed(ActionEvent e) {
       picture.setIcon(new ImageIcon(this.getClass().getResource(IMAGE_PATH_STR 
                                          + e.getActionCommand() 
                                          + ".jpg")));
       //System.out.println(e.getActionCommand());
        boardGame.updateThemes(e.getActionCommand());
        boardGame.repaint();
      }
    /**
     * 
     */
    public void setThemesLang()
    {
        if(boardGame.getlanguage() == null ? "EN" == null : boardGame.getlanguage().equals("EN"))
        {
          GAME_NAME = "MHELE THEMES"; 
          GAME_BOARDS= "Boards";
          boardIMG_lang = "Giraffe";
          boardIMG2_lang = "Leopard";
          boardIMG3_lang  = "Cow";
          boardIMG4_lang = "Traditional";
          boardIMG5_lang  = "Botswana";
        }
        else if(boardGame.getlanguage() == null ? "TN" == null : boardGame.getlanguage().equals("TN"))
        {
          GAME_NAME = "LESAKA LA MHELE"; 
          GAME_BOARDS= "LESAKA";
          boardIMG_lang = "Thutlwa";
          boardIMG2_lang = "Lengau";
          boardIMG3_lang  = "Kgomo";
          boardIMG4_lang = "Ngwao";
          boardIMG5_lang  = "Botswana";
        }
    }
    
}
