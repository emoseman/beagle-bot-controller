package org.emoseman.beagle.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.junit.Test;

public class LEDTest
{
  Logger log = Logger.getLogger(LEDTest.class);

  @Test
  public void testStringParse()
  {
    final String testValue = "none mmc0 timer heartbeat backlight [gpio] default-on";

    String result = null;
    Pattern p = Pattern.compile(".*\\[(\\w+-?o?n?)\\].*");
    Matcher m = p.matcher(testValue);

    assertTrue(m.matches());

    result = m.group(1);
    assertNotNull(result);
    assertEquals("gpio", result);
  }
}
