package kz.careerguidance.applicationapi.service.test;


import kz.careerguidance.applicationapi.entity.test.ProfessionKey;
import kz.careerguidance.applicationapi.repository.test.ProfessionKeysRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessionKeyService {
    private final ProfessionKeysRepository professionKeysRepository;

    public List<ProfessionKey> getAll(){
        return professionKeysRepository.findAll();
    }

    public List<ProfessionKey> getAllByName(String name){
        return professionKeysRepository.findAllByName(name);
    }

    public void create(ProfessionKey professionKey){
        professionKeysRepository.save(professionKey);
    }
}
