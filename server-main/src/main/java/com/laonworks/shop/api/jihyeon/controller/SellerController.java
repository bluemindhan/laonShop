package com.laonworks.shop.api.jihyeon.controller;

import com.laonworks.shop.api.controller.BaseController;
import com.laonworks.shop.api.controller.handler.seller.*;
import com.laonworks.shop.api.controller.request.seller.*;
import com.laonworks.shop.api.controller.response.seller.*;
import com.laonworks.shop.api.jihyeon.vo.ProductVo;
import com.laonworks.shop.api.service.CustomUserDetails;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientResponseException;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/seller")
public class SellerController extends BaseController {
  static Logger logger = LoggerFactory.getLogger(SellerController.class);

  @Autowired
  private AddProductHandler addProductHandler;

  /* 상품등록 */
  @RequestMapping(method = RequestMethod.POST, value = "product", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Add a new product")
  AddProductResponse addProduct(@AuthenticationPrincipal Authentication auth, @RequestBody AddProductRequest req, HttpServletRequest request) {
    addProductHandler.setHttpServletRequest(request);
    CustomUserDetails user = null;
    if (auth == null) {
      throw new RestClientResponseException("", HttpStatus.UNAUTHORIZED.value(), "", null, null, null);
    }
    user = (CustomUserDetails) auth.getPrincipal();
    if (user == null) {
      throw new RestClientResponseException("", HttpStatus.UNAUTHORIZED.value(), "", null, null, null);
    }
    if (checkRoute(RequestMethod.POST, "/api/v1/seller/product", user) == false) {
      throw new RestClientResponseException("", HttpStatus.UNAUTHORIZED.value(), "", null, null, null);
    }
    return addProductHandler.execute(user, req) ;
  }

  @Autowired
  private GetProductListHandler getProductListHandler;

  /* 등록 상품 리스트 불러오기 */
  @RequestMapping(method = RequestMethod.GET, value = "product/list")
  @ApiOperation(value = "Get product list")
  GetProductListResponse getProductList(@AuthenticationPrincipal Authentication auth, @RequestParam(value = "pageNo", required = true) int pageNo, @RequestParam(value = "pageSize", required = true) int pageSize, HttpServletRequest request) {
    getProductListHandler.setHttpServletRequest(request);
    CustomUserDetails user = null;
    if (auth == null) {
      throw new RestClientResponseException("", HttpStatus.UNAUTHORIZED.value(), "", null, null, null);
    }
    user = (CustomUserDetails) auth.getPrincipal();
    if (user == null) {
      throw new RestClientResponseException("", HttpStatus.UNAUTHORIZED.value(), "", null, null, null);
    }
    GetProductListRequest req = new GetProductListRequest();
    req.setPageNo(pageNo);
    req.setPageSize(pageSize);
    if (checkRoute(RequestMethod.GET, "/api/v1/seller/product/list", user) == false) {
      throw new RestClientResponseException("", HttpStatus.UNAUTHORIZED.value(), "", null, null, null);
    }
    return getProductListHandler.execute(user, req);
  }

  @Autowired
  private GetProductDetailHandler getProductDetailHandler;

  /* 등록 상품 불러오기 */
  @RequestMapping(method = RequestMethod.GET, value = "product/modify")
  @ApiOperation(value = "Get Product Detail")
  GetProductDetailResponse GetProductDetail(@AuthenticationPrincipal Authentication auth, @RequestBody GetProductDetailRequest req, HttpServletRequest request) {
    getProductDetailHandler.setHttpServletRequest(request);
    CustomUserDetails user = null;
    if (auth == null) {
      throw new RestClientResponseException("", HttpStatus.UNAUTHORIZED.value(), "", null, null, null);
    }
    user = (CustomUserDetails) auth.getPrincipal();
    if (user == null) {
      throw new RestClientResponseException("", HttpStatus.UNAUTHORIZED.value(), "", null, null, null);
    }
    if (checkRoute(RequestMethod.GET, "/api/v1/seller/product/modify", user) == false) {
      throw new RestClientResponseException("", HttpStatus.UNAUTHORIZED.value(), "", null, null, null);
    }
    return getProductDetailHandler.execute(user, req);
  }

  @Autowired
  private ModifyProductHandler modifyProductHandler;

  /* 등록 상품 수정하기 */
  @RequestMapping(method = RequestMethod.PUT, value = "product/modify")
  @ApiOperation(value = "Product Modification")
  ModifyProductResponse modifyProduct(@AuthenticationPrincipal Authentication auth, @RequestBody ModifyProductRequest req, HttpServletRequest request) {
    modifyProductHandler.setHttpServletRequest(request);
    CustomUserDetails user = null;
    if (auth == null) {
      throw new RestClientResponseException("", HttpStatus.UNAUTHORIZED.value(), "", null, null, null);
    }
    user = (CustomUserDetails) auth.getPrincipal();
    if (user == null) {
      throw new RestClientResponseException("", HttpStatus.UNAUTHORIZED.value(), "", null, null, null);
    }
    if (checkRoute(RequestMethod.PUT, "/api/v1/seller/product/modify", user) == false) {
      throw new RestClientResponseException("", HttpStatus.UNAUTHORIZED.value(), "", null, null, null);
    }
    return modifyProductHandler.execute(user, req);
  }

  @Autowired
  private DeleteProductHandler deleteProductHandler;

  /* 등록 상품 삭제하기 */
  @RequestMapping(method = RequestMethod.DELETE, value = "product/delete")
  @ApiOperation(value = "Product delete")
  DeleteProductResponse deleteProduct(@AuthenticationPrincipal Authentication auth, @RequestBody DeleteProductRequest req, HttpServletRequest request) {
    deleteProductHandler.setHttpServletRequest(request);
    CustomUserDetails user = null;
    if (auth == null) {
      throw new RestClientResponseException("", HttpStatus.UNAUTHORIZED.value(), "", null, null, null);
    }
    user = (CustomUserDetails) auth.getPrincipal();
    if (user == null) {
      throw new RestClientResponseException("", HttpStatus.UNAUTHORIZED.value(), "", null, null, null);
    }
    if (checkRoute(RequestMethod.DELETE, "/api/v1/seller/product/delete", user) == false) {
      throw new RestClientResponseException("", HttpStatus.UNAUTHORIZED.value(), "", null, null, null);
    }
    return deleteProductHandler.execute(user, req);
  }

}
