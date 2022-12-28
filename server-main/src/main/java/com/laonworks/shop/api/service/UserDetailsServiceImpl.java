package com.laonworks.shop.api.service;

import com.laonworks.shop.api.controller.vo.UserType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    public UserDetails loadUserByUsername(String userPk) {
        String tokens[] = userPk.split(":");
        CustomUserDetails user = new CustomUserDetails();
        user.setUsername(tokens[0]);
        if (tokens[1].equals(UserType.User.getValue())) {
            user.setUserType(UserType.User.getValue());
        } else if (tokens[1].equals(UserType.Seller.getValue())) {
            user.setUserType(UserType.Seller.getValue());
        } else {
            return null;
        }
        return user;
    }
}
