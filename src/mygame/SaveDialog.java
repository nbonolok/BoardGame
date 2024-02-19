/*
 * SaveDialog.java
 *
 * Created on January 24, 2010, 8:04 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package mygame;



import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
/**
 * This is a dialog window class that is used as a GUI for getting a file name
 * that will be used as a saved game.
 * @author BKNyoni
 */
class SaveDialog extends JDialog implements ActionListener
  {
    final private String GAME_NAME ="Save Game"; 
    private JTextField gameName;
    private JPanel panel;
    private JButton save,cancel;
    board boardGame;
    saveGame storage;
        /**
         * Class Constructor
         * @param frame JFrame
         * @param boardGame board class
         */
	public SaveDialog(JFrame frame,board boardGame) 
	 {
	   super(frame);
	   this.boardGame = boardGame; 
           setTitle(GAME_NAME);
	    setSize(320,150);
		
			setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    setLocationRelativeTo(null);
	setIconImage(new ImageIcon(new board().imgHandler.getGameBoard()).getImage());
	 setCursor( boardGame.getGameCursor());
       
        add(getDialogPanel());
        repaint();
		}
        /**
         * This method is used for generating a TextField.
         * @return gameName this is a textField
         */
        private JTextField getGameSaveField()
        {
            
          gameName = new JTextField(10);
          //gameName.setBackground(Color.DARK_GRAY);
          gameName.setForeground(Color.GREEN);
          
          TitledBorder title = BorderFactory.createTitledBorder("Enter Game to be Saved");

          gameName.setBorder(title);
          Font textfont = new Font("Book Antiqua",Font.BOLD,30);
          gameName.setFont(textfont);
          gameName.setTransferHandler(null); //disable paste
        
           gameName.addKeyListener(new KeyAdapter() {
            @Override
           public void keyTyped(KeyEvent e) {
              char c = e.getKeyChar();
               
            if (!((c >= 'a') && (c <= 'z') || (c >= 'A') && (c <= 'Z') ||
               (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) 
               || (c==KeyEvent.VK_CONTROL )) || gameName.getText().length() >= 15) 
                     {
                      e.consume();
                      }
                 else if( gameName.getText().length() < 1)
                       {
                         save.setEnabled(false);
                         cancel.setEnabled(true);
                         }
                 else if( gameName.getText().length() >= 1)
                       {
                         save.setEnabled(true);
                         cancel.setEnabled(true);
                         }
                     }
                  });    
         return gameName;
        }
        private JPanel getDialogPanel()
        {
            panel= new JPanel(new FlowLayout());
            ImageIcon ButtonIcon = new ImageIcon(this.getClass().getResource("res/images/b_save.png"));
            save = new JButton("Save",ButtonIcon);
            
            save.setFont(new Font("Comic Sans MS",Font.ITALIC,18));
            save.setForeground(Color.ORANGE);
            save.setBackground(Color.BLACK);
            save.setActionCommand("save");
            save.setEnabled(false);
            save.addActionListener(this);

            cancel = new JButton("Cancel");
            cancel.setFont(new Font("Comic Sans MS",Font.ITALIC,18));
            cancel.setForeground(Color.ORANGE);
            cancel.setBackground(Color.BLACK);
            cancel.setActionCommand("cancel");
            cancel.setEnabled(false);
            cancel.addActionListener(this);

             panel.add(getGameSaveField());
             panel.add(save);
             panel.add(cancel);
            return panel;
     }
    @Override
         public void actionPerformed(ActionEvent e)
        {
             if(e.getActionCommand() == null ? "save" == null : e.getActionCommand().equals("save"))
             {
                  //System.out.println("saving......");
                  storage = new saveGame(boardGame);
                  storage.saveGameMode();
                  storage.savePlayerLocation(boardGame.C_PLAYER,boardGame.Ms_count_Click,boardGame.POSITION,
                                             boardGame.START_POSITION,boardGame.END_POSITION,boardGame.TEMP_POSITION,
                                             boardGame.boardGameFlag,boardGame.moveflag);
                  
                  storage.saveRoamingModeFlag(boardGame.ROAM_PLAYER1,boardGame.ROAM_PLAYER1_MSG,boardGame.ROAM_PLAYER2,boardGame.ROAM_PLAYER2_MSG,
                                              boardGame.ColumnFlag,boardGame.RowFlag,boardGame.ROAM_COUNTER_P1,boardGame.ROAM_COUNTER_P2,boardGame.dialogCounter);
                  
                  storage.saveGameTheme(boardGame.THEME_POINTER,boardGame.SV_player1,boardGame.SV_player2,boardGame.SV_mheleBoard,
                                       boardGame.SV_dock,boardGame.SV_background,boardGame.music.getSoundFlag(),boardGame.getlanguage());
                  
                  storage.saveCapsInfo(boardGame.getGameTokens(),boardGame.CAP_LAYOUT);
                  storage.saveDragCapsInfo();
                  storage.saveGameWorld(gameName.getText());
                  
                  this.setVisible(false);
                }
             else if(e.getActionCommand() == null ? "cancel" == null : e.getActionCommand().equals("cancel"))
               {
                  this.setVisible(false);
                }
        }
    @Override
         public void paint(Graphics g)
         {
             super.paint(g);
             g.drawImage(boardGame.imgHandler.tempImghandler(boardGame.THEME_PATH_STR+boardGame.SaveDialogBackground),0,0,getWidth(),getHeight(), this);
             
         }
}