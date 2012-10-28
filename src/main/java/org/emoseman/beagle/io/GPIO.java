package org.emoseman.beagle.io;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

import org.emoseman.beagle.config.Config;

public class GPIO
  extends IO
{
  private final int _pinNumber;
  private int _value = 0;
  private PINDirection _pinDirection;
  private int _edge = 0;
  private int _activeLow = 0;
  private int _uEvent = 0;

  private final Path _path;

  private final String _basePath;
  private final String _exportPath;
  private final String _unexportPath;

  private final String _directionPath;
  private final String _valuePath;
  private final String _edgePath;
  private final String _activeLowPath;
  private final String _uEventPath;

  public GPIO(final int no)
  {
    _pinNumber = no;
    _basePath = Config.getGPIOPath("base_path") + "/gpio" + no;
    _exportPath = _basePath + "/export";
    _unexportPath = _basePath + "/unexport";
    _directionPath = _basePath + "/direction";
    _valuePath = _basePath + "/value";
    _edgePath = _basePath + "/edge";
    _activeLowPath = _basePath + "/active_low";
    _uEventPath = _basePath + "/uevent";

    _path = FileSystems.getDefault().getPath(_basePath);

    try
    {
      activatePin();
      readAllValues();
    }
    catch (IOException e)
    {
      e.printStackTrace();
      throw new RuntimeException("Failed to activate GPIO pin " + _pinNumber + "-" + e.getMessage());
    }
  }

  public void activatePin()
    throws IOException
  {
    if (checkGPIODirectory())
      return;

    writeString(_exportPath, new Integer(_pinNumber).toString());
  }

  public void deactivatePin()
    throws IOException
  {
    if (!checkGPIODirectory())
      return;

    writeString(_unexportPath, String.valueOf(_pinNumber));
  }

  public final PINDirection getPinDirection()
  {
    return _pinDirection;
  }

  public final int getPinNumber()
  {
    return _pinNumber;
  }

  public final int getValue()
  {
    return _value;
  }

  public final int getEdge()
  {
    return _edge;
  }

  public final int getActiveLow()
  {
    return _activeLow;
  }

  public final int uEvent()
  {
    return _uEvent;
  }

  public final void setPinDirection(final PINDirection _pinDirection)
    throws IOException
  {
    this._pinDirection = _pinDirection;
    writePinDirection();
  }

  public final void setValueHigh()
    throws IOException
  {
    this._value = 1;
    writePinValue();
  }

  public final void setValueLow()
    throws IOException
  {
    this._value = 0;
    writePinValue();
  }

  private boolean checkGPIODirectory()
  {
    return Files.isDirectory(_path, LinkOption.NOFOLLOW_LINKS);
  }

  private void readAllValues()
    throws NumberFormatException, IOException
  {
    if (!checkGPIODirectory())
      return;

    readPinValue();
    readPinDirection();
    readActiveLow();
    readEdge();
    readUEvent();
  }

  private void readPinValue()
    throws NumberFormatException, IOException
  {
    _value = Integer.parseInt(readString(_valuePath));
  }

  private void readPinDirection()
    throws IOException
  {
    _pinDirection = PINDirection.fromString(readString(_directionPath));
  }

  private void readActiveLow()
    throws IOException
  {
    _activeLow = readInteger(_activeLowPath);
  }

  private void readEdge()
    throws IOException
  {
    _edge = readInteger(_edgePath);
  }

  private void readUEvent()
    throws IOException
  {
    _uEvent = readInteger(_uEventPath);
  }

  private void writePinDirection()
    throws IOException
  {
    writeString(_directionPath, _pinDirection.toString());
  }

  private void writePinValue()
    throws IOException
  {
    writeString(_valuePath, String.valueOf(_value));
  }

  private void writeActiveLow()
  {

  }

  private void writeEdge()
  {

  }
}
