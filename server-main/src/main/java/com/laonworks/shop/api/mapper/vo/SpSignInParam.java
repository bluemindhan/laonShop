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
public class SpSignInParam {

  public int ret;

  public String email;
}
