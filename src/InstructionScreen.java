import java.awt.*;   
import java.awt.event.*;

import javax.swing.*;
public class InstructionScreen extends JFrame implements ActionListener{
	JPanel mainPanel;
	JLabel lblTitle;
	JLabel lblInstructions;
	JButton btnMainMenu;
	Font titleFont;
	Font textFont;
	
	public InstructionScreen(){
		titleFont = new Font("Serif", Font.PLAIN, 45);
		lblTitle = new JLabel("Instructions");
		lblTitle.setFont(titleFont);
		lblTitle.setForeground(Color.WHITE);
		textFont = new Font("Serif", Font.PLAIN, 25);
		lblInstructions = new JLabel("<html>Use left and right arrow keys to move left or right.<br>Use down arrow key to fire bullets.<br>Stop the alien invaders from reaching your base."
				+ "<br>You have a collection of shields that can protect you<br>from from the incoming fire of enemies. These shields<br>can take up to 3 hits from enemies but beware<br> "
				+ "because you can shoot your own shields as well.<br>Use your reflexes and wits to defend the base, good luck.</html>");
		lblInstructions.setFont(textFont);
		lblInstructions.setForeground(Color.WHITE);
		//System.out.println(lblInstructions.getPreferredSize().toString());
		btnMainMenu = new JButton("Main Menu");
		btnMainMenu.setFont(textFont);
		btnMainMenu.setBackground(Color.BLACK);
		btnMainMenu.setForeground(Color.WHITE);
		btnMainMenu.setBorderPainted(false);
		btnMainMenu.setContentAreaFilled(false);
		btnMainMenu.setFocusable(false);
		btnMainMenu.setActionCommand("btnMainMenuIsClicked");
		btnMainMenu.addActionListener(this);
		btnMainMenu.addMouseListener(new MouseAdapter(){
			   public void mouseEntered(MouseEvent e){
				   btnMainMenu.setForeground(Color.GREEN);
			   }
			   public void mouseExited(MouseEvent e){
				   btnMainMenu.setForeground(Color.WHITE);
			   }
			  });
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.BLACK);
		lblTitle.setAlignmentX(CENTER_ALIGNMENT);
		lblInstructions.setAlignmentX(CENTER_ALIGNMENT);
		btnMainMenu.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.add(lblTitle);
		mainPanel.add(lblInstructions);
		mainPanel.add(Box.createVerticalStrut(100));
		mainPanel.add(btnMainMenu);
		getContentPane().setBackground(Color.BLACK);
		getContentPane().setLayout(new FlowLayout());
		getContentPane().add(mainPanel);
		setSize(600, 600);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent evt){
		  if(evt.getActionCommand().equals("btnMainMenuIsClicked")){
			  this.setVisible(false);
			  MainMenu menu = new MainMenu();
		  }
	}
}
