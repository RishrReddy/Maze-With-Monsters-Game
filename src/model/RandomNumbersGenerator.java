package model;

/**
 * Interface represents Random number generation.
 */
public interface RandomNumbersGenerator {
  /**
   * generates a random number.
   *
   * @param minNumber minimum number
   * @param maxNumber maximum number
   * @return Integer random number
   */
  int getRandomNumber(int minNumber, int maxNumber);
}


