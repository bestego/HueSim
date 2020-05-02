package nl.bestego.huesim.control;

import nl.bestego.huesim.model.Lamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LampService {

    @Autowired
    private LampRepository repository;

    @Autowired
    GroepService groepService;

    public void nieuweLamp(Lamp lamp) {
        repository.save(lamp); //ToDo add checks later
    }

    public Lamp statusLamp(Long id) {
        Optional<Lamp> result = repository.findById(id);
        return result.orElseGet(() -> result.orElse(new Lamp()));
    }

    public List<Lamp> statusLampen() {
        return repository.findAll();
    }

    public void verwijderLamp() {
    }

    public boolean wijzigNaamLamp(Lamp lamp, Long id) {
        Optional<Lamp> oudeLamp = repository.findById(id);
        if (oudeLamp.isPresent() && lamp.getOmschrijving().length() > 0) {
            oudeLamp.get().setOmschrijving(lamp.getOmschrijving());
            repository.save(oudeLamp.get());
            return true;
        } else {
            return false;
        }
    }

    public boolean wijzigStatusLamp(Lamp lamp, Long id) {
        Optional<Lamp> oudeLamp = repository.findById(id);
        if (oudeLamp.isPresent() && lamp.getOmschrijving().length() > 0) {
            oudeLamp.get().setAan(lamp.isAan());
            oudeLamp.get().setHelderheid(lamp.getHelderheid());
            repository.save(oudeLamp.get());
            groepService.synchroniseerMetLampen();
            return true;
        } else {
            return false;
        }
    }
}
