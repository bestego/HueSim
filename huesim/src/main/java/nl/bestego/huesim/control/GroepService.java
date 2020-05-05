package nl.bestego.huesim.control;

import nl.bestego.huesim.dto.GroepDTO;
import nl.bestego.huesim.model.Groep;
import nl.bestego.huesim.model.Lamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class GroepService {

    @Autowired
    private GroepRepository repository;

    @Autowired
    LampService lampService;

    public void nieuweGroep(Groep groep) {
        repository.save(groep); //ToDo add check later
    }

    public List<GroepDTO> statusGroepen() {
        synchroniseerMetLampen();
        Stream<Groep> groepen = repository.findAll().stream();
        return groepen
                .map(GroepDTO::new)
                .collect(Collectors.toList());
    }

    public GroepDTO statusGroep(Long id) {
        Optional<Groep> groep = repository.findById(id);
        if (groep.isPresent()) {
            synchroniseerMetLampen();
            return new GroepDTO(groep.get());
        } else {
            return new GroepDTO();
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

    public boolean voegLampToe(Lamp lamp, Long id) {
        if (!lampService.isAanwezig(lamp.getId())) return false;
        Optional<Groep> oudeGroep = repository.findById(id);
        if (oudeGroep.isPresent()) {
            Set<Lamp> lampSet = oudeGroep.get().getLampen();
            lampSet.add(lamp);
            oudeGroep.get().setLampen(lampSet);
            repository.save(oudeGroep.get());
            return true;
        } else {
            return false;
        }
    }

    public boolean actieGroep(Lamp lampUpdate, Long groepId) {

        Optional<Groep> groep = repository.findById(groepId);
        if (groep.isPresent()) {
            Set<Lamp> lampen = groep.get().getLampen();
            for (Lamp lamp : lampen) {
                Lamp lampGewijzigd = lampService.statusLamp(lamp.getId());
                lampGewijzigd.setAan(lampUpdate.isAan());
                lampGewijzigd.setHelderheid(lampUpdate.getHelderheid());
                lampService.wijzigStatusLamp(lampGewijzigd, lamp.getId());
            }
            synchroniseerMetLampen();
            return true;
        } else {
            return false;
        }
    }

    public void synchroniseerMetLampen() {

        List<Groep> groepen = repository.findAll();
        for (Groep groep : groepen) {
            boolean alleAan = isAlleAan(groep.getLampen());
            boolean enkeleAan = isEnkeleAan(groep.getLampen());
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

    private boolean isEnkeleAan(Set<Lamp> lampen) {
        return lampen
                .stream()
                .map(lamp -> lampService.statusLamp(lamp.getId()))
                .anyMatch(Lamp::isAan);
    }

    private boolean isAlleAan(Set<Lamp> lampen) {
        return lampen
                .stream()
                .map(lamp -> lampService.statusLamp(lamp.getId()))
                .allMatch(Lamp::isAan);
    }

}
