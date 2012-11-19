package org.emoseman.beagle.io.i2c;

import static org.emoseman.beagle.io.i2c.Constants.*;

public abstract class ADS1115
  extends I2C
{
  public static final int initialConfig =
    ADS1115_REG_CONFIG_CQUE_NONE    |
    ADS1115_REG_CONFIG_MUX_SINGLE_0 |
    ADS1115_REG_CONFIG_PGA_6_144V   |
    ADS1115_REG_CONFIG_MODE_CONTIN  |
    ADS1115_REG_CONFIG_DR_1600SPS   |
    ADS1115_REG_CONFIG_CMODE_TRAD   |
    ADS1115_REG_CONFIG_CPOL_ACTVLOW |
    ADS1115_REG_CONFIG_CLAT_NONLAT;
  
  public static final int initialRead = 
    ADS1115_REG_CONFIG_CQUE_NONE    |
    ADS1115_REG_CONFIG_MODE_CONTIN  |
    ADS1115_REG_CONFIG_DR_1600SPS   |
    ADS1115_REG_CONFIG_CMODE_TRAD   |
    ADS1115_REG_CONFIG_CPOL_ACTVLOW |
    ADS1115_REG_CONFIG_CLAT_NONLAT
    ;

  public ADS1115()
  {
    super();
  }

  @Override
  public void initialize()
  {
    System.err.println("Sending config value: " + initialConfig);
    writeWord(ADS1115_REG_CONFIG, initialConfig);
  }
  
  public final int readChannel(final int channel)
  {
    int result, readConfig;
    readConfig = initialRead;
    
    switch(channel) {
      case 0 : readConfig |= ADS1115_REG_CONFIG_MUX_SINGLE_0;
               break;
      case 1 : readConfig |= ADS1115_REG_CONFIG_MUX_SINGLE_1;
               break;
      case 2 : readConfig |= ADS1115_REG_CONFIG_MUX_SINGLE_2;
               break;
      case 3 : readConfig |= ADS1115_REG_CONFIG_MUX_SINGLE_3;
               break;
    }
    
    writeWord(ADS1115_REG_CONFIG, readConfig);
    
    result = readWord(ADS1115_REG_CONVERSION);
    
    return result;
  }

  @Override
  public void calibrate()
  {
    // todo
  }

}
