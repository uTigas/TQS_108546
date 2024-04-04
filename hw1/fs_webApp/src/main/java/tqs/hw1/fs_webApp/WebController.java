package tqs.hw1.fs_webApp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

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
        System.out.println(restTemplate.getForObject(uri + "terminals",JsonNode.class));
        model.addAttribute("terminals", restTemplate.getForObject(uri + "terminals",JsonNode.class));
        return "terminals";
    }
}
