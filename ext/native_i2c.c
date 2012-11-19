/**
 * Evan Moseman
 *
 * Wrapper around i2c calls.
 */

#include <jni.h>
#include <sys/ioctl.h>
#include <fcntl.h>
#include <linux/i2c.h>
#include <linux/i2c-dev.h>

JNIEXPORT jbyte JNICALL Java_org_emoseman_utils_I2CUtils_readByteFromI2CDevice(
  JNIEnv *env, jobject obj, jstring path, jint device_address, jint register_address)
{
  const char *cpath = (*env)->GetStringUTFChars(env, path, 0);
  int fd, result;

  // Open file descriptor
  if ((fd = open(cpath, O_RDWR)) < 0) {
    printf("Failed to open the bus\n");
    return fd;
  }

  // Set slave address
  if (ioctl(fd, I2C_SLAVE, device_address) < 0) {
    printf("Failed to get bus access to I2C slave\n");
    return -1;
  }

  // Read data from i2c device at register address
  result = i2c_smbus_read_byte_data(fd, register_address);

  // cleanup
  (*env)->ReleaseStringUTFChars(env, path, cpath);
  close(fd);

  return result;
}

JNIEXPORT jshort JNICALL Java_org_emoseman_utils_I2CUtils_readWordFromI2CDevice(
  JNIEnv *env, jobject obj, jstring path, jint device_address, jint register_address)
{
  const char *cpath = (*env)->GetStringUTFChars(env, path, 0);
  int fd, result;

  // Open file descriptor
  if ((fd = open(cpath, O_RDWR)) < 0) {
    printf("Failed to open the bus\n");
    return fd;
  }

  // Set slave address
  if (ioctl(fd, I2C_SLAVE, device_address) < 0) {
    printf("Failed to get bus access to I2C slave\n");
    return -1;
  }

  // Read data from i2c device at register address
  result = i2c_smbus_read_word_data(fd, register_address);

  // cleanup
  (*env)->ReleaseStringUTFChars(env, path, cpath);
  close(fd);

  return result;
}

JNIEXPORT jbyte JNICALL Java_org_emoseman_utils_I2CUtils_writeByteToI2CDevice(
  JNIEnv *env, jobject obj, jstring path, jint device_address, jint register_address, jint data)
{
  const char *cpath = (*env)->GetStringUTFChars(env, path, 0);
  int fd,result;

  // Open file descriptor
  if ((fd = open(cpath, O_RDWR)) < 0) {
    printf("Failed to open the bus\n");
    return fd;
  }

  // Set slave address
  if (ioctl(fd, I2C_SLAVE, device_address) < 0) {
    printf("Failed to get bus access to I2C slave\n");
    return -1;
  }

  // Write data value to register
  result = i2c_smbus_write_byte_data(fd, register_address, data);

  // cleanup
  (*env)->ReleaseStringUTFChars(env, path, cpath);
  close(fd);

  return result;
}

JNIEXPORT jshort JNICALL Java_org_emoseman_utils_I2CUtils_writeWordToI2CDevice(
  JNIEnv *env, jobject obj, jstring path, jint device_address, jint register_address, jint data)
{
  const char *cpath = (*env)->GetStringUTFChars(env, path, 0);
  int fd,result;

  // Open file descriptor
  if ((fd = open(cpath, O_RDWR)) < 0) {
    printf("Failed to open the bus\n");
    return fd;
  }

  // Set slave address
  if (ioctl(fd, I2C_SLAVE, device_address) < 0) {
    printf("Failed to get bus access to I2C slave\n");
    return -1;
  }

  // Write data value to register
  result = i2c_smbus_write_word_data(fd, register_address, data);

  // cleanup
  (*env)->ReleaseStringUTFChars(env, path, cpath);
  close(fd);

  return result;
}

// vim: ts=2:sw=2:expandtab:
