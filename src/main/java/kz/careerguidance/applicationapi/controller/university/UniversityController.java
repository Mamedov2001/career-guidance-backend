package kz.careerguidance.applicationapi.controller.university;

import kz.careerguidance.applicationapi.dto.university.UniversityDto;
import kz.careerguidance.applicationapi.dto.university.UpdateUniversityDto;
import kz.careerguidance.applicationapi.entity.university.University;
import kz.careerguidance.applicationapi.exceptions.UniversityCreateException;
import kz.careerguidance.applicationapi.mapper.UniversityMapper;
import kz.careerguidance.applicationapi.service.impl.university.UniversitiesService;
import kz.careerguidance.applicationapi.validators.UniversityValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/universities"
//        , method = RequestMethod.POST,
//        consumes = MediaType.MULTIPART_FORM_DATA_VALUE
)
public class UniversityController {
    private final UniversitiesService universitiesService;
    private final UniversityMapper mapper;
    private final UniversityValidator universityValidator;

    @GetMapping
    public ResponseEntity<List<University>> getAllUniversities() {
        return new ResponseEntity<>(universitiesService.findAll(), HttpStatus.OK);
    }

    @PostMapping
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<UniversityDto> createUniversity(@RequestBody @Valid UniversityDto universityDTO, BindingResult bindingResult) {
        universityValidator.validate(mapper.toUniversityEntity(universityDTO), bindingResult);
        if (bindingResult.hasErrors()) {
            throw new UniversityCreateException(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        return ResponseEntity.ok(universitiesService.create(universityDTO));
    }


    @GetMapping("/search")
    public ResponseEntity<List<University>> searchUniversitiesStartingWith(@RequestParam String name) {
        return new ResponseEntity<>(universitiesService.findByNameContaining(name), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<University> getUniversityByName(@PathVariable String name) {
        return new ResponseEntity<>(universitiesService.findByNameToDisplay(name), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUniversity(@PathVariable Long id,
                                              @RequestBody @Valid UpdateUniversityDto universityDTO,
                                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new UniversityCreateException(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }

        return ResponseEntity.ok(universitiesService.update(id, mapper.toUniversityEntity(universityDTO)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUniversity(@PathVariable Long id) {
        return ResponseEntity.ok(universitiesService.delete(id));
    }

    @PostMapping("/{id}/addSpeciality")
    public ResponseEntity<?> addSpeciality(@PathVariable Long id, @RequestParam Long specialityId) {
        universitiesService.update(specialityId, id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/searchBySpecialityName")
    public ResponseEntity<List<University>> searchUniversitiesBySpeciality(@RequestParam String name) {
        return ResponseEntity.ok(universitiesService.searchUniversitiesBySpecialityName(name));
    }

//    @PostMapping("/img")
//    public ResponseEntity<?> uploadImage(@RequestPart("image") MultipartFile image) {
//        try {
//            byte[] img = image.getBytes();
//            return ResponseEntity.ok(img);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

}
