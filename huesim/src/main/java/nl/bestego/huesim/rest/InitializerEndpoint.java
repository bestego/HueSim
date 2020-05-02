package nl.bestego.huesim.rest;

import lombok.extern.slf4j.Slf4j;
import nl.bestego.huesim.control.InitializerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class InitializerEndpoint {

    @Autowired
    InitializerService service;

    @GetMapping("/init")
    public void initializeer(){
        log.info("/init");
        service.laadGegevens();
    }




}
