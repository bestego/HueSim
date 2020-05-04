package nl.bestego.huesim.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import nl.bestego.huesim.model.Groep;
import nl.bestego.huesim.model.Lamp;
import nl.bestego.huesim.model.validation.Range;
import nl.bestego.huesim.util.Converter;

import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class LampDTO {

    private Long id;
    private String omschrijving;
    private String groepLijst;
    private boolean aan;
    private int helderheid;

    public LampDTO() {
    }

    public LampDTO(Lamp lamp) {

        this.id = lamp.getId();
        this.omschrijving = lamp.getOmschrijving();

        Set<Long> groepIds = new HashSet<>();
        groepIds = lamp.getGroepen().stream().map(groep -> groep.getId()).collect(Collectors.toSet());
        this.groepLijst = Converter.set2csv(groepIds);

        this.aan = lamp.isAan();
        this.helderheid = lamp.getHelderheid();

    }
}
