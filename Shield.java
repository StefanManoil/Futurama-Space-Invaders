/**  
 *class Shield - used to represent a Shield object
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
public class Shield extends Shape{
	protected ImageIcon img;
	protected int numLives;
	
	/**  
	 *constructor: instantiates a Shape object
	 *pre: x1 >=0, y1>=0, width>=0; height>=0                  
	 *post: a Shield object is created   
	 */
	public Shield(int x1, int y1, int width, int height, boolean canCollide, ImageIcon img){
		super(x1, y1, width, height, canCollide);
		this.img = img;
		numLives = 3;
	}
	 /**  
	 *method that draws a Shield object on a Graphics object provided as parameter
	 *pre: none                   
	 *post:an Shield object is drawn
	 */
	public void draw(Graphics g, ImageObserver parent){
		if(numLives > 0){
			g.drawImage(img.getImage(), x1, y1, parent);
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
	/**  
	 *method that returns the image for the Shield object
	 *pre: none                   
	 *post:an ImageIcon object is rerturned
	 */
	public ImageIcon getImageIcon(){
		return img;
	}
	/**  
	 *method that returns the numLives member for a Shield object
	 *pre: none                   
	 *post:an integer representing the remaining lives is returned
	 */
	public int getNumLives(){
		return numLives;
	}
}


