package nl.bestego.huesim.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import sun.awt.OSInfo;

import javax.annotation.sql.DataSourceDefinitions;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity

@Data
public class Groep {

    @Id
    private Long id;
    private String omschrijving;
    private String lamplijst;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "groep_lamp",
            joinColumns = {@JoinColumn(name = "groep_id")},
            inverseJoinColumns = {@JoinColumn(name = "lamp_id")}
    )
    @JsonIgnore
    private Set<Lamp> lampen;
    private boolean enkele_aan;
    private boolean alle_aan;


}
