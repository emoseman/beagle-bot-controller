package org.emoseman.utils;

import java.security.SecureRandom;

import org.junit.Test;

public class StatsArrayTest
{
  @Test
  public void test()
  {
    SecureRandom random = new SecureRandom();
    float tmp, sum;
    tmp = sum = 0.0f;

    StatsArray sArray = new StatsArray(10);
    for (int i = 0; i < 10000; i++)
    {
      tmp = random.nextFloat() * 1000;
      sArray.addValue(tmp);
      sum += tmp;
    }

    System.out.println("avg: " + (sum / 100));
    System.out.println("total: " + sum);

    System.out.println("list: " + sArray.toString());
  }
}
