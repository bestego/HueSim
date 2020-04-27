package nl.bestego.huesim.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class Groep {

    @Id
    @Getter @Setter private Long id;
    @Getter @Setter private String omschrijving;
    @Getter @Setter private String lampen;
    @Getter @Setter private boolean enkele_aan;
    @Getter @Setter private boolean alle_aan;

}
