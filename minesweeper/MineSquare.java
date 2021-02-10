/**
Cole Frost - Final Project - MineSquare.java

Implements 2 abstract methods from Square. This is a Mine.
*/
public class MineSquare extends Square {
   /**
   If the Minesquare is not flagged - calls setUncovered and sets the element
   to a *
   @return Whether the square's uncovered var boolean value
   **/
   public boolean uncover() {
      if (!this.equals("f")) {
         super.setUncovered();
         super.setElement("*");
      }
      return super.isUncovered();
   }
   
   /**
   Returns true that this object is a mine.
   @return True
   **/
   public boolean isMine() {
      return true;
   }
}