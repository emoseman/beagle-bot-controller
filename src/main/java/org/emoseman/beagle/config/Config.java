package org.emoseman.beagle.config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.StringMap;

public class Config
{
  private static final Logger log       = Logger.getLogger(Config.class);
  private final String        _fileName = "BotConfig.json";
  private static boolean      loaded    = false;
  private static StringMap<String>   _pins;
  private static StringMap<String>   _leds;

  public Object getPin(String key)
    throws JsonSyntaxException, JsonIOException, FileNotFoundException
  {
    if (!loaded)
      load();

    return _pins.get(key);
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  private void load()
    throws JsonSyntaxException, JsonIOException, FileNotFoundException
  {
    ClassLoader cl = this.getClass().getClassLoader();
    URL url = cl.getResource(_fileName);

    log.info("url: " + url.toString());

    if (url != null)
    {
      Gson gson = new Gson();
      StringMap tmp = gson.fromJson(new FileReader(url.getFile()), StringMap.class);
      _pins = (StringMap<String>) tmp.get("pins");
      _leds = (StringMap<String>) tmp.get("leds");
       
      log.info("pins: " + _pins.toString());
      log.info("leds: " + _leds.toString());
    }
    loaded = true;
  }

  public String toString()
  {
    return (_pins == null) ? "null" : "pins:" + _pins.toString();
  }

  public StringMap getLeds()
  {
    return _leds;
  }

}
