package org.scoula.advice;

// Aspect : 공통 관심사(로깅(이 프로젝트에선))를 모아놓은 모듈

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Log4j2
@Component
public class LogAdvice {

    // Advice : Target에다가 적용시킬 동작들 (언제, 어떤 행동을 할 지 등등)

    // Before Advice : 대상 메서드(Target) 실행 직전에 동작
    
    // execution : Target을 지정하는 방법
    // execution([반환타입] [클래스 풀네임].[메서드명])
    @Before(value =
            "execution(* org.scoula.sample.service.SampleService*.doAdd(String, String)) " +
                    "&& args(str1,str2)", argNames = "str1,str2"
    )
    public void logBeforeWithParam(String str1, String str2) {
        log.info("Before Advice 동작합니다 ....!");
        log.info("str1 : {}", str1);
        log.info("str2 : {}", str2);
    }

    // *(..) : 해당 클래스의 모든 메서드, 모든 파라미터 적용
    @AfterThrowing(value =
            "execution(* org.scoula.sample.service.SampleService*.*(..)) ",
            throwing = "exception"
    )
    public void afterThrowingTest(Exception exception) {
        log.info("After throwing 동작합니다 ....!");
        log.info("execution : {}", exception.getMessage());

    }



    @Around(value =
            "execution(* org.scoula.sample.service.SampleService*.*(..)) "
    )
    public Object aroundTest(ProceedingJoinPoint pjp) {

        long start = System.currentTimeMillis();
        log.info("After throwing 동작합니다 ....!");

        log.info("Target : {}", pjp.getTarget());

        Object result = null;
        // 실제 메서드 호출 필요
        try {
            result = pjp.proceed(); // 실제 Target 메서드 호출
        } catch (Throwable e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();

        log.info("실제 소요 시간 : {}", end-start);

        return result;
    }
}
