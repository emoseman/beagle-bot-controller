package org.emoseman.beagle.sensor;

import org.emoseman.beagle.io.AnalogFactory;
import org.emoseman.beagle.io.IO;

public class Accelerometer
  extends Sensor
{
  public static final Accelerometer build(final int pin)
  {
    return new Accelerometer(AnalogFactory.getAnalog(pin));
  }

  private Accelerometer(final IO io)
  {
    super(io);
  }

  private Accelerometer(final IO io, final int i)
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
