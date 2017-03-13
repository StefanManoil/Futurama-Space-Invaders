/*import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;
import java.awt.Graphics;
//import java.awt.geom.*;
public class BonusShip extends Shape{
	protected int direction;
	protected int speed;
	protected ImageIcon img;
	protected boolean isOutside = false;
	
	//constructor
	public BonusShip(int x1, int y1, int width, int height, boolean canCollide, int speed, int direction, ImageIcon img){
		super(x1, y1, width, height, canCollide);
		this.speed = speed;
		this.direction = direction;
		this.img = img;
	}
	public void move(){
		//System.out.println(x2);
		if(x1 < 0 || x2 > 850){
			isOutside = true;
		}
		x1 += speed * direction;
		x2 += speed * direction;
	}
	public void draw(Graphics g, ImageObserver parent){
		if(x1 > 60 || x2 < 560){
			g.drawImage(img.getImage(), x1, y1, parent);
		}
	}
	public boolean isCollidable(){
		return canCollide;
	}
	public ImageIcon getImageIcon(){
		return img;
	}
}*/


