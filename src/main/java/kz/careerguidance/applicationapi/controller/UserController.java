package kz.careerguidance.applicationapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import kz.careerguidance.applicationapi.dto.CreateUserDto;
import kz.careerguidance.applicationapi.dto.UpdateUserDto;
import kz.careerguidance.applicationapi.dto.UserDto;
import kz.careerguidance.applicationapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/all")
//  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @Operation(summary = "Получить всех пользователей")
    public ResponseEntity<List<UserDto>> getAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
//  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @Operation(summary = "Получить пользователя")
    public ResponseEntity<UserDto> get(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @PostMapping
//  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @Operation(summary = "Создать нового пользователя")
    public ResponseEntity<UserDto> create(@RequestBody @Valid CreateUserDto userDto) {
        return ResponseEntity.ok(userService.create(userDto));
    }

    @PutMapping("/{id}")
//  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @Operation(summary = "Обновить пользователя")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody @Valid UpdateUserDto userDto) {
        return ResponseEntity.ok(userService.update(id, userDto));
    }

    @DeleteMapping("/{id}")
//  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @Operation(summary = "Удалить, пользователя")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
