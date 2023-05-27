package kz.careerguidance.applicationapi.service.impl.university;

import kz.careerguidance.applicationapi.dto.university.SpecialityDto;
import kz.careerguidance.applicationapi.entity.university.Speciality;
import kz.careerguidance.applicationapi.entity.university.University;
import kz.careerguidance.applicationapi.exceptions.NotFoundException;
import kz.careerguidance.applicationapi.mapper.SpecialityMapper;
import kz.careerguidance.applicationapi.repository.university.SpecialitiesRepository;
import kz.careerguidance.applicationapi.repository.university.UniversitiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SpecialitiesService {

    private final SpecialitiesRepository specialitiesRepository;

    private final UniversitiesRepository universitiesRepository;

    private final SpecialityMapper mapper;

    @Transactional
    public Speciality save(Speciality speciality) {
        return specialitiesRepository.save(speciality);
    }

    @Transactional
    public SpecialityDto update(Long id, Speciality dto) {
        Speciality specialityToUpdate = specialitiesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Speciality not found"));

        if (null != dto.getName() && !dto.getName().isEmpty()) {
            specialityToUpdate.setName(dto.getName());
        }
        if (null != dto.getCategory()) {
            specialityToUpdate.setCategory(dto.getCategory());
        }
        if (null != dto.getCategoryImage() && !dto.getCategoryImage().isEmpty()) {
            specialityToUpdate.setCategoryImage(dto.getCategoryImage());
        }
        if (null != dto.getImage() && !dto.getImage().isEmpty()) {
            specialityToUpdate.setImage(dto.getImage());
        }
        if (null != dto.getDescription() && !dto.getDescription().isEmpty()) {
            specialityToUpdate.setDescription(dto.getDescription());
        }
        if (null != dto.getWhatMakes() && !dto.getWhatMakes().isEmpty()) {
            specialityToUpdate.setWhatMakes(dto.getWhatMakes());
        }
        if (null != dto.getRequiredKnowledge() && !dto.getRequiredKnowledge().isEmpty()) {
            specialityToUpdate.setRequiredKnowledge(dto.getRequiredKnowledge());
        }
        if (null != dto.getSalaryInfo() && !dto.getSalaryInfo().isEmpty()) {
            specialityToUpdate.setSalaryInfo(dto.getSalaryInfo());
        }

        return mapper.toSpecialityDto(specialitiesRepository.save(specialityToUpdate));
    }

    @Transactional
    public SpecialityDto delete(Long id) {
        Speciality s = specialitiesRepository.findById(id).map(specialityToDelete -> {
                    List<University> universitiesToChange = universitiesRepository.findBySpecialities(specialityToDelete);

                    universitiesToChange.forEach(
                            university ->
                                    university.getSpecialities().remove(specialityToDelete)
                    );

                    universitiesRepository.saveAll(universitiesToChange);
                    specialitiesRepository.deleteById(id);
                    return specialityToDelete;
                })
                .orElseThrow(() -> new NotFoundException("Speciality not found"));
        return mapper.toSpecialityDto(s);
    }

    public Speciality findById(Long id) {
        return specialitiesRepository.findById(id).orElseThrow(() -> new NotFoundException("Speciality not found"));
    }

    public Speciality findByName(String name) {
        return specialitiesRepository.findByName(name).orElseThrow(() -> new NotFoundException("Speciality not found"));
    }

    public Optional<Speciality> findByNameForCreate(String name) {
        return specialitiesRepository.findByName(name);
    }

    public List<Speciality> findAll() throws NotFoundException {
        return specialitiesRepository.findAll();
    }

    public List<Speciality> findByNameContaining(String query) {
        if (!query.trim().isEmpty())
            return specialitiesRepository.findByNameStartingWith(query);
        else
            return Collections.emptyList();
    }
}
