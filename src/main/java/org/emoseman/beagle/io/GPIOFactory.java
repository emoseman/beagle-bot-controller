package org.emoseman.beagle.io;

import java.io.IOException;

public class GPIOFactory
{
  public static GPIO getGPIO(final int i)
    throws IOException
  {
    GPIO g = new GPIO(i);
    g.activatePin();

    return g;
  }

  private GPIOFactory() { }
}
