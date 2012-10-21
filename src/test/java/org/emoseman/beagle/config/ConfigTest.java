package org.emoseman.beagle.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.log4j.Logger;
import org.junit.Test;

public class ConfigTest
{
  private static final Logger log = Logger.getLogger(ConfigTest.class);

  @Test
  public void test()
    throws Exception
  {
    Config config = new Config();

    assertNotNull(config);
    log.debug("config: " + config.toString());

    assertEquals(config.getPin("LED0"), "/sys/devices/platform/leds-gpio/leds/beaglebone::usr0/brightness");
  }
}
