/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class org_emoseman_utils_I2CUtils */

#ifndef _Included_org_emoseman_utils_I2CUtils
#define _Included_org_emoseman_utils_I2CUtils
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     org_emoseman_utils_I2CUtils
 * Method:    readByteFromI2CDevice
 * Signature: (Ljava/lang/String;II)B
 */
JNIEXPORT jbyte JNICALL Java_org_emoseman_utils_I2CUtils_readByteFromI2CDevice
  (JNIEnv *, jclass, jstring, jint, jint);

/*
 * Class:     org_emoseman_utils_I2CUtils
 * Method:    readWordFromI2CDevice
 * Signature: (Ljava/lang/String;II)S
 */
JNIEXPORT jshort JNICALL Java_org_emoseman_utils_I2CUtils_readWordFromI2CDevice
  (JNIEnv *, jclass, jstring, jint, jint);

/*
 * Class:     org_emoseman_utils_I2CUtils
 * Method:    writeByteToI2CDevice
 * Signature: (Ljava/lang/String;III)B
 */
JNIEXPORT jbyte JNICALL Java_org_emoseman_utils_I2CUtils_writeByteToI2CDevice
  (JNIEnv *, jclass, jstring, jint, jint, jint);

/*
 * Class:     org_emoseman_utils_I2CUtils
 * Method:    writeWordToI2CDevice
 * Signature: (Ljava/lang/String;III)S
 */
JNIEXPORT jshort JNICALL Java_org_emoseman_utils_I2CUtils_writeWordToI2CDevice
  (JNIEnv *, jclass, jstring, jint, jint, jint);

#ifdef __cplusplus
}
#endif
#endif
