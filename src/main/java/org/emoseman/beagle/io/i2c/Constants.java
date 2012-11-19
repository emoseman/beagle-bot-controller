package org.emoseman.beagle.io.i2c;

public class Constants
{
  // Copied from
  // https://github.com/adafruit/Adafruit_ADS1X15/blob/master/Adafruit_ADS1015.h

  /**
   * ADS1115
   *
   */
  // Registers
  public static final int ADS1115_REG_CONVERSION = 0x00;
  public static final int ADS1115_REG_CONFIG     = 0x01;
  public static final int ADS1115_REG_LO_THRESH  = 0x02;
  public static final int ADS1115_REG_HI_THRESH  = 0x03;

  // Config Register
  public static final int ADS1115_REG_CONFIG_OS_SINGLE    = 0x8000;  // Write: Set to start a single-conversion
  public static final int ADS1115_REG_CONFIG_OS_BUSY      = 0x0000;  // Read: Bit = 0 when conversion is in progress
  public static final int ADS1115_REG_CONFIG_OS_NOTBUSY   = 0x8000;  // Read: Bit = 1 when device is not performing a conversion

  public static final int ADS1115_REG_CONFIG_MUX_DIFF_0_1 = 0x0000;  // Differential P = AIN0, N = AIN1
  public static final int ADS1115_REG_CONFIG_MUX_DIFF_0_3 = 0x1000;  // Differential P = AIN0, N = AIN3
  public static final int ADS1115_REG_CONFIG_MUX_DIFF_1_3 = 0x2000;  // Differential P = AIN1, N = AIN3
  public static final int ADS1115_REG_CONFIG_MUX_DIFF_2_3 = 0x3000;  // Differential P = AIN2, N = AIN3
  public static final int ADS1115_REG_CONFIG_MUX_SINGLE_0 = 0x4000;  // Single-ended AIN0
  public static final int ADS1115_REG_CONFIG_MUX_SINGLE_1 = 0x5000;  // Single-ended AIN1
  public static final int ADS1115_REG_CONFIG_MUX_SINGLE_2 = 0x6000;  // Single-ended AIN2
  public static final int ADS1115_REG_CONFIG_MUX_SINGLE_3 = 0x7000;  // Single-ended AIN3

  public static final int ADS1115_REG_CONFIG_PGA_6_144V   = 0x0000;  // +/-6.144V range
  public static final int ADS1115_REG_CONFIG_PGA_4_096V   = 0x0200;  // +/-4.096V range
  public static final int ADS1115_REG_CONFIG_PGA_2_048V   = 0x0400;  // +/-2.048V range
  public static final int ADS1115_REG_CONFIG_PGA_1_024V   = 0x0600;  // +/-1.024V range
  public static final int ADS1115_REG_CONFIG_PGA_0_512V   = 0x0800;  // +/-0.512V range
  public static final int ADS1115_REG_CONFIG_PGA_0_256V   = 0x0A00;  // +/-0.256V range

  public static final int ADS1115_REG_CONFIG_MODE_CONTIN  = 0x0000;  // Continuous conversion mode
  public static final int ADS1115_REG_CONFIG_MODE_SINGLE  = 0x0100;  // Power-down single-shot mode

  public static final int ADS1115_REG_CONFIG_DR_128SPS    = 0x0000;  // 128 samples per second
  public static final int ADS1115_REG_CONFIG_DR_250SPS    = 0x0020;  // 250 samples per second
  public static final int ADS1115_REG_CONFIG_DR_490SPS    = 0x0040;  // 490 samples per second
  public static final int ADS1115_REG_CONFIG_DR_920SPS    = 0x0050;  // 920 samples per second
  public static final int ADS1115_REG_CONFIG_DR_1600SPS   = 0x0080;  // 1600 samples per second
  public static final int ADS1115_REG_CONFIG_DR_2400SPS   = 0x00A0;  // 2400 samples per second
  public static final int ADS1115_REG_CONFIG_DR_3300SPS   = 0x00C0;  // 3300 samples per second

  public static final int ADS1115_REG_CONFIG_CMODE_TRAD   = 0x0000;  // Traditional comparator with hysteresis
  public static final int ADS1115_REG_CONFIG_CMODE_WINDOW = 0x0010;  // Window comparator

  public static final int ADS1115_REG_CONFIG_CPOL_ACTVLOW = 0x0000;  // ALERT/RDY pin is low when active
  public static final int ADS1115_REG_CONFIG_CPOL_ACTVHI  = 0x0008;  // ALERT/RDY pin is high when active

  public static final int ADS1115_REG_CONFIG_CLAT_NONLAT  = 0x0000;  // Non-latching comparator
  public static final int ADS1115_REG_CONFIG_CLAT_LATCH   = 0x0004;  // Latching comparator

  public static final int ADS1115_REG_CONFIG_CQUE_1CONV   = 0x0000;  // Assert ALERT/RDY after one conversions
  public static final int ADS1115_REG_CONFIG_CQUE_2CONV   = 0x0001;  // Assert ALERT/RDY after two conversions
  public static final int ADS1115_REG_CONFIG_CQUE_4CONV   = 0x0002;  // Assert ALERT/RDY after four conversions
  public static final int ADS1115_REG_CONFIG_CQUE_NONE    = 0x0003;  // Disable the comparator and put ALERT/RDY in high state


}
