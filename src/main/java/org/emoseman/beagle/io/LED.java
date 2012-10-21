package org.emoseman.beagle.io;

import java.io.IOException;

import org.emoseman.beagle.config.Config;

import com.google.gson.internal.StringMap;

public class LED
  extends IO
{
  private static final String BRIGHTNESS = "brightness";
  private static final String DELAY_OFF = "delay_off";
  private static final String DELAY_ON = "delay_on";
  private static final String TRIGGER = "trigger";

  private final int _number;
  private LEDTrigger _trigger;
  private StringMap<String> _paths;

  @SuppressWarnings("unchecked")
  public LED(int no)
  {
    Config config = new Config();
    _number = no;
    _paths = (StringMap<String>) config.getLeds().get(new Integer(_number).toString());
  }

  public final void updateTrigger()
    throws IOException
  {
    String value = readString(_paths.get(TRIGGER));
    String[] parts = value.split(" ");
    for (String p : parts)
    {
      if (p.startsWith("["))
      {
        value = p.replaceAll("(\\[\\])", "");
        break;
      }
    }
    if (value != null)
      setTrigger(value);
  }

  public final void setTrigger(String trigger)
    throws IOException
  {
    switch (trigger)
    {
      case "backlight":
        _trigger = LEDTrigger.backlight;
      case "default-on":
        _trigger = LEDTrigger.defaulton;
      case "none":
        _trigger = LEDTrigger.none;
      case "mmc0":
        _trigger = LEDTrigger.mmc0;
      case "timer":
        _trigger = LEDTrigger.timer;
      case "heartbeat":
        _trigger = LEDTrigger.heartbeat;
      case "gpio":
        _trigger = LEDTrigger.gpio;
    }
    writeString((String) _paths.get(TRIGGER), trigger);
  }

  public final String getTrigger()
    throws IOException
  {
    String trigger = readString(_paths.get(TRIGGER));
    return _trigger.toString();
  }

  public final void setBrightness(int value)
    throws IOException
  {
    if (value > 255)
      value = 255;
    else if (value < 0)
      value = 0;

    writeString(_paths.get(BRIGHTNESS), new Integer(value).toString());
  }

  public final void setDelayOn(int value)
    throws IOException
  {
    writeString(_paths.get(DELAY_ON), new Integer(value).toString());
  }

  public final void setDelayOff(int value)
    throws IOException
  {
    writeString(_paths.get(DELAY_OFF), new Integer(value).toString());
  }

}
