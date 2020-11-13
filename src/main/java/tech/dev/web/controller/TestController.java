package tech.dev.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model) {
        model.addAttribute("message", "Spring MVC XML Config Test...");
        return "message";
    }

    @RequestMapping("/msg")
    String message(Model model) {
        model.addAttribute("messageTest", "Spring MVC Thymeleaf Template Example...");
        return "message";
    }
}
