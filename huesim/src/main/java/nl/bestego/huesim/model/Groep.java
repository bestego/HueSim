package nl.bestego.huesim.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.sql.DataSourceDefinitions;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;


@Entity
@Data
public class Groep {

    @Id
    private Long id;
    private String omschrijving;
    private String lampen;
    private boolean enkele_aan;
    private boolean alle_aan;

}
