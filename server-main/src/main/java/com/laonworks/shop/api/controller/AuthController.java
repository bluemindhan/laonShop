package com.laonworks.shop.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.laonworks.shop.api.controller.request.auth.*;
import com.laonworks.shop.api.controller.response.auth.*;
import com.laonworks.shop.api.service.CustomUserDetails;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
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

  @Autowired
  private SignInHandler signInHandler;

  @RequestMapping(method = RequestMethod.POST, value = "signin", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "sing in")
  SignInResponse signIn(@AuthenticationPrincipal Authentication auth, @RequestBody SignInRequest req, HttpServletRequest request, HttpServletResponse reponse) {
    signInHandler.setHttpServletRequest(request);
    CustomUserDetails user = null;
    if (auth != null) {
      user = (CustomUserDetails) auth.getPrincipal();
    }
    if (checkRoute(RequestMethod.POST, "/api/v1/auth/signin", user) == false) {
      throw new RestClientResponseException("", HttpStatus.UNAUTHORIZED.value(), "", null, null, null);
    }
    return signInHandler.execute(req,reponse);
  }

  @Autowired
  private SignUpHandler signUpHandler;

  @RequestMapping(method = RequestMethod.POST, value = "signup", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "sign up")
  SignUpResponse signUp(@AuthenticationPrincipal Authentication auth, @RequestBody @Valid SignUpRequest req, Errors errors, HttpServletRequest request) {
    signUpHandler.setHttpServletRequest(request);
    CustomUserDetails user = null;
    if (auth != null) {
      user = (CustomUserDetails) auth.getPrincipal();
    }
    if (checkRoute(RequestMethod.POST, "/api/v1/auth/signup", user) == false) {
      throw new RestClientResponseException("", HttpStatus.UNAUTHORIZED.value(), "", null, null, null);
    }

    if(errors.hasErrors()){
//      return signUpHandler.validateSignup();
    }

    return signUpHandler.execute(req);
  }

  @Autowired
  private RefreshHandler refreshHandler;

  @PostMapping(value="refresh")
  RefreshResponse testJwtTokenRefresh(@AuthenticationPrincipal Authentication auth, @RequestBody RefreshRequest req) throws Exception {

    return refreshHandler.excute(req);
  }





//    // 관리자 정보 조회
//    AdminDTO aDTO = // ... DB에서 정보 조회 로직 실행
//
//            // refreshToken 정보 조회
//            RefreshTokenDTO rDTO = new RefreshTokenDTO();
//    rDTO.setAdmIdx(aDTO.getAdmIdx());
//    rDTO = // ... DB에서 refreshToken 정보 조회
//
//    // token 정보가 존재하지 않는 경우
//    if(rDTO == null) {
//      returnMap.put("result", "fail");
//      returnMap.put("msg", "refresh token 정보가 존재하지 않습니다.");
//      return returnMap;
//    }
//    // token 정보가 존재하는 경우
//    else {
//      refreshToken = rDTO.getRefreshToken();
//    }
//
//    // refreshToken이 존재하는 경우 검증
//    boolean tokenFl = false;
//    try {
//      refreshToken = refreshToken.substring(7);
//      adminId = jwtTokenUtil.getUsernameFromToken(refreshToken);
//      tokenFl = true;
//    } catch (SignatureException e) {
//      log.error("Invalid JWT signature: {}", e.getMessage());
//    } catch (MalformedJwtException e) {
//      log.error("Invalid JWT token: {}", e.getMessage());
//    } catch (ExpiredJwtException e) {
//      log.error("JWT token is expired: {}", e.getMessage());
//    } catch (UnsupportedJwtException e) {
//      log.error("JWT token is unsupported: {}", e.getMessage());
//    } catch (IllegalArgumentException e) {
//      log.error("JWT claims string is empty: {}", e.getMessage());
//    }
//
//    // refreshToken 사용이 불가능한 경우
//    if(!tokenFl) {
//      returnMap.put("result", "refresh 토큰 만료");
//      returnMap.put("msg", "다시 로그인 해주세요");
//
//      // ... refreshToken 정보 조회 실패 시 기존에 존재하는 refreshToken 정보 삭제
//
//      return returnMap;
//    }
//
//    // refreshToken 인증 성공인 경우 accessToken 재발급
//    if(adminId != null && !adminId.equals("")) {
//      // 권한 map 저장
//      Map<String, Object> rules = new HashMap<String, Object>();
//      rules.put("rules", /* ... 권한 정보 조회 로직 실행 */);
//
//      // JWT 발급
//      String tokens = jwtTokenUtil.generateAccessToken(input.get("adminId").toString(), rules);
//      String accessToken = URLEncoder.encode(tokens, "utf-8");
//
//      log.info("[JWT 재발급] accessToken : " + accessToken);
//
//      returnMap.put("result", "success");
//      returnMap.put("msg", "JWT가 발급되었습니다.");
//    }else {
//      returnMap.put("result", "fail");
//      returnMap.put("msg", "access token 발급 중 문제가 발생했습니다.");
//      return returnMap;
//    }
//
//    return returnMap;
//  }

}
