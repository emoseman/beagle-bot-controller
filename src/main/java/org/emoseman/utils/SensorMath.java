package org.emoseman.utils;

public class SensorMath
{
  private SensorMath() { }

  public static float calcXAngle(float x, float y, float z)
  {
    return Math.arctan(x / Math.sqrt(Math.pow(y, 2) + Math.pow(z,2)));
  }

  public static float calcYAngle(float x, float y, float z)
  {
    return Math.arctan(y / Math.sqrt(Math.pow(x, 2) + Math.pow(z,2)));
  }
}