package nl.bestego.huesim.control;

import nl.bestego.huesim.dto.GroepDTO;
import nl.bestego.huesim.model.Groep;
import nl.bestego.huesim.model.Lamp;
import nl.bestego.huesim.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

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
        return repository.findAll().stream()
                .map(GroepDTO::new)
                .collect(Collectors.toList());
    }

    public GroepDTO statusGroep(Long id) {
        Optional<Groep> result = repository.findById(id);
        if (result.isPresent()) {
            //synchroniseerMetLampen();
            return new GroepDTO(result.get());
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

    public boolean wijzigActieGroep(Groep groep, Long id) {
        Optional<Groep> oudeGroep = repository.findById(id);
        if (oudeGroep.isPresent()) {
            if (groep.getOmschrijving() != null) oudeGroep.get().setOmschrijving(groep.getOmschrijving());
            if (groep.getLamplijst() != null) oudeGroep.get().setLamplijst(groep.getLamplijst());
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

    public void synchroniseerMetLampen() {

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
        Set<Long> lampIds = Arrays.stream(groep.getLamplijst().split(","))
                .filter(s -> s.length() > 0)
                .map(Long::new)
                .collect(Collectors.toSet());

        Set<Long> validatedLampIds = validateLampIds(lampIds);
        groep.setLamplijst(Converter.set2csv(validatedLampIds));
        wijzigActieGroep(groep, groep.getId());

        return validatedLampIds;
    }

    private Set<Long> validateLampIds(Set<Long> lampIds) {
        Set<Long> validatedIds = new HashSet<>(lampIds);
        for (Long id : lampIds) {
            if (lampService.statusLamp(id).getId() == null) {
                validatedIds.remove(id);
            }
        }
        return validatedIds;
    }


}
