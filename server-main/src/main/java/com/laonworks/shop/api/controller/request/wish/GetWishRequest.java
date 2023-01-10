package com.laonworks.shop.api.controller.request.wish;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

@ApiModel(description = "get wishs")
@Data
@ToString
public class GetWishRequest {

    public boolean valid() {
        return true;
    }

    public boolean invalid() {
        return !valid();
    }
}
