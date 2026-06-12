package org.scoula.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/todo")
public class TodoController {

    // GET /todo/list
    @GetMapping("/list")
    public String getList(Model model) {
        List<String> list = Arrays.asList("Todo 1", "Todo 2", "Todo 3");
        model.addAttribute("todoList", list);
        System.out.println("GET /todo/list");
        return "todo/list";
    }

    // GET /todo/view
    @GetMapping("/view")
    public String getView() {
        System.out.println("GET /todo/view");
        return "todo/view";
    }

    // GET /todo/create
    @GetMapping("/create")
    public String getCreate() {
        System.out.println("GET /todo/create");
        return "todo/create";
    }

    // POST /todo/create
    @PostMapping("/create")
    public String postCreate() {
        System.out.println("POST /todo/create");
        return "redirect:/todo/list";
    }

    // GET /todo/update
    @GetMapping("/update")
    public String getUpdate() {
        System.out.println("GET /todo/update");
        return "todo/update";
    }

    // POST /todo/update
    @PostMapping("/update")
    public String postUpdate() {
        System.out.println("POST /todo/update");
        return "redirect:/todo/list";
    }

    // POST /todo/delete
    @PostMapping("/delete")
    public String postDelete() {
        System.out.println("POST /todo/delete");
        return "redirect:/todo/list";
    }
}
