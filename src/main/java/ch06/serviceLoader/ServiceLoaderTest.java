package ch06.serviceLoader;

import java.io.*;
import java.nio.charset.*;
import java.util.*;

/**
 * @version 1.01 2018-03-17
 * @author Cay Horstmann
 */

public class ServiceLoaderTest
{
   public static ServiceLoader<Cipher> cipherLoader 
      = ServiceLoader.load(Cipher.class);

   public static void main(String[] args) throws UnsupportedEncodingException
   {
      Cipher cipher = getCipher(80);
      var message = "Meet me at the toga party.";
      byte[] key = new byte[] { 3 };
      byte[] bytes = cipher.encrypt(message.getBytes(), key);
      var encrypted = new String(bytes, StandardCharsets.UTF_8);
      System.out.println(encrypted);
      var dbytes = cipher.decrypt(bytes, key);
      var decrypted = new String(dbytes, StandardCharsets.UTF_8);
       System.out.println(decrypted);
   }

   public static Cipher getCipher(int minStrength)
   {
      for (Cipher cipher : cipherLoader)
         // Implicitly calls iterator
         if (cipher.strength() >= minStrength) return cipher;
      return null;
   }
}
