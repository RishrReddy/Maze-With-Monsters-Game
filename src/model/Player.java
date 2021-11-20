package model;

import java.util.HashMap;

/**
 * Player class represents a single player who will be playing the dungeon game.
 * PLayer has attributes like playerName, playerLocation and treasure.
 */
public class Player implements GamePlayer {
  private final String playerName;
  private int playerLocation;
  private HashMap<Treasure, Integer> treasure;
  private int arrowCount;

  /**
   * Creates a new player object.
   *
   * @param playerName name of the player
   * @param caveId     current cave the player is visiting.
   */
  public Player(String playerName, int caveId) {
    if (playerName == null || playerName.equals("") || caveId < 1) {
      throw new IllegalArgumentException("Invalid player creation");
    }
    this.playerName = playerName;
    this.playerLocation = caveId;
    this.treasure = new HashMap<>();
    for (Treasure t : Treasure.values()) {
      treasure.put(t, 0);
    }
    this.arrowCount = 3;
  }

  @Override
  public void setTreasureCount(Treasure treasureType, HashMap<Treasure, Integer> newTreasureCount) {
    for (Treasure treasure : Treasure.values()) {
      if (treasure.equals(treasureType)) {
        this.treasure.put(treasure,
                this.treasure.get(treasure) + newTreasureCount.get(treasure));
      }
    }
  }

  @Override
  public int getPlayerLocation() {
    return this.playerLocation;
  }

  @Override
  public void setPlayerLocation(int caveId) {
    this.playerLocation = caveId;
  }

  @Override
  public HashMap<Treasure, Integer> getTreasureWithPlayer() {
    return this.treasure;
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append("Player Name: " + this.playerName);
    sb.append("\nLocation :  Cave Number : " + this.playerLocation);
    sb.append("\nTreasure:  Diamond: " + treasure.get(Treasure.DIAMONDS));
    sb.append(" Sapphire: " + treasure.get(Treasure.SAPPHIRE));
    sb.append(" Ruby: " + treasure.get(Treasure.RUBY));
    return sb.toString();
  }

  @Override
  public int getArrowCount() {
    return arrowCount;
  }

  @Override
  public void setArrowCount(boolean isIncrease) {
    this.arrowCount = isIncrease ? arrowCount + 1 : arrowCount - 1;
  }
}
