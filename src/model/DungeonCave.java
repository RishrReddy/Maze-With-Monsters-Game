package model;

import java.util.HashMap;

/**
 * DungeonCave represents the Cave in a game.
 * All the operations to be performed on the cave are declared in the
 * DungeonCave interface. Cave class implements the DungeonCave and implements all the
 * methods.
 */
public interface DungeonCave {

  /**
   * Provides the cave id of a cave.
   *
   * @return cave id of a cave.
   */
  int getCaveId();

  /**
   * Provides the information of caves in neighbouring.
   * It returns the direction along with caveId of the cave.
   *
   * @return Map of Direction and caveID of connected neighbouring caves.
   */
  HashMap<Direction, Integer> getDirection();

  /**
   * Check if a cave is tunnel.
   *
   * @return true if cave is a tunnel else false.
   */
  boolean isTunnel();

  /**
   * Update the cave to be a tunnel or not based on the value passed.
   *
   * @param b true if tunnel else false.
   */
  void setTunnel(boolean b);

  /**
   * Provides the column number of a cave.
   *
   * @return column number of a cave.
   */
  int getColumnNo();

  /**
   * Provides the row number of a cave.
   *
   * @return row number of a cave.
   */
  int getRowNo();

  /**
   * Updates the neighbouring caves of the current cave with the direction and caveid provided.
   *
   * @param direction direction of next cave
   * @param caveId    cave id of the next cave
   */
  void setDirection(Direction direction, Integer caveId);

  /**
   * Updates the treasure of the current cave with the treasure type and quantity provided.
   *
   * @param treasure treasure type (ruby, diamond or sapphire)
   * @param quantity amount of treasure
   */
  void setCaveTreasure(Treasure treasure, int quantity);

  /**
   * Get the treasure associated with the cave.
   *
   * @return treasure associated with the cave in form of a Map with treasure type and quantity.
   */
  HashMap<Treasure, Integer> getTreasure();

  /**
   * Check if a cave has arrows.
   *
   * @return true if cave has an arrow else false.
   */
  boolean getIsArrowAvailable();

  /**
   * Adds the given monster to the cave.
   *
   * @param monster Monster to be added to the cave.
   */
  void setMonsterInCave(Monster monster);

  /**
   * Adds or removes arrow in the cave.
   *
   * @param isArrowAvailable true adds arrow to the cave,
   *                         false removes arrow from the cave
   */
  void setArrowInCave(boolean isArrowAvailable);

  /**
   * Get the monster residing in the cave.
   *
   * @return monster in the cave.
   */
  Monster getMonster();

  /**
   * Updates the health of the monster when it is
   * hit by the arrow.
   *
   * @return the monster after updating the health.
   */
  Monster updateMonsterHealth();
}
