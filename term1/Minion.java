package term1;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Minion extends JButton{
      int weight, start, des, elenum, x, y, linkedIndex, remove;
      JButton b = new JButton();
      ImageIcon img[] = new ImageIcon[2];
      
      public Minion(){
         int weight=0;
         int start=-1;
         int des=-1;
         int linkedIndex=0;
         int elenum=0;
         int remove = 0;
         
         b.setBorderPainted(false);
         b.setFocusPainted(false);
         b.setContentAreaFilled(false);
         b.setVisible(true);
      }
      //
      public void setImage(int rand){
         if (rand == 0) {
            for (int j = 0; j < 2; j++) {
               img[j] = new ImageIcon("long" + (j + 1) + ".png");
               weight=50;
            }
         } else if (rand == 1) {
            for (int j = 0; j < 2; j++) {
               img[j] = new ImageIcon("icon" + (j + 1) + ".png");
               weight=60;
            }
         } else if (rand == 2) {
            for (int j = 0; j < 2; j++) {
               img[j] = new ImageIcon("f" + (j + 1) + ".png");
               weight=55;
            }
         }else if (rand == 3) {
            for (int j = 0; j < 2; j++) {
               img[j] = new ImageIcon("s" + (j + 1) + ".png");
               weight=65;
            }
         }else if (rand == 4) {
            for (int j = 0; j < 2; j++) {
               img[j] = new ImageIcon("q" + (j + 1) + ".png");
               weight=70;
            }
         }else if (rand == 5) {
            for (int j = 0; j < 2; j++) {
               img[j] = new ImageIcon("h" + (j + 1) + ".png");
               weight=75;
            }
         }
      }
      public ImageIcon getImage1(){
         return img[0];
      }
      public ImageIcon getImage2(){
         return img[1];
      }
      //
      public void setButton(JButton button){
         b = button;
      }
      public JButton getButton(){
         return b;
      }
      //
      public int getweight(){
         return weight;
      }
      //
      public void setStart(int s){
         start = s;
      }
      public int getStart(){
         return start;
      }
      //
      public void setDestination(int d){
         des = d;
      }
      public int getDestination(){
         return des;
      }
      //
      public void setX(int X){
         x = X;
      }
      public int getX(){
         return x;
      }
      //
      public void setY(int Y){
         y = Y;
      }
      public int getY(){
         return y;
      }
      //
      public void setEleNum(int en){
         elenum = en;
      }
      public void setEleNum()
      {
         elenum=0;
      }
      public int getEleNum(){
         return elenum;
      }
      //
      public void setLinkedIndex(int c){
         linkedIndex = c;
      }
      public int getLinkedIndex(){
         return linkedIndex;
      }
      //
      public void setRemove(int r){
         remove = r;
      }
      public int getRemove(){
         return remove;
      }
      public void setfloor(){
         
      }
   }