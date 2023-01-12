package com.laonworks.shop.api.controller.request.auth;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "delete account")
public class WithdrawalRequest {

    public boolean valid() {return true; }
    public boolean invalid(){return !valid(); }
}
