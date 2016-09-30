
package com.gits.rms.utils;

import java.io.UnsupportedEncodingException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.codec.binary.Base64;

import org.w3c.tools.codec.Base64Decoder;

public class DesEncrypter {
    public static void main(String[] args) {
        DesEncrypter ds = new DesEncrypter("4Y2349324HH8932C4HC9UHE9RHW9EY823496723647823678C4627836478C568C6234CWGRGWEGRWEY");
        String sStr = "test@gmail.com,test@gmail.com";
        ds.encrypt(sStr);
        ds.decrypt("9992X999C3ZDOlLLT999osl3aVVG66oCPCYxT9");
    }

    Cipher dcipher;
    Cipher ecipher;
    // Iteration count
    int iterationCount = 40;

    // 8-byte Salt
    byte[] salt = { (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32, (byte) 0x56, (byte) 0x35,
        (byte) 0xE3, (byte) 0x03 };

    public DesEncrypter(String passPhrase) {
        try {
            // Create the key
            KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), salt, iterationCount);
            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
            ecipher = Cipher.getInstance(key.getAlgorithm());
            dcipher = Cipher.getInstance(key.getAlgorithm());

            // Prepare the parameter to the ciphers
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);

            // Create the ciphers
            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
        } catch (java.security.InvalidAlgorithmParameterException e) {
        } catch (java.security.spec.InvalidKeySpecException e) {
        } catch (javax.crypto.NoSuchPaddingException e) {
        } catch (java.security.NoSuchAlgorithmException e) {
        } catch (java.security.InvalidKeyException e) {
        }

    }

    public String decrypt(String str) {
        try {
            // Decode base64 to get bytes
        	byte[] dec = Base64.decodeBase64(str);

            // Decrypt
            byte[] utf8 = dcipher.doFinal(dec);

            // Decode using utf-8
            return new String(utf8, "UTF8");
        } catch (javax.crypto.BadPaddingException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (UnsupportedEncodingException e) {
        } catch (java.io.IOException e) {
        }
        return null;

    }

    public String encrypt(String str) {
        try {
            // Encode the string into bytes using utf-8
            byte[] utf8 = str.getBytes("UTF8");

            // Encrypt
            byte[] enc = ecipher.doFinal(utf8);

            // Encode bytes to base64 to get a string
            return Base64.encodeBase64URLSafeString(enc);
        } catch (javax.crypto.BadPaddingException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (UnsupportedEncodingException e) {
        } catch (java.io.IOException e) {
        }
        return null;

    }

    private String getDecryptedFriendlyString(String sEncrypted) {
        String sArray[] = { "111", "222", "333", "444", "555", "666", "777", "888", "999", "000" };
        String dArray[] = { "#", "$", "!", "@", "%", "&", "+", "=", "/", "\\" };
        for (int i = 0; i < sArray.length; i++) {
            sEncrypted = sEncrypted.replace(sArray[i], dArray[i]);
        }
        return sEncrypted;
    }

    private String getURLFriendlyString(String sEncrypted) {
        String sReturn = "";
        for (int j = 0; j < sEncrypted.length(); j++) {
            char cChar = sEncrypted.charAt(j);
            if (Character.isSpace(cChar)) {
            } else {
                sReturn += cChar;
            }
        }
        String sArray[] = { "#", "$", "!", "@", "%", "&", "+", "=", "/", "\\" };
        String dArray[] = { "111", "222", "333", "444", "555", "666", "777", "888", "999", "000" };
        for (int i = 0; i < sArray.length; i++) {
            sReturn = sReturn.replace(sArray[i], dArray[i]);
        }
        return sReturn;
    }
}