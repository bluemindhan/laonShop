package com.laonworks.shop.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.laonworks.shop.api.controller.handler.*;
import com.laonworks.shop.api.controller.request.user.*;
import com.laonworks.shop.api.controller.response.user.*;
import com.laonworks.shop.api.service.CustomUserDetails;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.multipart.MultipartFile;
import com.laonworks.shop.api.framework.libs.FormFile;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import com.laonworks.shop.api.controller.handler.user.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController extends BaseController {
  static Logger logger = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private GetProfileHandler getProfileHandler;

  @RequestMapping(method = RequestMethod.GET, value = "profile")
  @ApiOperation(value = "get profile")
  GetProfileResponse getProfile(@AuthenticationPrincipal Authentication auth, HttpServletRequest request) {
    getProfileHandler.setHttpServletRequest(request);
    CustomUserDetails user = null;
    if (auth == null) {
      throw new RestClientResponseException("", HttpStatus.UNAUTHORIZED.value(), "", null, null, null);
    }
    user = (CustomUserDetails) auth.getPrincipal();
    if (user == null) {
      throw new RestClientResponseException("", HttpStatus.UNAUTHORIZED.value(), "", null, null, null);
    }
    GetProfileRequest req = new GetProfileRequest();
    if (checkRoute(RequestMethod.GET, "/api/v1/user/profile", user) == false) {
      throw new RestClientResponseException("", HttpStatus.UNAUTHORIZED.value(), "", null, null, null);
    }
    return getProfileHandler.execute(user, req);
  }
}

