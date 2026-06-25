package org.scoula.security.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

/*
Spring Security의 보안 설정 클래스
 */
@Configuration
@EnableWebSecurity // 필터 체인 활성화
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public CharacterEncodingFilter encodingFilter() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        return encodingFilter;
    }

    /*
    CSRF : 로그인한 사용자를 악의적인 사이트에서 몰래 요청을 보내게하는 공격
     */
    // 시큐리티 세팅할 공간
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // CSRF 필터 앞에다 encodingFilter를 놓겠다.
        http.addFilterBefore(encodingFilter(), CsrfFilter.class);

        // URL별 접근 권할 설정
        http.authorizeRequests()
                .antMatchers("/security/all")
                    .permitAll() // 모든 권한 접근 허용
                .antMatchers("/security/admin")
                    .access("hasRole('ROLE_ADMIN')")
                .antMatchers("/security/member")
                    .access("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')");

        http.formLogin() // form 기반 로그인 활성화
                .loginPage("/security/login") // 로그인 페이지(커스텀) 이동
                .loginProcessingUrl("/security/login") // 스프링 기본제공 POST 요청 시 로그인 시도
                .defaultSuccessUrl("/");

        http.logout()
                .logoutUrl("/security/logout") // POST로 요청을 보내면 로그아웃 시도
                .invalidateHttpSession(true) // 세션 초기화
                .deleteCookies("JSESSIONID") // 삭제할 쿠키
                .logoutSuccessUrl("/"); // 로그아웃 성공하면 이동할 페이지
    }

    // 테스트용으로 메모리 상에 사용자 정보 등록 -> db 사용 x
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // 관리자 계정
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("$2a$10$KN3iA9XnNUcbmYT6GbdQDuv4d3GElyA.I/4sIzIulmmLfAZYoLNAS")
                .roles("ADMIN","MEMBER");

        // 일반 회원
        auth.inMemoryAuthentication()
                .withUser("member")
                .password("1234") // 메모리에 암호화된 비밀번호가 저장된것이 아니라 원문 그대로 저장되어 메모리상 패스워드와 form에서 입력을 준 패스워드(암호화됨)가 매칭이 안되어 로그인 불가
                .roles("MEMBER");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
