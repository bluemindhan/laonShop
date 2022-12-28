package com.laonworks.shop.api.controller.vo;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "null")
public enum UserType {

  User(1),

  Seller(2)
  ;

  private final int value;
  UserType(int num) {
    this.value = num;
  }

  public int getValue() {
    return this.value;
  }

  public static UserType parse(int num) {
    for (UserType type: UserType.values()) {
      if (type.getValue() == num) {
        return type;
      }
    }
    return null;
  }
}
