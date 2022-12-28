package com.laonworks.shop.api.controller.response;

import com.laonworks.shop.api.controller.ResultCode;
import lombok.Data;

@Data
public class BaseResponse {
  protected int code = -1;
  protected String message;

  public void setCode(int code) {
    this.code = code;
    this.message = ResultCode.get(code);
  }
}
