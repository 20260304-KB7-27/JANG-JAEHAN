package org.scoula.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

// DispatcherServlet 설정 (FrontController)
@EnableWebMvc
// 안에 @Configuration 포함돼있음, Spring MVC 기능 활성화 -> 모든 요청이 DispatcherServlet을 거치게 됨.
// @Controller 인식 / @RequestMapping, @GetMapping
// ViewResolver 설정
@ComponentScan(basePackages = {
        "org.scoula.controller",
        "org.scoula.exception"
}) // Spring MVC용 컴포넌트 등록을 위한 스캔 패키지
public class ServletConfig implements WebMvcConfigurer { // WebMvcConfigurer 상속

    // 정적 리소스 (이미지, css, js 등)은 컨트롤러에서 처리하지 않기 위함.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/**") // url이 /resources/로 시작하는 모든 경로 *-> 해당레벨, **-> 하위 폴더 포함 모든 파일
                .addResourceLocations("/resources/"); // webapp/resources/경로로 매핑
    }

    // jsp 쓰기 위한 view Resolver 설정
    // -> 컨트롤러가 return 한 view 이름을 실제 JSP 파일 경로로 변환해주는 역할
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();

        // jsp파일을 view로 활용함
        bean.setViewClass(JstlView.class);
        bean.setPrefix("/WEB-INF/views/");
        bean.setSuffix(".jsp");

        registry.viewResolver(bean);
    }

    // MultipartResolver -> 이미지나, 영상 multipart/form-data오는 데이터를 처리하는 resolver
    // 빈으로 등록하기
    @Bean
    public MultipartResolver multipartResolver() {
        StandardServletMultipartResolver resolver = new StandardServletMultipartResolver();
        return resolver;
    }
}
