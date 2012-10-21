package org.emoseman.beagle.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.google.gson.internal.StringMap;

public class ConfigTest
{
  private static final Logger log = Logger.getLogger(ConfigTest.class);

  @Test
  public void test()
    throws Exception
  {
    StringMap leds = Config.getLeds();
    StringMap paths = (StringMap) leds.get("1");
    assertNotNull(leds);
    assertTrue(leds.size() > 0);

    assertEquals(leds.get("inverted"), "/sys/devices/platform/leds-gpio/leds/beaglebone::usr1/inverted");
  }
}
