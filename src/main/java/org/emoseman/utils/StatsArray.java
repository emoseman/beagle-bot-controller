package org.emoseman.utils;

import java.util.Arrays;
import java.util.LinkedList;

public class StatsArray
{
  protected float _sum = 0.0f;
  protected float _currentMax = 0.0f;
  protected float _currentMin = Float.MAX_VALUE;
  protected float _absoluteMax = 0.0f;
  protected float _absoluteMin = Float.MAX_VALUE;
  protected float _median = 0.0f;

  protected final int _itemCount;

  protected final LinkedList<Float> _values = new LinkedList<>();

  public StatsArray(final int itemCount)
  {
    if (itemCount == 0)
      throw new RuntimeException("Item count cannot be zero.");

    _itemCount = itemCount;
  }

  public final void addValue(final float f)
  {
    if (_absoluteMax < f)
      _absoluteMax = new Float(f);

    if (_absoluteMin > f)
      _absoluteMin = new Float(f);

    _values.addFirst(f);
    _sum += f;

    if (_values.size() > _itemCount)
      _sum -= _values.removeLast();
  }

  public final float getAverage()
  {
    return _sum / _values.size();
  }

  public final float getCurrentMax()
  {
    _currentMax = 0.0f;
    for (Float f : _values)
      if (f > _currentMax)
        _currentMax = f;

    return _currentMax;
  }

  public final float getMedian()
  {
    Float[] tmpArray = new Float[_values.size()];
    for (int i = 0; i < _values.size(); i++)
      tmpArray[i] = new Float(_values.get(i));

    Arrays.sort(tmpArray);

    return tmpArray[tmpArray.length / 2];
  }

  public final float getCurrentMin()
  {
    _currentMin = Float.MAX_VALUE;
    for (Float f : _values)
      if (_currentMin > f)
        _currentMin = f;

    return _currentMin;
  }

  public final float getSum()
  {
    return _sum;
  }

  @Override
  public String toString()
  {
    _currentMin = Float.MAX_VALUE;
    _currentMax = 0.0f;

    Float[] tmpArray = new Float[_values.size()];
    for (int i = 0; i < _values.size(); i++)
      tmpArray[i] = new Float(_values.get(i));

    for (Float f : tmpArray)
    {
      if (f > _currentMax)
        _currentMax = f;

      if (f < _currentMin)
        _currentMin = f;
    }

    _median = tmpArray[tmpArray.length / 2];

    return String.format("Curr/Abs Min: %02f/%02f Curr/Abs Max: %02f/%02f Avg: %02f Median: %02f Sum: %02f Last: %02f",
      _currentMin, _absoluteMin, _currentMax, _absoluteMax, getAverage(), getMedian(), _sum, _values.getLast());
  }
}
