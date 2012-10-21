package org.emoseman.beagle.io;

public enum LEDTrigger
{
  none,
  
  mmc0,
  
  timer,
  
  heartbeat,
  
  backlight,
  
  gpio,
  
  defaulton {
    public String toString() {
      return "default-on";
    }
  }
}
