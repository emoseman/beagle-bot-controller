package org.emoseman.beagle.config;

import java.io.FileReader;
import java.net.URL;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.internal.StringMap;

public class Config
{
  private static final Logger log = Logger.getLogger(Config.class);
  private static final String _fileName = "BotConfig.json";
  private static boolean loaded = false;
  private static StringMap<String> _leds;
  private static StringMap<String> _analog;
  private static StringMap<String> _gpio;

  public static String getAnalogPath(final int pin)
  {
    if (_analog == null)
      return "";

    return _analog.get(new Integer(pin).toString());
  }

  public static String getGPIOPath(final String s)
  {
    if (!loaded)
      load();

    return _gpio.get(s);
  }

  public static final StringMap getLeds()
  {
    if (!loaded)
      load();

    return _leds;
  }

  private static void load()
  {
    log.info("Loading configuration file...");

    ClassLoader cl = Config.class.getClassLoader();
    if (cl == null)
    {
      throw new RuntimeException("Failed to find classloader!");
    }
    URL url = cl.getResource(_fileName);

    log.debug("url: " + url.toString());

    if (url != null)
    {
      try
      {
        Gson gson = new Gson();
        StringMap tmp = gson.fromJson(new FileReader(url.getFile()), StringMap.class);
        _analog = (StringMap<String>) tmp.get("analog");
        _leds = (StringMap<String>) tmp.get("leds");
        _gpio = (StringMap<String>) tmp.get("gpio");

        log.debug("analog: " + ((_analog == null) ? "null" : _analog.toString()));
        log.debug("leds: " + ((_leds == null) ? "null" : _leds.toString()));
      }
      catch (Exception e)
      {
        log.error(e);
        throw new RuntimeException(e);
      }
    }
    Config.loaded = true;
  }


  @Override
  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    if (_analog != null)
      sb.append("Analog:").append(_analog.toString());
    if (_leds != null)
      sb.append("LEDs: ").append(_leds.toString());

    return sb.toString();
  }

}
