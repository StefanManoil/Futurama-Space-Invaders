/**  
 *class PlayerBullet - used to create and draw a player bullet
 *Author: Stefan Manoil	                             
 *Created: January 8th, 2017
 *Modified: January 13th, 2017 
 */

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.geom.*;

public class PlayerBullet extends Shape{
	protected int direction;
	protected int speed;
	protected boolean hitTarget = false;
	protected ImageIcon img;
	boolean bulletDrawn = false;
	Player shooter;
	
	/**  
	 *constructor: instantiates a PlayerBullet object
	 *pre: x1 >=0, y1>=0, width>=0; height>=0, speed>=0                   
	 *post: a PlayerBullet object is created   
	 */
		public PlayerBullet(int x1, int y1, int width, int height, boolean canCollide, int speed, ImageIcon img, Player shooter){
			super(x1, y1, width, height, canCollide);
			this.speed = speed;
			this.img = img;
			this.shooter = shooter;
		}
		 /**  
		 *method that draws an PlayerBullet object on a Graphics object provided as parameter, and updates position
		 *pre: none                   
		 *post:a PlayerBullet object is drawn
		 */
		public void drawBullet(Graphics g, ImageObserver parent, int x, int y){
			g.drawImage(img.getImage(), x, y, parent);
			x1 = x;
			y1 = y;
			bulletDrawn = true;
		}
		 /**  
		 *method that draws an Enemy object on a Graphics object provided as parameter
		 *pre: none                   
		 *post:an Enemy object is drawn
		 */
		public void draw(Graphics g, ImageObserver parent){
			if(bulletDrawn){
				g.drawImage(img.getImage(), x1, y1, parent);
			}
			//System.out.println(x1);
	    }
		 /**  
		 *method that moves the PlayerBullet object 
		 *pre: none                   
		 *post:the y coordinates are changed
		 */
		public void move(){
			if(bulletDrawn){	
				if(y1 > 0 || hitTarget == false){
					y1 -= speed;
					y2 -= speed;
					}
				}
			else{
				bulletDrawn = false;
				}
			}
		 /**  
		 *method that checks is this shape is collidable 
		 *pre: none                   
		 *post:a boolean true is other shape can collide with this shape
		 */
		public boolean isCollidable(){
			return canCollide;
		}
	/*	public void intersect(Enemy[][] other){
			for(int i = 0; i < other.length; i++){
				for(int j = 0; j < other[i].length; j++){
					if((other[i][j].isShot == false && (this.x1 < other[i][j].x2) && (this.y1 < other[i][j].y2) && (this.x2 > other[i][j].x1) && (this.y2 > other[i][j].y1))){
						other[i][j].isShot = true;
						hitTarget = true;
					}
				}
			}
		}*/
		 /**  
		 *method that updates the status of enemies upon collision with PlayerBullet 
		 *pre: none                   
		 *post:enemies updated
		 */
		public void colide(Enemy[][] enemies){
			for(int i = 0; i < enemies.length; i++){
				for(int j = 0; j < enemies[i].length; j++){
					if (intersects(enemies[i][j])){
						enemies[i][j].isShot = true;
						hitTarget = true;
						shooter.score+=10;
						shooter.numKilled++;
					}
				}
			}
		}
		 /**  
		 *method that checks is the PlayerBullet colliades with the Shield objects 
		 *pre: none                   
		 *post:The Shield objects' numLivers variables are updated
		 */
		public void collideShields(Shield[] shields){
			for(int i = 0; i < shields.length; i++){
				if(shields[i].numLives > 0){
					if(super.intersects(shields[i])){
						shields[i].numLives--;
						hitTarget = true;
					}
				}
			}
		}
		
		/**  
		 *method that checks if a PlayerBullet intersects with another shape
		 *pre: none                   
		 *post: boolean value is returned 
		 */
		public boolean intersects(Shape shape){
			Enemy other = (Enemy)shape;
			boolean intersects = false;
			if((other.isShot == false && (this.x1 < other.x2) && (y1 < other.y2) && (x2 > other.x1) && (y2 > other.y1))){
				
			    intersects = true;
			}
			return intersects;
			

		}
}	
