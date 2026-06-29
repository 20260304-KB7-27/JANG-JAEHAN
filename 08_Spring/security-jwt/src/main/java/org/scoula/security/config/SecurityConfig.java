package org.scoula.security.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.annotation.MapperScan;
import org.scoula.security.filter.JwtUsernamePasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionCreationEvent;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.Arrays;

/*
Spring Security의 보안 설정 클래스
 */
@Configuration
@EnableWebSecurity // 필터 체인 활성화
@RequiredArgsConstructor
@Log4j2
@MapperScan(basePackages = {"org.scoula.security.account.mapper"})
@ComponentScan(basePackages = {"org.scoula.security"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Autowired
    private JwtUsernamePasswordAuthenticationFilter jwtUsernamePasswordAuthenticationFilter;

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
        http.addFilterBefore(encodingFilter(), CsrfFilter.class)
                .addFilterBefore(jwtUsernamePasswordAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class);

        // URL별 접근 권한 설정
        http.authorizeRequests()
                .antMatchers("/security/alle")
                .permitAll()
                .antMatchers("/security/admin")
                .access("hasRole('ROLE_ADMIN')")
                .antMatchers("/security/member")
                .access("hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')");

        // CORS 설정 추가
        http.cors();

        http
                .httpBasic().disable() // 기본 HTTP 인증 비활성화
                .csrf().disable() // csrf 비활성화
                .formLogin().disable() // form Login 비활성화
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 세션 생성 안함

        // URL별 접근 권할 설정
        http.authorizeRequests()
                .antMatchers("/security/all")
                .permitAll() // 모든 권한 접근 허용
                .antMatchers("/security/admin")
                .access("hasRole('ROLE_ADMIN')")
                .antMatchers("/security/member")
                .access("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')");

    }

    // 테스트용으로 메모리 상에 사용자 정보 등록 -> db 사용 x
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//        // 관리자 계정
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password("$2a$10$KN3iA9XnNUcbmYT6GbdQDuv4d3GElyA.I/4sIzIulmmLfAZYoLNAS")
//                .roles("ADMIN","MEMBER");
//
//        // 일반 회원
//        auth.inMemoryAuthentication()
//                .withUser("member")
//                .password("1234") // 메모리에 암호화된 비밀번호가 저장된것이 아니라 원문 그대로 저장되어 메모리상 패스워드와 form에서 입력을 준 패스워드(암호화됨)가 매칭이 안되어 로그인 불가
//                .roles("MEMBER");


        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder()); // userDetailService
    }

    // 커스텀 하기 위한 Authentication 객체를 빈으로 등록
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    // 인증 인가 제외 URL
    // - 보안 검사가 필요없는 정적 리소스나 특정 API는 Security Filter Chain을 거치지 않도록 설정
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(
                        "/assets/**",
                        "/*", // 루트 경로 바로 아래 /login, /member 등
                        "/api/member/**" // /api/member 하위 경로 제외
                );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration(); // CORS 설정 객체

        // ORIGIN -> 출처
        configuration.setAllowedOriginPatterns(Arrays.asList("*")); // 모든 출처에 대해 허용

        // 허용할 HTTP 메서드 목록
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));

        // 모든 요청 헤더 허용
        configuration.setAllowedHeaders(Arrays.asList("*"));

        // 쿠키, Authorization 헤더 등 자격 증명을 포함한 요청 허용
        configuration.setAllowCredentials(true);

        // 특정 URL 경로 패턴에 대해 CORS 설정 적용
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);

        return source; // Security
    }
}
