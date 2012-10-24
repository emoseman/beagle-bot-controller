package org.emoseman.beagle.io;

public enum LEDTrigger
{
  none, mmc0, timer, heartbeat, backlight, gpio,
  defaulton {
    @Override
    public String toString() {
      return "default-on";
    }
  };

  public static LEDTrigger fromString(final String trigger)
  {
    switch (trigger)
    {
      case "backlight":
        return LEDTrigger.backlight;
      case "default-on":
        return LEDTrigger.defaulton;
      case "none":
        return LEDTrigger.none;
      case "mmc0":
        return  LEDTrigger.mmc0;
      case "timer":
        return  LEDTrigger.timer;
      case "heartbeat":
        return  LEDTrigger.heartbeat;
      case "gpio":
        return  LEDTrigger.gpio;
      default:
        return  LEDTrigger.none;
    }
  }

  LEDTrigger()
  {}
}
