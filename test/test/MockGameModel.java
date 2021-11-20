package test;

import java.io.IOException;
import java.util.HashMap;

import model.GameDungeonModel;
import model.Treasure;

public class MockGameModel implements GameDungeonModel {
  private final Appendable appendable;

  public MockGameModel(Appendable appendable) {
    this.appendable = appendable;
  }

  /**
   * Provides all the neighbouring caves which a player can
   * visit from his current location along with the direction.
   *
   * @return String of direction and cave id where player can next move.
   */
  @Override
  public String availablePathsWithPlayer() {
    try {
      appendable.append("GameDungeonModel.availablePathsWithPlayer() method called \n");
    } catch (IOException e) {
      throw new AssertionError(e);
    }
    return "";
  }

  /**
   * Moves player to the next gave based on the direction provided.
   *
   * @param move direction of players next move.
   * @return
   */
  @Override
  public boolean moveToPosition(String move) {
    try {
      appendable.append("GameDungeonModel.moveToPosition() method called \n");
    } catch (IOException e) {
      throw new AssertionError(e);
    }
    if(move.equalsIgnoreCase("w")){
      return true;
    }else{
      return false;
    }
  }

  /**
   * Returns the total treasure with the player at the time of
   * invocation.
   *
   * @return HashMap with Treasure type and Quantity with the player.
   */
  @Override
  public HashMap<Treasure, Integer> printPlayerStatus() {
    try {
      appendable.append("GameDungeonModel.printPlayerStatus() method called \n");
    } catch (IOException e) {
      throw new AssertionError(e);
    }
    HashMap<Treasure, Integer> playerStatus = new HashMap();
    playerStatus.put(Treasure.DIAMONDS, 100);
    playerStatus.put(Treasure.RUBY, 300);
    playerStatus.put(Treasure.SAPPHIRE, 200);
    return playerStatus;
  }

  /**
   * Returns the total treasure present in the cave which the player
   * is currently visiting.
   *
   * @return HashMap with Treasure type and Quantity in the cave.
   */
  @Override
  public HashMap<Treasure, Integer> availableTreasureAtCurrentNode() {
    try {
      appendable.append("GameDungeonModel.availableTreasureAtCurrentNode() method called \n");
    } catch (IOException e) {
      throw new AssertionError(e);
    }
    HashMap<Treasure, Integer> treasure = new HashMap();
    treasure.put(Treasure.DIAMONDS, 100);
    treasure.put(Treasure.RUBY, 300);
    treasure.put(Treasure.SAPPHIRE, 200);
    return treasure;
  }

  /**
   * Method to check if there is any treasure present in the current cave which player is visiting.
   *
   * @return true if treasure present else false.
   */
  @Override
  public boolean isTreasureAvailableAtCurrentNode() {
    try {
      appendable.append("GameDungeonModel.isTreasureAvailableAtCurrentNode() method called \n");
    } catch (IOException e) {
      throw new AssertionError(e);
    }
    return true;
  }

  /**
   * Provides the caveId of the cave the player is current present in.
   *
   * @return CaveId of player's current location.
   */
  @Override
  public String[] printPlayerLocation() {
    try {
      appendable.append("GameDungeonModel.printPlayerLocation() method called \n");
    } catch (IOException e) {
      throw new AssertionError(e);
    }
    String[] playerLoc = new String[2];
    playerLoc[0] = "1";
    playerLoc[1] = "It is a cave";
    return playerLoc;
  }

  /**
   * Provides the start cave and end cave that were randomly generated for the dungeon name.
   * It's an array of start cave id and end cave id.
   *
   * @return Array od cave ID , first element is start cave id, second element is end cave id.
   */
  @Override
  public int[] getStartAndEndCave() {
    try {
      appendable.append("GameDungeonModel.getStartAndEndCave() method called \n");
    } catch (IOException e) {
      throw new AssertionError(e);
    }
    int[] startEnd = new int[2];
    startEnd[0] = 1;
    startEnd[1] = 10;
    return startEnd;
  }

  /**
   * Checks if the string provided by user for the next move is valid or not, i.e.,
   * checks if there is path in the direction provided.
   *
   * @param move direction of next move.
   * @return true for valid move else false.
   */
  @Override
  public boolean checkValidMove(String move) {
    try {
      appendable.append("GameDungeonModel.checkValidMove() method called \n");
    } catch (IOException e) {
      throw new AssertionError(e);
    }
    return true;
  }

  /**
   * Checks if the game is still running , i.e., the player hasn't reached the end cave.
   *
   * @return true if player is still playing else false.
   */
  @Override
  public boolean checkGameRunning() {
    try {
      appendable.append("GameDungeonModel.checkGameRunning() method called \n");
    } catch (IOException e) {
      throw new AssertionError(e);
    }
    return false;
  }

  /**
   * Dumps the dungeon to the user's console. :P represents player and :G represents goal.
   * After every move the dungeon can be visualized using this method.
   *
   * @return visual depiction of dungeon grid with current player location.
   */
  @Override
  public String printUpdatedDungeon() {
    try {
      appendable.append("GameDungeonModel.printUpdatedDungeon() method called \n");
    } catch (IOException e) {
      throw new AssertionError(e);
    }
    return "";
  }

  /**
   * Method to pick up the treasure in the cave the player visits, only if the player wants to pick.
   *
   * @param treasureType type of treasure to pick up.
   */
  @Override
  public void pickUpTreasure(Treasure treasureType) {
    try {
      appendable.append("GameDungeonModel.pickUpTreasure() method called \n");
    } catch (IOException e) {
      throw new AssertionError(e);
    }
  }

  /**
   * To check if arrow is present in the current cave.
   *
   * @return true when arrow is present else false.
   */
  @Override
  public boolean isArrowAvailableAtCurrentNode() {
    try {
      appendable.append("GameDungeonModel.isArrowAvailableAtCurrentNode() method called \n");
    } catch (IOException e) {
      throw new AssertionError(e);
    }
    return true;
  }

  /**
   * Picks up arrow present in the cave and assign to the player.
   */
  @Override
  public void pickUpArrow() {
    try {
      appendable.append("GameDungeonModel.pickUpArrow() method called \n");
    } catch (IOException e) {
      throw new AssertionError(e);
    }
  }

  /**
   * To check if player has at least one arrow.
   *
   * @return true when arrow is present else false.
   */
  @Override
  public boolean isPlayerHavingArrow() {
    try {
      appendable.append("GameDungeonModel.isPlayerHavingArrow() method called \n");
    } catch (IOException e) {
      throw new AssertionError(e);
    }
    return true;
  }

  /**
   * To shoot arrow in the specified distance and direction.
   *
   * @param distance  no of caves for the arrow to move.
   * @param direction direction in which arrow should move.
   * @return
   */
  @Override
  public boolean shootArrow(int distance, String direction) {
    try {
      appendable.append("GameDungeonModel.shootArrow() method called \n");
    } catch (IOException e) {
      throw new AssertionError(e);
    }
    return true;
  }

  /**
   * Get the smell at the current position.
   *
   * @return string value of the smell type.
   */
  @Override
  public String getSmell() {
    try {
      appendable.append("GameDungeonModel.getSmell() method called \n");
    } catch (IOException e) {
      throw new AssertionError(e);
    }
    return "";
  }

  @Override
  public String printAllCaves() {
    try {
      appendable.append("GameDungeonModel.printAllCaves() method called \n");
    } catch (IOException e) {
      throw new AssertionError(e);
    }
    return "";
  }

  /**
   * Get the count of arrows with the player.
   *
   * @return number of arrows present with the player.
   */
  @Override
  public int printArrowsWithPlayer() {
    try {
      appendable.append("GameDungeonModel.printArrowsWithPlayer() method called \n");
    } catch (IOException e) {
      throw new AssertionError(e);
    }
    return 3;
  }
}
