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
public class UserVo {

  public String userId;

  public String name;

  public String email;

  public String password;

  public String gender;

  public String phone;

  public String birth;

  public String salt;

  public int userType;
}
