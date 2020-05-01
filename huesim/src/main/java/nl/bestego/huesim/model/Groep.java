package nl.bestego.huesim.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.sql.DataSourceDefinitions;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class Groep {

    @Id
    private Long id;
    private String omschrijving;
    private String lampen;
    @OneToMany
    private List<Lamp> lamplijst;
    private boolean enkele_aan;
    private boolean alle_aan;

}
