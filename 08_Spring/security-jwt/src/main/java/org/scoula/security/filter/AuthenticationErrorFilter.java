package org.scoula.security.filter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.scoula.security.util.JsonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// JWT 인증 예외 처리 필터
@Component
public class AuthenticationErrorFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            // 다음 순서의 필터에서 예외가 발생하면 이 위치에서 예외를 처리하겠다.
            super.doFilter(request, response, filterChain);
        } catch (ExpiredJwtException e) {
            // -> 실제 개발 시엔 토큰 만료에 대한 재발급 로직 ...
            JsonResponse.sendError(
                    response,
                    HttpStatus.UNAUTHORIZED,
                    "토큰의 유효시간이 만료됨");
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException e) {
            // 지원하지 않는 형식이거나 구조가 깨진 토근 401
            JsonResponse.sendError(
                    response,
                    HttpStatus.UNAUTHORIZED,
                    e.getMessage());
        } catch (JwtException e) { // 그외 모든 JWT 관련 예외
            JsonResponse.sendError(
                    response,
                    HttpStatus.UNAUTHORIZED,
                    e.getMessage());
        } catch (ServletException e) {
            // 그외 서블릿 레벨 예외 (500)
            JsonResponse.sendError(
                    response,
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage());
        }

    }
}
