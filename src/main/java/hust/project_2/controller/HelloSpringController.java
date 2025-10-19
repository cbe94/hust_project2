package hust.project_2.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloSpringController {
    @PostMapping("/hello")
    String sayHello () {
        return "Hello spring";
    }

}