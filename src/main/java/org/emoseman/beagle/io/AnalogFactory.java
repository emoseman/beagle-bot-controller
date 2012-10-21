package org.emoseman.beagle.io;

public class AnalogFactory
{
  public final static Analog getAnalog(final int i)
  {
    return new Analog(i);
  }

  private AnalogFactory() {}
}
