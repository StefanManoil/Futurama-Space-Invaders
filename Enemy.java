/**  
 *class Enemy - used to represent an enemy with location, speed, direction...
 *Author: Stefan Manoil	                             
 *Created: January 5th, 2017
 *Modified: January 13th, 2017 
 */
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

import java.awt.Graphics;
//import java.awt.geom.*;

public class Enemy extends Shape{
	protected int direction;
	protected int speed;
	protected boolean reachedEnd = false;
	protected boolean isShot = false;
	protected boolean shotOnce = false;
	protected ImageIcon img = new ImageIcon(getClass().getResource("/images/enemy.gif"));
	
	 /**  
	 *constructor: instantiates an Enemy object 
	 *pre: x1 >=0, y1>=0, width>=0; height>=0, speed>=0                   
	 *post: an Enemy object is created   
	 */
	public Enemy(int x1, int y1, int width, int height, boolean canCollide, int speed, int direction, ImageIcon img){
		super(x1, y1, width, height, canCollide);
		this.speed = speed;
		this.direction = direction;
		this.img = img;
	}
	 /**  
	 *method that moves the Enemy object left/right
	 *pre: none                   
	 *post:the x1, x2 coordinates are changed
	 */
	public void moveRightLeft(){
		//System.out.println(x2);
		//if((x1 < 20 || x2 > 780) && reachedEnd == false){
		//	reachedEnd = true;
		//}
		//else{
			//reachedEnd = false;
			x1 += speed * direction;
			x2 += speed * direction;
		//}
	}
	
	 /**  
	 *method that moves the Enemy object up/down
	 *pre: none                   
	 *post:the y1, y2 coordinates are changed
	 */
	public void moveDown(){
			y1 += speed + 10;
			y2 += speed + 10;
	}
	/*public void fireBullet(EnemyBullet eb){
		g.drawImage(eb);
	}*/
	
	 /**  
	 *method that draws an Enemy object on a Graphics object provided as parameter
	 *pre: none                   
	 *post:an Enemy object is drawn
	 */
	public void draw(Graphics g, ImageObserver parent){
			g.drawImage(img.getImage(), x1, y1, parent);
	}
	 /**  
	 *fires a bullet at the position of the enemy
	 *pre: none                   
	 *post:bullet is fired from enemy's position
	 */
	public void fireBullet(EnemyBullet eb, Graphics g, ImageObserver i){
		if(isShot == false){
		eb.drawBullet(g, i, x1 + 22, y1 + 25);
		}
	}
	/**  
	 *method that returns whether a shape can collide or not
	 *pre: none                   
	 *post:boolean that shows if shape collides
	 */
	public boolean isCollidable(){
		return canCollide;
	}
	/*public ImageIcon getImageIcon(){
		return img;
	}
	public boolean getReachedEnd(){
		return reachedEnd;
	}*/
}


