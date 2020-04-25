package nl.bestego.huesim.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Lamp {

    @Id
    @Getter @Setter private Long id;
    @Getter @Setter private String omschrijving;
    @Getter @Setter private boolean aan;
    @Getter @Setter private int helderheid;

}
