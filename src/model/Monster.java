package model;

/**
 * Monster interface represents the monsters residing in the dungeon.
 * They can be detected by their smell.
 * A monster object stores the unique monster id and the current health of the monster.
 */
public interface Monster {
  /**
   * Gets the current health status of the monster.
   *
   * @return health of the monster
   */
  int getHealth();

  /**
   * Set the health of the monster when it is hit by the arrow.
   */
  void setMonsterHealth();

  /**
   * Gets the unique monster id assigned to the monster.
   *
   * @return monsterID.
   */
  int getOtyughId();
}
