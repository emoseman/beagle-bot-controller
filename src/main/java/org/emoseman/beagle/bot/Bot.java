package org.emoseman.beagle.bot;

import org.emoseman.beagle.io.Analog;
import org.emoseman.beagle.io.AnalogFactory;
import org.emoseman.beagle.io.LED;

public class Bot
{
  public static void main(final String[] args)
    throws Exception
  {
    LED led0 = new LED(0);
    LED led1 = new LED(1);
    LED led2 = new LED(2);
    LED led3 = new LED(3);
    Analog a1 = AnalogFactory.getAnalog(1);

    int i = 0;
    int d = 1;

    while (true)
    {
      if (i < 0)
      {
        d = 1;
      }
      if (i > 255)
      {
        d = -1;
      }
      i += d;

      led0.setBrightness(i);
      led1.setBrightness(i);
      led2.setBrightness(i);
      led3.setBrightness(i);
      Thread.sleep(100);

      System.out.println(a1.read());
    }
  }
}
