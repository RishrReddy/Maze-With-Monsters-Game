package controller;

import java.io.IOException;

/**
 * Represents a Controller for Dungeon Game: handle user moves by executing them using the model;
 * convey move outcomes to the user in some form.
 */
public interface DungeonController {

  /**
   * Execute a single game of dungeon game given a dungeon Model. When the game is over,
   * the playGame method ends.
   */
  void playGame() throws IOException;
}
