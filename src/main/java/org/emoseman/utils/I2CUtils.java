package org.emoseman.utils;

public class I2CUtils
{
  static
  {
    try
    {
      // System.out.println("java.library.path: " + System.getProperty("java.library.path"));
      System.loadLibrary("i2c_native");
    }
    catch (UnsatisfiedLinkError e)
    {
      System.err.println("Failed to load library: " + e.getMessage());
      System.exit(1);
    }
  }

  public static void main(final String[] args)
    throws Exception
  {
    int result = writeByteToI2CDevice("/dev/i2c-3", 0x1E, 0x00, 0x58);
    System.out.println("write test: " + String.format("%02X", result));

    result = writeByteToI2CDevice("/dev/i2c-3", 0x1E, 0x01, 0x20);
    System.out.println("write test: " + String.format("%02X", result));

    result = writeByteToI2CDevice("/dev/i2c-3", 0x1E, 0x02, 0x00);
    System.out.println("write test: " + String.format("%02X", result));

    result = readByteFromI2CDevice("/dev/i2c-3", 0x1E, 0x00);
    System.out.println("read test: " + String.format("%02X", result));

    result = readByteFromI2CDevice("/dev/i2c-3", 0x1E, 0x01);
    System.out.println("read test: " + String.format("%02X", result));

    result = readByteFromI2CDevice("/dev/i2c-3", 0x1E, 0x02);
    System.out.println("read test: " + String.format("%02X", result));
  }

  public static native byte readByteFromI2CDevice(String path, int address, int register_address);

  public static native byte writeByteToI2CDevice(String path, int address, int register_address, int data);
}


// vim:ts=2:sw=2:expandtab
