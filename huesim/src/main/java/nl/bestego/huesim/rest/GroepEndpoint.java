package nl.bestego.huesim.rest;

import lombok.extern.slf4j.Slf4j;
import nl.bestego.huesim.control.GroepService;
import nl.bestego.huesim.dto.GroepDTO;
import nl.bestego.huesim.dto.LampDTO;
import nl.bestego.huesim.model.Groep;
import nl.bestego.huesim.model.Lamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@Slf4j
public class GroepEndpoint {

    @Autowired
    GroepService service;

    @GetMapping("/groep")
    public @ResponseBody
    List<GroepDTO> StatusGroepen() {
        System.out.println("StatusGroepen: /groep");
        return service.statusGroepen();
    }

    @GetMapping("/groep/{id}")
    public @ResponseBody
    ResponseEntity<GroepDTO> StatusGroep(@PathVariable Long id) {
        log.info("StatusGroep: /groep/{id}", id);
        GroepDTO groepDTO = service.statusGroep(id);
        if (groepDTO.getId() != null){
            return new ResponseEntity<>(groepDTO,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(groepDTO,HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/groep/{id}/lamp")
    public @ResponseBody
    ResponseEntity<String> voegLampToe(@RequestBody Lamp lamp, @PathVariable Long id) {
        log.info("voegLampToe: /groep/{}/lamp", id);
        if (service.voegLampToe(lamp, id)) {
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("NOK", HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/groep/{id}")
    public @ResponseBody
    ResponseEntity<String> wijzigNaamGroep(@RequestBody Groep groep, @PathVariable Long id) {
        System.out.println("WijzigNaamGroep: /lamp/" + id);
        if (service.wijzigNaamGroep(groep, id)) {
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("NOK", HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/groep/{id}/actie")
    public @ResponseBody
    ResponseEntity<String> actieGroep(@RequestBody @Valid Lamp lamp, @PathVariable Long id) {
        System.out.println("actieGroep: /groep/" + id + "/actie");
        if (service.actieGroep(lamp, id)) {
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("NOK", HttpStatus.NO_CONTENT);
        }
    }
}
