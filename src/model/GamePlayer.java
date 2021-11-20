package model;

import java.util.HashMap;

/**
 * GamePlayer interface represent the player playing the dungeon game.
 * A player object stores a player's location and treasure and has methods
 * that can be performed on a Player object.
 */
public interface GamePlayer {
  /**
   * Updates the treasure of the player with the treasure type and quantity provided.
   *
   * @param treasureType type of the treasure
   * @param treasure hashMap of treasure type (ruby, diamond or sapphire) and the quantity of the
   */
  void setTreasureCount(Treasure treasureType, HashMap<Treasure, Integer> treasure);

  /**
   * Provides the cave id of the cave player is currently present in.
   *
   * @return cave id of the cave player is visiting.
   */
  int getPlayerLocation();

  /**
   * Updates the location of the player with the cave id of the
   * cave the player os currently present in.
   *
   * @param nextCaveId cave id of the cave player is in.
   */
  void setPlayerLocation(int nextCaveId);

  /**
   * Get the treasure associated with the player.
   *
   * @return treasure associated with the cave in form of a Map with treasure type and quantity.
   */
  HashMap<Treasure, Integer> getTreasureWithPlayer();

  /**
   * Get the count of the treasure associated with the player.
   *
   * @return total number of arrows with the player.
   */
  int getArrowCount();

  /**
   * Updates the arrow count with player based on the arrow picked or used by him.
   *
   * @param isIncrease true increases the count of arrows and false decreases the count.
   */
  void setArrowCount(boolean isIncrease);
}
