package model;

import java.util.List;

/**
 * DungeonInterface represents the Dungeon in a game.
 * All the operations to be performed or the dungeon are declared in the
 * DungeonInterface. Dungeon class implements the DungeonInterface and implements all the
 * methods.
 */
public interface DungeonInterface {

  /**
   * Provides the start cave of the dungeon in the current game.
   *
   * @return start cave of the dungeon.
   */
  DungeonCave getStartCave();

  /**
   * Returns list of all caves present in the dungeon.
   * It's a list of list, each internal list representing  a row of caves.
   *
   * @return list of all caves in the dungeon.
   */
  List<List<DungeonCave>> getAllCaves();

  /**
   * Provides the end cave of the dungeon in the current game.
   *
   * @return end cave of the dungeon.
   */
  DungeonCave getEndCave();

  /**
   * Dumps the dungeon to the user's console. :P represents player and :G represents goal.
   * After every move the dungeon can be visualized using this method.
   *
   * @return visual depiction of dungeon grid with current player location.
   */
  String printDungeon(DungeonCave currentCave);

  /**
   * Provides the copy of the cave with provided cave id.
   *
   * @param nextCaveId cave id of the cave to be retrieved.
   * @return cave object corresponding to the cave id sent.
   */
  DungeonCave getCave(int nextCaveId);

  /**
   * Method to clear the treasure in cave after the player has visited it and picked up
   * all the treasure present in that cave.
   *
   * @param treasureType type of the treasure
   * @param currentCave  cave the player is currently visiting and collecting the treasure.
   */
  void emptyTreasureInCave(Treasure treasureType, DungeonCave currentCave);

  /**
   * Removes the monster from the cave when it is hit twice by an arrow.
   *
   * @param arrowIsInCave current caveId where the second arrow landed.
   */
  void killMonster(int arrowIsInCave);

  /**
   * Remove the arrow from the cave when player has picked it up.
   *
   * @param caveId cave id of the cave to be updated.
   */
  void removeArrowInCave(int caveId);


  /**
   * Udpate the health of monster when it is hit by an arrow.
   *
   * @param arrowIsInCave current caveId where the arrow landed.
   */
  void updateMonsterHealth(int arrowIsInCave);
}
