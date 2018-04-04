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
    public List<Samochod> populateCoins() {
        return this.gieldaService.findAll();
    }

//    @ModelAttribute("coinsToSell")
//    public List<Samochod> populateCoinsToSell() {
//        return this.gieldaService.findAllToSell();
//    }

//    @ModelAttribute("coinsLast3")
//    public List<Samochod> populateLast3Coins() {
//        return this.gieldaService.findLatest3();
//    }

    @RequestMapping({ "/", "/index" })
    public String index(Model model) {
        return "th/index";
    }

    @RequestMapping(value = "/samochody", method = RequestMethod.GET)
    public String showMainPage(Model model) {
        model.addAttribute("MyMessages",  notificationService.getNotificationMessages());
        return "th/gielda";
    }

    @RequestMapping("/tosell")
    public String showToSellPage() {
        return "th/tosell";
    }

}
