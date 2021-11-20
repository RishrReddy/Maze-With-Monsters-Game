package test;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Direction;
import model.Dungeon;
import model.DungeonCave;
import model.DungeonInterface;
import model.RandomNumberGenerateTest;
import model.RandomNumbersGenerator;
import model.Treasure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * DungeonTest class represents all the test cases related ta a Dungeon object and
 * other methods implemented from the Dungeon class.
 */
public class DungeonTest {
  DungeonInterface dungeon;
  RandomNumbersGenerator r1 = new RandomNumberGenerateTest(2);

  @Before
  public void setUp() {
    dungeon = new Dungeon(4, 4, 2, true, 30, 5, r1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDungeonCreationTest() {
    DungeonInterface dungeon1 = new Dungeon(2, 3, 2, true, 30, 5, r1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDungeonNonWrappingCreationTest() {
    DungeonInterface nonWrappingDungeon = new Dungeon(1, 5, 2, false, 30, 5, r1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDungeonCreationRandomNullTest() {
    DungeonInterface dungeon1 = new Dungeon(4, 3, 6, true, 30, 5, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDungeonCreationNonWrappingRandomNullTest() {
    DungeonInterface nonWrappingDungeon = new Dungeon(5, 5, 2, false, 30, 5, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDungeonCreationNegTreasureTest() {
    DungeonInterface dungeon1 = new Dungeon(4, 3, 6, true, -30, 5, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDungeonCreationNonWrappingNegTreasureTest() {
    DungeonInterface nonWrappingDungeon = new Dungeon(5, 5, 2, false, -30, 5, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDungeonCreationNegInterConTest() {
    DungeonInterface dungeon1 = new Dungeon(4, 3, -6, true, 30, 5, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDungeonCreationNonWrappingNegInterConTest() {
    DungeonInterface nonWrappingDungeon = new Dungeon(5, 5, -2, false, 30, 5, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDungeonCreationNonWrappingNegMonsterTest() {
    DungeonInterface testDungeon = new Dungeon(5, 5, 5, false, 30, -2, r1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDungeonCreationWrappingNegMonsterTest() {
    DungeonInterface testDun1 = new Dungeon(5, 5, 5, true, 30, -5, r1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDungeonCreationNonWrappingMonsterTest() {
    DungeonInterface testDungeon = new Dungeon(5, 5, 5, false, 30, 30, r1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDungeonCreationWrappingMonsterTest() {
    DungeonInterface testDun1 = new Dungeon(5, 5, 5, true, 30, 30, r1);
  }

  /**
   * Asserting that all caves have 0 diamonds, 0 ruby and 0 sapphire when treasurePercentage is 0.
   */
  @Test
  public void createDungeonWithZeroTreasure() {
    //asserting all caves in whole dungeon for treasure.
    DungeonInterface dungeon = new Dungeon(5, 5, 6, false, 0, 5, r1);
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        assertTrue(dungeon.getAllCaves().get(i).get(j)
                .getTreasure().get(Treasure.SAPPHIRE) == 0
                && dungeon.getAllCaves().get(i).get(j).getTreasure().get(Treasure.RUBY) == 0
                && dungeon.getAllCaves().get(i).get(j).getTreasure().get(Treasure.DIAMONDS) == 0);
      }
    }
  }

  @Test
  public void createValidDungeon() {
    //asserting all caves in whole dungeon for treasure.
    int countOfCaves = 0;
    int cavesWithTreasure = 0;
    int tunnelCount = 0;
    for (List l1 : dungeon.getAllCaves()) {
      countOfCaves = countOfCaves + l1.size();
    }
    assertEquals(16, countOfCaves); // Asserted that there are n x m in dungeon.
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        if (dungeon.getAllCaves().get(i).get(j).getTreasure().get(Treasure.SAPPHIRE) > 0
                || dungeon.getAllCaves().get(i).get(j).getTreasure().get(Treasure.RUBY) > 0
                || dungeon.getAllCaves().get(i).get(j).getTreasure().get(Treasure.DIAMONDS) > 0) {
          cavesWithTreasure++;
        }
        if (dungeon.getAllCaves().get(i).get(j).isTunnel()) {
          tunnelCount++;
        }
      }
    }
    //asserted that for total of 16 caves 4 get treasure when treasurePercentage is set to 30%.
    assertEquals(4, cavesWithTreasure);
    assertEquals(5, tunnelCount);
    assertTrue(dungeon.getAllCaves().get(0).get(0).isTunnel()
            && dungeon.getAllCaves().get(0).get(2).isTunnel()
            && dungeon.getAllCaves().get(1).get(1).isTunnel()
            && dungeon.getAllCaves().get(1).get(2).isTunnel()
            && dungeon.getAllCaves().get(2).get(0).isTunnel());
  }

  @Test
  public void createValidNxM_Dungeon() {
    //asserting all caves in whole dungeon for treasure.
    DungeonInterface dungeon1 = new Dungeon(4, 6, 2, true, 30, 5, r1);
    int countOfCaves = 0;
    int cavesWithTreasure = 0;
    int tunnelCount = 0;
    for (List l1 : dungeon1.getAllCaves()) {
      countOfCaves = countOfCaves + l1.size();
    }
    assertEquals(24, countOfCaves); // Asserted that there are n x m in dungeon.
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 6; j++) {
        if (dungeon1.getAllCaves().get(i).get(j).getTreasure().get(Treasure.SAPPHIRE) > 0
                || dungeon1.getAllCaves().get(i).get(j).getTreasure().get(Treasure.RUBY) > 0
                || dungeon1.getAllCaves().get(i).get(j).getTreasure().get(Treasure.DIAMONDS) > 0) {
          cavesWithTreasure++;
        }
        if (dungeon1.getAllCaves().get(i).get(j).isTunnel()) {
          tunnelCount++;
        }
      }
    }
    //asserted that for total of 16 caves 4 get treasure when treasurePercentage is set to 30%.
    assertEquals(7, cavesWithTreasure);
    System.out.println(dungeon1.printDungeon(dungeon1.getStartCave()));
    assertEquals(5, tunnelCount);
    assertTrue(dungeon1.getAllCaves().get(0).get(0).isTunnel()
            && dungeon1.getAllCaves().get(0).get(4).isTunnel()
            && dungeon1.getAllCaves().get(2).get(0).isTunnel()
            && dungeon1.getAllCaves().get(2).get(1).isTunnel()
            && dungeon1.getAllCaves().get(2).get(4).isTunnel());
  }

  @Test
  public void createValidNxM_NonWrappingDungeon() {
    //asserting all caves in whole dungeon for treasure.
    DungeonInterface dungeon1 = new Dungeon(4, 6, 2, false, 30, 5, r1);
    int countOfCaves = 0;
    int cavesWithTreasure = 0;
    int tunnelCount = 0;
    for (List l1 : dungeon1.getAllCaves()) {
      countOfCaves = countOfCaves + l1.size();
    }
    assertEquals(24, countOfCaves); // Asserted that there are n x m in dungeon.
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 6; j++) {
        if (dungeon1.getAllCaves().get(i).get(j).getTreasure().get(Treasure.SAPPHIRE) > 0
                || dungeon1.getAllCaves().get(i).get(j).getTreasure().get(Treasure.RUBY) > 0
                || dungeon1.getAllCaves().get(i).get(j).getTreasure().get(Treasure.DIAMONDS) > 0) {
          cavesWithTreasure++;
        }
        if (dungeon1.getAllCaves().get(i).get(j).isTunnel()) {
          tunnelCount++;
        }
      }
    }
    //asserted that for total of 16 caves 4 get treasure when treasurePercentage is set to 30%.
    assertEquals(7, cavesWithTreasure);
    System.out.println(dungeon1.printDungeon(dungeon1.getStartCave()));
    assertEquals(3, tunnelCount);
    assertTrue(dungeon1.getAllCaves().get(0).get(1).isTunnel()
            && dungeon1.getAllCaves().get(1).get(3).isTunnel()
            && dungeon1.getAllCaves().get(2).get(0).isTunnel());
  }

  @Test
  public void createValidNonWrappingDungeon() {
    //asserting all caves in whole dungeon for treasure.
    DungeonInterface nonWrappingDungeon = new Dungeon(5, 5, 3, false, 50, 5, r1);
    int countOfCaves = 0;
    int cavesWithTreasure = 0;
    int tunnelCount = 0;
    for (List l1 : nonWrappingDungeon.getAllCaves()) {
      countOfCaves = countOfCaves + l1.size();
    }
    assertEquals(25, countOfCaves); // Asserted that there are n x m in dungeon.
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        if (nonWrappingDungeon.getAllCaves().get(i).get(j)
                .getTreasure().get(Treasure.SAPPHIRE) > 0
                || nonWrappingDungeon.getAllCaves().get(i)
                .get(j).getTreasure().get(Treasure.RUBY) > 0
                || nonWrappingDungeon.getAllCaves().get(i)
                .get(j).getTreasure().get(Treasure.DIAMONDS) > 0) {
          cavesWithTreasure++;
        }
        if (nonWrappingDungeon.getAllCaves().get(i).get(j).isTunnel()) {
          tunnelCount++;
        }
      }
    }
    //asserted that for total of 16 caves 4 get treasure when treasurePercentage is set to 30%.
    assertEquals(12, cavesWithTreasure);
    System.out.println(nonWrappingDungeon.printDungeon(nonWrappingDungeon.getStartCave()));
    assertEquals(6, tunnelCount);
    assertTrue(nonWrappingDungeon.getAllCaves().get(0).get(0).isTunnel()
            && nonWrappingDungeon.getAllCaves().get(0).get(2).isTunnel()
            && nonWrappingDungeon.getAllCaves().get(0).get(3).isTunnel()
            && nonWrappingDungeon.getAllCaves().get(1).get(2).isTunnel()
            && nonWrappingDungeon.getAllCaves().get(1).get(3).isTunnel()
            && nonWrappingDungeon.getAllCaves().get(3).get(0).isTunnel());
  }

  @Test
  public void interconnectivityCheck() {
    DungeonInterface dungeon1 = new Dungeon(4, 5, 0, false, 20, 5, r1);
    System.out.println(dungeon1.printDungeon(dungeon1.getStartCave()));
    long verticalPathCount = (dungeon1.printDungeon(dungeon1.getStartCave())
            .chars().filter(ch -> ch == '|').count()) / 2;
    long horizontalPathCount = (dungeon1.printDungeon(dungeon1.getStartCave())
            .chars().filter(ch -> ch == '=').count()) / 2;
    assertEquals(19, verticalPathCount + horizontalPathCount);
    DungeonInterface dungeon2 = new Dungeon(4, 5, 2, false, 20, 5, r1);
    long verticalPathCount1 = (dungeon2.printDungeon(dungeon2.getStartCave())
            .chars().filter(ch -> ch == '|').count()) / 2;
    long horizontalPathCount1 = (dungeon2.printDungeon(dungeon2.getStartCave())
            .chars().filter(ch -> ch == '=').count()) / 2;
    assertEquals(21, verticalPathCount1 + horizontalPathCount1);
    DungeonInterface dungeon3 = new Dungeon(4, 5, 4, false, 20, 5, r1);
    long verticalPathCount2 = (dungeon3.printDungeon(dungeon3.getStartCave())
            .chars().filter(ch -> ch == '|').count()) / 2;
    long horizontalPathCount2 = (dungeon3.printDungeon(dungeon3.getStartCave())
            .chars().filter(ch -> ch == '=').count()) / 2;
    assertEquals(23, verticalPathCount2 + horizontalPathCount2);
  }

  @Test
  public void interconnectivityNonWrappingCheck() {
    DungeonInterface dungeon1 = new Dungeon(4, 5, 0, true, 20, 5, r1);
    System.out.println(dungeon1.printDungeon(dungeon1.getStartCave()));
    long verticalPathCount = (dungeon1.printDungeon(dungeon1.getStartCave())
            .chars().filter(ch -> ch == '|').count()) / 2;
    long horizontalPathCount = (dungeon1.printDungeon(dungeon1.getStartCave())
            .chars().filter(ch -> ch == '=').count()) / 2;
    assertEquals(21, verticalPathCount + horizontalPathCount);
    DungeonInterface dungeon2 = new Dungeon(4, 5, 2, true, 20, 5, r1);
    long verticalPathCount1 = (dungeon2.printDungeon(dungeon2.getStartCave())
            .chars().filter(ch -> ch == '|').count()) / 2;
    long horizontalPathCount1 = (dungeon2.printDungeon(dungeon2.getStartCave())
            .chars().filter(ch -> ch == '=').count()) / 2 - 3;
    assertEquals(23, verticalPathCount1 + horizontalPathCount1);
    DungeonInterface dungeon3 = new Dungeon(4, 5, 4, true, 20, 5, r1);
    long verticalPathCount2 = (dungeon3.printDungeon(dungeon3.getStartCave())
            .chars().filter(ch -> ch == '|').count()) / 2;
    long horizontalPathCount2 = (dungeon3.printDungeon(dungeon3.getStartCave())
            .chars().filter(ch -> ch == '=').count()) / 2 - 3;
    assertEquals(26, verticalPathCount2 + horizontalPathCount2);
  }

  @Test
  public void caveHasAtleastOneConnection() {
    DungeonInterface nonWrap = new Dungeon(4, 4, 2, true, 20, 5, r1);
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        DungeonCave cave = dungeon.getAllCaves().get(i).get(j);
        DungeonCave cave2 = nonWrap.getAllCaves().get(i).get(j);
        assertTrue(cave.getDirection().get(Direction.EAST) != 0
                || cave.getDirection().get(Direction.WEST) != 0
                || cave.getDirection().get(Direction.SOUTH) != 0
                || cave.getDirection().get(Direction.NORTH) != 0);
        assertTrue(cave2.getDirection().get(Direction.EAST) != 0
                || cave2.getDirection().get(Direction.WEST) != 0
                || cave2.getDirection().get(Direction.SOUTH) != 0
                || cave2.getDirection().get(Direction.NORTH) != 0);
      }
    }
  }

  @Test
  public void startEndCaveTest() {
    assertEquals(14, dungeon.getStartCave().getCaveId());
    assertEquals(12, dungeon.getEndCave().getCaveId());
    DungeonInterface nonWrap = new Dungeon(4, 4, 2, true, 20, 5, r1);
    assertEquals(15, nonWrap.getStartCave().getCaveId());
    assertEquals(12, nonWrap.getEndCave().getCaveId());
  }

  @Test
  public void getCaveTest() {
    DungeonCave d = dungeon.getCave(15);
    assertEquals(15, d.getCaveId());
  }

  @Test
  public void emptyTreasureCave() {
    DungeonInterface d = new Dungeon(5, 4, 4, true, 50, 5, r1);
    assertEquals(8, d.getStartCave().getCaveId());
    assertTrue(d.getStartCave().getTreasure().get(Treasure.RUBY) == 200
            && d.getStartCave().getTreasure().get(Treasure.DIAMONDS) == 300
            && d.getStartCave().getTreasure().get(Treasure.SAPPHIRE) == 100);
    d.emptyTreasureInCave(Treasure.DIAMONDS, d.getStartCave());
    d.emptyTreasureInCave(Treasure.RUBY, d.getStartCave());
    d.emptyTreasureInCave(Treasure.SAPPHIRE, d.getStartCave());
    assertTrue(d.getStartCave().getTreasure().get(Treasure.RUBY) == 0
            && d.getStartCave().getTreasure().get(Treasure.DIAMONDS) == 0
            && d.getStartCave().getTreasure().get(Treasure.SAPPHIRE) == 0);
  }

  @Test
  public void notAllWrap() {
    int countVertical = 0;
    System.out.println(dungeon.printDungeon(dungeon.getStartCave()));
    for (int j = 0; j < 4; j++) {
      DungeonCave caveInRow1 = dungeon.getAllCaves().get(0).get(j);
      DungeonCave caveInRowN = dungeon.getAllCaves().get(3).get(j); //n-1,j
      if (caveInRow1.getDirection().get(Direction.NORTH) == caveInRowN.getCaveId()
              && caveInRowN.getDirection().get(Direction.SOUTH) == caveInRow1.getCaveId()) {
        countVertical = countVertical + 1;
      }
    }
    assertTrue(countVertical > 0 && countVertical < 4);
    int countHorizontal = 0;
    for (int i = 0; i < 4; i++) {
      DungeonCave caveInRow1 = dungeon.getAllCaves().get(i).get(0);
      DungeonCave caveInRowN = dungeon.getAllCaves().get(i).get(3); //n-1,j
      if (caveInRow1.getDirection().get(Direction.WEST) == caveInRowN.getCaveId()
              && caveInRowN.getDirection().get(Direction.EAST) == caveInRow1.getCaveId()) {
        countHorizontal = countHorizontal + 1;
      }
    }
    assertTrue(countHorizontal > 0 && countHorizontal < 4);
  }

  @Test
  public void startEndDistCheck() {
    List<Integer>[] adjList = createAdjacencyList(dungeon);
    List<List<Integer>> startEndPath = new ArrayList<>();

    getAllPossiblePaths(dungeon.getStartCave().getCaveId(),
            dungeon.getEndCave().getCaveId(), adjList, startEndPath);
    assertTrue(startEndPath.size() > 0);

    for (List l : startEndPath) {
      System.out.println(l.toString());
      assertTrue(l.size() >= 5);
    }
    assertTrue(startEndPath.toString().contains("[14, 2, 3, 15, 11, 12]")
            || startEndPath.toString().contains("[14, 2, 1, 4, 8, 7, 11, 12])")
            || startEndPath.toString().contains("[14, 2, 6, 10, 11, 12])"));
  }

  @Test
  public void checkingOnlyOnePathAtInterConn0() {
    Dungeon zero_dungeon = new Dungeon(4, 4, 0,
            true, 40, 5, r1);
    List<Integer>[] adjList = createAdjacencyList(zero_dungeon);
    List<List<Integer>> startEndPath = new ArrayList<>();
    getAllPossiblePaths(4, 13, adjList, startEndPath);
    assertTrue(startEndPath.size() == 1);
    List<List<Integer>> startEndPath1 = new ArrayList<>();
    getAllPossiblePaths(1, 16, adjList, startEndPath1);
    System.out.println(startEndPath1);
    assertTrue(startEndPath1.size() == 1);
    startEndPath1 = new ArrayList<>();
    getAllPossiblePaths(1, 2, adjList, startEndPath1);
    System.out.println(startEndPath1);
    assertTrue(startEndPath1.size() == 1);
    startEndPath1 = new ArrayList<>();
    getAllPossiblePaths(5, 15, adjList, startEndPath1);
    System.out.println(startEndPath1);
    assertTrue(startEndPath1.size() == 1);
  }

  @Test
  public void testTunnelHasNoTreasure() {
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        if (dungeon.getAllCaves().get(i).get(j).isTunnel()) {
          for (Treasure treasure : Treasure.values()) {
            int expectedTreasure = dungeon.getAllCaves().get(i).get(j).getTreasure().get(treasure);
            assertEquals(0, expectedTreasure);
          }
        }
      }
    }
  }

  @Test
  public void testEndCaveWithMonster() {
    assertTrue(dungeon.getEndCave().getMonster() != null);
    assertEquals("\n OtyughId: 1 Health: 100", dungeon.getEndCave().getMonster().toString());
  }

  @Test
  public void testStartCaveWithoutMonster() {
    assertNull(dungeon.getStartCave().getMonster());
  }

  @Test
  public void testOtyughInTunnel() {
    int noOfTunnel = 0;
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        if (dungeon.getAllCaves().get(i).get(j).isTunnel()) {
          noOfTunnel++;
          assertNull(dungeon.getAllCaves().get(i).get(j).getMonster());
        }
      }
    }
    assertTrue(noOfTunnel > 0);
  }

  @Test
  public void noOfMonster() {
    int monsterCount = 0;
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        if (dungeon.getAllCaves().get(i).get(j).getMonster() != null) {
          monsterCount++;
        }
      }
    }
    assertEquals(5, monsterCount);
  }

  @Test
  public void noOfArrowTest() {
    int arrowCount = 0;
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        if (dungeon.getAllCaves().get(i).get(j).getIsArrowAvailable()) {
          arrowCount++;
        }
      }
    }
    assertEquals(4, arrowCount);
  }

  private List<Integer>[] createAdjacencyList(DungeonInterface dungeon) {
    List<Integer>[] adjList = new ArrayList[4 * 4 + 1];
    for (int i = 0; i <= 4 * 4; i++) {
      adjList[i] = new ArrayList<>();
    }
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        HashMap<Direction, Integer> directionMap = dungeon.getAllCaves()
                .get(i).get(j).getDirection();
        for (Direction direction : directionMap.keySet()) {
          if (directionMap.get(direction) != 0) {
            adjList[dungeon.getAllCaves().get(i).get(j).getCaveId()]
                    .add(directionMap.get(direction));
          }
        }
      }
    }
    return adjList;
  }

  private void getAllPossiblePaths(int start, int end,
                                   List<Integer>[] adjList, List<List<Integer>> startEndPath) {
    boolean[] isVisited = new boolean[4 * 4 + 1];
    List<Integer> pathList = new ArrayList<>();
    // add source to path[]
    pathList.add(start);
    // Call recursive utility
    getAllPossiblePathsUtil(start, end, isVisited, pathList, adjList, startEndPath);

  }

  private void getAllPossiblePathsUtil(int start, int end, boolean[] isVisited,
                                       List<Integer> localPathList,
                                       List<Integer>[] adjList, List<List<Integer>> startEndPath) {
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
          getAllPossiblePathsUtil(i, end, isVisited, localPathList, adjList, startEndPath);
          // remove current node in path[]
          localPathList.remove(i);
        }
      }
      // Mark the current node
      isVisited[start] = false;
    }
  }
}

