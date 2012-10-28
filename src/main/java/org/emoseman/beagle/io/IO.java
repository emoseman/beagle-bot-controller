package org.emoseman.beagle.io;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.apache.log4j.Logger;
import org.emoseman.beagle.config.Config;

public abstract class IO
{
  @SuppressWarnings("unused")
  private static final Logger log = Logger.getLogger(IO.class);

  protected final Config config = new Config();

  protected String readString(final String fileName)
    throws IOException
  {
    Path path = FileSystems.getDefault().getPath(fileName);
    List<String> lines = Files.readAllLines(path, Charset.forName("UTF-8"));
    return lines.get(0).replace('\0', ' ').trim();
  }

  protected Integer readInteger(final String fileName)
    throws IOException
  {
    Path path = FileSystems.getDefault().getPath(fileName);
    List<String> lines = Files.readAllLines(path, Charset.forName("UTF-8"));
    return Integer.parseInt(lines.get(0).replace('\0', ' ').trim());
  }

  protected void writeString(final String path, final String value)
    throws IOException
  {
    Path sysPath = Paths.get(path);
    Files.write(sysPath, value.getBytes(), StandardOpenOption.WRITE);
  }

  protected void writeInteger(final String path, final Integer value)
    throws IOException
  {
    Path sysPath = Paths.get(path);
    Files.write(sysPath, String.valueOf(value).getBytes(), StandardOpenOption.WRITE);
  }
}
