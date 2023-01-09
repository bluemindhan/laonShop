package com.laonworks.shop.api.controller.request.wish;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

@ApiModel(description = "post wish")
@Data
@ToString
public class PostWishRequest {
    public boolean valid() {
        return true;
    }
    public boolean invalid() {
        return !valid();
    }
}
