package nl.bestego.huesim.control;

import nl.bestego.huesim.model.Groep;
import nl.bestego.huesim.model.Lamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;

@Service
@Transactional
public class InitializerService {

    @Autowired GroepService groepService;
    @Autowired LampService lampService;

    public void laadGegevens() {

        Lamp lamp1=new Lamp(1L,"lamp-1",false,25);
        lampService.nieuweLamp(lamp1);
        Lamp lamp2=new Lamp(2L,"lamp-2",true,50);
        lampService.nieuweLamp(lamp2);
        Lamp lamp3=new Lamp(3L,"lamp-3",true,75);
        lampService.nieuweLamp(lamp3);

        groepService.nieuweGroep(new Groep(0L,"allemaal"));
        groepService.nieuweGroep(new Groep(1L,"groep-1"));
        groepService.nieuweGroep(new Groep(2L,"groep-2"));
//        groepService.voegLampToe(lamp1,0L);
//        groepService.voegLampToe(lamp2,0L);
//        groepService.voegLampToe(lamp3,0L);
    }
}
