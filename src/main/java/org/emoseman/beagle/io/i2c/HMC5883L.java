package org.emoseman.beagle.io.i2c;

public class HMC5883L
  extends I2C
{
  public HMC5883L()
  {
    super();
  }

  @Override
  public void initialize()
  {
    write(0x00, 0x58);
    write(0x01, 0x20);
    write(0x02, 0x00);
    int a, b, c;
    a = b = c = 0;
    c = Integer.rotateLeft(a, 8);
    float heading = (float) Math.atan2(a, b);

  }

  @Override
  public void calibrate()
  {
    // todo
  }

}
