package com.laonworks.shop.api.framework.security;

import com.laonworks.shop.api.framework.security.jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
//@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**",
                "/js/**",
                "/img/**",
                "/lib/*8",
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources",
                "/configuration/security",
                "/swagger-ui.html",
                "webjars/**",
                "/swagger/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // return new BCryptPasswordEncoder();
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 필터 등록
        http
                .httpBasic().disable() // REST API만을 고려, 기본 설정 해제
                .csrf().disable() // csrf 사용 X
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests() // 요청에 의한 보안 검사 시작
                .anyRequest().permitAll() // 어떤 요청에도 보안검사를 한다
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class); // 커스텀 필터를 추가 (UsernamePasswordAuthenticationFilter)보다 먼저 실행된다

        // 토큰 기반 인증이므로 세션도 사용 X
//                .and()
//                .authorizeRequests() // 요청에 대한 사용권한 체크
//                .antMatchers("/admin/**").hasRole("ROLE_ADMIN")
//                .antMatchers("/user/**").hasRole("ROLE_MEMBER")
//                .anyRequest().permitAll() // 나머지 요청은 누구나 접근 가능
//                .and()
//                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
//                        UsernamePasswordAuthenticationFilter.class);
        // JwtAuthenticationFilter는
        // UsernamePasswordAuthenticationFilter 전에 넣음
    }

}
