package com.laonworks.shop.api.controller.handler;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class BaseHandler {

  protected HttpServletRequest request;

  protected boolean emptyParam(String str) {
    if (str == null || str.isEmpty()) {
      return true;
    }
    return false;
  }

  protected boolean emptyParam(List<String> strs) {
    if (strs == null || strs.isEmpty()) {
      return true;
    }
    return false;
  }

  protected boolean emptyParam(int v) {
    if (v == 0) {
      return true;
    }
    return false;
  }

  boolean emptyParam(long v) {
    if (v == 0) {
      return true;
    }
    return false;
  }

  boolean emptyParam(double v) {
    if (v == 0) {
      return true;
    }
    return false;
  }

  public void setHttpServletRequest(HttpServletRequest request) {
    this.request = request;
  }
}
