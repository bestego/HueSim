package nl.bestego.huesim.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import nl.bestego.huesim.model.validation.Range;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
public class Lamp {

    @Id
    private Long id;
    //@Pattern(regexp = "[a-zA-z0-9\\-]+")   // pattern is anchored
    private String omschrijving;
    private boolean aan;
    @Range(message = "Waarde niet tussen {min}..{max}", min = 0, max = 100)
    private int helderheid;

    // Reverse side
    @ManyToMany(mappedBy = "lampen")
    @JsonIgnore
    private Set<Groep> groepen = new HashSet<>();

    public Lamp() {
    }

    public Lamp(Long id, String omschrijving, boolean aan, int helderheid) {
        this.id = id;
        this.omschrijving = omschrijving;
        this.aan = aan;
        this.helderheid = helderheid;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;

        if (!(object instanceof Lamp))
            return false;

        Lamp lamp = (Lamp) object;

        return (id.equals(lamp.id) && omschrijving.equals(lamp.omschrijving)
                && aan == lamp.aan && helderheid == lamp.helderheid);
    }

    @Override
    public int hashCode() {
        return id.intValue();
    }

}
