package nl.bestego.huesim.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import nl.bestego.huesim.model.Groep;
import nl.bestego.huesim.model.Lamp;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class GroepDTO {

    public GroepDTO() {
    }

    public GroepDTO(Groep groep) {
        this.id = groep.getId();
        this.omschrijving = groep.getOmschrijving();
        this.lampIds = lampen2lampids(groep.getLampen());
        this.enkele_aan = groep.isEnkele_aan();
        this.alle_aan = groep.isAlle_aan();
    }

    private Long id;
    private String omschrijving;
    private Set<Long> lampIds;
    private boolean enkele_aan;
    private boolean alle_aan;

    private Set<Long> lampen2lampids(Set<Lamp> lampen) {
        return lampen.stream()
                .map(l -> l.getId())
                .collect(Collectors.toSet());
    }

}
