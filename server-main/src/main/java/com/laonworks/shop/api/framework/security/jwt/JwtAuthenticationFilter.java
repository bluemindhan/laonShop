package com.laonworks.shop.api.framework.security.jwt;

import com.laonworks.shop.api.controller.utils.AuthUtils;
import com.laonworks.shop.api.service.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final String token = ((HttpServletRequest) request).getHeader("Authorization");
        if (token != null && AuthUtils.validateToken(token)) {
            final String userPk = AuthUtils.parseToken(token);
            String tokens[] = userPk.split(":");
            CustomUserDetails user = new CustomUserDetails();
            user.setUsername(tokens[0]);
            user.setUserType(Integer.parseInt(tokens[1]));
            Authentication authentication = new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }
}
