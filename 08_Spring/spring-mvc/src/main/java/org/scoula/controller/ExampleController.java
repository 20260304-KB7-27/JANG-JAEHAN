package org.scoula.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Log4j2
@RequestMapping("/example")
public class ExampleController {

    @RequestMapping(value = "", method = RequestMethod.GET) // GET Mapping
    public String hello(Model model) {
        log.info("============> hello");

        model.addAttribute("message", "신규 핸들러 메소드!");
        /*
        Model
        - 컨트롤러가 View로 데이터를 전달할 때 사용하는 객체 (Key - Value)

        ModelAndView
        - 데이터와 이동할 View이름을 같이 반환
         */

        return "page/mappingResult";
    }
}
