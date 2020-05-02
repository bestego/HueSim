package nl.bestego.huesim.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import nl.bestego.huesim.model.Groep;
import nl.bestego.huesim.model.Lamp;
import nl.bestego.huesim.control.GroepService;
import nl.bestego.huesim.util.Converter;

import java.util.Set;
import java.util.stream.Collectors;

@Data
public class GroepDTO {

    public GroepDTO() {
    }

    public GroepDTO(Groep groep) {
        this.id = groep.getId();
        this.omschrijving = groep.getOmschrijving();
        //this.lampLijst = Converter.set2csv(groep.getLampen().stream().map(l->l.getId()).collect(Collectors.toSet()));
        this.enkele_aan = groep.isEnkele_aan();
        this.alle_aan = groep.isAlle_aan();
    }

    private Long id;
    private String omschrijving;
    private String lampLijst;
    private boolean enkele_aan;
    private boolean alle_aan;

}
