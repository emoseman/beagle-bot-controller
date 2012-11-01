package org.emoseman.beagle.io.i2c;

import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.apache.log4j.Logger;
import org.emoseman.beagle.config.Config;
import org.emoseman.utils.I2CUtils;

import com.google.gson.internal.StringMap;

public abstract class I2C
{
  private static final Logger log = Logger.getLogger(I2C.class);

  private String _path;
  private int _address;
  private Path _sysPath;

  public I2C()
  {
    String identifier = this.getClass().getSimpleName().toLowerCase();
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

  public int write(final int register, final int value)
  {
    return I2CUtils.writeByteToI2CDevice(_sysPath.toString(), _address, register, value);
  }

  public int read(final int register)
  {
    return I2CUtils.readByteFromI2CDevice(_sysPath.toString(), _address, register);
  }
}
