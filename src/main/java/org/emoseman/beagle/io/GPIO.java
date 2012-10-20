package org.emoseman.beagle.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class GPIO
{
  public final String LED0Path = "/sys/devices/platform/leds-gpio/leds/beaglebone::usr0/brightness";
  public final String LED1Path = "/sys/devices/platform/leds-gpio/leds/beaglebone::usr1/brightness";
  public final String LED2Path = "/sys/devices/platform/leds-gpio/leds/beaglebone::usr2/brightness";
  public final String LED3Path = "/sys/devices/platform/leds-gpio/leds/beaglebone::usr3/brightness";
  
  private final HashMap<PIN, String> _pinMap = new HashMap<PIN, String>();
  
  public GPIO()
  {
    _pinMap.put(PIN.LED0, LED0Path);
    _pinMap.put(PIN.LED1, LED1Path);
    _pinMap.put(PIN.LED2, LED2Path);
    _pinMap.put(PIN.LED3, LED3Path);
  }

  public final void setPin(int pin)
  {

  }

  public final int getPin(int pin)
  {
    return 0;
  }

  public void setPinOut(int pin)
  {

  }

  public void setPinIn(int pin)
  {

  }
  
  private void write(PIN pin, String value) throws IOException
  {
    File file = new File(_pinMap.get(pin));
    FileOutputStream out = new FileOutputStream(file);
    BufferedOutputStream bos = new BufferedOutputStream(out);
    bos.write(value.getBytes());
    bos.flush();
    bos.close();
    out.close();
  }

  public void setLed0(String brightness) throws IOException
  {
    write(PIN.LED0, brightness);
  }
  
  public void setLed1(String brightness) throws IOException
  {
    write(PIN.LED1, brightness);
  }
  
  public void setLed2(String brightness) throws IOException
  {
    write(PIN.LED2, brightness);
  }
  
  public void setLed3(String brightness) throws IOException
  {
    write(PIN.LED3, brightness);
  }
  
  
}
