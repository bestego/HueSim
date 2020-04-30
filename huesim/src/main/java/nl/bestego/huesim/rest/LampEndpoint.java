package nl.bestego.huesim.rest;

import lombok.extern.slf4j.Slf4j;
import nl.bestego.huesim.control.LampService;
import nl.bestego.huesim.model.Lamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class LampEndpoint {

    @Autowired
    LampService service;

    @GetMapping("/lamp/{id}")
    public @ResponseBody
    Lamp getStatusLamp(@PathVariable Long id) {
        //System.out.println("getStatusLamp: /lamp/" + id);
        log.info("getStatusLamp: /lamp/{}", id);
        return service.statusLamp(id);
    }

    @GetMapping("/lamp")
    public @ResponseBody
    List<Lamp> getStatusLampen() {
        //System.out.println("getStatusLampen: /lamp");
        log.info("getStatusLampen: /lamp");
        return service.statusLampen();
    }

    @PutMapping("/lamp/{id}")
    public @ResponseBody
    ResponseEntity<String> wijzigNaamLamp(@RequestBody @Valid Lamp lamp, @PathVariable Long id) {
        //System.out.println("putWijzigNaamLamp: /lamp/" + id);
        if (service.wijzigNaamLamp(lamp, id)) {
            log.info("putWijzigNaamLamp: /lamp/{}", id);
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } else {
            log.warn("putWijzigNaamLamp: /lamp/{}", id);
            return new ResponseEntity<>("NOK", HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/lamp/{id}/status")
    public @ResponseBody
    ResponseEntity<String> wijzigStatusLamp(@RequestBody @Valid Lamp lamp, @PathVariable Long id) { //ToDo: test with incomplete body (empty omschrijving)
        System.out.println("putWijzigNaamLamp: /lamp/" + id + "/status");
        if (service.wijzigStatusLamp(lamp, id)) {
            log.info("putWijzigNaamLamp: /lamp/{}/status", id);
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } else {
            log.warn("putWijzigNaamLamp: /lamp/{}/status", id);
            return new ResponseEntity<>("NOK", HttpStatus.NO_CONTENT);
        }
    }

} // LampEndpoint
