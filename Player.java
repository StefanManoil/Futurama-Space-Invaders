/**  
 *class Player - used to represent the player control by user
 *Author: Stefan Manoil	                             
 *Created: January 7th, 2017
 *Modified: January 13th, 2017 
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.geom.*;
public class Player extends Shape{
	protected int direction;
	protected int speed;
	protected boolean reachedEnd = false;
	protected boolean isShot = false;
	protected ImageIcon img;
	int numLives;
	int score;
	int numKilled;
	
	/**  
	 *constructor: instantiates a Player object
	 *pre: x1 >=0, y1>=0, width>=0; height>=0, speed>=0                   
	 *post: a Player object is created   
	 */
	public Player(int x1, int y1, int width, int height, boolean canCollide, int speed, ImageIcon img){
		super(x1, y1, width, height, canCollide);
		this.speed = speed;
		this.img = img;
		numLives = 3;
		score = 0;
		numKilled = 0;
	}
	/**  
	 *method that moves the player object right
	 *pre: none                   
	 *post:the x1, x2 coordinates are changed
	 */
	public void moveRight(){
		direction = 1;
		if(x2 < 780){
			x1 += speed * direction;
			x2 += speed * direction;
		}
	}
	/**  
	 *method that moves the player object left
	 *pre: none                   
	 *post:the x1, x2 coordinates are changed
	 */
	public void moveLeft(){
		direction = -1;
		if(x1 > 5){
			x1 += speed * direction;
			x2 += speed * direction;
		}
	}
	/**  
	 *method that fires bullet from pos of player
	 *pre: none                   
	 *post: a bullet is fired
	 */
	public void fireBullet(PlayerBullet eb, Graphics g, ImageObserver i){
		eb.drawBullet(g, i, x1 + 22, y1 - 25);
	}
	/**  
	 *method that draws player
	 *pre: none                   
	 *post: player position updated
	 */
	public void draw(Graphics g, ImageObserver parent){
		if(numLives > 0){
			g.drawImage(img.getImage(), x1, y1, parent);
		}
	}
	/**  
	 *method that resets poition of player
	 *pre: none                   
	 *post: player position updated
	 */
	public void warp(){
		x1 = 360;
		x2 = x1 + width;
	}
	/**  
	 *method that returns whether the player can collide or not
	 *pre: none                   
	 *post: boolean variable returned
	 */
	public boolean isCollidable(){
		return canCollide;
	}
	/**  
	 *method that returns the image of the class
	 *pre: none                   
	 *post: ImageIcon variable returned
	 */
	public ImageIcon getImageIcon(){
		return img;
	}
	public boolean getReachedEnd(){
		return reachedEnd;
	}
}