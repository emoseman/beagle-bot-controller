package org.emoseman.beagle.io;

public enum PINDirection
{
  in, out;

  public static final PINDirection fromString(final String s)
  {
    switch (s)
    {
      case "in":
        return PINDirection.in;
      case "out":
        return PINDirection.out;
      default:
        return null;
    }
  }
}
