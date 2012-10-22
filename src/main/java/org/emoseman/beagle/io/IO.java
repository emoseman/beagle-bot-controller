package org.emoseman.beagle.io;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.apache.log4j.Logger;
import org.emoseman.beagle.config.Config;

public abstract class IO
{
  private static final Logger log = Logger.getLogger(IO.class);

  protected final Config config = new Config();

  protected int readInteger(final String path)
    throws IOException
  {
    DataInputStream in = new DataInputStream(StreamCache.getInputStream(path));
    // todo you may have to open and re-open fds
    return in.readInt();
  }

  protected String readString(final String fileName)
    throws IOException
  {
    Path path = FileSystems.getDefault().getPath(fileName);
    return new String(Files.readAllBytes(path));
  }

  protected String readString2(final String path)
    throws IOException
  {
    InputStream in = StreamCache.getInputStream(path);

    int[] buffer = new int[1024];
    int read = 0;
    int idx = 0;

    while ((read = in.read()) != -1)
    {
      buffer[idx] = read;
      idx++;
    }

    for (int i = 0; i < idx; i++)
    {
      System.out.printf("[%d] '%c' (%04X) ", i, (char) buffer[i], buffer[i]);
    }

    return ""; // new String(buffer);
  }

  protected void writeString(final String path, final String value)
    throws IOException
  {
    Path sysPath = Paths.get(path);
    Files.write(sysPath, value.getBytes(), StandardOpenOption.WRITE);
  }

  protected void writeString2(final String path, final String value)
    throws IOException
  {
    BufferedOutputStream out = new BufferedOutputStream(StreamCache.getOutputStream(path));
    out.write(path.getBytes());
    StreamCache.closeOutputStream(path);
  }
}
