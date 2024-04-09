package tqs.hw1.fs_webApp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import tqs.hw1.fs_webApp.data.entity.Reservation;
import tqs.hw1.fs_webApp.support.PurchaseForm;


@Controller
@RequestMapping("/")

public class WebController {
    @Autowired
    private HttpServletRequest request;

    private final RestTemplate restTemplate = new RestTemplate();
    private String uri;

    @ModelAttribute
    public void setUri() {
        int port = request.getServerPort();
        uri = "http://localhost:" + port + "/api/v1/";
    }
    
    @GetMapping("/")
    public String home(Model model, HttpSession session){

        model.addAttribute("origins", restTemplate.getForObject(uri + "origins",List.class));
        model.addAttribute("destinations", restTemplate.getForObject(uri + "destinations",List.class));

        return "home";
    }

    @GetMapping("/tickets")
    public String tickets(@RequestParam(required = false) String token,  Model model){
        if (token != null){
            Map<String, String> params = new HashMap<String,String>();
            params.put("token", token);
            Reservation res= restTemplate.getForObject(uri + "tickets?token={token}",Reservation.class, params);
            model.addAttribute("ticket", res);
        }
        return "tickets";
    }

    @GetMapping("/terminals")
    public String terminals(Model model){
        model.addAttribute("terminals", restTemplate.getForObject(uri + "terminals",List.class));
        return "terminals";
    }

    @GetMapping("/results")
    public String results(@RequestParam String origin, @RequestParam String destination, @RequestParam String fromDate, @RequestParam String toDate, @RequestParam Boolean findReturn, Model model){
        Map<String, String> params = new HashMap<String,String>();
        params.put("origin", origin);
        params.put("destination", destination);
        if (findReturn){
            model.addAttribute("findReturn", true);
        }
        else
            model.addAttribute("findReturn", false);
        if (fromDate != ""){
            params.put("fromDate", fromDate);
            if (toDate != ""){
                params.put("toDate", toDate);
                model.addAttribute("connections", restTemplate.getForObject(uri + "connections?origin={origin}&destination={destination}&fromDate={fromDate}&toDate={toDate}",List.class, params));
                model.addAttribute("returnConnections", restTemplate.getForObject(uri + "connections?origin={destination}&destination={origin}&fromDate={fromDate}&toDate={toDate}",List.class, params));
            }
            else{
                model.addAttribute("connections", restTemplate.getForObject(uri + "connections?origin={origin}&destination={destination}&fromDate={fromDate}",List.class, params));
                model.addAttribute("returnConnections", restTemplate.getForObject(uri + "connections?origin={destination}&destination={origin}&fromDate={fromDate}",List.class, params));
            }
        }
        else{
            if (toDate != ""){
                params.put("toDate", toDate);
                model.addAttribute("connections", restTemplate.getForObject(uri + "connections?origin={origin}&destination={destination}&toDate={toDate}",List.class, params));
                model.addAttribute("returnConnections", restTemplate.getForObject(uri + "connections?origin={destination}&destination={origin}&toDate={toDate}",List.class, params));
            }
            else{
                model.addAttribute("connections", restTemplate.getForObject(uri + "connections?origin={origin}&destination={destination}",List.class, params));
                model.addAttribute("returnConnections", restTemplate.getForObject(uri + "connections?origin={destination}&destination={origin}",List.class, params));
            }
        }

        return "results";
    }

    @GetMapping("/purchase")
    public String getPurchase(@RequestParam String connectionId,@RequestParam(required = false) String returnId,  Model model){
        model.addAttribute("connectionId", connectionId);
        if (returnId != null)
            model.addAttribute("returnId", returnId);
        else
            model.addAttribute("returnId", "-1");

        return "form";
    }

    @PostMapping("/purchase")
    public String finishPurchase(@ModelAttribute PurchaseForm formData, Model model){

        Reservation ticket = restTemplate.postForObject(uri + "tickets", formData,Reservation.class);
        model.addAttribute("ticket", ticket);
        return "receipt";
    }

    @PostMapping("/currency")
    public String changeCurrency(@RequestParam("currency") String currency, HttpSession session, HttpServletRequest request) {
        session.setAttribute("preferredCurrency", currency);
        String symbol = "";
        switch (currency) {
            case "GBP":
                symbol = "£";
                break;
            case "USD":
                symbol = "$";
                break;
            case "BRL":
                symbol = "R$";
                break;
            default:
                symbol = "€";
                break;
        }
        session.setAttribute("currencySymbol", symbol);

        Double newRate = restTemplate.postForObject(uri + "currency", currency,Double.class);
        session.setAttribute("rate", newRate);

        String referer = request.getHeader("Referer");
        if (referer != null) {
            return "redirect:" + referer;
        } else {
            return "redirect:/";
        }
    }
}
