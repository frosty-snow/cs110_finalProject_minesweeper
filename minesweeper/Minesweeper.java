/**
Cole Frost - Final Project - Minesweeper.java

Runs the game, allowing for behavior of replaying, winning, losing, and
quitting.
**/

import java.util.Scanner;
import java.util.Arrays;
import java.util.List;

public class Minesweeper {
   private Grid grid;
   private boolean userQuit = false;
   private boolean userLost = false;
   private boolean invalidEntry = false;
   
   /**
   Creates a grid and calls runGame()
   **/
   public void startGame() {
      this.setupGame();
   }
   
   /**
   Prints out the grid using the Grid toString function
   **/
   private void displayGrid() {
      System.out.print(this.grid);
   }
   
   /**
   Loops through as long as WIN or MINE haven't been returned or the user hasn't quit
   Gets user input and then acts upon that - if not valid simply gives feedback and reloads
   the grid and asks for user input again.
   **/
   public void runGame() {
      // Create the Scanner object for reading input
      Scanner keyboard = new Scanner(System.in);
      
      Grid.Status gridStatus = Grid.Status.OK;
      
      while (gridStatus.equals(Grid.Status.OK) && !userLost && !userQuit) {
         this.displayGrid();
      
         System.out.println("What next?");
         System.out.println("Options: (U)ncover r c, (F)lag r c, (Q)uit");
         String input = keyboard.nextLine();
         String[] inputChars = input.split("\\s+");
         
         try {  
            int row, col; 
            switch (inputChars[0].toLowerCase()) {
               case "u":
                  row = Integer.parseInt(inputChars[1]);
                  col = Integer.parseInt(inputChars[2]);
                  gridStatus = this.grid.uncoverSquare(row, col);
                  break;
               case "f":
                  row = Integer.parseInt(inputChars[1]);
                  col = Integer.parseInt(inputChars[2]);
                  this.grid.flagSquare(row, col);
                  break;
               case "q":
                  this.userQuit = true;
                  break;
               default:
                  System.out.println("You must select either u, f, or q as the first character.");
                  break;
            }
         } catch (NumberFormatException e) {
            System.out.println("You must enter a command in the format char digit digit (ie. u 3 3).");
         } catch (ArrayIndexOutOfBoundsException err) {
            System.out.println("You must enter a command in the format char digit digit (ie. u 3 3).");
         } catch (NullPointerException error) {
            System.out.println("You must enter a command in the format char digit digit (ie. u 3 3).");
         }
      }
      
      if (userQuit) {
         this.quitGame();
      } else if (userLost || gridStatus.equals(Grid.Status.MINE)) {
         this.gameOver();
         this.playAgain();
      } else if (gridStatus.equals(Grid.Status.WIN)) {
         this.displayGrid();
         System.out.println("You won. Congrats!");
         this.playAgain();
      }
   }
   
   /**
   Exposes the mines and redisplays the grid.
   **/
   public void gameOver() {
      grid.exposeMines();
      this.displayGrid();
      System.out.println("Better luck next time!");
   }
   
   /**
   Simply says thanks to user
   **/
   public void quitGame() {
      System.out.println("Thanks for playing!");
   }
   
   /**
   Asks user if they wanna play again, validates answer, if y replays, if n ends game.
   **/
   public void playAgain() {
      // Create the Scanner object for reading input
      Scanner keyboard = new Scanner(System.in);
      
      String[] answers = new String[]{"y", "n"};
      List<String> list = Arrays.asList(answers);
      boolean invalid = true;
      
      while (invalid) {
      System.out.println("Would you like to play again? y/n");
         String replayInput = keyboard.nextLine();
         if (list.contains(replayInput) && replayInput.equals("y")) {
            invalid = false;
            this.startGame();
         } else if (list.contains(replayInput) && replayInput.equals("n")) {
            invalid = false;
            this.quitGame();
         } else {
            System.out.println("Invalid response.");
         }
      }
   }
   
   /**
   Gets difficulty and setups up grid with specific values based on that input then runs
   the game.
   **/
   public void setupGame() {
      // Create the Scanner object for reading input
      Scanner keyboard = new Scanner(System.in);
      
      boolean invalid = true;
      
      while (invalid) {
         System.out.println("Select a difficulty: \n");
         System.out.printf("1) Easy%n2) Medium%n3) Hard%n");
         String difficulty = keyboard.nextLine();

         switch (difficulty) {
            case "1":
               invalid = false;
               this.grid = new Grid(8,8,8);
               this.runGame();
               break;
            case "2":
               invalid = false;
               this.grid = new Grid(10, 12, 10);
               this.runGame();
               break;
            case "3":
               invalid = false;
               this.grid = new Grid(16, 20, 50);
               this.runGame();
               break;
            default:
               System.out.println("Please enter a valid choice");
               invalid = true;
               break;
         }
      }
   }
}