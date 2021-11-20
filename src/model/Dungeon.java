package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Dungeon class represents the Dungeon in a game.
 * All the operations to be performed or the dungeon are defined in the
 * Dungeon Class. Dungeon class implements the DungeonInterface and implements all the
 * methods.
 */
public class Dungeon implements DungeonInterface {
  private static DungeonCave startCave;
  private static DungeonCave endCave;
  private final int noOfRows;
  private final int noOfColumns;
  private final int interconnectivity;
  private final int treasurePercentage;
  private final boolean isWrapping;
  private final int noOfMonsters;
  //private boolean flag;
  private final List<List<Integer>> startEndPath;
  private final List<List<DungeonCave>> caveList;
  private final List<DungeonCave[]> possiblePath;
  private final RandomNumbersGenerator random;

  /**
   * Constructor to create a new Dungeon for the game with the configurations provided by the user.
   *
   * @param noOfRows           no of rows in the dungeon
   * @param noOfColumns        no of columns in the dungeon
   * @param interconnectivity  degree of interconnectivity of the dungeon
   * @param isWrapping         weather the dungeon is wrapping or non-wrapping
   * @param treasurePercentage percentage of treasure to be assigned in the dungeon
   * @param noOfMonsters       the total number of monsters in a cave
   * @param random             random variable to generate random numbers.
   */
  public Dungeon(int noOfRows, int noOfColumns, int interconnectivity, boolean isWrapping,
                 int treasurePercentage, int noOfMonsters, RandomNumbersGenerator random) {
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

    this.noOfRows = noOfRows;
    this.noOfColumns = noOfColumns;
    this.interconnectivity = interconnectivity;
    this.treasurePercentage = treasurePercentage;
    this.isWrapping = isWrapping;
    this.caveList = new ArrayList<>();
    this.possiblePath = new ArrayList<>();
    this.noOfMonsters = noOfMonsters;
    this.startEndPath = new ArrayList<>();
    this.random = random;
    addCavesToDungeon();
  }

  private void addCavesToDungeon() {
    int caveId = 1;
    for (int i = 0; i < noOfRows; i++) {
      List<DungeonCave> rowCaves = new ArrayList<>();
      for (int j = 0; j < noOfColumns; j++) {
        DungeonCave cave = new Cave(caveId, i, j);
        rowCaves.add(cave);
        caveId++;
      }
      caveList.add(rowCaves);
    }
    generatePossiblePaths();
    updateTunnel();
    generateStartAndEndCaves();
    updateCaveWithElement(treasurePercentage, "TREASURE");
    addMonstersToDungeon();
    updateCaveWithElement(treasurePercentage, "ARROW");

  }

  private void addMonstersToDungeon() {
    int countOfMonsters = 1;
    Monster endMonster = new Otyugh(1);
    List<Integer> cavesWithMonsters = new ArrayList<>();
    endCave.setMonsterInCave(endMonster);
    countOfMonsters++;
    cavesWithMonsters.add(endCave.getCaveId());
    while (cavesWithMonsters.size() != noOfMonsters) {
      int randomNumber = random.getRandomNumber(1, (noOfRows * noOfColumns - 1));
      if (!cavesWithMonsters.contains(randomNumber)) {
        for (int i = 0; i < noOfRows; i++) {
          for (int j = 0; j < noOfColumns; j++) {
            if (caveList.get(i).get(j).getCaveId() == randomNumber) {
              if (!caveList.get(i).get(j).isTunnel()
                      && caveList.get(i).get(j).getCaveId() != startCave.getCaveId()) {
                Monster m = new Otyugh(countOfMonsters);
                caveList.get(i).get(j).setMonsterInCave(m);
                cavesWithMonsters.add(caveList.get(i).get(j).getCaveId());
                countOfMonsters++;
              }
            }
          }
        }
      }
    }
  }

  @Override
  public DungeonCave getCave(int newCaveId) {
    for (int i = 0; i < noOfRows; i++) {
      for (int j = 0; j < noOfColumns; j++) {
        if (caveList.get(i).get(j).getCaveId() == newCaveId) {
          return new Cave(caveList.get(i).get(j));
        }
      }
    }
    throw new IllegalStateException("Invalid Cave Id");
  }

  private void generatePossiblePaths() {
    for (int i = 0; i <= this.noOfRows - 1; i++) {
      for (int j = 0; j <= this.noOfColumns - 1; j++) {
        if (i != this.noOfRows - 1 && j != this.noOfColumns) {
          if (j != this.noOfColumns - 1) {
            this.possiblePath.add(new DungeonCave[]{this.caveList.get(i).get(j),
                    this.caveList.get(i).get(j + 1)});
          }
          if (i != this.noOfRows - 1) {
            this.possiblePath.add(new DungeonCave[]{this.caveList.get(i).get(j),
                    this.caveList.get(i + 1).get(j)});
          }
        }
      }
    }
    if (this.isWrapping) {
      for (int i = 0; i < this.noOfRows - 1; i++) {
        this.possiblePath.add(new DungeonCave[]{this.caveList.get(i).get(0),
                this.caveList.get(i).get(this.noOfColumns - 1)});
      }
      for (int i = 0; i < this.noOfColumns - 1; i++) {
        this.possiblePath.add(new DungeonCave[]{this.caveList.get(0).get(i),
                this.caveList.get(this.noOfRows - 1).get(i)});
      }
    }

    createPaths(createSets());
  }

  private void createPaths(List<HashSet<Integer>> hashSetList) {
    List<DungeonCave[]> leftOverList = new ArrayList<>();
    while (possiblePath.size() != 0) {
      int randomNumber = random.getRandomNumber(0, possiblePath.size() - 1);
      DungeonCave[] possiblePath1 = possiblePath.get(randomNumber);
      HashSet<Integer> set1 = getSets(hashSetList, possiblePath1[0].getCaveId());
      HashSet<Integer> set2 = getSets(hashSetList, possiblePath1[1].getCaveId());
      if (set1.equals(set2)) {
        leftOverList.add(possiblePath1);

      } else {
        HashSet<Integer> mergeSet = new HashSet<Integer>() {
          {
            addAll(set1);
            addAll(set2);
          }
        };
        updateCaveDirection(possiblePath1);
        hashSetList.add(mergeSet);
        hashSetList.remove(set1);
        hashSetList.remove(set2);

      }
      possiblePath.remove(possiblePath1);
    }
    connectLeftover(leftOverList);
  }

  private void connectLeftover(List<DungeonCave[]> leftOverList) {
    int interConnectivityCount = this.interconnectivity;
    if (leftOverList.size() > interConnectivityCount) {
      while (interConnectivityCount != 0) {
        int randomNumber = random.getRandomNumber(0, leftOverList.size() - 1);
        DungeonCave[] possiblePath2 = leftOverList.get(randomNumber);
        updateCaveDirection(possiblePath2);
        leftOverList.remove(randomNumber);
        interConnectivityCount--;
      }
    }
  }

  private void updateCaveDirection(DungeonCave[] possiblePath1) {
    int row1 = possiblePath1[0].getRowNo();
    int col1 = possiblePath1[0].getColumnNo();
    int row2 = possiblePath1[1].getRowNo();
    int col2 = possiblePath1[1].getColumnNo();
    if (row1 == row2 - 1) {
      possiblePath1[0].setDirection(Direction.SOUTH, possiblePath1[1].getCaveId());
      possiblePath1[1].setDirection(Direction.NORTH, possiblePath1[0].getCaveId());
    }
    if (col1 == col2 - 1) {
      possiblePath1[0].setDirection(Direction.EAST, possiblePath1[1].getCaveId());
      possiblePath1[1].setDirection(Direction.WEST, possiblePath1[0].getCaveId());
    }
    if (isWrapping) {
      if (col1 == 0 && col2 == noOfColumns - 1 && row1 == row2) {
        possiblePath1[0].setDirection(Direction.WEST, possiblePath1[1].getCaveId());
        possiblePath1[1].setDirection(Direction.EAST, possiblePath1[0].getCaveId());
      }
      if (row1 == 0 && row2 == noOfRows - 1 && col1 == col2) {
        possiblePath1[0].setDirection(Direction.NORTH, possiblePath1[1].getCaveId());
        possiblePath1[1].setDirection(Direction.SOUTH, possiblePath1[0].getCaveId());
      }
    }
  }

  private HashSet getSets(List<HashSet<Integer>> hashSetList, int caveId) {
    for (HashSet set : hashSetList) {
      if (set.contains(caveId)) {
        return set;
      }
    }
    throw new IllegalArgumentException("Invalid Cave Id");
  }

  private List<HashSet<Integer>> createSets() {
    List<HashSet<Integer>> hashSetList = new ArrayList<>();
    for (int i = 0; i <= noOfRows - 1; i++) {
      for (int j = 0; j <= noOfColumns - 1; j++) {
        HashSet<Integer> hs = new HashSet<>();
        hs.add(caveList.get(i).get(j).getCaveId());
        hashSetList.add(hs);
      }
    }
    return hashSetList;
  }

  @Override
  public String printDungeon(DungeonCave currentCave) {
    StringBuilder dungeon = new StringBuilder();
    for (List list : caveList) {
      for (int i = 0; i < list.size(); i++) {
        DungeonCave cave = (DungeonCave) list.get(i);
        dungeon = cave.getDirection().get(Direction.NORTH) != 0 ? dungeon.append("   || ")
                : dungeon.append("      ");
      }
      dungeon.append("\n");
      for (int i = 0; i < list.size(); i++) {
        DungeonCave c1 = (DungeonCave) list.get(i);
        String color = "\u001B[37m";
        dungeon = c1.getDirection().get(Direction.WEST) != 0 ? dungeon.append("==")
                : dungeon.append("  ");
        if (c1.getCaveId() == currentCave.getCaveId()) {
          color = getOutputColour(c1);
          dungeon.append(color + "(" + "\u001B[0m" + "\u001B[31m" + ":P" + "\u001B[0m"
                  + color + ")" + "\u001B[0m");
        } else {
          color = getOutputColour(c1);
          dungeon = c1.getCaveId() == this.endCave.getCaveId() ? dungeon.append(color + "(:G)"
                  + "\u001B[0m") :
                  c1.isTunnel() ? dungeon.append(color + " TT " + "\u001B[0m") : c1.getCaveId() <= 9
                          ? dungeon.append(color + "(0").append(c1.getCaveId()).append(")"
                          + "\u001B[0m")
                          : dungeon.append(color + "(").append(c1.getCaveId()).append(")"
                          + "\u001B[0m");
        }
        if (c1.getDirection().get(Direction.EAST) != 0 && c1.getColumnNo() == noOfColumns - 1) {
          dungeon.append("==");
        }
      }
      dungeon.append("\n");
      for (int i = 0; i < list.size(); i++) {
        DungeonCave c1 = (DungeonCave) list.get(i);
        if (c1.getRowNo() == noOfRows - 1) {
          dungeon = c1.getDirection().get(Direction.SOUTH) != 0 ? dungeon.append("   || ")
                  : dungeon.append("      ");
        }
      }
    }
    return dungeon.toString();
  }

  private String getOutputColour(DungeonCave c1) {
    String colour;
    if (c1.getMonster() != null && c1.getIsArrowAvailable()) {
      colour = "\u001B[35m";
    } else if (c1.getMonster() != null && !c1.getIsArrowAvailable()) {
      colour = "\u001B[32m";
    } else if (c1.getMonster() == null && c1.getIsArrowAvailable()) {
      colour = "\u001B[33m";
    } else {
      colour = "\u001B[37m";
    }
    return colour;
  }

  private void updateCaveWithElement(int elementPercent, String elementType) {
    if (elementPercent >= 0) {
      int treasureNumber = Math.round((treasurePercentage * noOfRows * noOfColumns) / 100);
      List<Integer> elementIndex = new ArrayList<>();
      while (elementIndex.size() != treasureNumber) {
        int randomNumber = random.getRandomNumber(1, (noOfRows * noOfColumns));
        if (!elementIndex.contains(randomNumber)) {
          for (int i = 0; i < noOfRows; i++) {
            for (int j = 0; j < noOfColumns; j++) {
              if (caveList.get(i).get(j).getCaveId() == randomNumber) {
                if (elementType.equalsIgnoreCase("TREASURE")
                        && !caveList.get(i).get(j).isTunnel()) {
                  caveList.get(i).get(j).setCaveTreasure(Treasure.RUBY,
                          random.getRandomNumber(0, 3) * 100);
                  caveList.get(i).get(j).setCaveTreasure(Treasure.SAPPHIRE,
                          random.getRandomNumber(0, 3) * 100);
                  if (caveList.get(i).get(j).getTreasure().get(Treasure.RUBY) == 0
                          && caveList.get(i).get(j).getTreasure().get(Treasure.SAPPHIRE) == 0) {
                    caveList.get(i).get(j).setCaveTreasure(Treasure.DIAMONDS,
                            random.getRandomNumber(1, 3) * 100);
                  } else {
                    caveList.get(i).get(j).setCaveTreasure(Treasure.DIAMONDS,
                            random.getRandomNumber(0, 3) * 100);
                  }
                  elementIndex.add(randomNumber);
                } else if (elementType.equalsIgnoreCase("ARROW")) {
                  caveList.get(i).get(j).setArrowInCave(true);
                  elementIndex.add(randomNumber);
                }
              }
            }
          }
        }
      }
    }
  }

  private void updateTunnel() {
    for (int i = 0; i < noOfRows - 1; i++) {
      for (int j = 0; j < noOfColumns - 1; j++) {
        int count = 0;
        for (Direction d : caveList.get(i).get(j).getDirection().keySet()) {
          if (caveList.get(i).get(j).getDirection().get(d) != 0) {
            count++;
          }
        }
        if (count == 2) {
          caveList.get(i).get(j).setTunnel(true);
        }
      }
    }
  }

  private void generateStartAndEndCaves() {
    boolean flag1 = false;
    while (!flag1) {
      DungeonCave start = getRandomCaveObject();
      DungeonCave end = getRandomCaveObject();
      startEndPath.clear();
      checkDistance(start, end);
      List<Integer> possiblePathSizeList = new ArrayList<>();
      for (List path : startEndPath) {
        possiblePathSizeList.add(path.size());
      }
      int min = Collections.min(possiblePathSizeList);
      if (min >= 6) {
        startCave = start;
        endCave = end;
        flag1 = true;
      }
    }
  }

  private DungeonCave getRandomCaveObject() {
    DungeonCave c1;
    int randomXnumber = random.getRandomNumber(1, (noOfRows) - 1);
    int randomYnumber = random.getRandomNumber(1, (noOfColumns) - 1);
    c1 = caveList.get(randomXnumber).get(randomYnumber);
    if (!c1.isTunnel()) {
      return c1;
    } else {
      return getRandomCaveObject();
    }
    // throw new IllegalStateException("Valid cave could not be found");
  }

  private void checkDistance(DungeonCave start, DungeonCave end) {
    List<Integer>[] adjList = createAdjacencyList();
    getAllPossiblePaths(start.getCaveId(), end.getCaveId(), adjList);
  }

  private List<Integer>[] createAdjacencyList() {
    List<Integer>[] adjList = new ArrayList[noOfRows * noOfColumns + 1];
    for (int i = 0; i <= noOfRows * noOfColumns; i++) {
      adjList[i] = new ArrayList<>();
    }
    for (int i = 0; i < noOfRows; i++) {
      for (int j = 0; j < noOfColumns; j++) {
        HashMap<Direction, Integer> directionMap = caveList.get(i).get(j).getDirection();
        for (Direction direction : directionMap.keySet()) {
          if (directionMap.get(direction) != 0) {
            adjList[caveList.get(i).get(j).getCaveId()].add(directionMap.get(direction));
          }
        }
      }
    }
    return adjList;
  }

  private void getAllPossiblePaths(int start, int end, List<Integer>[] adjList) {
    boolean[] isVisited = new boolean[noOfColumns * noOfRows + 1];
    List<Integer> pathList = new ArrayList<>();
    // add source to path[]
    pathList.add(start);
    // Call recursive utility
    getAllPossiblePathsUtil(start, end, isVisited, pathList, adjList);
  }

  private void getAllPossiblePathsUtil(int start, int end, boolean[] isVisited,
                                       List<Integer> localPathList, List<Integer>[] adjList) {
    if (start == end) {
      List<Integer> caveInPath = new ArrayList<>(localPathList);
      startEndPath.add(caveInPath);
    } else {
      // Mark the current node
      isVisited[start] = true;
      // Recur for all the vertices adjacent to current vertex
      for (Integer i : adjList[start]) {
        if (!isVisited[i]) {
          // store current node in path[]
          localPathList.add(i);
          getAllPossiblePathsUtil(i, end, isVisited, localPathList, adjList);
          // remove current node in path[]
          localPathList.remove(i);
        }
      }
      // Mark the current node
      isVisited[start] = false;
    }
  }

  @Override
  public void emptyTreasureInCave(Treasure treasureType, DungeonCave currentCave) {
    for (int i = 0; i < noOfRows; i++) {
      for (int j = 0; j < noOfColumns; j++) {
        if (caveList.get(i).get(j).getCaveId() == currentCave.getCaveId()) {
          currentCave = caveList.get(i).get(j);
        }
      }
    }
    currentCave.setCaveTreasure(treasureType, 0);
  }

  @Override
  public void killMonster(int arrowIsInCave) {
    for (int i = 0; i < noOfRows; i++) {
      for (int j = 0; j < noOfColumns; j++) {
        if (caveList.get(i).get(j).getCaveId() == arrowIsInCave) {
          caveList.get(i).get(j).setMonsterInCave(null);
        }
      }
    }
  }

  @Override
  public void removeArrowInCave(int caveId) {
    for (int i = 0; i < noOfRows; i++) {
      for (int j = 0; j < noOfColumns; j++) {
        if (caveList.get(i).get(j).getCaveId() == caveId) {
          caveList.get(i).get(j).setArrowInCave(false);
        }
      }
    }
  }

  @Override
  public void updateMonsterHealth(int arrowIsInCave) {
    for (int i = 0; i < noOfRows; i++) {
      for (int j = 0; j < noOfColumns; j++) {
        if (caveList.get(i).get(j).getCaveId() == arrowIsInCave) {
          caveList.get(i).get(j).setMonsterInCave(caveList.get(i).get(j).updateMonsterHealth());
        }
      }
    }
  }

  @Override
  public DungeonCave getStartCave() {
    return new Cave(startCave);
  }

  @Override
  public List<List<DungeonCave>> getAllCaves() {
    List<List<DungeonCave>> caveListCopy = new ArrayList<>();
    List<DungeonCave> rowCaves;
    for (int i = 0; i < noOfRows; i++) {
      rowCaves = new ArrayList<>();
      for (int j = 0; j < noOfColumns; j++) {
        DungeonCave cave = new Cave(caveList.get(i).get(j));
        rowCaves.add(cave);
      }
      caveListCopy.add(rowCaves);
    }
    return caveListCopy;
  }

  @Override
  public DungeonCave getEndCave() {
    return new Cave(endCave);
  }

}
