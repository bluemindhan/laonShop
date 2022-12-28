package com.laonworks.shop.api.mapper.vo;

import lombok.Data;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import java.sql.Date;
import java.sql.Time;

@Data
@ToString
public class SpSignUpParam {

  public int ret;

  public String email;

  public String password;

  public String nickname;
}
