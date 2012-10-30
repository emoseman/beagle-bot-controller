package org.emoseman.beagle.io.i2c;

public class HMC5883L
  extends I2C
{

  @Override
  public void initialize()
  {
    write((byte) 0x00, (byte) 0x58);
    write((byte) 0x01, (byte) 0x20);
    write((byte) 0x02, (byte) 0x00);
  }

  @Override
  public void calibrate()
  {
    // todo
  }

}
