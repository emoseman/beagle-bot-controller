package org.emoseman.beagle.bot;

import org.apache.log4j.Logger;
import org.emoseman.beagle.io.Analog;
import org.emoseman.beagle.io.AnalogFactory;
import org.emoseman.beagle.io.LED;
import org.emoseman.beagle.io.LEDFactory;

public class Bot
{
  private static final Logger log = Logger.getLogger(Bot.class);

  public static void main(final String[] args)
    throws Exception
  {
    log.info("Starting Bot...");

    log.info("Initializing IO Objects...");
    LED led0 = LEDFactory.getLED(0);
    LED led1 = LEDFactory.getLED(1);
    LED led2 = LEDFactory.getLED(2);
    LED led3 = LEDFactory.getLED(3);
    Analog a1 = AnalogFactory.getAnalog(1);
    Analog a2 = AnalogFactory.getAnalog(2);
    Analog a3 = AnalogFactory.getAnalog(3);
    Analog a4 = AnalogFactory.getAnalog(4);
    Analog a5 = AnalogFactory.getAnalog(5);
    Analog a6 = AnalogFactory.getAnalog(6);
    Analog a7 = AnalogFactory.getAnalog(7);
    Analog a8 = AnalogFactory.getAnalog(8);

    led0.setTrigger("none");
    led1.setTrigger("none");
    led2.setTrigger("none");
    led3.setTrigger("none");

    log.info("Starting endless loop...");
    while (true)
    {
      led0.setBrightness(0);
      led1.setBrightness(255);
      led2.setBrightness(0);
      led3.setBrightness(255);
      Thread.sleep(100);

      led0.setBrightness(0);
      led1.setBrightness(255);
      led2.setBrightness(0);
      led3.setBrightness(255);
      Thread.sleep(100);

      System.out.println("1:" + a1.read() + "2:" + a2.read() + "3:" + a3.read() + "4:" + a4.read() + "5:" + a5.read()
        + "6:" + a6.read() + "7:" + a7.read() + "8:" + a8.read());
    }
  }
}
