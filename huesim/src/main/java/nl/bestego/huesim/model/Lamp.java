package nl.bestego.huesim.model;

import lombok.Data;
import nl.bestego.huesim.model.validation.Range;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;


@Data
@Entity
public class Lamp {

    @Id
    private Long id;
    //@Pattern(regexp = "[a-zA-z0-9\\-]+")   // pattern is anchored
    private String omschrijving;
    private boolean aan;
    @Range(message = "Waarde niet tussen {min}..{max}",min=0,max=100)
    private int helderheid;

    // ToDo: only for debugging; remove
    public String toString() {
        return String.format("id:%d omschrijving:%s aan:%s helderheid:%d", getId(), getOmschrijving(), isAan(), getHelderheid());
    }

}
