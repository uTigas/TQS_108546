package tqs.hw1.fs_webApp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;


@Controller
@RequestMapping("/")

public class WebController {
    private final RestTemplate restTemplate = new RestTemplate();
    private String uri="http://localhost:8080/api/v1/";
    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("origins", restTemplate.getForObject(uri + "origins",List.class));
        model.addAttribute("destinations", restTemplate.getForObject(uri + "destinations",List.class));

        return "home";
    }

    @GetMapping("/terminals")
    public String terminals(Model model){
        model.addAttribute("terminals", restTemplate.getForObject(uri + "terminals",List.class));
        return "terminals";
    }

    @GetMapping("/results")
    public String results(@RequestParam String origin, @RequestParam String destination, Model model){
        Map<String, String> params = new HashMap<String,String>();
        params.put("origin", origin);
        params.put("destination", destination);
        model.addAttribute("connections", restTemplate.getForObject(uri + "connections?origin={origin}&destination={destination}",List.class, params));
        return "results";
    }
}
