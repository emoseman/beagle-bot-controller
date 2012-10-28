package org.emoseman.beagle.sensor;

import org.emoseman.beagle.io.IO;
import org.emoseman.utils.StatsArray;

public abstract class Sensor
  extends StatsArray
{
  protected IO _io;
  protected int _statsArrayCount = 20;

  public Sensor(final IO io)
  {
    super(20);
    _io = io;
  }

  public Sensor(final IO io, final int count)
  {
    super(count);
    _statsArrayCount = count;
    _io = io;
  }

  public abstract void calibrate();

  public float getValue()
  {
    return getAverage();
  }

  public abstract void reset();

}
