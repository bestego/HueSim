package nl.bestego.huesim.util;

import nl.bestego.huesim.dto.LampDTO;
import nl.bestego.huesim.model.Lamp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LampUtil {

    public static int COLLECTION_COUNT = 3;

    private static Lamp nieuweLamp(Long id) {
        Lamp lamp = new Lamp();
        lamp.setId(id);
        lamp.setOmschrijving("lamp-" + id);
        lamp.setAan(true);
        lamp.setHelderheid(55);
        return (lamp);
    }

    public static Optional<Lamp> nieuweOptionalLamp(Long id) {
        return Optional.of(nieuweLamp(id));
    }

    public static LampDTO nieuweLampDTO(Long id) {
        LampDTO lamp = new LampDTO();
        lamp.setId(id);
        lamp.setOmschrijving("lamp-" + id);
        lamp.setAan(true);
        lamp.setHelderheid(55);
        lamp.setGroepLijst((id + 1) + "," + "id+2");
        return lamp;
    }

    public static List<LampDTO> nieuweLampenDTO() {
        List<LampDTO> lampen = new ArrayList<>();
        for (long i = 1; i <= COLLECTION_COUNT; i++) {
            LampDTO lampDTO = new LampDTO();
            lampDTO.setId(i);
            lampDTO.setOmschrijving("lamp-" + i);
            lampDTO.setAan(true);
            lampDTO.setHelderheid((int) (50 + i));
            lampen.add(lampDTO);
        }
        return lampen;
    }

}
