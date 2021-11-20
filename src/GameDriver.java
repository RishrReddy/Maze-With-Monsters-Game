import controller.ControllerImpl;
import controller.DungeonController;
import model.Game;
import model.GameDungeonModel;
import model.RandomNumber;
import model.RandomNumbersGenerator;

import java.io.IOException;
import java.io.InputStreamReader;


/**
 * GameDriver class executes Dungeon Game with the
 * default configurations provided by users or clients as command line arguments.
 * It's important to maintain the order of the arguments' no_of_rows, no_of_columns
 * degree_of_interconnectivity isWrapping? treasure_Percentage .
 */
public class GameDriver {
  /**
   * Main method to run the program with sample data.
   *
   * @param args arguments for executing main method.
   */
  public static void main(String[] args) {
    RandomNumbersGenerator random = new RandomNumber();
    GameDungeonModel game = new Game(Integer.parseInt(args[0]), Integer.parseInt(args[1]),
            Integer.parseInt(args[2]), Boolean.parseBoolean(args[3]),
            Integer.parseInt(args[4]), Integer.parseInt(args[5]), random);
    DungeonController controller = new ControllerImpl(game,
            new InputStreamReader(System.in), System.out);
    try {
      controller.playGame();
    } catch (IOException e) {
      System.out.println("An error occurred with output");
    }
  }
}
