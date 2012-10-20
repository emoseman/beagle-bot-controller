package org.emoseman.beagle.bot;

import org.emoseman.beagle.io.GPIO;

public class Bot
{
  public static void main(String[] args) throws Exception
  {
    GPIO gpio = new GPIO();
    
    while (true) {
      gpio.setLed0("255");
      gpio.setLed1("0");
      gpio.setLed2("255");
      gpio.setLed3("0");
      Thread.sleep(1);
      gpio.setLed0("0");
      gpio.setLed1("255");
      gpio.setLed2("0");
      gpio.setLed3("255");
      Thread.sleep(1);
    }
  }
}
