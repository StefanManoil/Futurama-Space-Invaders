/**  
 *class HighScores - used to keep track of the name and score associated with a person
 *Author: Stefan Manoil	                             
 *Created: January 12th, 2017
 *Modified: January 13th, 2017 */
public class NameAndScore {
	private int score;
	private String name;
	 /**  
	 *constructor: instantiates a NameandScore object 
	 *pre: score >=0                    
	 *post: a NameAndScore object is created   */
	public NameAndScore(String name, int score){
	  this.name = name;
	  this.score = score;
	}
	 /**  
	 *getter that returns the name instance variable 
	 *pre: none                   
	 *post: the name of a NameAndScore object is returned   */
	public String getName(){
		return name;
	}
	 /**  
	 *getter that returns the score instance variable 
	 *pre: none                   
	 *post: the score of a NameAndScore object is returned   */
	public int getScore(){
		return score;
	}

}
