package nl.bestego.huesim.rest;

import lombok.extern.slf4j.Slf4j;
import nl.bestego.huesim.control.LampService;
import nl.bestego.huesim.dto.LampDTO;
import nl.bestego.huesim.model.Groep;
import nl.bestego.huesim.model.Lamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
public class LampEndpoint {

    @Autowired
    LampService service;

    @GetMapping("/lamp/{id}")
    public @ResponseBody
    LampDTO getStatusLamp(@PathVariable Long id) {
        log.info("getStatusLamp: /lamp/{}", id);
        return service.statusLampDTO(id);
    }

    @GetMapping("/lamp")
    public @ResponseBody
    List<LampDTO> getStatusLampen() {
        //System.out.println("getStatusLampen: /lamp");
        log.info("getStatusLampen: /lamp");
        return service.statusLampenDTO();
    }

    @PutMapping("/lamp/{id}")
    public @ResponseBody
    ResponseEntity<String> wijzigNaamLamp(@RequestBody @Valid Lamp lamp, @PathVariable Long id) {
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

    @PutMapping("/lamp/{id}/groep")
    public @ResponseBody
    ResponseEntity<String> plaatsInGroep(@RequestBody @Valid Groep groep, @PathVariable Long id) {
        log.info("plaatsInGroep: /lamp/{}/groep", id);
        if (service.plaatsInGroep(groep, id)) {
            log.info("plaatsInGroep: /lamp/{}/groep", id);
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } else {
            log.warn("plaatsInGroep: /lamp/{}/groep", id);
            return new ResponseEntity<>("NOK", HttpStatus.NO_CONTENT);
        }
    }

} // LampEndpoint
