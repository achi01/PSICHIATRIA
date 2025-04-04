package psichiatria.be.controller;

import psichiatria.be.model.dto.UserDto;
import psichiatria.be.model.domain.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import psichiatria.be.service.UserService;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> getAllUtenti() {
        List<UserDto> utenti = userService.getAllUtenti();
        return utenti;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public UserDto getUtenteById(@RequestParam("id") Long id) {
        UserDto utenteDto = userService.getUtenteById(id);
        return utenteDto;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserDto createUtente(@Valid @RequestBody UserDto utenteDto) {
        UserDto createdUtenteDto = userService.createUtente(utenteDto);
        return createdUtenteDto;
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public UserDto updateUtente(@Valid @RequestBody UserDto utenteToUpdateDto) {
        UserDto utenteDto = userService.updateUtente(utenteToUpdateDto);
        return utenteDto;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void deleteUtente(@RequestParam("id") Long id) {
        userService.deleteUtente(id);
    }
}

