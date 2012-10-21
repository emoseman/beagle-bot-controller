package org.emoseman.beagle.io;

import java.io.IOException;

import org.emoseman.beagle.config.Config;

public class Analog
  extends IO
{
  private final int _pin;
  private final String _path;

  public Analog(final int i)
  {
    _pin = i;
    _path = Config.getAnalogPath(_pin);
  }

  public final int read()
    throws IOException
  {
    String stringValue = readString(_path);
    return Integer.parseInt(stringValue);
  }
}
