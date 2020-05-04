package nl.bestego.huesim.control;

import nl.bestego.huesim.dto.LampDTO;
import nl.bestego.huesim.model.Groep;
import nl.bestego.huesim.model.Lamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

    public boolean plaatsInGroep(Groep groep, Long id) {
        Optional<Lamp> lamp = repository.findById(id);
        if (lamp.isPresent()) {
            Set<Groep> groepSet = lamp.get().getGroepen();
            groepSet.add(groep);
            lamp.get().setGroepen(groepSet);
            repository.save(lamp.get());
            return true;
        } else {
            return false;
        }
    }

    public Lamp statusLamp(Long id) {
        Optional<Lamp> result = repository.findById(id);
        return result.orElse(new Lamp());
    }

    public LampDTO statusLampDTO(Long id) {
        Optional<Lamp> result = repository.findById(id);
        if (result.isPresent()) {
            return new LampDTO(result.get());
        } else {
            return new LampDTO();
        }
    }

    public List<LampDTO> statusLampenDTO() {
        return repository.findAll().stream()
                .map(LampDTO::new)
                .collect(Collectors.toList());
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
