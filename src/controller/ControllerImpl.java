package controller;

import java.io.IOException;
import java.util.Scanner;
import model.GameDungeonModel;
import model.Treasure;

/**
 * ControllerImpl implements the DungeonController interface for the text based
 * Dungeon Game.
 */
public class ControllerImpl implements DungeonController {
  private final GameDungeonModel game;
  private final Readable rd;
  private final Appendable ap;

  /**
   * Constructor to create new controller object.
   *
   * @param game GameDungeonModel model
   * @param rd   the source to read from
   * @param ap   the target to print to
   */
  public ControllerImpl(GameDungeonModel game, Readable rd, Appendable ap) {
    this.game = game;
    this.rd = rd;
    this.ap = ap;
  }

  @Override
  public void playGame() throws IOException {
    try {
      ap.append("********************** Starting the Game!! ****************************\n");
      ap.append(game.printUpdatedDungeon()).append("\n");
      printCurrentStatus();
      Scanner scan = new Scanner(rd);
      boolean quit = false;
      while (!game.checkGameRunning() && !quit) {

        ap.append("\n" + game.getSmell());
        ap.append("\nIn the current position the available paths with the player are : \n")
                .append(game.availablePathsWithPlayer());
        ap.append("In the current position the available treasure is : \n")
                .append(game.availableTreasureAtCurrentNode().toString());
        ap.append("\nPlease Enter 'M' to move to new cave,"
                + "'P' to pick up treasure/arrows, 'S' to shoot an arrow\n");
        String userInput = scan.next();
        if (userInput.equalsIgnoreCase("M")) {
          if(!moveToNewCave(scan)){
            quit=true;
          }
        } else if (userInput.equalsIgnoreCase("P")) {
          pickUpTreasureOrArrow(scan);
        } else if (userInput.equalsIgnoreCase("S")) {
          shootArrow(scan);
        } else if (userInput.equalsIgnoreCase("Q")) {
          quit = true;
        } else {
          ap.append("Invalid input!! Please provide proper input!!\n");
        }
      }
      if(!quit){
        ap.append("\n *********************Wining PLayer**************************** \n");
        printCurrentStatus();
        ap.append("\nPlayer Successfully Reached the End of the Dungeon" + "\n");
        ap.append("\nThank you for playing" + "\n");
      }
    } catch (NumberFormatException numEx) {
      ap.append("Found NumberFormatException : " + numEx.getMessage());
    } catch (IllegalArgumentException ilEx) {
      ap.append("Found IllegalArgumentException : " + ilEx.getMessage());
    } catch (IllegalStateException isEx) {
      ap.append("Found IllegalStateException : " + isEx.getMessage());
    } catch (IOException ioEx) {
      ap.append("Found IOException : " + ioEx.getMessage());
    }
  }

  private void printCurrentStatus() throws IOException {
    ap.append("\u001B[34m" + "\n***************** CURRENT PLAYER STATUS *************************"
            + "\n");
    ap.append("Player is currently at: ").append(game.printPlayerLocation()[0])
            .append(" ").append(game.printPlayerLocation()[1]).append("\n");
    ap.append("Player is with treasure: ").append(game.printPlayerStatus().toString()).append("\n");
    ap.append("Player is with arrows: ")
            .append(String.valueOf(game.printArrowsWithPlayer())).append("\n");
    ap.append("****************************************************************\n" + "\u001B[0m");
  }

  private boolean moveToNewCave(Scanner scan) throws IOException {
    ap.append("\nEnter the key for your next move among available paths: ")
            .append(game.availablePathsWithPlayer()).append("\n")
            .append(" W: North \n S: South \n A: West \n D: East \n");
    String move = scan.next();
    boolean isValidMove = false;
    while (!isValidMove) {
      if (!game.checkValidMove(move)) {
        ap.append("Invalid Move , Please Enter Move from Available Path" + "\n");
        move = scan.next();
      } else {
        isValidMove = true;
      }
    }
    if (game.moveToPosition(move)) {
      printCurrentStatus();
      return true;
    } else {
      ap.append("\u001b[41;1m" + "Chomp, chomp, chomp, you are eaten by an Otyugh!! "
              + "Better luck next time......" + "\u001B[0m");
      return false;
    }
  }

  private void pickUpTreasureOrArrow(Scanner scan) throws IOException {
    if (!game.isTreasureAvailableAtCurrentNode() && !game.isArrowAvailableAtCurrentNode()) {
      ap.append("No Treasure/Arrow Available at Current Location" + "\n");
    } else {
      if (game.isTreasureAvailableAtCurrentNode()) {
        ap.append("\u001b[35m" + "************ WOOHOO!! You found yourself some "
                + "treasure ***************" + "\n" + "\u001B[0m");
        ap.append("Type yes if you want to pick up the treasure : " + "\n");
        String pickup = scan.next();
        if (pickup.equalsIgnoreCase("yes")) {
          for (Treasure t : Treasure.values()) {
            ap.append("Type yes if you want to pick up the " + t + " :" + "\n");
            if (scan.next().equalsIgnoreCase("yes")) {
              game.pickUpTreasure(t);
              ap.append("Successfully picked available " + t + "\n");
            }
          }
        }
      }
      if (game.isArrowAvailableAtCurrentNode()) {
        ap.append("\u001b[35m" + "************ Ohh Yeah!! You found yourself an arrow"
                + " *****************" + "\n" + "\u001B[0m");
        ap.append("Type yes if you want to pick up the arrow : " + "\n");
        String pickup = scan.next();
        if (pickup.equalsIgnoreCase("yes")) {
          game.pickUpArrow();
        }
      }
      printCurrentStatus();
    }
  }

  private void shootArrow(Scanner scan) throws IOException {
    boolean shootFlag = true;
    while (shootFlag) {
      if (game.isPlayerHavingArrow()) {
        int distance = 0;
        while (true) {
          ap.append("Enter a number : At what distance do you want to shoot? 1-5 \n");
          try {
            distance = Integer.parseInt(scan.next());
          } catch (NumberFormatException numEx) {
            ap.append("Invalid Input!! \n");
          }
          if (distance > 0) {
            break;
          }
        }
        String direction;
        while (true) {
          ap.append("Enter the direction you want to shoot in --->  W: North,"
                  + "  S: South, A: West, D: East");
          direction = scan.next();
          try {
            if (game.shootArrow(distance, direction)) {
              ap.append("You hear a great howl in the distance\n");
            }
          } catch (IllegalArgumentException ilEx) {
            ap.append(ilEx.getMessage() + " Please enter valid direction!! \n");
            direction = "";
          }
          if (!direction.equals("")) {
            break;
          }
        }
        ap.append("Type yes to shoot again : " + "\n");
        String shoot = scan.next();
        shootFlag = shoot.equalsIgnoreCase("yes");
      } else {
        ap.append("Player doesn't have arrows to shoot");
        shootFlag = false;
      }
    }
    printCurrentStatus();
  }
}
