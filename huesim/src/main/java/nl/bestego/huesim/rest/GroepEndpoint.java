package nl.bestego.huesim.rest;

import nl.bestego.huesim.control.GroepService;
import nl.bestego.huesim.model.Groep;
import nl.bestego.huesim.model.Lamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroepEndpoint {

    @Autowired
    GroepService service;

    @GetMapping("/groep")
    public @ResponseBody
    List<Groep> StatusGroepen() {
        System.out.println("StatusGroepen: /groep");
        return service.statusGroepen();
    }

    @GetMapping("/groep/{id}")
    public @ResponseBody
    Groep StatusGroep(@PathVariable Long id) {
        System.out.println("StatusGroep: /lamp/" + id);
        return service.statusGroep(id);
    }

    @PutMapping("/groep/{id}")
    public @ResponseBody
    ResponseEntity<String> wijzigNaamGroep(@RequestBody Groep groep, @PathVariable Long id) {
        System.out.println("WijzigNaamGroep: /lamp/" + id);
        if ( service.wijzigNaamGroep(groep, id) ){
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("NOK", HttpStatus.NO_CONTENT);
        }
    }
}
