package nl.bestego.huesim.util;

import nl.bestego.huesim.dto.LampDTO;
import nl.bestego.huesim.model.Lamp;

import java.util.ArrayList;
import java.util.List;

public class LampUtil {

    public static int COLLECTION_COUNT = 3;

    public static LampDTO nieuweLamp(Long id) {
        LampDTO lamp = new LampDTO();
        lamp.setId(id);
        lamp.setAan(false);
        lamp.setHelderheid(50);
        return lamp;
    }

    public static List<LampDTO> nieuweLampen() {
        List<LampDTO> lampen = new ArrayList<>();
        for (long i = 1; i <= COLLECTION_COUNT; i++) {
            lampen.add(nieuweLamp(i));
        }
        return lampen;
    }
}
