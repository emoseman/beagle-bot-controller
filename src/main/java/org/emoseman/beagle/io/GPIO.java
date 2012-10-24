package org.emoseman.beagle.io;

import java.io.IOException;

import org.emoseman.beagle.config.Config;

public class GPIO
  extends IO
{
  private final int _pinNumber;
  private final String _path;
  private int _value;
  private PINDirection _pinDirection;

  private static final String basePath = "/sys/class/gpio";
  private static final String exportPath = basePath + "/export";
  private static final String unexportPath = basePath + "/unexport";

  public GPIO(final int no)
  {
    _pinNumber = no;
    _path = Config.getGPIOPath(no);
    try
    {
      activatePin();
    }
    catch (IOException e)
    {
      e.printStackTrace();
      throw new RuntimeException("Failed to activate GPIO pin " + _pinNumber + "-" + e.getMessage());
    }
  }

  private void activatePin()
    throws IOException
  {
    writeString(exportPath, new Integer(_pinNumber).toString());
  }

  private void deactivatePin()
    throws IOException
  {
    writeString(unexportPath, String.valueOf(_pinNumber));
  }

  private void updatePinDirection()
    throws IOException
  {
    writeString(_path, _pinDirection.toString());
  }

  private void updatePinValue()
    throws IOException
  {
    writeString(_path, String.valueOf(_value));
  }

}
