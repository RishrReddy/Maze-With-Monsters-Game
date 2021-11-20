package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Game class represents the whole dungeon game.
 * All the operation visible to the user of dungeon game for playing
 * are defined in this class. Game class implements GameDungeonInterface.
 */
public class Game implements GameDungeonModel {
  private static DungeonCave currentCave;
  private final GamePlayer player;
  private final DungeonInterface dungeon;
  private RandomNumbersGenerator random;

  /**
   * Constructor to create a new Game with the configurations provided by the user.
   *
   * @param noOfRows           no of rows in the dungeon
   * @param noOfColumns        no of columns in the dungeon
   * @param interconnectivity  degree of interconnectivity of the dungeon
   * @param isWrapping         weather the dungeon is wrapping or non-wrapping
   * @param treasurePercentage percentage of treasure to be assigned in the dungeon
   * @param random             random variable to generate random numbers.
   */
  public Game(int noOfRows, int noOfColumns, int interconnectivity,
              boolean isWrapping, int treasurePercentage, int noOfMonsters,
              RandomNumbersGenerator random) {
    if (noOfColumns * noOfRows < 10) {
      throw new IllegalArgumentException("Please change the configuration of grid!! "
              + "For better gaming experience the product of columns and rows is expected to be "
              + "greater than 10 ");
    }
    if (random == null) {
      throw new IllegalArgumentException("Illegal random object");
    }
    if (treasurePercentage < 0 || treasurePercentage > 100) {
      throw new IllegalArgumentException("Please change the configuration of treasure percentage."
              + "The value should be in 0-100 range");
    }
    if (interconnectivity < 0) {
      throw new IllegalArgumentException("Please change the configuration of interconnectivity."
              + "The value should be in 0-100 range");
    }
    if (noOfMonsters <= 0) {
      throw new IllegalArgumentException("Please change the configuration of number of monsters."
              + "It has to be positive number and less than number of caves");
    } else if (noOfMonsters > (noOfRows * noOfColumns - 1)) {
      throw new IllegalArgumentException("Please change the configuration of number of monsters."
              + "It has to be positive number and less than number of caves");
    }

    this.dungeon = new Dungeon(noOfRows, noOfColumns, interconnectivity,
            isWrapping, treasurePercentage, noOfMonsters, random);
    //dungeon.addCavesToDungeon();
    currentCave = dungeon.getStartCave();
    this.player = new Player("Player", currentCave.getCaveId());
    this.random = random;
  }

  @Override
  public String availablePathsWithPlayer() {
    HashMap<Direction, Integer> direction = currentCave.getDirection();
    StringBuilder sb = new StringBuilder();
    for (Direction d : Direction.values()) {
      if (direction.get(d) > 0) {
        sb.append(d).append("-> Cave: ").append(direction.get(d)).append("\n");
      }
    }
    return sb.toString();
  }

  @Override
  public boolean moveToPosition(String move) {
    int nextCaveId = currentCave.getDirection().get(Direction.moveDirection(move));
    if (dungeon.getCave(nextCaveId).getMonster() != null
            && dungeon.getCave(nextCaveId).getMonster().getHealth() == 100) {
      return false;
    } else if (dungeon.getCave(nextCaveId).getMonster() != null
            && dungeon.getCave(nextCaveId).getMonster().getHealth() == 50) {
      return (random.getRandomNumber(0, 1) != 0);
    } else if (dungeon.getCave(nextCaveId).getMonster() == null) {
      currentCave = dungeon.getCave(nextCaveId);
      player.setPlayerLocation(nextCaveId);
      return true;
    }
    return false;
  }

  @Override
  public HashMap<Treasure, Integer> printPlayerStatus() {
    return player.getTreasureWithPlayer();
  }

  @Override
  public HashMap<Treasure, Integer> availableTreasureAtCurrentNode() {
    return currentCave.getTreasure();
  }

  @Override
  public boolean isTreasureAvailableAtCurrentNode() {
    boolean isTreasureAvailable = false;
    for (Treasure t : Treasure.values()) {
      if (currentCave.getTreasure().get(t) != 0) {
        isTreasureAvailable = true;
      }
    }
    return isTreasureAvailable;
  }

  @Override
  public String[] printPlayerLocation() {
    String[] playerLoc = new String[2];
    playerLoc[0] = String.valueOf(player.getPlayerLocation());
    if (dungeon.getCave(player.getPlayerLocation()).isTunnel()) {
      playerLoc[1] = "It is a tunnel";
    } else {
      playerLoc[1] = "It is a cave";
    }
    return playerLoc;
  }

  @Override
  public int[] getStartAndEndCave() {
    int[] caves = {dungeon.getStartCave().getCaveId(), dungeon.getEndCave().getCaveId()};
    return caves;
  }

  @Override
  public boolean checkValidMove(String move) {
    if (currentCave.getDirection().get(Direction.moveDirection(move)) != null) {
      return currentCave.getDirection().get(Direction.moveDirection(move)) > 0;
    } else {
      return false;
    }
  }

  @Override
  public boolean checkGameRunning() {
    return currentCave.getCaveId() == dungeon.getEndCave().getCaveId();
  }

  @Override
  public String printUpdatedDungeon() {
    return dungeon.printDungeon(currentCave);
  }

  @Override
  public void pickUpTreasure(Treasure treasureType) {
    if (treasureType.equals(Treasure.DIAMONDS)) {
      player.setTreasureCount(Treasure.DIAMONDS, currentCave.getTreasure());
      dungeon.emptyTreasureInCave(Treasure.DIAMONDS, currentCave);
    } else if (treasureType.equals(Treasure.RUBY)) {
      player.setTreasureCount(Treasure.RUBY, currentCave.getTreasure());
      dungeon.emptyTreasureInCave(Treasure.RUBY, currentCave);
    } else if (treasureType.equals(Treasure.SAPPHIRE)) {
      player.setTreasureCount(Treasure.SAPPHIRE, currentCave.getTreasure());
      dungeon.emptyTreasureInCave(Treasure.SAPPHIRE, currentCave);
    }
    currentCave = dungeon.getCave(currentCave.getCaveId());
  }

  @Override
  public boolean isArrowAvailableAtCurrentNode() {
    return currentCave.getIsArrowAvailable();
  }

  @Override
  public void pickUpArrow() {
    dungeon.removeArrowInCave(currentCave.getCaveId());
    player.setArrowCount(true);
  }

  @Override
  public boolean isPlayerHavingArrow() {
    return player.getArrowCount() > 0;
  }

  @Override
  public boolean shootArrow(int distance, String direction) {
    Direction direct = Direction.moveDirection(direction);
    int arrowIsInCave = currentCave.getCaveId();
    while (distance != 0) {
      arrowIsInCave = nextCaveOfArrow(direct, dungeon.getCave(arrowIsInCave));
      if (arrowIsInCave == 0) {
        player.setArrowCount(false);
        return false;
      } else {
        direct = dungeon.getCave(arrowIsInCave).isTunnel()
                ? getArrowNextMoveInTunnel(arrowIsInCave) : direct;
      }
      distance = distance - 1;
    }
    if (dungeon.getCave(arrowIsInCave).getMonster() != null
            && dungeon.getCave(arrowIsInCave).getMonster().getHealth() > 0) {
      dungeon.updateMonsterHealth(arrowIsInCave);
      if (dungeon.getCave(arrowIsInCave).getMonster().getHealth() == 0) {
        dungeon.killMonster(arrowIsInCave);
      }
      player.setArrowCount(false);
      return true;
    }
    return false;
  }

  private Direction getArrowNextMoveInTunnel(int caveId) {
    DungeonCave currCave = dungeon.getCave(caveId);
    for (Direction d : currCave.getDirection().keySet()) {
      if (currCave.getDirection().get(d) > 0) {
        return d;
      }
    }
    return null;
  }

  private int nextCaveOfArrow(Direction direct, DungeonCave cave) {
    if (cave.isTunnel()) {
      return cave.getDirection().get(getArrowNextMoveInTunnel(cave.getCaveId()));
    } else {
      if (cave.getDirection().get(direct) != null) {
        return cave.getDirection().get(direct) > 0 ? cave.getDirection().get(direct) : 0;
      } else {
        throw new IllegalArgumentException("Invalid direction provided!!");
      }
    }
  }

  @Override
  public String getSmell() {
    return getSmellHelper();
  }

  private String getSmellHelper() {
    List<List<Integer>> neighbourCaves = new ArrayList<>();
    List<Integer> innerNeighbourList = new ArrayList<>();
    List<Integer> outerNeighbourList = new ArrayList<>();
    for (int innerCaveId : currentCave.getDirection().values()) {
      if (innerCaveId != 0) {
        DungeonCave innerCave = dungeon.getCave(innerCaveId);
        if (innerCave.getMonster() != null) {
          innerNeighbourList.add(innerCaveId);
        }
        for (int outerCaveId : innerCave.getDirection().values()) {
          if (outerCaveId != 0 && outerCaveId != currentCave.getCaveId()) {
            DungeonCave outerCave = dungeon.getCave(outerCaveId);
            if (outerCave.getMonster() != null) {
              outerNeighbourList.add(outerCaveId);
            }
          }
        }
      }
    }
    neighbourCaves.add(innerNeighbourList);
    neighbourCaves.add(outerNeighbourList);
    return computeSmell(neighbourCaves);
  }

  private String computeSmell(List<List<Integer>> neighbourCaves) {
    if (neighbourCaves.get(0).size() >= 1 || neighbourCaves.get(1).size() >= 2) {
      return "\u001b[42;1m" + "BEWARE!! Smells very pungent in here!!" + " \u001b[0m" + "\n";
    } else if (neighbourCaves.get(1).size() == 1) {
      return "\u001b[43;1m" + "BEWARE!! Smells little pungent in here!!" + "\u001b[0m" + "\n";
    } else {
      return "";
    }
  }

  @Override
  public String printAllCaves() {
    StringBuilder sb = new StringBuilder();
    sb.append(dungeon.getAllCaves().toString());
    return sb.toString();
  }

  @Override
  public int printArrowsWithPlayer() {
    return player.getArrowCount();
  }

}
