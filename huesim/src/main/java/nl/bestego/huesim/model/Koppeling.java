package nl.bestego.huesim.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data public class Koppeling {

    @Id
    private Long id;
    private Long groep;
    private Long lamp;

}
