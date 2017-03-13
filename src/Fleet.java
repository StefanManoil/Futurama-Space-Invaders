/**  
 *class Fleet - used to represent a fleet of enemies
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
public class Fleet{
 int numEnemies;
 Enemy[][] fleet = new Enemy[5][10];
 int lastColumn;
 int remainingShipRow;
 int remainingShipColumn;
 int lastRow;
 int numElementsInEachColumn[] = {5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
 int numElementsInEachRow[] = {10, 10, 10, 10, 10};
 protected ImageIcon img = new ImageIcon(getClass().getResource("/images/enemy.gif"));
 int fleetDirection = 1;
 boolean reachedEnd = false;
 /**  
  *method that creates a 2d array of Enemies
  *pre: none                   
  *post:all the enemies are created
  */
 public void setFleet(){
  for(int i = 0; i < fleet.length; i++){
   for(int j = 0; j < fleet[i].length; j++){
    fleet[i][j] = new Enemy((j * 45 + 100), (i * 50 + 50), 55, 40, true, 5, 1, new ImageIcon(getClass().getResource("/images/enemy.gif")));
   }
  }
 }
 /**  
  *method that draws all the Enemies at their required positions
  *pre: none                   
  *post:all the Enemies are drawn
  */
 public void drawFleet(Graphics g, ImageObserver parent){
  for(int i = 0; i < fleet.length; i++){
   for(int j = 0; j < fleet[i].length; j++){
    if(fleet[i][j].isShot == false){
     g.drawImage(img.getImage(), fleet[i][j].x1, fleet[i][j].y1, parent);
    }
   }
  }
 }
 /**  
  *method that moves all the enemies as a unit, checking for collisions
  *pre: none                   
  *post:all the Enemies are moved
  */
 public void move(){
  checkLastColumn();
  setLastColumn();
  findPosColumn(lastColumn);
  checkLastRow();
  setLastRow();
  findPosRow(lastRow);
  /*for(int i = 0; i < fleet.length; i++){
   for(int j = 0; j < fleet[i].length; j++){
    if(fleet[remainingShipRow][lastColumn].isShot == false && fleet[remainingShipRow][lastColumn].x2 > 780){
     fleet[i][j].moveDown();
     fleet[i][j].direction = -1;
     fleetDirection = -1;
     //System.out.println(fleet[0][9].x2);
    }
    fleet[i][j].moveRightLeft();
    if(fleet[remainingShipRow][lastColumn].isShot == false && fleet[remainingShipRow][lastColumn].x1 < 10){
     fleet[i][j].moveDown();
     fleet[i][j].direction = 1;
     fleetDirection = 1;
     //System.out.println(fleet[0][0].x1);
    }
   }
  }*/
  if(fleet[remainingShipRow][lastColumn].isShot == false && fleet[remainingShipRow][lastColumn].x2 > 780){
   for(int i = 0; i < fleet.length; i++){
    for(int j = 0; j < fleet[i].length; j++){
     fleet[i][j].moveDown();
     fleet[i][j].direction = -1;
     fleetDirection = -1;
    }
   }
  }
  if(fleet[remainingShipRow][lastColumn].isShot == false && fleet[remainingShipRow][lastColumn].x1 < 10){
   for(int i = 0; i < fleet.length; i++){
    for(int j = 0; j < fleet[i].length; j++){
     fleet[i][j].moveDown();
     fleet[i][j].direction = 1;
     fleetDirection = 1;
    }
   }
  }
  //System.out.println("==="+fleet[remainingShipColumn][lastRow].y2);
  if(fleet[4][0].y2 >= 700){
   reachedEnd = true;
  // System.out.println(remainingShipColumn + "  " + lastRow);
  // System.out.println(fleet[remainingShipColumn][lastRow].y2);
  }
  for(int i = 0; i < fleet.length; i++){
   for(int j = 0; j < fleet[i].length; j++){
    fleet[i][j].moveRightLeft();
   }
  }
 }
 /**  
  *method that checks which column still contains enemies, used for checking wall collision
  *pre: none                   
  *post:updates how many elements are left in columns
  */
 private void checkLastColumn(){
  for(int i = 0; i < fleet.length; i++){
   for(int j = 0; j < fleet[i].length; j++){
    if(fleet[i][j].shotOnce == false){
     if(fleet[i][j].isShot == true){
      fleet[i][j].shotOnce = true;
      numElementsInEachColumn[j] --;
     }
    }
   }
  }
  //for(int i = 0; i < 10; i++){
  // System.out.println(numElementsInEachColumn[i]);
  //}
 }
 /**  
  *method that checks which row still contains enemies, used for terminating the game
  *pre: none                   
  *post:updates how many elements are left in rows
  */
 private void checkLastRow(){
  for(int i = 0; i < fleet.length; i++){
   for(int j = 0; j < fleet[i].length; j++){
    if(fleet[i][j].shotOnce == false){
     if(fleet[i][j].isShot == true){
      fleet[i][j].shotOnce = true;
      numElementsInEachRow[i] --;
     }
    }
   }
  }
 }
 /**  
  *method that sets the last possible row that still contains enemies
  *pre: none
  *post:sets last row of enemies
  */
 private void setLastRow(){
  for(int i = numElementsInEachRow.length - 1; i >= 0; i--){
   if(numElementsInEachRow[i] > 0){
    lastRow = i;
    break;
   }
  }
 }
 /**  
  *method that sets the last possible column that still contains enemies
  *pre: none
  *post:sets last column of enemies for wall detection
  */
 private void setLastColumn(){
  if(fleetDirection == 1){
   for(int i = numElementsInEachColumn.length - 1; i >= 0; i--){
    if(numElementsInEachColumn[i] > 0){
     lastColumn = i;
     break;
    }
   }
  }
  if(fleetDirection == -1){
   for(int i = 0; i < numElementsInEachColumn.length; i++){
    if(numElementsInEachColumn[i] > 0){
     lastColumn = i;
     break;
    }
   }
  }
  //System.out.println(lastColumn);
 }
 /**  
  *method that finds an enemy still alive in the required column
  *pre: column >= 0
  *post:sets pos of remaining ship in column
  */
 private void findPosColumn(int column){
  for(int i = 0; i < fleet.length; i++){
   if(fleet[i][column].isShot == false){
    remainingShipRow = i;
    break;
   }
  }
  //System.out.println(remainingShipRow);
 }
 /**  
  *method that finds an enemy still alive in the required row
  *pre: row >= 0
  *post:sets pos of remaining ship in row
  */
 private void findPosRow(int row){
  for(int i = 0; i < 10; i++){
   if(fleet[row][i].isShot == false){
    remainingShipColumn = i;
    break;
   }
  }
 }
 
}
