package com.laonworks.shop.api.controller.request.auth;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

@ApiModel(description = "refreshToken")
@Data
@ToString
public class RefreshRequest{

    public String usertype;

    public String userid;
}
