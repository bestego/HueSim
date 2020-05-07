package nl.bestego.huesim.soap;

import lombok.extern.slf4j.Slf4j;
import nl.bestego.guides.gs_producing_web_service.GetLampRequest;
import nl.bestego.guides.gs_producing_web_service.GetLampResponse;
import nl.bestego.huesim.control.LampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Slf4j
@Endpoint
public class LampEndpointSoap {     // name must be unique in project
    private static final String NAMESPACE_URI = "http://bestego.nl/guides/gs-producing-web-service"; // must match entry in config file

    @Autowired
    private LampService service;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getLampRequest")   // refers to 'element name' in .xsd
    @ResponsePayload
    public GetLampResponse getLamp(@RequestPayload GetLampRequest request) {    // names GetLampResponse/Request can be freely chosen
        log.info("SOAP getLamp ");
        GetLampResponse response = new GetLampResponse();
        response.setLamp(service.dto2soap(service.statusLampDTO(request.getId()))); // ToDo: fix NullpointerException for non-existing lamp
        return response;
    }

}
