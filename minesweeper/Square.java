/**
Cole Frost - Final Project - Square.java

An abstract class that forms the base for number and mine squares.
2 constructors and a series of getters and setters with a toString.
Contains 2 abstract classes.
*/
public abstract class Square {
   private String element;
   private boolean flagged;
   private boolean uncovered;
   
   /**
   Default constructor sets flagged and uncovered to false
   **/
   public Square() {
      this.flagged = false;
      this.uncovered = false;
   }
   
   /**
   Alternate constructor
   @param element The symbol of the object
   @param flagged true or false if the object is flagged
   @param uncovered true or false if the object is covered or not
   **/
   public Square(String element, boolean flagged, boolean uncovered) {
      this.element = element;
      this.flagged = flagged;
      this.uncovered = uncovered;
   }
   
   /**
   Return the flagged value
   @return The boolean value of flagged
   **/
   public boolean isFlagged() {
      return flagged;
   }
   
   /**
   Return the value of uncovered
   @return The boolean value of uncovered
   **/
   public boolean isUncovered() {
      return uncovered;
   }
   
   /**
   Sets the flagged value - simply flips the boolean from true to false
   or false to true
   **/
   public void flagSquare() {
      this.flagged = !this.flagged;
   }
   
   /**
   Sets the uncovered value - simply flips the boolean from true to false
   or false to true
   **/
   public void setUncovered() {
      this.uncovered = !this.uncovered;
   }
   
   /**
   Setter for the element var
   @param element String value
   **/
   public void setElement(String element) {
      this.element = element;
   }
   
   /**
   Overriden toString that just returns the string value of the element var
   @return String value of the element var
   **/
   @Override
   public String toString() {
      return this.element;
   }
   
   /**
   Abstact method
   **/
   public abstract boolean uncover();
   
   /**
   Abstract method
   **/
   public abstract boolean isMine();
}