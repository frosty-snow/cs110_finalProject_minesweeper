/**
Cole Frost - Final Project - NumberSquare.java

Implements 2 abstract methods from Square and one additional
method along with a constructor. Keep vars of the number of
mines adjacent and the row and col of the object in the grid.
*/
public class NumberSquare extends Square {
   private int neighborMines;
   private int myRow;
   private int myCol;
   
   /**
   Constructor that creates an instance of a numbersquare object
   @param neighborMines The number of mines adjacent to this object in the grid
   @param myRow The row of the object
   @param myCol The column of the object
   **/
   public NumberSquare(int neighborMines, int myRow, int myCol) {
      super();
      if (neighborMines == 0)
         super.setElement("_");
      else
         super.setElement(Integer.toString(neighborMines));
      this.neighborMines = neighborMines;
      this.myRow = myRow;
      this.myCol = myCol;
   }
   
   /**
   If the object is not flagged - then it sets the uncovered value and returns it
   @return The value of the uncovered var, whether true or false
   **/
   public boolean uncover() {
      if (!this.isFlagged())
         super.setUncovered();
      return super.isUncovered();
   }
   
   /**
   Getter for the neighborMines var
   @return The number of neighbor mines
   **/
   public int getNeighborMines() {
      return this.neighborMines;
   }
   
   /**
   Always returns false as a numbersquare is never a mine
   @return False
   **/
   public boolean isMine() {
      return false;
   }
}