package tqs.hw1.fs_webApp;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;


@Controller
@RequestMapping("/")

public class WebController {
    private final RestTemplate restTemplate = new RestTemplate();
    private String uri="http://localhost:8080/api/v1/";
    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("hello", "Hello World!");
        return "home";
    }
    @GetMapping("/terminals")
    public String terminals(Model model){
        model.addAttribute("terminals", restTemplate.getForObject(uri + "terminals",List.class));
        return "terminals";
    }
}
