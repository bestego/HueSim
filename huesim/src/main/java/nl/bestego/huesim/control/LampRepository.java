package nl.bestego.huesim.control;

import nl.bestego.huesim.model.Lamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LampRepository extends JpaRepository<Lamp, Long> {
}
