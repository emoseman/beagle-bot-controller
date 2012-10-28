package org.emoseman.utils;

import java.util.Arrays;
import java.util.LinkedList;

public class StatsArray
{
  private float _sum = 0.0f;
  private float _max = 0.0f;
  private float _min = Float.MAX_VALUE;
  private float _median = 0.0f;

  private final int _itemCount;

  private final LinkedList<Float> _values = new LinkedList<>();

  public StatsArray(final int itemCount)
  {
    if (itemCount == 0)
      throw new RuntimeException("Item count cannot be zero.");

    _itemCount = itemCount;
  }

  public final void addValue(final float f)
  {
    if (_max < f)
      _max = new Float(f);

    if (_min > f)
      _min = new Float(f);

    _values.addFirst(f);
    _sum += f;

    if (_values.size() > _itemCount)
      _sum -= _values.removeLast();
  }

  public final float getAverage()
  {
    return _sum / _values.size();
  }

  public final float getMax()
  {
    _max = 0.0f;
    for (Float f : _values)
      if (f > _max)
        _max = f;

    return _max;
  }

  public final float getMedian()
  {
    Float[] tmpArray = new Float[_values.size()];
    for (int i = 0; i < _values.size(); i++)
      tmpArray[i] = new Float(_values.get(i));

    Arrays.sort(tmpArray);

    return tmpArray[tmpArray.length / 2];
  }

  public final float getMin()
  {
    _min = Float.MAX_VALUE;
    for (Float f : _values)
      if (_min > f)
        _min = f;

    return _min;
  }

  public final float getSum()
  {
    return _sum;
  }

  @Override
  public String toString()
  {
    _min = Float.MAX_VALUE;
    _max = 0.0f;

    Float[] tmpArray = new Float[_values.size()];
    for (int i = 0; i < _values.size(); i++)
      tmpArray[i] = new Float(_values.get(i));

    for (Float f : tmpArray)
    {
      if (f > _max)
        _max = f;

      if (f < _min)
        _min = f;
    }

    _median = tmpArray[tmpArray.length / 2];

    return String.format("Min: %02f Max: %02f Avg: %02f Median: %02f Sum: %02f Last: %02f", _min, _max, getAverage(),
      getMedian(), _sum, _values.getLast());
  }
}
