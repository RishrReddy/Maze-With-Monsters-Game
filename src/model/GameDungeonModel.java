package model;

import java.util.HashMap;

/**
 * GameDungeonModel represents the whole dungeon game.
 * All the operation visible to the user of dungeon game for playing
 * are declared in this interface. The methods are further implemented in Game class.
 * Only GameDungeonModel class communicates with the driver class in the whole model.
 */
public interface GameDungeonModel {

  /**
   * Provides all the neighbouring caves which a player can
   * visit from his current location along with the direction.
   *
   * @return String of direction and cave id where player can next move.
   */
  String availablePathsWithPlayer();

  /**
   * Moves player to the next gave based on the direction provided.
   *
   * @param move direction of players next move.
   * @return if it was a successful move or not.
   */
  boolean moveToPosition(String move);

  /**
   * Returns the total treasure with the player at the time of
   * invocation.
   *
   * @return HashMap with Treasure type and Quantity with the player.
   */
  HashMap<Treasure, Integer> printPlayerStatus();

  /**
   * Returns the total treasure present in the cave which the player
   * is currently visiting.
   *
   * @return HashMap with Treasure type and Quantity in the cave.
   */
  HashMap<Treasure, Integer> availableTreasureAtCurrentNode();

  /**
   * Method to check if there is any treasure present in the current cave which player is visiting.
   *
   * @return true if treasure present else false.
   */
  boolean isTreasureAvailableAtCurrentNode();

  /**
   * Provides the caveId of the cave the player is current present in.
   *
   * @return CaveId of player's current location.
   */
  String[] printPlayerLocation();

  /**
   * Provides the start cave and end cave that were randomly generated for the dungeon name.
   * It's an array of start cave id and end cave id.
   *
   * @return Array od cave ID , first element is start cave id, second element is end cave id.
   */
  int[] getStartAndEndCave();

  /**
   * Checks if the string provided by user for the next move is valid or not, i.e.,
   * checks if there is path in the direction provided.
   *
   * @param move direction of next move.
   * @return true for valid move else false.
   */
  boolean checkValidMove(String move);

  /**
   * Checks if the game is still running , i.e., the player hasn't reached the end cave.
   *
   * @return true if player is still playing else false.
   */
  boolean checkGameRunning();

  /**
   * Dumps the dungeon to the user's console. :P represents player and :G represents goal.
   * After every move the dungeon can be visualized using this method.
   *
   * @return visual depiction of dungeon grid with current player location.
   */
  String printUpdatedDungeon();

  /**
   * Method to pick up the treasure in the cave the player visits, only if the player wants to pick.
   *
   * @param treasureType type of treasure to pick up.
   */
  void pickUpTreasure(Treasure treasureType);

  /**
   * To check if arrow is present in the current cave.
   *
   * @return true when arrow is present else false.
   */
  boolean isArrowAvailableAtCurrentNode();

  /**
   * Picks up arrow present in the cave and assign to the player.
   */
  void pickUpArrow();

  /**
   * To check if player has at least one arrow.
   *
   * @return true when arrow is present else false.
   */
  boolean isPlayerHavingArrow();

  /**
   * To shoot arrow in the specified distance and direction.
   *
   * @param distance  no of caves for the arrow to move.
   * @param direction direction in which arrow should move.
   * @return if a monster was shot successfully.
   */
  boolean shootArrow(int distance, String direction);

  /**
   * Get the smell at the current position.
   *
   * @return string value of the smell type.
   */
  String getSmell();

  /**
   * Prints the details of all the caves in dungeon.
   *
   * @return Strign of all the cave details.
   */
  String printAllCaves();

  /**
   * Get the count of arrows with the player.
   *
   * @return number of arrows present with the player.
   */
  int printArrowsWithPlayer();
}
