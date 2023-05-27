package kz.careerguidance.applicationapi.controller.test;

import kz.careerguidance.applicationapi.dto.test.ProfessionDto;
import kz.careerguidance.applicationapi.entity.test.Profession;
import kz.careerguidance.applicationapi.service.test.ProfessionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("profession")
@RequiredArgsConstructor
public class ProfessionController {
    private final ProfessionsService professionsService;


    @GetMapping("getAll")
    public List<Profession> getAll() {
        return professionsService.getAll();
    }

    @PostMapping("createProfessions")
    public ResponseEntity<String> createProfessions(@RequestBody List<ProfessionDto> dtos) {

        professionsService.createAll(dtos);
        return new ResponseEntity<>("Questions was created successfully", HttpStatus.OK);
    }

    @PostMapping("getProfessionsByKey")
    public ResponseEntity<List<Profession>> getProfessionsByKey(@RequestParam String keyName) {

        return new ResponseEntity<>(professionsService.getByKeyName(keyName), HttpStatus.OK);
    }
}
