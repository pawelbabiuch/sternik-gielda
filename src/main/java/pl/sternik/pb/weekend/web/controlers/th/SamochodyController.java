package pl.sternik.pb.weekend.web.controlers.th;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.sternik.pb.weekend.entities.Samochod;
import pl.sternik.pb.weekend.entities.Status;
import pl.sternik.pb.weekend.services.GieldaService;
import pl.sternik.pb.weekend.services.NotificationService;
import pl.sternik.pb.weekend.services.NotificationService.NotificationMessage;

@Controller
public class SamochodyController {

    @Autowired
    // @Qualifier("spring-data")
    @Qualifier("tablica")
    // @Qualifier("lista")
    private GieldaService gieldaService;

    @Autowired
    private NotificationService notifyService;

    @ModelAttribute("statusyAll")
    public List<Status> populateStatusy() {
        return Arrays.asList(Status.ALL);
    }
    
    @ModelAttribute("MyMessages")
    public List<NotificationMessage> populateMessages() {
        System.out.println("dupa");
        return notifyService.getNotificationMessages();
    }
    

    @GetMapping(value = "/samochody/{id}")
    public String view(@PathVariable("id") long id, final ModelMap model) {
        Optional<Samochod> result;
        result = gieldaService.findByVin(id);
        if (result.isPresent()) {
            Samochod samochod = result.get();
            model.addAttribute("samochod", samochod);
            return "th/samochod";
        } else {
            notifyService.addErrorMessage("Cannot find samochod #" + id);
            model.clear();
            return "redirect:/samochody";
        }
    }

    @RequestMapping(value = "/samochody/{id}/json", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Samochod> viewAsJson(@PathVariable("id") long id, final ModelMap model) {
        Optional<Samochod> result;
        result = gieldaService.findByVin(id);
        if (result.isPresent()) {
            Samochod samochod = result.get();
            return new ResponseEntity<Samochod>(samochod, HttpStatus.OK);
        } else {
            notifyService.addErrorMessage("Cannot find samochod #" + id);
            model.clear();
            return new ResponseEntity<Samochod>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/samochody", params = { "save" }, method = RequestMethod.POST)
    public String saveSamochod(Samochod samochod, BindingResult bindingResult, ModelMap model) {
        // @Valid
        if (bindingResult.hasErrors()) {
            notifyService.addErrorMessage("Please fill the form correctly!");
            model.addAttribute("MyMessages",  notifyService.getNotificationMessages());
            return "th/samochod";
        }
        
        if (samochod.getStatus() == Status.NOWA) {
            samochod.setStatus(Status.UZYWANA);
        }
        
        Optional<Samochod> result = gieldaService.edit(samochod);
        if (result.isPresent())
            notifyService.addInfoMessage("Zapis udany");
        else
            notifyService.addErrorMessage("Zapis NIE udany");
        model.clear();
       
        return "redirect:/samochody";
    }

    @RequestMapping(value = "/samochody", params = { "create" }, method = RequestMethod.POST)
    public String createSamochod(Samochod samochod, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            notifyService.addErrorMessage("Please fill the form correctly!");
            model.addAttribute("MyMessages",  notifyService.getNotificationMessages());
            return "th/samochod";
        }
        gieldaService.create(samochod);
        model.clear();
        notifyService.addInfoMessage("Zapis nowej udany");
        return "redirect:/samochody";
    }

    @RequestMapping(value = "/samochody", params = { "remove" }, method = RequestMethod.POST)
    public String removeRow(final Samochod samochod, final BindingResult bindingResult, final HttpServletRequest req) {
        final Integer rowId = Integer.valueOf(req.getParameter("remove"));
        Optional<Boolean> result = gieldaService.deleteByVin(rowId.longValue());
        return "redirect:/samochody";
    }

    @RequestMapping(value = "/samochody/create", method = RequestMethod.GET)
    public String showMainPages(final Samochod samochod) {
        samochod.setData(Calendar.getInstance().getTime());
        return "th/samochod";
    }
}