/**  
 *class GameScreen - used to make the GUI
 *Author: Stefan Manoil	                             
 *Created: January 5th, 2017
 *Modified: January 13th, 2017 
 */
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class GameScreen extends JFrame{
	Graphics g;
	BufferedImage bImage;
	JLabel label;
	ImageIcon img;
	ImageIcon shipImg;
	ImageIcon playerImg;
	ImageIcon playerBulletImg;
	ImageIcon shieldImg;
	//BonusShip bonus;
	Fleet myFleet = new Fleet();
	Player myPlayer;
	PlayerBullet myBullet;
	EnemyBullet enemyBullet;
	Shield[] shields;
	Timer moveTimer;
	Timer enemyFleetTimer;
	Timer fireBulletTimer;
	ImageObserver i;
	String key = "";
	/**  
	 *constructor: instantiates a GameScreen object
	 *pre: none                
	 *post: a GameScreen object is created
	 */
	public GameScreen(){
		setBounds(0, 0, 800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		label = new JLabel();
		bImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
		g = bImage.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		shipImg = new ImageIcon(getClass().getResource("/images/bonusShip.png"));
		playerImg = new ImageIcon(getClass().getResource("/images/player.png"));
		playerBulletImg = new ImageIcon(getClass().getResource("/images/playerBullet.png"));
		shieldImg = new ImageIcon(getClass().getResource("/images/shield.png"));
		i = this;
		//bonus = new BonusShip(0, 100, 54, 50, true, 1, 1, shipImg);
		myFleet.setFleet();
		myPlayer = new Player(360, 675, 50, 30, true, 10, playerImg);
		shields = new Shield[5];
		for(int i = 0; i < shields.length; i++){
			shields[i] = new Shield((i * 150 + 50), 550, 79, 60, true, shieldImg);
		}
		//
		
		this.addKeyListener (new KeyAdapter(){
		      public void keyPressed(KeyEvent e){
		        System.out.println(e.getKeyCode());
		          if (e.getKeyCode() == KeyEvent.VK_LEFT){
		        	  myPlayer.moveLeft();
		          }
		          if (e.getKeyCode() == KeyEvent.VK_RIGHT){
		        	  myPlayer.moveRight();
		          }
		          if (e.getKeyCode() == KeyEvent.VK_DOWN){
		        	  myBullet = new PlayerBullet(myPlayer.x1, myPlayer.y1, 10, 20, true, 15, playerBulletImg, myPlayer);
		        	  if(myPlayer.numLives > 0){
		        	  myPlayer.fireBullet(myBullet, g, i);
		        	  }
		        	/*  else{
		        		  HighScores score = new HighScores(myPlayer.score);
		        		  score.setVisible(true);
		        	  }*/
			      }
		       }
		      public void keyReleased(KeyEvent e){
		      }
		    });
		moveTimer = new Timer(10, new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				if (myPlayer.numLives> 0 && myFleet.reachedEnd == false && myPlayer.numKilled < 50){
				drawBackground();
				/*if(bonus != null && bonus.isOutside){
					bonus = null;
				}
				if(bonus != null){
					bonus.move();
					bonus.draw(g, i);
				}*/
				myFleet.drawFleet(g, i);
				for(int x = 0; x < shields.length; x++){
					shields[x].draw(g, i);
				}
				myPlayer.draw(g, i);
				if(myBullet != null && myBullet.hitTarget){
					myBullet = null;
				}
				if(myBullet != null){
					myBullet.draw(g, i);
					myBullet.move();
					myBullet.colide(myFleet.fleet);
					myBullet.collideShields(shields);
				}
				if(enemyBullet != null && enemyBullet.hitTarget){
					enemyBullet = null;
				}
				if(enemyBullet != null){
					enemyBullet.draw(g, i);
					enemyBullet.move();
					enemyBullet.collideShields(shields);
					enemyBullet.collidePlayer(myPlayer);
				}
				Font f = new Font("Papyrus", Font.BOLD, 30);
				g.setFont(f);
				g.setColor(Color.WHITE);
				g.drawString("Lives: " + myPlayer.numLives, 600, 50);
				g.drawString("Score: " + myPlayer.score, 600, 100);
				applyBufferedImage();
				}
				else{
					HighScores score = new HighScores(myPlayer.score);
	        		score.setVisible(true);
	        		moveTimer.stop();
	        		enemyFleetTimer.stop();
	        		fireBulletTimer.stop();
				}
				
			}
		});
		moveTimer.start();
		
		enemyFleetTimer = new Timer(40, new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				myFleet.move();
			}
		});
		enemyFleetTimer.start();
		fireBulletTimer = new Timer(800, new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				int row = (int)(5 * Math.random());
				int col = (int)(10 * Math.random());
				enemyBullet = new EnemyBullet(myFleet.fleet[row][col].x1, myFleet.fleet[row][col].y1, 10, 20, true, 15, playerBulletImg);
	        	myFleet.fleet[row][col].fireBullet(enemyBullet, g, i);
			}
		});
		fireBulletTimer.start();
	}
	/**  
	 *method that draws the background
	 *pre: none                   
	 *post: black background is drawn
	 */
	public void drawBackground(){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
	}
	/**  
	 *method used in doubleBuffering to paint graphics
	 *pre: none                   
	 *post: images are shown
	 */
	public void applyBufferedImage(){
		img = new ImageIcon(bImage);
		label.setIcon(img);
		this.add(label);
		setVisible(true);
	}
	public static void main(String[] args){
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				MainMenu frame = new MainMenu();
				//GameScreen frame = new GameScreen();
			}
		});
	}
}
