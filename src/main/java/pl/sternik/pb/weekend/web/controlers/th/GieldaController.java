package pl.sternik.pb.weekend.web.controlers.th;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.sternik.pb.weekend.entities.Samochod;
import pl.sternik.pb.weekend.services.GieldaService;
import pl.sternik.pb.weekend.services.NotificationService;


@Controller
public class GieldaController {

    @Autowired
//    @Qualifier("spring-data")
    @Qualifier("tablica")
//    @Qualifier("lista")
    private GieldaService gieldaService;

    @Autowired
    private NotificationService notificationService;

//    @ModelAttribute("statusyAll")
//    public List<Status> populateStatusy() {
//        return Arrays.asList(Status.ALL);
//    }

    @ModelAttribute("carsAll")
    public List<Samochod> populateCars() {
        return this.gieldaService.findAll();
    }

    @ModelAttribute("carsToSell")
    public List<Samochod> populateCarsToSell() {
        return this.gieldaService.findAllToSell();
    }
    
    @ModelAttribute("carsCrashed")
    public List<Samochod> populateCarsCrashed() {
        return this.gieldaService.findCrashed();
    }

    @RequestMapping({ "/", "/index" })
    public String index(Model model) {
        return "th/index";
    }

    @RequestMapping(value = "/samochody", method = RequestMethod.GET)
    public String showMainPage(Model model) {
        return "th/gielda";
    }

    @RequestMapping("/tosell")
    public String showToSellPage() {
        return "th/tosell";
    }

    @RequestMapping("/crashed")
    public String showCrashedPage() {
        return "th/crashed";
    }
}
