/**  
 *class GameScreen - used to make the main menu screen
 *Author: Stefan Manoil	                             
 *Created: December 28, 2016
 *Modified: January 13th, 2017 
 */
import java.awt.*;   
import java.awt.event.*;
import javax.swing.*;
public class MainMenu extends JFrame implements ActionListener{
 JPanel mainPanel;
 ImageIcon imgFry;
 JLabel lblFry;
 Font titleFont;
 JLabel lblTitle;
 Font btnPlayFont;
 JButton btnPlay;
 Font btnInstructionsFont;
 JButton btnInstructions;
 Font btnHighScoresFont;
 //JButton btnHighScores;
 /**  
	 *constructor: instantiates a MainMenu object
	 *pre: none                
	 *post: a MainMenu object is created
	 */
 public MainMenu(){
  imgFry = new ImageIcon(getClass().getResource("images/fry.png"));
  lblFry = new JLabel();
  lblFry.setIcon(imgFry);
  titleFont = new Font("Serif", Font.PLAIN, 45);
  lblTitle = new JLabel("Futurama Space Invaders");
  lblTitle.setFont(titleFont);
  lblTitle.setForeground(Color.WHITE);
  btnPlayFont = new Font("Serif", Font.PLAIN, 30);
  btnPlay = new JButton("Play");
  btnPlay.setFont(btnPlayFont);
  btnPlay.setBackground(Color.BLACK);
  btnPlay.setForeground(Color.WHITE);
  btnPlay.setBorderPainted(false);
  btnPlay.setContentAreaFilled(false);
  btnPlay.setFocusable(false);
  btnPlay.setActionCommand("btnPlayIsClicked");
  btnPlay.addActionListener(this);
  btnPlay.addMouseListener(new MouseAdapter(){
   public void mouseEntered(MouseEvent e){
    btnPlay.setForeground(Color.GREEN);
   }
   public void mouseExited(MouseEvent e){
    btnPlay.setForeground(Color.WHITE);
   }
  });
  btnInstructions = new JButton("Instructions");
  btnInstructions.setFont(btnPlayFont);
  btnInstructions.setBackground(Color.BLACK);
  btnInstructions.setForeground(Color.WHITE);
  btnInstructions.setBorderPainted(false);
  btnInstructions.setContentAreaFilled(false);
  btnInstructions.setFocusable(false);
  btnInstructions.setActionCommand("btnInstructionsIsClicked");
  btnInstructions.addActionListener(this);
  btnInstructions.addMouseListener(new MouseAdapter(){
   public void mouseEntered(MouseEvent e){
	   btnInstructions.setForeground(Color.GREEN);
   }
   public void mouseExited(MouseEvent e){
	   btnInstructions.setForeground(Color.WHITE);
   }
  });
  mainPanel = new JPanel();
  mainPanel.setBackground(Color.BLACK);
  lblFry.setAlignmentX(CENTER_ALIGNMENT);
  lblTitle.setAlignmentX(CENTER_ALIGNMENT);
  btnPlay.setAlignmentX(CENTER_ALIGNMENT);
  btnInstructions.setAlignmentX(CENTER_ALIGNMENT);
  mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
  mainPanel.add(lblFry);
  mainPanel.add(lblTitle);
  mainPanel.add(btnPlay);
  mainPanel.add(btnInstructions);
  getContentPane().setBackground(Color.BLACK);
  getContentPane().setLayout(new FlowLayout());
  getContentPane().add(mainPanel);
  setSize(600, 600);
  setVisible(true);
 }
 /**  
	 *method used in listening for mouse events
	 *pre: none                   
	 *post: none
	 */
 public void actionPerformed(ActionEvent evt){
  if(evt.getActionCommand().equals("btnPlayIsClicked")){
	  this.setVisible(false);
	  GameScreen game = new GameScreen();
	  System.out.println("Play button clicked");
  }
  if(evt.getActionCommand().equals("btnInstructionsIsClicked")){
	  this.setVisible(false);
	  InstructionScreen instructions = new InstructionScreen();
	  System.out.println("Instructions button clicked");
  }
  if(evt.getActionCommand().equals("btnHighScoresIsClicked")){
	  System.out.println("High Scores button clicked");
  }
 }
 /*public static void main(String[] args){
  MainMenu startMenu = new MainMenu();
 }*/
}
