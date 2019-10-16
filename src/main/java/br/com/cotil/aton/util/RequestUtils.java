package br.com.cotil.aton.util;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {

  public static String getUserAgentFromRequest(HttpServletRequest request) {
    return request.getHeader("user-agent") == null ? "" : request.getHeader("user-agent");
  }

  public static String getIpFromRequest(HttpServletRequest request) {
    return request.getHeader("X-Forwarded-For") == null ? request.getRemoteHost()
        : request.getHeader("X-Forwarded-For");
  }
}
