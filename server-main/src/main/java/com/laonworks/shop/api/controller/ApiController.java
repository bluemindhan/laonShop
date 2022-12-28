package com.laonworks.shop.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.laonworks.shop.api.controller.handler.*;
import com.laonworks.shop.api.controller.request.api.*;
import com.laonworks.shop.api.controller.response.api.*;
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
import com.laonworks.shop.api.controller.handler.api.*;

@RestController
@RequestMapping("/api/v1")
public class ApiController extends BaseController {
  static Logger logger = LoggerFactory.getLogger(ApiController.class);

  @Autowired
  private AddCountHandler addCountHandler;

  @RequestMapping(method = RequestMethod.POST, value = "add", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "add")
  AddCountResponse addCount(@AuthenticationPrincipal Authentication auth, @RequestBody AddCountRequest req, HttpServletRequest request) {
    addCountHandler.setHttpServletRequest(request);
    CustomUserDetails user = null;
    if (auth != null) {
      user = (CustomUserDetails) auth.getPrincipal();
    }
    if (checkRoute(RequestMethod.POST, "/api/v1/add", user) == false) {
      throw new RestClientResponseException("", HttpStatus.UNAUTHORIZED.value(), "", null, null, null);
    }
    return addCountHandler.execute(req);
  }
}
