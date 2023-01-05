package com.laonworks.shop.api.controller.handler.auth;

import com.laonworks.shop.api.controller.ResultCode;
import com.laonworks.shop.api.controller.handler.BaseHandler;
import com.laonworks.shop.api.controller.request.auth.RefreshRequest;
import com.laonworks.shop.api.controller.response.auth.RefreshResponse;
import com.laonworks.shop.api.controller.utils.AuthUtils;
import com.laonworks.shop.api.mapper.AuthMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;


@Component
@Slf4j
public class RefreshHandler extends BaseHandler {

    @Autowired
    AuthMapper authMapper;


    public RefreshResponse excute(RefreshRequest req) {

        RefreshResponse res = new RefreshResponse();
        HashMap<String,String> map = new HashMap<>();
        String adminId = null;

        String userid = req.getUserid();
        String usertype = req.getUsertype();
        map.put("userType",usertype);
        map.put("userId",userid);

        String refreshToken = authMapper.selectRefreshToken(map);

        // refreshToken이 존재하지 않을 경우
        if(refreshToken == null){
            res.setCode(ResultCode.Failed);
            return res;
        }
        // 존재할 경우
        else {
            res.setRefreshToken(refreshToken);
        }

        boolean tokenFl = false;
        try {
            refreshToken = refreshToken;
            adminId = AuthUtils.validateRefreshToken(refreshToken);
            System.out.println("adminId --> " + adminId);
            tokenFl = true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        String accessToken = null;
        // refresh 토큰이 유효하면 accessToken 재생성
        if(adminId != null && !adminId.equals("")){
            accessToken = AuthUtils.CreateAccessToken(userid, Integer.parseInt(usertype));
        }

//         토큰이 유효하지 않기 때문에 로그인 화면으로 가야함
        if(tokenFl == false){
            res.setCode(ResultCode.Failed);
            return res;
        }

        res.accessToken = accessToken;
        res.refreshToken = refreshToken;
        res.setCode(ResultCode.Success);


        return res;


    }
}
