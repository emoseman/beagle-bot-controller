package org.emoseman.beagle.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.emoseman.beagle.config.Config;

public abstract class IO
{
  protected final Config config = new Config();

  protected final HashMap<String, FileOutputStream> _outStream = new HashMap<String, FileOutputStream>();

  protected String readString(final String path) throws IOException
  {
    BufferedInputStream in = new BufferedInputStream(StreamCache.getInputStream(path));
    StringBuilder sb = new StringBuilder();

    byte[] buffer = new byte[128];

    int available = in.available();
    int readSize = 0;
    int offset = 0;
    while (available > 0) {
      if (available >= 128)
        readSize = 128;
      else
        readSize = available;

      offset += in.read(buffer, offset, readSize);
    }
    return sb.toString();
  }

  protected void writeString(final String path, final String value)
    throws IOException
  {
    BufferedOutputStream out = new BufferedOutputStream(StreamCache.getOutputStream(path));
    out.write(path.getBytes());
    StreamCache.closeOutputStream(path);
  }
}
