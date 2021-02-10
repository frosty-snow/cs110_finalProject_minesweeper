/**
Cole Frost - Final Project - Grid.java

Creates an object that holds a 2D array of Squares. This grid
is the board for the minesweeper game.
*/
public class Grid {
   private Square[][] grid;
   private int height;  // rows
   private int width;   //  columns
   private int numMines;
   private int numSquaresUncovered = 0;
   public enum Status {
      OK, WIN, MINE
   }
   
   /**
   Constructor for grid object. Randomly chooses places in the grid for mines to
   be.
   @param height The number of rows
   @param width The number of columns
   @param numMines The number of mines to have in the puzzle
   **/
   public Grid(int height, int width, int numMines) {
      this.grid = new Square[height][width];
      this.height = height;
      this.width = width;
      this.numMines = numMines;
      
      int totalSquares = height * width;
      String selectedSquare = "";

      // Below block randomly selects places for the mines
      for (int i = 0; i < numMines; i++){
           int indexToSelect = (int)(Math.random()*totalSquares);
           
           while(selectedSquare.indexOf(String.valueOf(indexToSelect)) > 0) {
                indexToSelect = (int)(Math.random()*totalSquares);
           }
           
           selectedSquare = selectedSquare + indexToSelect;
           int row = (int)indexToSelect / width;
           int col = indexToSelect % width;
           this.grid[row][col] = new MineSquare();
           this.grid[row][col].setElement("*");
      }
      
      // This loops makes the rest of the board into NumberSquares
      for (int j = 0; j < height; j++) {
         for (int k = 0; k < width; k++) {
            if (this.grid[j][k] == null) {
               int adjMines = getNeighbors(j,k);
               this.grid[j][k] = new NumberSquare(adjMines, j,k);   
            }
         }
      }
   }
   
   /**
   Calculates the number of mines adjacent to the given square and returns this int
   @param row The index of the row value of the square you are trying to get neighbors for
   @param col The index of the col value of the square you are trying to get neighbors for
   @return The number of adjacent mines
   **/
   public int getNeighbors(int row, int col) {
      int adjMines = 0;
      int firstRow = 0;
      int lastRow = 0;
      int firstCol = 0;
      int lastCol = 0;
      
      if (row < 2) {
         firstRow = 0;
         lastRow = row + 1;
      } else if (row >= this.height - 2) {
         lastRow = this.height - 1;
         firstRow = row - 1;
      } else {
         firstRow = row - 1;
         lastRow = row + 1;
      }
      
      if (col < 2) {
         firstCol = 0;
         lastCol = col + 1;
      } else if (col > this.width - 2) {
         firstCol = col - 1;
         lastCol = this.width - 1;
      } else {
         firstCol = col - 1;
         lastCol = col + 1;
      }
         
      for (int i = firstRow; i <= lastRow; i++) {
         for (int j = firstCol; j <= lastCol; j++) {
            try {
                  if (this.grid[i][j].isMine())
                     adjMines++;
            } catch (NullPointerException ignored) {
                  // This is left blank - as if the Square is null, then it is not a mine
                  // and therefore we simply do not want to increase adjMines but also
                  // don't want the program to crash. By the end of the constructor loop
                  // all Squares will no longer be null, either mines or numberSquares
            }
         }
      }
         
      return adjMines;
   }
   
   /**
   If the square is flagged or already uncovered then get the num of adjacent mines and then
   uncover the square. If it is a mine then return enum for MINE. Increment the num uncovered
   each time one is uncovered
   @param row The index of the row value of the square
   @param col The index of the col value of the square
   @return The Status enum value OK,MINE, or WIN 
   **/
   public Status uncoverSquare(int row, int col) {
      if (this.grid[row][col].isFlagged() || this.grid[row][col].isUncovered())
         return Status.OK;
      else if (this.grid[row][col].isMine())    
         return Status.MINE;
      else {
         if (this.getNeighbors(row, col) < 2) {
            int firstRow = 0;
            int lastRow = 0;
            int firstCol = 0;
            int lastCol = 0;
            
            if (row < 2) {
               firstRow = 0;
               lastRow = row + 2;
            } else if (row >= this.height - 3) {
               lastRow = this.height - 1;
               firstRow = row - 2;
            } else {
               firstRow = row - 2;
               lastRow = row + 2;
            }
            
            if (col < 2) {
               firstCol = 0;
               lastCol = col + 2;
            } else if (col > this.width - 3) {
               firstCol = col - 2;
               lastCol = this.width - 1;
            } else {
               firstCol = col - 2;
               lastCol = col + 2;
            }
               
            for (int i = firstRow; i <= lastRow; i++) {
               for (int j = firstCol; j <= lastCol; j++) {
                  if (!(this.grid[i][j].isMine())) {
                     if (!this.grid[i][j].isUncovered()) {
                        this.grid[i][j].uncover();
                        this.numSquaresUncovered++;
                     }
                  }
               }
            }
         } else {
            this.grid[row][col].uncover();
            this.numSquaresUncovered++;
         }
      }
      
      System.out.println((width * height) - numMines);
      if (this.numSquaresUncovered >= (width * height) - numMines) {
         return Status.WIN;
      } else {
         return Status.OK;
      }
   }
   
   /**
   Uncovers all mines in the grid
   **/
   public void exposeMines() {
      for (int i = 0; i < this.height; i++) {
         for (int j = 0; j < this.width; j++) {
            if(this.grid[i][j].isMine()) {
               this.grid[i][j].uncover();
            }
         }
      } 
   }
   
   /**
   Calls the flag square function of the Square
   @param row The index of the row value of the square
   @param col The index of the col value of the square
   **/
   public void flagSquare(int row, int col) {
      this.grid[row][col].flagSquare();
   }
   
   /**
   Overriden toString that prints the grid to the console.
   @return String formatted into a grid that is the minesweeper board
   **/
   @Override
   public String toString() {
      String gridString = "   ";
      for (int a = 0; a <= this.width - 1; a++) {
         if (a >= 9) {
            gridString += String.format("%d ", a);
         } else {
            gridString += String.format("%d  ", a);
         }
      }
      gridString += "\n";
      
      for (int i = 0; i < this.height; i++) {
         if (i > 9)
            gridString += String.format("%d ", i);
         else
            gridString += String.format("%d  ", i);
         for (int j = 0; j < this.width; j++) {
            if (this.grid[i][j].isUncovered()) {
               gridString += String.format("%s  ", this.grid[i][j]);  
            } else if (this.grid[i][j].isFlagged()) {
               gridString += "f  ";
            } else {
               gridString += "x  ";
            }
            
         }
         gridString += "\n";
      }
      return gridString;
   }
}
