package kz.careerguidance.applicationapi.service.test;


import kz.careerguidance.applicationapi.dto.test.ProfessionDto;
import kz.careerguidance.applicationapi.entity.test.Profession;
import kz.careerguidance.applicationapi.entity.test.ProfessionKey;
import kz.careerguidance.applicationapi.exceptions.NotFoundException;
import kz.careerguidance.applicationapi.repository.test.ProfessionKeysRepository;
import kz.careerguidance.applicationapi.repository.test.ProfessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProfessionsService {
    private final ProfessionRepository professionRepository;
    private final ProfessionKeysRepository professionKeysRepository;

    public List<Profession> getAll() {
        return professionRepository.findAll();
    }

    public List<Profession> getAllByName(String name) {
        return professionRepository.findAllByName(name);
    }

    public void create(Profession profession) {
        professionRepository.save(profession);
    }

    public void createAll(List<ProfessionDto> dtos) {
        Set<Profession> professions = new LinkedHashSet<>();

        dtos.forEach(x -> {
            Optional<Profession> professionOptional = professionRepository.findByName(x.getName().toLowerCase().trim());
            Optional<ProfessionKey> key = professionKeysRepository.findByName(x.getKey().toLowerCase().trim());

            if (professionOptional.isEmpty()) {
                Profession profession = new Profession();
                ProfessionKey keys = new ProfessionKey();

                profession.setName(x.getName().toLowerCase().trim());
                profession.setDescription(x.getDescription());
                if (key.isEmpty()) {
                    keys.setName(x.getKey().toLowerCase().trim());
                } else {
                    keys = key.get();
                }
                ;
                profession.setKey(keys);
                professions.add(profession);
                keys.setProfessions(professions);

                professionRepository.saveAll(professions);
                professionKeysRepository.save(keys);
                professions.clear();

            }
        });
    }

    public List<Profession> getByKeyName(String keyName){
        ProfessionKey key = professionKeysRepository.findByName(keyName.toLowerCase()).orElseThrow(()->
                new NotFoundException("Professions with such key not found"));

        return professionRepository.findAllByKey(key)
                .orElseThrow(()-> new NotFoundException("Professions with such key not found"));
    }

}
