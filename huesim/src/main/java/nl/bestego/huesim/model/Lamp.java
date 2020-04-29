package nl.bestego.huesim.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;


@Data
@Entity
public class Lamp {

    @Id
    private Long id;
    @Pattern(regexp = "[a-zA-z0-9\\-]+")   // pattern is anchored
    private String omschrijving;
    private boolean aan;
    private int helderheid;

    // ToDo: only for debugging; remove
    public String toString() {
        return String.format("id:%d omschrijving:%s aan:%s helderheid:%d", getId(), getOmschrijving(), isAan(), getHelderheid());
    }

}
