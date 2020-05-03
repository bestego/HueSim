package nl.bestego.huesim.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import nl.bestego.huesim.model.Groep;
import nl.bestego.huesim.model.Lamp;
import nl.bestego.huesim.control.GroepService;
import nl.bestego.huesim.util.Converter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class GroepDTO {

    private Long id;
    private String omschrijving;
    private String lampLijst;
    private boolean enkele_aan;
    private boolean alle_aan;

    public GroepDTO() {
    }

    public GroepDTO(Groep groep) {

        this.id = groep.getId();
        this.omschrijving = groep.getOmschrijving();

        Set<Long> lampIds = new HashSet<>();
        lampIds = groep.getLampen().stream().map(lamp -> lamp.getId()).collect(Collectors.toSet());
        this.lampLijst = Converter.set2csv(lampIds);

        this.enkele_aan = groep.isEnkele_aan();
        this.alle_aan = groep.isAlle_aan();
    }

}
