package nl.bestego.huesim.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class Ruimte {

    @Id
    @Getter @Setter private Long id;
    @Getter @Setter private String omschrijving;
    @Getter @Setter private String lampen;
    @Getter @Setter private boolean aan; //ToDo: splitsen in allenAan en enkeleAan + data.sql aanpassen
    @Getter @Setter private int helderheid;

}
