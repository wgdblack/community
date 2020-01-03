package life.majiang.community.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")//只有/的时候可以直接返回根目录
    public String index(){
        return "index";
    }
}
