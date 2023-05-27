package kz.careerguidance.applicationapi.service.impl.university;

import kz.careerguidance.applicationapi.dto.university.UniversityDto;
import kz.careerguidance.applicationapi.entity.university.Speciality;
import kz.careerguidance.applicationapi.entity.university.Subject;
import kz.careerguidance.applicationapi.entity.university.Tag;
import kz.careerguidance.applicationapi.entity.university.University;
import kz.careerguidance.applicationapi.exceptions.NotFoundException;
import kz.careerguidance.applicationapi.mapper.UniversityMapper;
import kz.careerguidance.applicationapi.repository.university.SpecialitiesRepository;
import kz.careerguidance.applicationapi.repository.university.SubjectRepository;
import kz.careerguidance.applicationapi.repository.university.TagRepository;
import kz.careerguidance.applicationapi.repository.university.UniversitiesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UniversitiesService {

    private final UniversitiesRepository universitiesRepository;

    private final SpecialitiesRepository specialitiesRepository;

    private final TagRepository tagRepository;

    private final SubjectRepository subjectRepository;

    private final UniversityMapper universityMapper;

    public UniversitiesService(UniversitiesRepository universitiesRepository, SpecialitiesRepository specialitiesRepository, TagRepository tagRepository, SubjectRepository subjectRepository, UniversityMapper universityMapper) {
        this.universitiesRepository = universitiesRepository;
        this.specialitiesRepository = specialitiesRepository;
        this.tagRepository = tagRepository;
        this.subjectRepository = subjectRepository;
        this.universityMapper = universityMapper;
    }

    public Optional<University> get(Long id) {
        return universitiesRepository.findById(id);
    }

    @Transactional
    public UniversityDto create(UniversityDto universityDTO) {
        if (null != universityDTO.getTags()) {
            List<Long> tags = tagRepository.findAll().stream()
                    .map(Tag::getId).toList();
            universityDTO.getTags().forEach(t -> {
                if (!tags.contains(t.getId()))
                    throw new NotFoundException("There is no tag with id " + t.getId());
            });
        }
        if (null != universityDTO.getSubjects()) {
            List<Long> subjects = subjectRepository.findAll()
                    .stream().map(Subject::getId).toList();
            universityDTO.getSubjects().forEach(s -> {
                if (!subjects.contains(s.getId()))
                    throw new NotFoundException("There is no subject with id " + s.getId());
            });
        }
        return universityMapper.toUniversityDto(universitiesRepository
                .save(universityMapper
                        .toUniversityEntity(universityDTO)));
    }

    @Transactional
    public void update(Long specialityId, Long universityId) {
        universitiesRepository.findById(universityId)
                .orElseThrow(() -> new NotFoundException("University not found"))
                .getSpecialities().add(specialitiesRepository.findById(specialityId)
                        .orElseThrow(() -> new NotFoundException("Speciality not found"))
                );
    }


    @Transactional
    public UniversityDto update(Long id, University universityDto) {
        University universityToUpdate = universitiesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("University not found"));

        if (null != universityDto.getName() && !universityDto.getName().isEmpty()) {
            universityToUpdate.setName(universityDto.getName());
        }
        if (null != universityDto.getHasHotel()) {
            universityToUpdate.setHasHotel(universityDto.getHasHotel());
        }
        if (null != universityDto.getStatus() && !universityDto.getStatus().isEmpty()) {
            universityToUpdate.setStatus(universityDto.getStatus());
        }
        if (null != universityDto.getBenefits() && !universityDto.getBenefits().isEmpty()) {
            universityToUpdate.setBenefits(universityDto.getBenefits());
        }
        if (null != universityDto.getHasMilitaryDepartment()) {
            universityToUpdate.setHasMilitaryDepartment(universityDto.getHasMilitaryDepartment());
        }
        if (null != universityDto.getDescription() && !universityDto.getDescription().isEmpty()) {
            universityToUpdate.setDescription(universityDto.getDescription());
        }
        if (null != universityDto.getBudget()) {
            universityToUpdate.setBudget(universityDto.getBudget());
        }
        if (null != universityDto.getImage()) {
            universityToUpdate.setImage(universityDto.getImage());
        }
        if (null != universityDto.getLocation() && !universityDto.getLink().isEmpty()) {
            universityToUpdate.setLocation(universityDto.getLocation());
        }
        if (null != universityDto.getRating()) {
            universityToUpdate.setRating(universityDto.getRating());
        }
        if (null != universityDto.getPassScores()) {
            universityToUpdate.setPassScores(universityDto.getPassScores());
        }
        if (null != universityDto.getSpecialities()) {
            List<Long> ids = universityDto.getSpecialities().stream().map(Speciality::getId).toList();
            List<Speciality> specialities = specialitiesRepository.findAllById(ids);
            specialities.forEach(t -> t.getUniversities().add(universityToUpdate));
            universityToUpdate.setSpecialities(specialities);
            specialitiesRepository.saveAll(specialities);
        }
        if (null != universityDto.getSubjects()) {
            List<Long> ids = universityDto.getSubjects().stream().map(Subject::getId).toList();
            List<Subject> subjects = subjectRepository.findAllById(ids);
            subjects.forEach(t -> t.getUniversities().add(universityToUpdate));
            universityToUpdate.setSubjects(subjects);
            subjectRepository.saveAll(subjects);
        }
        if (null != universityDto.getTags()) {
            List<Long> ids = universityDto.getTags().stream().map(Tag::getId).toList();
            List<Tag> tags = tagRepository.findAllById(ids);
            tags.forEach(t -> t.getUniversities().add(universityToUpdate));
            universityToUpdate.setTags(tags);
            tagRepository.saveAll(tags);
        }
        if (null != universityDto.getOffBudget()) {
            universityToUpdate.setOffBudget(universityDto.getOffBudget());
        }
        if (null != universityDto.getLink() && !universityDto.getLink().isEmpty()) {
            universityToUpdate.setLink(universityDto.getLink());
        }

        return universityMapper.toUniversityDto(universitiesRepository.save(universityToUpdate));
    }


    @Transactional
    public UniversityDto delete(Long id) {
        return universitiesRepository.findById(id).map(
                university -> {
                    universitiesRepository.delete(university);
                    return universityMapper.toUniversityDto(university);
                }
        ).orElseThrow(
                () -> new NotFoundException("University not found")
        );
    }

    public Optional<University> findByName(String name) {
        return universitiesRepository.findByName(name);
    }

    public University findByNameToDisplay(String name) {
        return universitiesRepository.findByName(name).orElseThrow(() -> new NotFoundException("University not found"));
    }

    public List<University> findAll() {
        return universitiesRepository.findAll();
    }

    public List<University> searchUniversitiesBySpecialityName(String specialityName) {
        return universitiesRepository.findBySpecialityName(specialityName);
    }


    public List<University> findByNameContaining(String query) {
        if (!query.trim().isEmpty()) {
            return universitiesRepository.findByNameStartingWith(query);
        } else {
            return Collections.emptyList();
        }
    }
}
