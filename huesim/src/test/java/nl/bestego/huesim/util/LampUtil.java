package nl.bestego.huesim.util;

import nl.bestego.huesim.dto.LampDTO;
import nl.bestego.huesim.model.Lamp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LampUtil {

    public static int COLLECTION_COUNT = 3;

    public static LampDTO nieuweLampDTO(Long id) {
        LampDTO lamp = new LampDTO();
        lamp.setId(id);
        lamp.setOmschrijving("lamp-"+id);
        lamp.setAan(false);
        lamp.setHelderheid(50);
        return lamp;
    }

    public static List<LampDTO> nieuweLampenDTO() {
        List<LampDTO> lampen = new ArrayList<>();
        for (long i = 1; i <= COLLECTION_COUNT; i++) {
            lampen.add(nieuweLampDTO(i));
        }
        return lampen;
    }

    public Optional<Lamp> nieuweLamp(Long id){
        Lamp lamp = new Lamp();
        lamp.setId(id);
        lamp.setOmschrijving("lamp-"+id);
        lamp.setAan(true);
        lamp.setHelderheid(55);
        return Optional.of(lamp);
    }
}
