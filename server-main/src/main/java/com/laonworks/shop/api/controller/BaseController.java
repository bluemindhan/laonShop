package com.laonworks.shop.api.controller;

import com.laonworks.shop.api.service.CustomUserDetails;
import org.springframework.web.bind.annotation.RequestMethod;

public class BaseController {

  protected boolean checkRoute(RequestMethod method, String path, CustomUserDetails user) {
    return true;
  }
}
