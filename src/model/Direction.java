package model;

/**
 * Direction is an enumeration which provides,
 * possible directions a player can travel in.
 * It has the following acceptable values
 * NORTH, SOUTH, EAST, WEST.
 */
public enum Direction {
  NORTH("W"), SOUTH("S"), EAST("D"), WEST("A");
  private final String value;


  /**
   * Associates the Direction with provided value.
   *
   * @param newValue value to be updated to the Direction
   */
  Direction(final String newValue) {
    value = newValue;
  }


  /**
   * Provides the Direction enum based on the move provided.
   *
   * @param move string direction to be moved
   * @return Direction based on the move provided.
   */
  public static Direction moveDirection(String move) {
    for (Direction direction : values()) {
      if (direction.value.equalsIgnoreCase(move)) {
        return direction;
      }
    }
    return null;
  }

  /**
   * Returns the value associated with a Direction.
   *
   * @return value associated with a Direction.
   */
  public String getValue() {
    return value;
  }
}
