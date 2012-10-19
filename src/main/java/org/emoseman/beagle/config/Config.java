package org.emoseman.beagle.config;

import java.net.URL;

import com.google.gson.internal.StringMap;

public class Config {
  private StringMap<String> _config;

  private final String _fileName = "BotConfig.json";
  private boolean loaded = false;

  public String get(String key) {
    if (!loaded) load();

    return (String) _config.get(key);
  }

  private void load() {
    ClassLoader cl = this.getClass().getClassLoader();
    URL url = cl.getResource(_fileName);
    if (url != null) {

    }
  }

}
