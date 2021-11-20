package model;

import java.util.HashMap;

/**
 * Cave class represents an individual Cave in a game.
 * It has a rowId, columnId, unique caveId, treasure and details of neighbouring connected caves.
 * All the operations to be performed on the cave are defined in the
 * DungeonCave interface. Cave class implements the DungeonCave and implements all the
 * methods.
 */
public class Cave implements DungeonCave {
  private final int caveId;
  private final int rowId;
  private final int columnId;
  private final HashMap<Treasure, Integer> caveTreasure;
  private final HashMap<Direction, Integer> direction;
  private boolean isTunnel;
  private boolean isArrowAvailable;
  private Monster monster;

  /**
   * Copy constructor to create copy of cave object to return so that the original
   * cave object stays unchanged.
   *
   * @param copyCave cave to ve copied.
   */
  public Cave(DungeonCave copyCave) {
    this.caveId = copyCave.getCaveId();
    this.columnId = copyCave.getColumnNo();
    this.rowId = copyCave.getRowNo();
    this.direction = copyCave.getDirection();
    this.caveTreasure = copyCave.getTreasure();
    this.isTunnel = copyCave.isTunnel();
    this.isArrowAvailable = copyCave.getIsArrowAvailable();
    this.monster = copyCave.getMonster();
  }

  /**
   * Creates a new cave object with appropriate attributes.
   *
   * @param caveId      unique caveId of the cave
   * @param noOfRows    rowId of the cave
   * @param noOfColumns columnId of the cave.
   */
  public Cave(int caveId, int noOfRows, int noOfColumns) {
    if (caveId < 0 || noOfColumns < 0 || noOfRows < 0) {
      throw new IllegalArgumentException("Invalid arguments");
    }
    this.caveId = caveId;
    this.columnId = noOfColumns;
    this.rowId = noOfRows;
    caveTreasure = new HashMap<>();
    for (Treasure t : Treasure.values()) {
      caveTreasure.put(t, 0);
    }
    this.direction = new HashMap<>();
    for (Direction d : Direction.values()) {
      direction.put(d, 0);
    }
    this.isTunnel = false;
    this.isArrowAvailable = false;
    this.monster = null;
  }

  @Override
  public int getCaveId() {
    return this.caveId;
  }

  @Override
  public HashMap<Direction, Integer> getDirection() {
    return this.direction;
  }

  @Override
  public boolean isTunnel() {
    return isTunnel;
  }

  @Override
  public void setTunnel(boolean tunnelStatus) {
    this.isTunnel = tunnelStatus;
  }

  @Override
  public int getColumnNo() {
    return this.columnId;
  }

  @Override
  public int getRowNo() {
    return this.rowId;
  }

  @Override
  public void setCaveTreasure(Treasure treasure, int quantity) {
    this.caveTreasure.put(treasure, quantity);
  }

  @Override
  public HashMap<Treasure, Integer> getTreasure() {
    return new HashMap<>(this.caveTreasure);
  }

  @Override
  public void setDirection(Direction direction, Integer caveId) {
    this.direction.put(direction, caveId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("\nCaveId: " + caveId + " RowNo: " + rowId + " ColumnNo: " + columnId + " isTunnel: "
            + isTunnel + " Treasure: Diamond: " + caveTreasure.get(Treasure.DIAMONDS)
            + " Ruby: " + caveTreasure.get(Treasure.RUBY)
            + " Sapphire: " + caveTreasure.get(Treasure.SAPPHIRE)
            + " Direction: East: "
            + direction.get(Direction.EAST) + " West: " + direction.get(Direction.WEST)
            + " NORTH: " + direction.get(Direction.NORTH)
            + " SOUTH: " + direction.get(Direction.SOUTH)
            + " Arrow available ? " + isArrowAvailable);
    if (this.getMonster() == null) {
      sb.append(" Monster Present ?" + "false");
    } else {
      sb.append("\\u001b[31m Monster Present ?" + monster.toString());
    }
    return sb.toString();
  }

  /**
   * Check if a cave has arrows.
   *
   * @return true if cave has an arrow else false.
   */
  @Override
  public boolean getIsArrowAvailable() {
    return isArrowAvailable;
  }

  @Override
  public void setMonsterInCave(Monster monster) {
    if (!this.isTunnel()) {
      this.monster = monster;
    }
  }

  @Override
  public void setArrowInCave(boolean isArrowAvailable) {
    this.isArrowAvailable = isArrowAvailable;
  }

  @Override
  public Monster getMonster() {
    if (this.monster != null) {
      return new Otyugh(this.monster);
    } else {
      return null;
    }
  }

  @Override
  public Monster updateMonsterHealth() {
    if (this.getMonster() != null && this.getMonster().getHealth() >= 50) {
      this.monster.setMonsterHealth();
    }
    return this.monster;
  }
}
