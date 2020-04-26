package nl.bestego.huesim.control;

import nl.bestego.huesim.model.Groep;
import nl.bestego.huesim.model.Lamp;
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

    public boolean actieGroep(Lamp lampUpdate, Long groepId) {
        Optional<Groep> groep = repository.findById(groepId);
        if (groep.isPresent()) {
            Set<Long> lampIds = getLampIds(groep.get());
            for (Long lampId : lampIds) {
                Lamp lamp = lampService.statusLamp(lampId);
                lamp.setAan(lampUpdate.isAan());
                lamp.setHelderheid(lampUpdate.getHelderheid());
                lampService.wijzigStatusLamp(lamp, lampId);
            }
            synchroniseerMetLampen();
            return true;
        } else {
            return false;
        }
    }

    private void synchroniseerMetLampen() { //ToDo test with nonexistent Lamp references

        List<Groep> groepen = repository.findAll();
        for (Groep groep : groepen) {
            Set<Long> lampIds = getLampIds(groep);
            boolean alleAan = isAlleAan(lampIds);
            boolean enkeleAan = isEnkeleAan(lampIds);
            updateGroep(groep, alleAan, enkeleAan);
        }
    }

    private void updateGroep(Groep groep, boolean alleAan, boolean enkeleAan) {
        if (alleAan) groep.setAlle_aan(true);
        if (enkeleAan) {
            groep.setEnkele_aan(true);
        } else {
            groep.setEnkele_aan(false);
            groep.setAlle_aan(false);
        }
        repository.save(groep);
    }

    private boolean isEnkeleAan(Set<Long> lampIds) {
        return lampIds
                .stream()
                .map(id -> lampService.statusLamp(id))
                .anyMatch(Lamp::isAan);
    }

    private boolean isAlleAan(Set<Long> lampIds) {
        return lampIds
                .stream()
                .map(id -> lampService.statusLamp(id))
                .allMatch(Lamp::isAan);
    }

    private Set<Long> getLampIds(Groep groep) {
        return Arrays.stream(groep.getLampen().split(","))
                .filter(s -> s.length() > 0)
                .map(Long::new)
                .collect(Collectors.toSet());
    }
}
