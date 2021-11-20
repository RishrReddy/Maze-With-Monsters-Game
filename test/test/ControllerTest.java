package test;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import controller.ControllerImpl;
import controller.DungeonController;
import model.GameDungeonModel;

public class ControllerTest {

  @Test(expected = IOException.class)
  public void testFailingAppendable() throws IOException {
    // Testing when something goes wrong with the Appendable
    // Here we are passing it a mock of an Appendable that always fails
    Reader input = new StringReader("M W");
    Appendable gameLog = new FailingAppendable();
    Appendable out = new StringBuilder();
    GameDungeonModel model = new MockGameModel(out);
    DungeonController c = new ControllerImpl(model, input, gameLog);
    c.playGame();
    System.out.println(gameLog);
    System.out.println(out.toString());
  }

  @Test
  public void pickUpTreasureArrowTest() throws IOException {
    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder();
    Reader in = new StringReader("p yes yes yes yes yes q");
    GameDungeonModel model = new MockGameModel(log);
    DungeonController controller = new ControllerImpl(model, in, out);
    controller.playGame();
    System.out.println(out.toString());
    Assert.assertEquals("GameDungeonModel.printUpdatedDungeon() method called \n" +
            "GameDungeonModel.printPlayerLocation() method called \n" +
            "GameDungeonModel.printPlayerLocation() method called \n" +
            "GameDungeonModel.printPlayerStatus() method called \n" +
            "GameDungeonModel.printArrowsWithPlayer() method called \n" +
            "GameDungeonModel.checkGameRunning() method called \n" +
            "GameDungeonModel.getSmell() method called \n" +
            "GameDungeonModel.availablePathsWithPlayer() method called \n" +
            "GameDungeonModel.availableTreasureAtCurrentNode() method called \n" +
            "GameDungeonModel.isTreasureAvailableAtCurrentNode() method called \n" +
            "GameDungeonModel.isTreasureAvailableAtCurrentNode() method called \n" +
            "GameDungeonModel.pickUpTreasure() method called \n" +
            "GameDungeonModel.pickUpTreasure() method called \n" +
            "GameDungeonModel.pickUpTreasure() method called \n" +
            "GameDungeonModel.isArrowAvailableAtCurrentNode() method called \n" +
            "GameDungeonModel.pickUpArrow() method called \n" +
            "GameDungeonModel.printPlayerLocation() method called \n" +
            "GameDungeonModel.printPlayerLocation() method called \n" +
            "GameDungeonModel.printPlayerStatus() method called \n" +
            "GameDungeonModel.printArrowsWithPlayer() method called \n" +
            "GameDungeonModel.checkGameRunning() method called \n" +
            "GameDungeonModel.getSmell() method called \n" +
            "GameDungeonModel.availablePathsWithPlayer() method called \n" +
            "GameDungeonModel.availableTreasureAtCurrentNode() method called \n" +
            "GameDungeonModel.checkGameRunning() method called \n", log.toString());
  }

  @Test
  public void movePlayerToCaveTest() throws IOException {
    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder();
    Reader in = new StringReader("m w q");
    GameDungeonModel model = new MockGameModel(log);
    DungeonController controller = new ControllerImpl(model, in, out);
    controller.playGame();
    System.out.println(out.toString());
    Assert.assertEquals("GameDungeonModel.printUpdatedDungeon() method called \n" +
            "GameDungeonModel.printPlayerLocation() method called \n" +
            "GameDungeonModel.printPlayerLocation() method called \n" +
            "GameDungeonModel.printPlayerStatus() method called \n" +
            "GameDungeonModel.printArrowsWithPlayer() method called \n" +
            "GameDungeonModel.checkGameRunning() method called \n" +
            "GameDungeonModel.getSmell() method called \n" +
            "GameDungeonModel.availablePathsWithPlayer() method called \n" +
            "GameDungeonModel.availableTreasureAtCurrentNode() method called \n" +
            "GameDungeonModel.availablePathsWithPlayer() method called \n" +
            "GameDungeonModel.checkValidMove() method called \n" +
            "GameDungeonModel.moveToPosition() method called \n" +
            "GameDungeonModel.printPlayerLocation() method called \n" +
            "GameDungeonModel.printPlayerLocation() method called \n" +
            "GameDungeonModel.printPlayerStatus() method called \n" +
            "GameDungeonModel.printArrowsWithPlayer() method called \n" +
            "GameDungeonModel.checkGameRunning() method called \n" +
            "GameDungeonModel.getSmell() method called \n" +
            "GameDungeonModel.availablePathsWithPlayer() method called \n" +
            "GameDungeonModel.availableTreasureAtCurrentNode() method called \n" +
            "GameDungeonModel.checkGameRunning() method called", log.toString());
  }

  @Test
  public void movePlayerToInvalidCaveTest() throws IOException {
    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder();
    Reader in = new StringReader("m s q");
    GameDungeonModel model = new MockGameModel(log);
    DungeonController controller = new ControllerImpl(model, in, out);
    controller.playGame();
    System.out.println(out.toString());
    Assert.assertTrue(out.toString().contains("Chomp, chomp, chomp, you are eaten "
            + "by an Otyugh!! Better luck next time......"));
    Assert.assertEquals("GameDungeonModel.printUpdatedDungeon() method called \n" +
            "GameDungeonModel.printPlayerLocation() method called \n" +
            "GameDungeonModel.printPlayerLocation() method called \n" +
            "GameDungeonModel.printPlayerStatus() method called \n" +
            "GameDungeonModel.printArrowsWithPlayer() method called \n" +
            "GameDungeonModel.checkGameRunning() method called \n" +
            "GameDungeonModel.getSmell() method called \n" +
            "GameDungeonModel.availablePathsWithPlayer() method called \n" +
            "GameDungeonModel.availableTreasureAtCurrentNode() method called \n" +
            "GameDungeonModel.availablePathsWithPlayer() method called \n" +
            "GameDungeonModel.checkValidMove() method called \n" +
            "GameDungeonModel.moveToPosition() method called \n" +
            "GameDungeonModel.checkGameRunning() method called \n", log.toString());
  }

  @Test
  public void shootArrowCaveTest() throws IOException {
    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder();
    Reader in = new StringReader("s 1 w yes 1 wm no q");
    GameDungeonModel model = new MockGameModel(log);
    DungeonController controller = new ControllerImpl(model, in, out);
    controller.playGame();
    System.out.println(out.toString());
    Assert.assertTrue(out.toString().contains("In the current position the"
            + "available paths with the player are : \n" +
            "In the current position the available treasure is : \n" +
            "{SAPPHIRE=200, RUBY=300, DIAMONDS=100}\n" +
            "Please Enter 'M' to move to new cave,'P' to pick up treasure/arrows,"
            + " 'S' to shoot an arrow\n" +
            "Enter a number : At what distance do you want to shoot? 1-5 \n" +
            "Enter the direction you want to shoot in --->  W: North,"
            + "  S: South, A: West, D: EastYou hear a great howl in the distance\n" +
            "Type yes to shoot again : \n" +
            "Enter a number : At what distance do you want to shoot? 1-5 \n" +
            "Enter the direction you want to shoot in --->  W: North,  S: South,"
            + " A: West, D: EastYou hear a great howl in the distance\n" +
            "Type yes to shoot again : "));
    Assert.assertEquals("GameDungeonModel.printUpdatedDungeon() method called \n" +
            "GameDungeonModel.printPlayerLocation() method called \n" +
            "GameDungeonModel.printPlayerLocation() method called \n" +
            "GameDungeonModel.printPlayerStatus() method called \n" +
            "GameDungeonModel.printArrowsWithPlayer() method called \n" +
            "GameDungeonModel.checkGameRunning() method called \n" +
            "GameDungeonModel.getSmell() method called \n" +
            "GameDungeonModel.availablePathsWithPlayer() method called \n" +
            "GameDungeonModel.availableTreasureAtCurrentNode() method called \n" +
            "GameDungeonModel.isPlayerHavingArrow() method called \n" +
            "GameDungeonModel.shootArrow() method called \n" +
            "GameDungeonModel.isPlayerHavingArrow() method called \n" +
            "GameDungeonModel.shootArrow() method called \n" +
            "GameDungeonModel.printPlayerLocation() method called \n" +
            "GameDungeonModel.printPlayerLocation() method called \n" +
            "GameDungeonModel.printPlayerStatus() method called \n" +
            "GameDungeonModel.printArrowsWithPlayer() method called \n" +
            "GameDungeonModel.checkGameRunning() method called \n" +
            "GameDungeonModel.getSmell() method called \n" +
            "GameDungeonModel.availablePathsWithPlayer() method called \n" +
            "GameDungeonModel.availableTreasureAtCurrentNode() method called \n" +
            "GameDungeonModel.checkGameRunning() method called \n", log.toString());
  }
}