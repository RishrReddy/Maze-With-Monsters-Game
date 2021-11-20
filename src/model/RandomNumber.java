package model;

import java.util.Random;

/**
 * Random Number Class is used to generate random numbers in the run of program.
 */
public class RandomNumber extends Random implements RandomNumbersGenerator {
  Random random = new Random();

  @Override
  public int getRandomNumber(int minNumber, int maxNumber) {
    return random.ints(minNumber, maxNumber + 1).findFirst().getAsInt();
  }
}
