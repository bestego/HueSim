package nl.bestego.huesim.control;

import nl.bestego.huesim.model.Groep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class GroepService {

    @Autowired
    private GroepRepository repository;

    @Autowired
    LampService lampService;

    public List<Groep> statusGroepen() {
        synchroniseerMetLampen();
        return repository.findAll();
    }

    public Groep statusGroep(Long id) {
        Optional<Groep> result = repository.findById(id);
        if (result.isPresent()) {
            synchroniseerMetLampen();
            return result.get();
        } else {
            return result.orElse(new Groep());
        }
    }

    public boolean wijzigNaamGroep(Groep groep, Long id) {
        Optional<Groep> oudeGroep = repository.findById(id);
        if (oudeGroep.isPresent() && groep.getOmschrijving().length() > 0) {
            oudeGroep.get().setOmschrijving(groep.getOmschrijving());
            repository.save(oudeGroep.get());
            return true;
        } else {
            return false;
        }
    }

    private void synchroniseerMetLampen() {
    //ToDo: check if lamp existing; test groep without lamps

        List<Groep> groepen = repository.findAll();

        for (Groep groep : groepen) {

            Set<Long> lampIds = Arrays.asList(groep.getLampen().split(","))
                    .stream()
                    .filter(s -> s.length() > 0)
                    .map(Long::new)
                    .collect(Collectors.toSet());

            boolean alleAan = lampIds
                    .stream()
                    .map(id -> lampService.statusLamp(id))
                    .map(lamp -> lamp.isAan())
                    .allMatch(b -> b);

            boolean enkeleAan = lampIds
                    .stream()
                    .map(id -> lampService.statusLamp(id))
                    .map(lamp -> lamp.isAan())
                    .anyMatch(b -> b);

            if (alleAan) groep.setAlle_aan(true);
            if (enkeleAan) {
                groep.setEnkele_aan(true);
            } else {
                groep.setEnkele_aan(false);
                groep.setAlle_aan(false);
            }
            repository.save(groep);
        }
    }
}
