package nl.bestego.huesim.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Groep {

    @Id
    private Long id;
    private String omschrijving;
    private String lamplijst;

    // Owning side
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "groep_lamp",
            joinColumns = @JoinColumn(name = "groep_id"),
            inverseJoinColumns = @JoinColumn(name = "lamp_id")
    )
    @JsonIgnore
    private Set<Lamp> lampen;

    private boolean enkele_aan;
    private boolean alle_aan;

    public Groep(){}

    public Groep(Long id, String omschrijving) {
        this.id = id;
        this.omschrijving = omschrijving;
    }

}
