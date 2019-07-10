package br.com.cotil.aton.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class Utils {
  
  public static Boolean isNullOrEmpty(String text) {
    if(text == null)
      return true;
    if(text.isEmpty())
      return true;
    return false;
  }
  
  public static Pageable setPageRequestConfig(Integer numPage, Integer numPageSize) {
    if ((numPage == null) || (numPage <= 0))
      numPage = 1;

    if ((numPageSize == null) || (numPageSize <= 0))
      numPageSize = 20;

    return PageRequest.of(numPage - 1, numPageSize);
  }

}
