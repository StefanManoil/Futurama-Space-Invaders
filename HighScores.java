import java.awt.*;   
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
/**  
 *class HighScores - used to keep track of the five highest scores
 *Author: Stefan Manoil	                             
 *Created: January 12th, 2017
 *Modified: January 13th, 2017 */
public class HighScores extends JFrame implements ActionListener{
 JPanel outputInfoPanel;
 JLabel lblHighScores;
 Font highScoresFont;
 JLabel lblPlayerInfo;
 Font playerInfoFont;
 JButton btnMainMenu;
 
 //
 int playerScore;
 
 JPanel enterInfoPanel;
 JLabel lblGameOver;
 Font gameOverFont;
 JLabel lblenterName;
 JLabel score1, score2, score3, score4, score5, score6;
 Font enterNameFont;
 JTextField txtName;
 JButton btnOk;
 Font btnOkFont;
 NameAndScore [] namesScores = new NameAndScore[6];
 int lastIndex;
 
 /**  
 *constructor: instantiates a HighScores object. 
 *pre: score >=0	                             
 *post: a HighScore object is created   */     
 public HighScores(int score){
	 playerScore = score;
  
  gameOverFont = new Font("Serif", Font.PLAIN, 45);
  lblGameOver = new JLabel("Game Over");
  lblGameOver.setFont(gameOverFont);
  lblGameOver.setForeground(Color.WHITE);
  enterNameFont = new Font("Serif", Font.PLAIN, 20);
  lblenterName = new JLabel("Enter Your Name: ");
  lblenterName.setForeground(Color.WHITE);
  txtName = new JTextField(10);
  score1 = new JLabel();
  score1.setFont(enterNameFont);
  score1.setForeground(Color.WHITE);
  score2 = new JLabel();
  score2.setFont(enterNameFont);
  score2.setForeground(Color.WHITE);
  score3 = new JLabel();
  score3.setFont(enterNameFont);
  score3.setForeground(Color.WHITE);
  score4 = new JLabel();
  score4.setFont(enterNameFont);
  score4.setForeground(Color.WHITE);
  score5 = new JLabel();
  score5.setFont(enterNameFont);
  score5.setForeground(Color.WHITE);
  score6 = new JLabel();
  score6.setFont(enterNameFont);
  score6.setForeground(Color.WHITE);
  btnOkFont =  new Font("Serif", Font.PLAIN, 15);
  btnOk = new JButton("Ok");
  btnOk.setFont(btnOkFont);
  btnOk.setBackground(Color.BLACK);
  btnOk.setForeground(Color.WHITE);
  btnOk.setBorderPainted(false);
  btnOk.setContentAreaFilled(false);
  btnOk.setFocusable(false);
  btnOk.setActionCommand("btnOkClicked");
  btnOk.addActionListener(this);
  btnOk.addMouseListener(new MouseAdapter(){
	   public void mouseEntered(MouseEvent e){
	    btnOk.setForeground(Color.GREEN);
	   }
	   public void mouseExited(MouseEvent e){
	    btnOk.setForeground(Color.WHITE);
	   }
	  });
  enterInfoPanel = new JPanel();
  enterInfoPanel.setBackground(Color.BLACK);
  lblGameOver.setAlignmentX(CENTER_ALIGNMENT);
  lblenterName.setAlignmentX(CENTER_ALIGNMENT);
  txtName.setAlignmentX(CENTER_ALIGNMENT);
  btnOk.setAlignmentX(CENTER_ALIGNMENT);
  enterInfoPanel.setLayout(new BoxLayout(enterInfoPanel, BoxLayout.Y_AXIS));
  enterInfoPanel.add(lblGameOver);
  enterInfoPanel.add(lblenterName);
  enterInfoPanel.add(txtName);
  enterInfoPanel.add(btnOk);
  enterInfoPanel.add(score1);
  enterInfoPanel.add(score2);
  enterInfoPanel.add(score3);
  enterInfoPanel.add(score4);
  enterInfoPanel.add(score5);
  enterInfoPanel.add(score6);
  getContentPane().setBackground(Color.BLACK);
  getContentPane().setLayout(new FlowLayout());
  getContentPane().add(enterInfoPanel);
  setSize(500, 600);
  readScoresFromFile();
  refreshScores();
  setVisible(true);
 }
 
 /**  
 *an ActionListener interface method, used to detect user events 
 */   
 public void actionPerformed(ActionEvent evt){
	  if(evt.getActionCommand().equals("btnOkClicked")){
		  namesScores[lastIndex]= new NameAndScore (txtName.getText(), playerScore);
		  selectionSort(namesScores);
		  refreshScores();
		  writeScoresToFile();
		  txtName.setEditable(false);
	  }
	 }
 
 
 /**
 * Simple algorithm that sorts the data in an array in ascending order. 
 *pre: none	                             
 *post: the array passed as parameters is sorted in descending order.  
 */   
 
 public void selectionSort(NameAndScore []   data)
 {
	
     for (int end = lastIndex; end > 0; end --)
     {
         int smallie = 0;   //Location of smallest number
         for (int i =   1; i <= end; i++)
         {
               if(data[i].getScore() <   data[smallie].getScore())
               {
                   smallie = i;
               }
         }
           NameAndScore temp = data[end];
           data[end] = data[smallie];
           data[smallie] = temp;
     }
    
 }
 
 /**
  * A method that creates an input stream to a file, reads the file line by line and load the namesAndScores arrays. 
  *pre: none	                             
  *post: the namesAndScores array is loaded with data from the input file.  
  */ 
 public void readScoresFromFile(){
	  File textFile = new File("scores.txt");      
	  FileReader in;
	  BufferedReader readFile;
	  String lineOfText;
	  lastIndex = 0;
	  try {           
	    in = new FileReader (textFile);
	    readFile = new BufferedReader (in);
	    int i =0;
	    while ((i <= 4 && (lineOfText = readFile.readLine())!= null)){
	      System.out.println(lineOfText); 
	      namesScores[i] = new NameAndScore (lineOfText.substring(0, lineOfText.indexOf(" ")), 
	                       Integer.parseInt(lineOfText.substring(lineOfText.indexOf(" ")+1)));
	      i++;
	    }
	    lastIndex = i;
	    readFile.close();
	    in.close();
	  }
	  catch (FileNotFoundException e){
	   System.out.println("File does not exist or not found");
	   System.err.println("FileNotFoundException: " + e.getMessage());
	  }
	  catch (IOException e){
	   System.out.println("Problem reading file.");
	   System.err.println("IOException: " + e.getMessage());
	  }
 }
 
 /**
  * A method that creates an output stream to a file, and writes the content of namesAndScores array into a file. 
  *pre: none	                             
  *post: the data from the namesAndScores array is written into an output file  
  */ 
 public void writeScoresToFile(){
   File dataFile = new File("scores.txt");
   FileWriter out;
   BufferedWriter writeFile;
   try {
     out = new FileWriter(dataFile);
     writeFile = new BufferedWriter(out);
     for (int i = 0; i < 5; i++) {
    	 if (namesScores[i]!=null){
            writeFile.write(namesScores[i].getName() + " "+namesScores[i].getScore());
            writeFile.newLine();
    	 }
     } 
     writeFile.close();
     out.close();
     System.out.println("Data written to file.");
  } 
  catch (IOException e) {
   System.out.println("Problem writing to file.");
   System.err.println("IOException: " + e.getMessage());
  }
}
 
 /**
  * A method that displays the highest scores in the appropriate labels. 
  *pre: none	                             
  *post: the highest scores are displayed  
  */ 
 public void refreshScores(){
	  if (namesScores[0]!= null){
		  score1.setText(namesScores[0].getName()+ "    "+namesScores[0].getScore());
	  }
	  if (namesScores[1]!= null){
		  score2.setText(namesScores[1].getName()+ "    "+namesScores[1].getScore());
	  }
	  if (namesScores[2]!= null){
		  score3.setText(namesScores[2].getName()+ "    "+namesScores[2].getScore());
	  }
	  if (namesScores[3]!= null){
		  score4.setText(namesScores[3].getName()+ "    "+namesScores[3].getScore());
	  }
	  if (namesScores[4]!= null){
		  score5.setText(namesScores[4].getName()+ "    "+namesScores[4].getScore());
	  }
	  if (namesScores[5]!= null){
		  score6.setText(namesScores[5].getName()+ "    "+namesScores[5].getScore());
	  }
 }
}
