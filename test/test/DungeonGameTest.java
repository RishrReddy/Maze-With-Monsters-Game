package test;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import model.Dungeon;
import model.DungeonInterface;
import model.Game;
import model.GameDungeonModel;
import model.RandomNumberGenerateTest;
import model.RandomNumbersGenerator;
import model.Treasure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * DungeonGameTest class represents all the test cases related ta a Game object and
 * other methods implemented from the Game class.
 */
public class DungeonGameTest {
  GameDungeonModel game;
  RandomNumbersGenerator r1 = new RandomNumberGenerateTest(2);

  @Before
  public void setUp() {
    game = new Game(5, 5, 8, true, 30, 5, r1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDungeonCreationTest() {
    DungeonInterface dungeon1 = new Dungeon(2, 3, 2, true, 30, 5, r1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDungeonNonWrappingCreationTest() {
    GameDungeonModel game1 = new Game(1, 5, 2, false, 30, 5, r1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDungeonCreationRandomNullTest() {
    GameDungeonModel game1 = new Game(4, 3, 6, true, 30, 5, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDungeonCreationNonWrappingRandomNullTest() {
    GameDungeonModel game1 = new Game(5, 5, 2, false, 30, 5, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDungeonCreationNegTreasureTest() {
    GameDungeonModel game1 = new Game(4, 3, 6, true, -30, 5, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDungeonCreationNonWrappingNegTreasureTest() {
    GameDungeonModel game1 = new Game(5, 5, 2, false, -30, 5, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDungeonCreationNegInterConTest() {
    GameDungeonModel game1 = new Game(4, 3, -6, true, 30, 5, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDungeonCreationNonWrappingNegInterConTest() {
    GameDungeonModel game1 = new Game(5, 5, -2, false, 30, 5, null);
  }

//  @Test
//  public void validDungeonTest() {
//    GameDungeonModel game1 = new Game(4, 5, 6, true, 30,5, r1);
//    assertEquals(""
//            + "   ||    ||    ||             \n"
//            + "==(01)==(02)==(03)==(04)==(05)==\n"
//            + "               ||    ||    || \n"
//            + "==(06)  (07)   TT    TT   (10)==\n"
//            + "         ||    ||    ||    || \n"
//            + "==(11)==(12)==(13)==(:P)==(15)==\n"
//            + "   ||          ||    ||    || \n"
//            + "  (16)  (:G)  (18)  (19)  (20)\n"
//            + "   ||    ||    ||             ", game1.printUpdatedDungeon());
//  }
//
//  @Test
//  public void validDungeonNonWrappingTest() {
//    GameDungeonModel game1 = new Game(4, 5, 6, false, 30,5, r1);
//    assertEquals("                              \n"
//            + "   TT ==(02)==(03)== TT ==(05)\n"
//            + "   ||    ||    ||          || \n"
//            + "   TT ==(07)==(08)==(09)==(:P)\n"
//            + "         ||    ||    ||    || \n"
//            + "   TT ==(12)==(13)==(14)==(15)\n"
//            + "   ||    ||    ||    ||    || \n"
//            + "  (16)  (:G)  (18)  (19)  (20)\n"
//            + "                              ", game1.printUpdatedDungeon());
//  }

  @Test
  public void moveToAllCaves() {
    GameDungeonModel game5 = new Game(4, 5, 6, true, 30, 2, r1);
    HashSet<Integer> set1 = new HashSet<>();
    game5.moveToPosition("s");
    game5.pickUpArrow();
    System.out.println(game5.printUpdatedDungeon());
    set1.add(Integer.parseInt(game5.printPlayerLocation()[0]));
    String[] moves = {"w", "a", "d", "w", "w", "d", "s", "d", "a", "s",
            "s", "w", "d", "d", "s", "w", "w", "w", "a"};
    int[] position = {15, 14, 15, 10, 5, 1, 6, 7, 6, 11, 16, 11, 12, 13, 18, 13, 8, 3, 2};

    for (int i = 0; i < moves.length; i++) {
      game5.moveToPosition(moves[i]);
      System.out.println(Integer.parseInt(game5.printPlayerLocation()[0]) + " " + position[i]);
      assertTrue(Integer.parseInt(game5.printPlayerLocation()[0]) == position[i]);
      set1.add(position[i]);
    }
    System.out.println(Integer.parseInt(game5.printPlayerLocation()[0]));
    game5.shootArrow(1, "w");
    game5.shootArrow(1, "w");
    System.out.println(game5.availablePathsWithPlayer());
    game5.moveToPosition("w");
    System.out.println(game5.availablePathsWithPlayer());
    set1.add(Integer.parseInt(game5.printPlayerLocation()[0]));
    game5.moveToPosition("s");
    set1.add(Integer.parseInt(game5.printPlayerLocation()[0]));
    game5.moveToPosition("d");
    set1.add(Integer.parseInt(game5.printPlayerLocation()[0]));
    game5.moveToPosition("d");
    set1.add(Integer.parseInt(game5.printPlayerLocation()[0]));
    game5.moveToPosition("s");
    set1.add(Integer.parseInt(game5.printPlayerLocation()[0]));
    game5.shootArrow(2, "w");
    game5.shootArrow(2, "w");
    game5.moveToPosition("w");
    set1.add(Integer.parseInt(game5.printPlayerLocation()[0]));
    game5.moveToPosition("w");
    set1.add(Integer.parseInt(game5.printPlayerLocation()[0]));
    assertEquals(20, set1.size());
  }

  @Test
  public void moveToAllCavesNonWrapping() {

    GameDungeonModel game1 = new Game(4, 5, 5, false, 40, 2, r1);
    System.out.println(game1.printUpdatedDungeon());
    game1.pickUpArrow();
    game1.shootArrow(2, "s");
    game1.shootArrow(2, "s");
    HashSet<Integer> set1 = new HashSet<>();
    set1.add(Integer.parseInt(game1.printPlayerLocation()[0]));
    String[] moves = {"d", "d", "d", "w", "a", "a", "a", "a", "d", "s", "a", "s", "s",
            "w", "d", "s", "w", "d", "s", "w", "d", "s", "w", "d"};
    int[] position = {8, 9, 10, 5, 4, 3, 2, 1, 2, 7, 6, 11, 16, 11, 12,
            17, 12, 13, 18, 13, 14, 19, 14, 15};
    for (int i = 0; i < moves.length; i++) {
      game1.moveToPosition(moves[i]);
      System.out.println(Integer.parseInt(game1.printPlayerLocation()[0]) + " " + position[i]);
      assertTrue(Integer.parseInt(game1.printPlayerLocation()[0]) == position[i]);
      set1.add(position[i]);
    }
    game1.shootArrow(1, "s");
    game1.shootArrow(1, "s");
    game1.moveToPosition("s");
    set1.add(Integer.parseInt(game1.printPlayerLocation()[0]));
    assertEquals(20, set1.size());
  }

  @Test
  public void availablePathsWithPlayerTest() {
    assertTrue(game.availablePathsWithPlayer().contains("NORTH-> Cave: 4\n" +
            "EAST-> Cave: 10\n" +
            "WEST-> Cave: 8\n"));
    game.moveToPosition("a");
    assertTrue(game.availablePathsWithPlayer().contains("EAST-> Cave: 9\n" +
            "WEST-> Cave: 7\n"));
    game.moveToPosition("d");
    assertTrue(game.availablePathsWithPlayer().contains("NORTH-> Cave: 4\n" +
            "EAST-> Cave: 10\n" +
            "WEST-> Cave: 8\n"));
    game.moveToPosition("w");
    assertTrue(game.availablePathsWithPlayer().contains("NORTH-> Cave: 24\n" +
            "SOUTH-> Cave: 9\n" +
            "WEST-> Cave: 3\n"));
    game.moveToPosition("s");
    assertTrue(game.availablePathsWithPlayer().contains("NORTH-> Cave: 4\n" +
            "EAST-> Cave: 10\n" +
            "WEST-> Cave: 8\n"));
  }

  @Test
  public void checkValidMoveTest() {
    game.moveToPosition("w");
    System.out.println(game.availablePathsWithPlayer());
    assertTrue(game.availablePathsWithPlayer().contains("NORTH-> Cave: 24\n" +
            "SOUTH-> Cave: 9\n" +
            "WEST-> Cave: 3\n"));
    assertTrue(game.checkValidMove("s"));
  }

  @Test(expected = Exception.class)
  public void invalidMoveTest() {
    assertFalse(game.availablePathsWithPlayer().contains("SOUTH"));
    game.moveToPosition("s");
  }

  @Test
  public void treasurePickUpTest() {
    GameDungeonModel game1 = new Game(5, 5, 8, true, 50, 5, r1);
    System.out.println(game1.printUpdatedDungeon());
    assertTrue(game1.availableTreasureAtCurrentNode().get(Treasure.RUBY) == 200
            && game1.availableTreasureAtCurrentNode().get(Treasure.DIAMONDS) == 300
            && game1.availableTreasureAtCurrentNode().get(Treasure.SAPPHIRE) == 100);
    assertTrue(game1.printPlayerStatus().get(Treasure.RUBY) == 0
            && game1.printPlayerStatus().get(Treasure.DIAMONDS) == 0
            && game1.printPlayerStatus().get(Treasure.SAPPHIRE) == 0);
    assertEquals(25, Integer.parseInt(game1.printPlayerLocation()[0]));
    game1.pickUpTreasure(Treasure.RUBY);
    game1.pickUpTreasure(Treasure.DIAMONDS);
    game1.pickUpTreasure(Treasure.SAPPHIRE);
    assertTrue(game1.printPlayerStatus().get(Treasure.RUBY) == 200
            && game1.printPlayerStatus().get(Treasure.DIAMONDS) == 300
            && game1.printPlayerStatus().get(Treasure.SAPPHIRE) == 100);
    assertTrue(game1.availableTreasureAtCurrentNode().get(Treasure.RUBY) == 0
            && game1.availableTreasureAtCurrentNode().get(Treasure.DIAMONDS) == 0
            && game1.availableTreasureAtCurrentNode().get(Treasure.SAPPHIRE) == 0);
    game1.shootArrow(1, "w");
    game1.shootArrow(1, "w");
    game1.moveToPosition("w");
    System.out.println(Integer.parseInt(game1.printPlayerLocation()[0]));
    assertTrue(game1.availableTreasureAtCurrentNode().get(Treasure.RUBY) == 100
            && game1.availableTreasureAtCurrentNode().get(Treasure.DIAMONDS) == 300
            && game1.availableTreasureAtCurrentNode().get(Treasure.SAPPHIRE) == 0);
    game1.pickUpTreasure(Treasure.RUBY);
    game1.pickUpTreasure(Treasure.DIAMONDS);
    game1.pickUpTreasure(Treasure.SAPPHIRE);
    assertTrue(game1.printPlayerStatus().get(Treasure.RUBY) == 300
            && game1.printPlayerStatus().get(Treasure.DIAMONDS) == 600
            && game1.printPlayerStatus().get(Treasure.SAPPHIRE) == 100);
    assertTrue(game1.availableTreasureAtCurrentNode().get(Treasure.RUBY) == 0
            && game1.availableTreasureAtCurrentNode().get(Treasure.DIAMONDS) == 0
            && game1.availableTreasureAtCurrentNode().get(Treasure.SAPPHIRE) == 0);
  }

  @Test
  public void winGameStartToEndTest() {
    GameDungeonModel game1 = new Game(4, 5, 6, false, 30, 2, r1);
    System.out.println(game1.printUpdatedDungeon());
    assertEquals(Integer.parseInt(game1.printPlayerLocation()[0]), game1.getStartAndEndCave()[0]);
    String[] moves = {"w", "w", "d", "d"};
    int[] position = {12, 7, 8, 9};
    for (int i = 0; i < moves.length; i++) {
      game1.moveToPosition(moves[i]);
      System.out.println(i + " " + game1.printPlayerLocation()[0]);
      assertTrue(Integer.parseInt(game1.printPlayerLocation()[0]) == position[i]);
    }
    assertTrue(game1.shootArrow(1, "d"));
    assertTrue(game1.shootArrow(1, "d"));
    game1.moveToPosition("d");
    assertEquals(Integer.parseInt(game1.printPlayerLocation()[0]), game1.getStartAndEndCave()[1]);
  }

  @Test
  public void winGameStartToEndNonWrappingTest() {
    GameDungeonModel game1 = new Game(4, 6, 5, false, 40, 2, r1);
    System.out.println(game1.printUpdatedDungeon());
    assertEquals(Integer.parseInt(game1.printPlayerLocation()[0]), game1.getStartAndEndCave()[0]);
    game1.moveToPosition("w");
    game1.pickUpArrow();
    game1.shootArrow(1, "d");
    game1.shootArrow(1, "d");
    String[] moves = {"d", "w", "w", "d", "d"};
    int[] position = {16, 10, 4, 5, 6};
    for (int i = 0; i < moves.length; i++) {
      game1.moveToPosition(moves[i]);
      System.out.println(Integer.parseInt(game1.printPlayerLocation()[0]) + " " + position[i]);
      assertTrue(Integer.parseInt(game1.printPlayerLocation()[0]) == position[i]);
    }
    game1.shootArrow(1, "s");
    game1.shootArrow(1, "s");
    game1.moveToPosition("s");
    assertEquals(Integer.parseInt(game1.printPlayerLocation()[0]), game1.getStartAndEndCave()[1]);
  }

  @Test
  public void playerStatus() {
    System.out.println(game.printUpdatedDungeon());
    StringBuilder sb = new StringBuilder();
    sb.append("Player is at location : " + game.printPlayerLocation()[0].toString());
    sb.append("\nIn the current position the available treasure and paths are :"
            + "\n" + Treasure.DIAMONDS + " "
            + game.availableTreasureAtCurrentNode().get(Treasure.DIAMONDS)
            + "\n" + Treasure.SAPPHIRE + " "
            + game.availableTreasureAtCurrentNode().get(Treasure.SAPPHIRE)
            + "\n" + Treasure.RUBY + " "
            + game.availableTreasureAtCurrentNode().get(Treasure.RUBY)
            + "\nPaths: " + game.availablePathsWithPlayer());
    assertEquals("Player is at location : 9\n" +
            "In the current position the available treasure and paths are :\n" +
            "DIAMONDS 0\n" +
            "SAPPHIRE 0\n" +
            "RUBY 0\n" +
            "Paths: NORTH-> Cave: 4\n" +
            "EAST-> Cave: 10\n" +
            "WEST-> Cave: 8\n", sb.toString());
    game.moveToPosition("d");
    sb = new StringBuilder();
    sb.append("Player is at location : " + game.printPlayerLocation()[0].toString());
    sb.append("\nIn the current position the available treasure and paths are :"
            + "\n" + Treasure.DIAMONDS + " "
            + game.availableTreasureAtCurrentNode().get(Treasure.DIAMONDS)
            + "\n" + Treasure.SAPPHIRE + " "
            + game.availableTreasureAtCurrentNode().get(Treasure.SAPPHIRE)
            + "\n" + Treasure.RUBY + " " + game.availableTreasureAtCurrentNode().get(Treasure.RUBY)
            + "\nPaths: " + game.availablePathsWithPlayer());
    assertEquals("Player is at location : 10\n" +
            "In the current position the available treasure and paths are :\n" +
            "DIAMONDS 100\n" +
            "SAPPHIRE 100\n" +
            "RUBY 100\n" +
            "Paths: EAST-> Cave: 6\n" +
            "WEST-> Cave: 9\n", sb.toString());
  }

  @Test
  public void testRandomGeneration() {
    RandomNumbersGenerator random2 = new RandomNumberGenerateTest(3);
    List<Integer> generatedIntegers = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      generatedIntegers.add(random2.getRandomNumber(1, 10));
    }
    assertEquals("[5, 1, 1, 2, 9]", generatedIntegers.toString());
  }

  @Test
  public void smellTest() {
    GameDungeonModel gameTest = new Game(4, 6, 5, false, 40, 2, r1);
    System.out.println(gameTest.printUpdatedDungeon());
    System.out.println(gameTest.availablePathsWithPlayer());
    assertEquals("NORTH-> Cave: 15\n", gameTest.availablePathsWithPlayer());
    assertFalse(gameTest.shootArrow(1, "w"));
    assertTrue(gameTest.getSmell().contains("BEWARE!! Smells little pungent in here!!"));

    gameTest.moveToPosition("w");
    assertTrue(gameTest.getSmell().contains("BEWARE!! Smells very pungent in here!! "));
    System.out.println(gameTest.availablePathsWithPlayer());
    gameTest.pickUpArrow();
    assertEquals("SOUTH-> Cave: 21\n" +
            "EAST-> Cave: 16\n" +
            "WEST-> Cave: 14\n", gameTest.availablePathsWithPlayer());
    assertFalse(gameTest.shootArrow(1, "a"));
    assertTrue(gameTest.shootArrow(1, "d"));
    assertFalse(gameTest.shootArrow(1, "s"));
    assertTrue(gameTest.getSmell().contains("BEWARE!! Smells very pungent in here!! "));
  }

  @Test
  public void smellTestWhen2MonstersOuterCaves() {
    GameDungeonModel gameTest = new Game(4, 5, 5, false, 40, 5, r1);
    System.out.println(gameTest.printUpdatedDungeon());
    assertTrue(gameTest.getSmell().contains(""));
    gameTest.pickUpArrow();
    gameTest.moveToPosition("s");
    gameTest.moveToPosition("a");
    gameTest.pickUpArrow();
    gameTest.moveToPosition("d");
    gameTest.moveToPosition("d");
    assertEquals("SOUTH-> Cave: 18\n" +
            "EAST-> Cave: 14\n" +
            "WEST-> Cave: 12\n", gameTest.availablePathsWithPlayer());
    assertFalse(gameTest.shootArrow(1, "D"));
    assertFalse(gameTest.shootArrow(1, "a"));
    assertFalse(gameTest.shootArrow(1, "s"));
    System.out.println(gameTest.availablePathsWithPlayer());
    assertTrue(gameTest.getSmell().contains("BEWARE!! Smells very pungent in here!! "));
    gameTest.moveToPosition("d");
    assertTrue(gameTest.shootArrow(1, "D"));
    assertTrue(gameTest.shootArrow(1, "s"));
  }

  @Test
  public void testArrowTravellingThroughTunnel() {
    GameDungeonModel gameTest = new Game(4, 4, 4, true, 40, 7, r1);
    System.out.println(gameTest.printUpdatedDungeon());
    gameTest.moveToPosition("w");
    //asserted cave is tunnel
    assertEquals("SOUTH-> Cave: 6\n" +
            "WEST-> Cave: 1\n", game.availablePathsWithPlayer());
    gameTest.moveToPosition("a");
    //asserted cave is tunnel
    assertEquals("SOUTH-> Cave: 5\n" +
            "EAST-> Cave: 2\n", game.availablePathsWithPlayer());
    //going back to the starting position
    gameTest.moveToPosition("d");
    gameTest.moveToPosition("s");
    //passed two tunnels changed direction twice and hit the monster.
    gameTest.shootArrow(3, "w");
  }

  @Test
  public void testArrowTravellingThroughCave() {
    GameDungeonModel gameTest = new Game(4, 4, 4, true, 40, 7, r1);
    System.out.println(gameTest.printUpdatedDungeon());
    assertEquals("NORTH-> Cave: 2\n" +
            "EAST-> Cave: 7\n" +
            "WEST-> Cave: 5\n", gameTest.availablePathsWithPlayer());
    assertFalse(gameTest.shootArrow(2, "s"));
    gameTest.moveToPosition("d");
    gameTest.moveToPosition("d");
    assertTrue(gameTest.shootArrow(3, "a"));
    gameTest.moveToPosition("d");
    assertTrue(gameTest.shootArrow(1, "w"));
    assertTrue(gameTest.shootArrow(1, "s"));
  }

  @Test
  public void testMonsterDies() {
    GameDungeonModel gameMonster = new Game(4, 4, 4, true, 40, 7, r1);
    System.out.println(gameMonster.printUpdatedDungeon());
    assertTrue(gameMonster.shootArrow(1, "a"));
    assertTrue(gameMonster.shootArrow(1, "a"));
    assertFalse(gameMonster.shootArrow(1, "a"));
  }

  @Test
  public void playerMovesToCaveWithMonster() {
    GameDungeonModel gameMonster = new Game(4, 4, 4, true, 40, 7, r1);
    System.out.println(gameMonster.printUpdatedDungeon());
    assertTrue(gameMonster.checkValidMove("a"));
    // although it was valid move, moveToPosition gave false
    assertFalse(gameMonster.moveToPosition("a"));
    //hit a monster, player was able to move.
    gameMonster.shootArrow(1, "a");
    assertTrue(gameMonster.moveToPosition("a"));
    assertFalse(gameMonster.checkGameRunning());
    assertTrue(gameMonster.moveToPosition("d"));
    assertTrue(gameMonster.moveToPosition("a"));
    assertTrue(gameMonster.checkGameRunning());
  }

  @Test
  public void playerMovesToCaveWithMonsterAtEnd() {
    GameDungeonModel gameMonster = new Game(4, 4, 4, true, 40, 3, r1);
    System.out.println(gameMonster.printUpdatedDungeon());
    gameMonster.moveToPosition("d");
    gameMonster.moveToPosition("d");
    gameMonster.moveToPosition("w");
    gameMonster.moveToPosition("a");
    assertTrue(gameMonster.shootArrow(1, "w"));
    assertTrue(gameMonster.moveToPosition("w"));
    assertFalse(gameMonster.checkGameRunning());
  }

}