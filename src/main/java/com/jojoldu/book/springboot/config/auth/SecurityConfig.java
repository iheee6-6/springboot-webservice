package com.jojoldu.book.springboot.config.auth;

import com.jojoldu.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@RequiredArgsConstructor
@EnableWebSecurity //스프링 시큐리티 설정들을 활성화시킴
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().headers().frameOptions().disable() //h2-console화면을 사용하기 위해 해당 옵션들을 disable()하는 것!
                .and()
                    .authorizeRequests() //url별 권한 관리를 설정하는 옵션의 시작점 -> antMatchers옵션을 사용할 수 있음
                    //antMatchers : 권한 관리 대상을 지정한느 옵션! url,http 메소드별로 관리가 가능함. "/" 등 지정된 url들은 permitAll()옵션을 통해 전체 열람 권한을 줌
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // /api/v1/**주소를 가진 api는 user권한을 가진 사람만 가능하도록 함!
                    .anyRequest().authenticated() //anyRequest() : 설정된 값들 이외 나머지 url을 나타냄 , authenticated(): 나머지 url은 로그인한 사용자만 허용하도록하는 것
                .and()
                    .logout().logoutSuccessUrl("/")  //로그아웃 기능에 대한 여러 설정의 진입점. 로그아웃 성공시 "/"로 이동!
                .and()
                    .oauth2Login() //로그인 기능에 대한 여러 설정의 진입점
                        .userInfoEndpoint() // 로그인 성공 이후 사용자 정보를 가져올때의 설정들을 담당함.
                            .userService(customOAuth2UserService); //로그인 성공시 조치할 UserService인터페이스 구현체를 등록! 소셜서비스에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시할 수 있음.
    }
}
