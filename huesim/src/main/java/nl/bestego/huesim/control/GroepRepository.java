package nl.bestego.huesim.control;

import nl.bestego.huesim.model.Groep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroepRepository extends JpaRepository<Groep,Long> {
}
