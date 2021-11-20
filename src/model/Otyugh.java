package model;

/**
 * Otyughs are extremely smelly creatures
 * that lead solitary lives in the deep,
 * dark places of the world like our dungeon.
 */
public class Otyugh implements Monster {
  private int otyughId;
  private int health;

  /**
   * Constructs a new otyugh.
   *
   * @param id unique id assigned to an otyugh.
   */
  public Otyugh(int id) {
    this.otyughId = id;
    this.health = 100;
  }

  /**
   * Copy constructor to create new otyugh.
   *
   * @param monster an otyugh whose values are to be
   *                copied to new otyugh.
   */
  public Otyugh(Monster monster) {
    this.otyughId = monster.getOtyughId();
    this.health = monster.getHealth();
  }

  @Override
  public int getOtyughId() {
    return this.otyughId;
  }

  @Override
  public int getHealth() {
    return this.health;
  }

  @Override
  public void setMonsterHealth() {
    this.health = this.health - 50;
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append("\n OtyughId: " + otyughId + " Health: " + health);
    return sb.toString();
  }
}
