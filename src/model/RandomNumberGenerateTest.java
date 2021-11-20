package model;

import java.util.Random;

/**
 * Random Number Class is used to generate random numbers in the testing.
 */
public class RandomNumberGenerateTest implements RandomNumbersGenerator {
  Random random;

  /**
   * Provides randon object based on the seed provided.
   *
   * @param seed value of the seed which then generates associated random numbers.
   */
  public RandomNumberGenerateTest(int seed) {
    random = new Random(seed);
  }

  @Override
  public int getRandomNumber(int minNumber, int maxNumber) {
    return random.ints(minNumber, maxNumber + 1).findFirst().getAsInt();

  }
}