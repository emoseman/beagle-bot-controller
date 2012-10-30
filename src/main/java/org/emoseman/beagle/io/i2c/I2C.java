package org.emoseman.beagle.io.i2c;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.emoseman.beagle.config.Config;

import com.google.gson.internal.StringMap;

public abstract class I2C
{
  private String _path;
  private byte _address;
  private Path _sysPath;

  public I2C()
  {
    String identifier = this.getClass().getName().toLowerCase();
    StringMap<String> tmp = Config.getI2C(identifier);
    if (tmp == null || tmp.size() == 0)
      throw new RuntimeException("Failed to find i2c configuration for " + identifier);

    _address = Byte.parseByte(tmp.get("address"));
    _path = tmp.get("path");

    _sysPath = FileSystems.getDefault().getPath(_path);
    if (_sysPath == null)
      throw new RuntimeException("Failed to find i2c configured path " + _path + " for configuration " + identifier);

    initialize();

    calibrate();
  }

  public abstract void initialize();

  public abstract void calibrate();

  public void write(final byte register, final byte value)
  {
    try (DataOutputStream out = new DataOutputStream(new FileOutputStream(_sysPath.toString()));)
    {
      out.writeByte(_address);
      out.writeByte(register);
      out.writeByte(value);
    }
    catch (IOException e)
    {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  public byte read(final byte register)
  {
    try (DataInputStream in = new DataInputStream(new FileInputStream(_sysPath.toString()));)
    {
      return in.readByte();
    }
    catch (IOException e)
    {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }
}
