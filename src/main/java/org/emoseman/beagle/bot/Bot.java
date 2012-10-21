package org.emoseman.beagle.bot;

import org.emoseman.beagle.io.LED;

public class Bot
{
  public static void main(String[] args) throws Exception
  {
    LED led0 = new LED(0);
    LED led1 = new LED(1);
    LED led2 = new LED(2);
    LED led3 = new LED(3);
    
    while (true) {
      led0.setBrightness(255);
      led1.setBrightness(0);
      led2.setBrightness(255);
      led3.setBrightness(0);
      Thread.sleep(10);
      
      led0.setBrightness(0);
      led1.setBrightness(255);
      led2.setBrightness(0);
      led3.setBrightness(255);
      Thread.sleep(10);
    }
  }
}
