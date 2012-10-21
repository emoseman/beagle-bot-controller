package org.emoseman.beagle.io;

public class LEDFactory
{
  public static final LED getLED(final int i)
  {
    return new LED(i);
  }

  private LEDFactory()
  {}
}
