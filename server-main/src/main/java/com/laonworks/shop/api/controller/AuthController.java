package com.laonworks.shop.api.controller;

import com.laonworks.shop.api.controller.handler.user.PatchProfileHandler;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.laonworks.shop.api.controller.request.auth.*;
import com.laonworks.shop.api.controller.response.auth.*;
import com.laonworks.shop.api.service.CustomUserDetails;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientResponseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.laonworks.shop.api.controller.handler.auth.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController extends BaseController {
  static Logger logger = LoggerFactory.getLogger(AuthController.class);

  // login
  @Autowired
  private SignInHandler signInHandler;

  @RequestMapping(method = RequestMethod.POST, value = "signin", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "sing in")
  SignInResponse signIn(@AuthenticationPrincipal Authentication auth, @RequestBody SignInRequest req, HttpServletRequest request) {
    signInHandler.setHttpServletRequest(request);
    CustomUserDetails user = null;
    if (auth != null) {
      user = (CustomUserDetails) auth.getPrincipal();
    }
    if (!checkRoute(RequestMethod.POST, "/api/v1/auth/signin", user)) {
      throw new RestClientResponseException("", HttpStatus.UNAUTHORIZED.value(), "", null, null, null);
    }
    return signInHandler.execute(req);
  }

  // signUp
  @Autowired
  private SignUpHandler signUpHandler;

  @RequestMapping(method = RequestMethod.POST, value = "signup", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "sign up")
  SignUpResponse signUp(@AuthenticationPrincipal Authentication auth, @RequestBody @Valid SignUpRequest req,
                        @ApiParam(value = "errors", hidden = true, required = false) Errors errors, HttpServletRequest request) {
    signUpHandler.setHttpServletRequest(request);
    CustomUserDetails user = null;


    if (auth != null) {
      user = (CustomUserDetails) auth.getPrincipal();
    }

    if (!checkRoute(RequestMethod.POST, "/api/v1/auth/signup", user)) {
      throw new RestClientResponseException("", HttpStatus.UNAUTHORIZED.value(), "", null, null, null);
    }

    if (errors.hasErrors()) {
      return signUpHandler.validateSignup(errors);
    }

    return signUpHandler.execute(req);
  }

  @Autowired
  private RefreshHandler refreshHandler;

  @PostMapping(value = "refresh")
  RefreshResponse JwtTokenRefresh(@AuthenticationPrincipal Authentication auth, @RequestBody RefreshRequest req) throws Exception {

    if (auth == null) {
      throw new RestClientResponseException("", 403, "", null, null, null);
    }

    return refreshHandler.excute(req);
  }

    //login User/Seller 비밀번호 변경
    @Autowired
    PatchPasswordHandler patchPasswordHandler;

   @RequestMapping(method = RequestMethod.PATCH, value = "password",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "change password")
    PatchPasswordResponse changePassword(@AuthenticationPrincipal Authentication auth, @RequestBody @Valid PatchPasswordRequest req,
                                        @ApiParam(value = "errors", hidden = true, required = false)Errors errors,HttpServletRequest request){
     patchPasswordHandler.setHttpServletRequest(request);
     System.out.println("Authoriztion -->  " + request.getHeader("Authorization"));
     CustomUserDetails user = null;
     if (auth == null) {
       throw new RestClientResponseException("", HttpStatus.UNAUTHORIZED.value(), "", null, null, null);
     }
     user = (CustomUserDetails) auth.getPrincipal();
     if (user == null) {
       throw new RestClientResponseException("", HttpStatus.UNAUTHORIZED.value(), "", null, null, null);
     }
     if (!checkRoute(RequestMethod.PATCH, "/api/v1/user/profile", user)) {
       throw new RestClientResponseException("", HttpStatus.UNAUTHORIZED.value(), "", null, null, null);
     }
     if(errors.hasErrors()){
       return PatchPasswordHandler.validatePatchPassword(errors);
     }
     return patchPasswordHandler.execute(user,req);
   }

  // login User/Seller 계정 탈퇴
  @Autowired
  WithdrawalHandler withdrawalHandler;

  @RequestMapping(method = RequestMethod.DELETE, value = "withdrawal")
  @ApiOperation(value = "withdrawal")
  WithdrawalResponse withdrawal(@AuthenticationPrincipal Authentication auth,@RequestBody @Valid WithdrawalRequest req,
                                @ApiParam(value = "errors",hidden = true,required = false) Errors errors,HttpServletRequest request){
    withdrawalHandler.setHttpServletRequest(request);
    System.out.println("Authoriztion -->  " + request.getHeader("Authorization"));
    CustomUserDetails user = null;
    if (auth == null) {
      throw new RestClientResponseException("", HttpStatus.UNAUTHORIZED.value(), "", null, null, null);
    }
    user = (CustomUserDetails) auth.getPrincipal();
    if (user == null) {
      throw new RestClientResponseException("", HttpStatus.UNAUTHORIZED.value(), "", null, null, null);
    }
    if (!checkRoute(RequestMethod.DELETE, "/api/v1/auth/withdrawal", user)) {
      throw new RestClientResponseException("", HttpStatus.UNAUTHORIZED.value(), "", null, null, null);
    }
    if(errors.hasErrors()){
      return withdrawalHandler.validateWithdrawal(errors);
    }
    return withdrawalHandler.execute(user,req);
   }




}







