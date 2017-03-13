/**  
 *class Shape - used to represent a base class shape
 *Author: Stefan Manoil	                             
 *Created: January 5th, 2017
 *Modified: January 13th, 2017 
 */
public abstract class Shape {
	protected int x1;
	protected int y1;
	protected int width;
	protected int height;
	protected int x2;
	protected int y2;
	protected boolean canCollide;
	
	/**  
	 *constructor: instantiates a Shape object
	 *pre: x1 >=0, y1>=0, width>=0; height>=0                  
	 *post: an Shape object is created   
	 */
	public Shape(int x1, int y1, int width, int height, boolean canCollide){
		this.x1 = x1;
		this.y1 = y1;
		this.width = width;
		this.height = height;
		x2 = x1 + width;
		y2 = y1 + height;
		this.canCollide = canCollide;
	}
	// setters
	
	//public void setCanCollide(boolean canCollide){
	//	this.canCollide = canCollide;
	//}
	// getters
	// abstract method
	/**  
	 *abstract method that needs to be overwritten
	 *pre: none                   
	 *post:none
	 */
	public abstract boolean isCollidable();
	/**  
	 *method that checks if a shape intersects with another shape
	 *pre: none                   
	 *post: boolean value is returned 
	 */
	public boolean intersects(Shape other){
		return (x1 < other.x2) && (y1 < other.y2) 
	            && (x2 > other.x1) && (y2 > other.y1);
	}
	
}
