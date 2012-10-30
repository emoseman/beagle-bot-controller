package org.emoseman.beagle.io;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.emoseman.beagle.config.Config;

import com.google.gson.internal.StringMap;

public class LED
  extends IO
{
  private static final Logger log = Logger.getLogger(LED.class);

  private static final String BRIGHTNESS = "brightness";
  private static final String DELAY_OFF = "delay_off";
  private static final String DELAY_ON = "delay_on";
  private static final String TRIGGER = "trigger";

  private final int _number;
  private LEDTrigger _trigger = LEDTrigger.none;
  private final StringMap<String> _paths;

  @SuppressWarnings("unchecked")
  public LED(final int no)
  {
    _number = no;
    _paths = (StringMap<String>) Config.getLeds().get(new Integer(_number).toString());
    try
    {
      updateTrigger();
    }
    catch (IOException e)
    {
      e.printStackTrace();
      throw new RuntimeException("Failed to update trigger value for LED" + no);
    }
  }

  public final String getTrigger()
    throws IOException
  {
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

  public final void setDelayOff(final int value)
    throws IOException
  {
    writeString(_paths.get(DELAY_OFF), new Integer(value).toString());
  }

  public final void setDelayOn(final int value)
    throws IOException
  {
    writeString(_paths.get(DELAY_ON), new Integer(value).toString());
  }

  public final void setTrigger(final LEDTrigger trigger)
    throws IOException
  {
    log.debug("setting trigger to " + trigger);
    _trigger = trigger;
    writeString(_paths.get(TRIGGER), _trigger.toString());
  }

  public final void updateTrigger()
    throws IOException
  {
    final String value = readString(_paths.get(TRIGGER));

    Pattern p = Pattern.compile(".*\\[(\\w+-?o?n?)\\].*");
    Matcher m = p.matcher(value);

    if (m.matches())
      _trigger = LEDTrigger.fromString(m.group(1));
  }

  @Override
  public Object read()
  {
    try
    {
      return getTrigger();
    }
    catch (IOException e)
    {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

}
