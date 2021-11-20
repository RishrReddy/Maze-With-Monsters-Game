package test;

import org.junit.Test;

import model.Cave;
import model.Direction;
import model.DungeonCave;
import model.Monster;
import model.Otyugh;
import model.Treasure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * CaveTestClass class represents all the test cases related ta a cave object and
 * other methods implemented from the Cave class.
 */
public class CaveTestClass {
  DungeonCave cave = new Cave(1, 4, 5);

  @Test(expected = IllegalArgumentException.class)
  public void invalidCave() {
    new Cave(-1, -1, 3);
  }

  @Test
  public void getRowNoTest() {
    assertEquals(4, cave.getRowNo());
  }

  @Test
  public void getColumnNoTest() {
    assertEquals(5, cave.getColumnNo());
  }

  @Test
  public void tunnelTest() {
    assertFalse(cave.isTunnel());
    cave.setTunnel(true);
    assertTrue(cave.isTunnel());
  }

  @Test
  public void treasureTest() {
    assertTrue(cave.getTreasure().get(Treasure.RUBY) == 0
            && cave.getTreasure().get(Treasure.DIAMONDS) == 0
            && cave.getTreasure().get(Treasure.SAPPHIRE) == 0);
    cave.setCaveTreasure(Treasure.RUBY, 100);
    cave.setCaveTreasure(Treasure.DIAMONDS, 200);
    assertTrue(cave.getTreasure().get(Treasure.RUBY) == 100
            && cave.getTreasure().get(Treasure.DIAMONDS) == 200
            && cave.getTreasure().get(Treasure.SAPPHIRE) == 0);
  }

  @Test
  public void directionTest() {
    assertTrue(cave.getDirection().get(Direction.EAST) == 0
            && cave.getDirection().get(Direction.WEST) == 0
            && cave.getDirection().get(Direction.NORTH) == 0
            && cave.getDirection().get(Direction.SOUTH) == 0);
    cave.setDirection(Direction.NORTH, 4);
    cave.setDirection(Direction.SOUTH, 8);
    assertTrue(cave.getDirection().get(Direction.EAST) == 0
            && cave.getDirection().get(Direction.WEST) == 0
            && cave.getDirection().get(Direction.NORTH) == 4
            && cave.getDirection().get(Direction.SOUTH) == 8);
  }

  @Test
  public void addArrowToCave() {
    assertFalse(cave.getIsArrowAvailable());
    cave.setArrowInCave(true);
    assertTrue(cave.getIsArrowAvailable());
    cave.setArrowInCave(false);
    assertFalse(cave.getIsArrowAvailable());
    cave.setTunnel(true);
    cave.setArrowInCave(true);
    assertTrue(cave.isTunnel());
    assertTrue(cave.getIsArrowAvailable());
  }

  @Test
  public void addMonsterToCave() {
    assertFalse(cave.isTunnel());
    Monster m = new Otyugh(1);
    cave.setMonsterInCave(m);
    assertEquals("\n OtyughId: 1 Health: 100", cave.getMonster().toString());
  }

  @Test
  public void addMonsterToTunnel() {
    DungeonCave cave1 = new Cave(1, 4, 5);
    assertFalse(cave1.isTunnel());
    cave1.setTunnel(true);
    assertTrue(cave1.isTunnel());
    Monster m = new Otyugh(1);
    cave1.setMonsterInCave(m);
    assertEquals(null, cave1.getMonster());
  }

  @Test
  public void checkMonsterHealth() {
    Monster m = new Otyugh(1);
    cave.setMonsterInCave(m);
    assertEquals("\n OtyughId: 1 Health: 100", cave.getMonster().toString());
    cave.updateMonsterHealth();
    assertEquals("\n OtyughId: 1 Health: 50", cave.getMonster().toString());
    cave.updateMonsterHealth();
    assertEquals("\n OtyughId: 1 Health: 0", cave.getMonster().toString());
  }

}
