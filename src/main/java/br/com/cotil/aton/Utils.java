package br.com.cotil.aton;

public class Utils {
  
  public static Boolean isNullOrEmpty(String text) {
    if(text.isEmpty() || text == null)
      return true;
    
    return false;
  }

}
