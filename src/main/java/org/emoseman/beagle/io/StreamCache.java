package org.emoseman.beagle.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class StreamCache
{
  private static class StreamCacheItem<T>
    implements Comparable<StreamCacheItem<T>>
  {
    private int _count = 0;
    private final T _stream;

    protected StreamCacheItem(final T o)
    {
      _stream = o;
    }

    @Override
    public int compareTo(final StreamCacheItem<T> o)
    {
      if (_count > o._count)
        return 1;

      if (_count == o._count)
        return 0;

      return -1;
    }

    @SuppressWarnings("unused")
    public boolean equals(final StreamCacheItem<T> o)
    {
      return _count == o._count;
    }

    public T get()
    {
      _count++;
      return _stream;
    }

    public T look()
    {
      return _stream;
    }
  }

  private static final HashMap<String, StreamCacheItem<OutputStream>> _outputCache =
    new HashMap<String, StreamCacheItem<OutputStream>>();

  private static final HashMap<String, StreamCacheItem<InputStream>> _inputCache =
    new HashMap<String, StreamCacheItem<InputStream>>();

  private static final int _streamLimit = 20;

  private static void addInputStream(final String path)
    throws IOException
  {
    if (_inputCache.size() > _streamLimit)
      removeInputStreamItem();

    Path sysPath = Paths.get(path);
    _inputCache.put(path, new StreamCacheItem<InputStream>(Files.newInputStream(sysPath, StandardOpenOption.READ)));
  }

  public static final void addOutputStream(final String path)
    throws IOException
  {
    if (_outputCache.size() > _streamLimit)
      removeOutputStreamItem();

    Path sysPath = Paths.get(path);
    _outputCache.put(path, new StreamCacheItem<OutputStream>(Files.newOutputStream(sysPath, StandardOpenOption.WRITE)));
  }

  public static final void closeAllInputStreams()
    throws IOException
  {
    for (String path : _inputCache.keySet())
    {
      _inputCache.get(path).look().close();
    }
  }

  public static final void closeAllOutputStreams()
    throws IOException
  {
    for (String path : _outputCache.keySet())
    {
      _outputCache.get(path).look().flush();
      _outputCache.get(path).look().close();
    }
  }

  public static final void closeInputStream(final String path)
    throws IOException
  {
    if (!_inputCache.containsKey(path))
      return;

    _inputCache.get(path).look().close();
  }

  public static final void closeOutputStream(final String path)
    throws IOException
  {
    if (!_outputCache.containsKey(path))
      return;

    _outputCache.get(path).look().flush();
    _outputCache.get(path).look().close();
  }

  public static final InputStream getInputStream(final String path)
    throws IOException
  {
    if (!_inputCache.containsKey(path))
      addInputStream(path);

    return _inputCache.get(path).get();
  }

  public static final OutputStream getOutputStream(final String path)
    throws IOException
  {
    if (!_outputCache.containsKey(path))
      addOutputStream(path);

    return _outputCache.get(path).get();
  }

  private static void removeInputStreamItem()
    throws IOException
  {
    if (_inputCache.size() == 0)
      return;

    List<StreamCacheItem<InputStream>> streams = new ArrayList<StreamCacheItem<InputStream>>(_inputCache.values());
    Collections.sort(streams);
    StreamCacheItem<InputStream> item = streams.get(streams.size() - 1);
    item._stream.close();
    streams.remove(streams.size() - 1);
  }

  private static void removeOutputStreamItem()
    throws IOException
  {
    if (_outputCache.size() == 0)
      return;

    List<StreamCacheItem<OutputStream>> streams = new ArrayList<StreamCacheItem<OutputStream>>(_outputCache.values());
    Collections.sort(streams);
    StreamCacheItem<OutputStream> item = streams.get(streams.size() - 1);
    item._stream.flush();
    item._stream.close();
    streams.remove(streams.size() - 1);
  }

  private StreamCache() { }

}
