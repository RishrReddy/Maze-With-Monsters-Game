package test;

import org.junit.Test;

import java.util.HashMap;

import model.Dungeon;
import model.DungeonInterface;
import model.GamePlayer;
import model.Player;
import model.RandomNumberGenerateTest;
import model.RandomNumbersGenerator;
import model.Treasure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * PlayerTestClass class represents all the test cases related ta a player object and
 * other methods implemented from the GamePlayer class.
 */
public class PlayerTestClass {

  RandomNumbersGenerator r1 = new RandomNumberGenerateTest(3);
  DungeonInterface playerDun = new Dungeon(4, 5, 4, false, 30, 5, r1);
  GamePlayer player = new Player("player", 8);

  @Test(expected = IllegalArgumentException.class)
  public void invalidPlayer() {
    new Player("", 7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidPlayerNameNull() {
    new Player(null, 7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidPlayerId() {
    new Player("", -7);
  }

  @Test
  public void getPlayerLocation() {
    assertEquals(8, player.getPlayerLocation());
  }

  @Test
  public void setPlayerLocTest() {
    player.setPlayerLocation(4);
    assertEquals(4, player.getPlayerLocation());
  }

  @Test
  public void playerTostring() {
    assertEquals("Player Name: player\n" +
            "Location :  Cave Number : 8\n" +
            "Treasure:  Diamond: 0 Sapphire: 0 Ruby: 0", player.toString());
  }

  @Test
  public void getSetTreasure() {
    assertTrue(player.getTreasureWithPlayer().get(Treasure.RUBY) == 0
            && player.getTreasureWithPlayer().get(Treasure.RUBY) == 0
            && player.getTreasureWithPlayer().get(Treasure.RUBY) == 0);
    HashMap<Treasure, Integer> newMap = new HashMap<>();
    newMap.put(Treasure.SAPPHIRE, 100);
    newMap.put(Treasure.RUBY, 300);
    newMap.put(Treasure.DIAMONDS, 200);
    player.setTreasureCount(Treasure.SAPPHIRE, newMap);
    player.setTreasureCount(Treasure.RUBY, newMap);
    player.setTreasureCount(Treasure.DIAMONDS, newMap);
    assertTrue(player.getTreasureWithPlayer().get(Treasure.RUBY) == 300
            && player.getTreasureWithPlayer().get(Treasure.DIAMONDS) == 200
            && player.getTreasureWithPlayer().get(Treasure.SAPPHIRE) == 100);
  }

  @Test
  public void testArrowCount() {
    assertTrue(player.getArrowCount() == 3);
    player.setArrowCount(true);
    player.setArrowCount(true);
    assertTrue(player.getArrowCount() == 5);
    player.setArrowCount(false);
    assertTrue(player.getArrowCount() == 4);
  }
}
