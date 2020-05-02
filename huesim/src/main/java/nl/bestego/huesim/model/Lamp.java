package nl.bestego.huesim.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import nl.bestego.huesim.model.validation.Range;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
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
    @ManyToMany(mappedBy = "lampen")
    @JsonIgnore
    private Set<Groep> groepen = new HashSet();

    public Lamp() {
    }

    public Lamp(Long id, String omschrijving, boolean aan, int helderheid) {
        this.id = id;
        this.omschrijving = omschrijving;
        this.aan = aan;
        this.helderheid = helderheid;
    }

    // ToDo: only for debugging; remove
    public String toString() {
        return String.format("id:%d omschrijving:%s aan:%s helderheid:%d", getId(), getOmschrijving(), isAan(), getHelderheid());
    }

}
