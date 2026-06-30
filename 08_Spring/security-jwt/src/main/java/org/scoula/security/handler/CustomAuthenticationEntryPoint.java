package org.scoula.security.handler;

import lombok.extern.log4j.Log4j2;
import org.scoula.security.util.JsonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 인증되지 않은 요청의 처리. 보호된 자원에 접근했을 때 예외 처리
@Component
@Log4j2
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // 인증되지 않은 요청이 보호된 자원에 접근했을 떄 발생한 예외를 다루는 핸들러
        log.error("==================== 인증 에러 ====================");
        JsonResponse.sendError(
                response,
                HttpStatus.UNAUTHORIZED,
                authException.getMessage()
        );
    }
}
