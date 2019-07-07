package br.com.cotil.aton.util;

public class Utils {
  
  public static Boolean isNullOrEmpty(String text) {
    if(text == null)
      return true;
    if(text.isEmpty())
      return true;
    return false;
  }

}
