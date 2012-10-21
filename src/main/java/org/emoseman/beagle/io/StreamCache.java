package org.emoseman.beagle.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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

  private static final HashMap<String, StreamCacheItem<FileOutputStream>> _outputCache =
    new HashMap<String, StreamCacheItem<FileOutputStream>>();

  private static final HashMap<String, StreamCacheItem<FileInputStream>> _inputCache =
    new HashMap<String, StreamCacheItem<FileInputStream>>();

  private static final int _streamLimit = 20;
  
  private static void addFileInputStream(final String path)
    throws IOException
  {
    if (_inputCache.size() > _streamLimit)
      removeFileInputStreamItem();

    _inputCache.put(path, new StreamCacheItem<FileInputStream>(new FileInputStream(path)));
  }

  public static final void addFileOutputStream(final String path)
    throws IOException
  {
    if (_outputCache.size() > _streamLimit)
      removeFileOutputStreamItem();

    _outputCache.put(path, new StreamCacheItem<FileOutputStream>(new FileOutputStream(path)));
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

  public static final FileInputStream getInputStream(final String path)
    throws IOException
  {
    if (!_inputCache.containsKey(path))
      addFileInputStream(path);

    return _inputCache.get(path).get();
  }

  public static final FileOutputStream getOutputStream(final String path)
    throws IOException
  {
    if (!_outputCache.containsKey(path))
      addFileOutputStream(path);

    return _outputCache.get(path).get();
  }

  private static void removeFileInputStreamItem()
    throws IOException
  {
    if (_inputCache.size() == 0)
      return;

    List<StreamCacheItem<FileInputStream>> streams =
      new ArrayList<StreamCacheItem<FileInputStream>>(_inputCache.values());
    Collections.sort(streams);
    StreamCacheItem<FileInputStream> item = streams.get(streams.size() - 1);
    item._stream.close();
    streams.remove(streams.size() - 1);
  }

  private static void removeFileOutputStreamItem()
    throws IOException
  {
    if (_outputCache.size() == 0)
      return;

    List<StreamCacheItem<FileOutputStream>> streams =
      new ArrayList<StreamCacheItem<FileOutputStream>>(_outputCache.values());
    Collections.sort(streams);
    StreamCacheItem<FileOutputStream> item = streams.get(streams.size() - 1);
    item._stream.flush();
    item._stream.close();
    streams.remove(streams.size() - 1);
  }

  private StreamCache() { }

}
