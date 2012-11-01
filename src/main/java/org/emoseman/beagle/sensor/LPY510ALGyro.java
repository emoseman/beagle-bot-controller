package org.emoseman.beagle.sensor;

import org.emoseman.beagle.io.AnalogFactory;
import org.emoseman.beagle.io.IO;

public class LPY510ALGyro
  extends Sensor
{
  public static final LPY510ALGyro build(final int pin)
  {
    return new LPY510ALGyro(AnalogFactory.getAnalog(pin));
  }

  private LPY510ALGyro(final IO io)
  {
    super(io);
  }

  private LPY510ALGyro(final IO io, final int i)
  {
    super(io, i);
  }

  @Override
  public void calibrate()
  {
    return;
  }

  @Override
  public void reset()
  {
    _values.clear();
  }

}
