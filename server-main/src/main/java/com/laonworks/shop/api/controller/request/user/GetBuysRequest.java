package com.laonworks.shop.api.controller.request.user;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;

@ApiModel(description = "get buys")
public class GetBuysRequest {

    public boolean valid() {
            return  true;
        }
    public boolean invalid() {
        return !valid();
    }
}
