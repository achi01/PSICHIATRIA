package psichiatria.be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import psichiatria.be.model.domain.User;
import psichiatria.be.model.dto.UserDto;
import psichiatria.be.model.mapper.UserMapper;
import psichiatria.be.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    /**
     * Costruttore del  UtenteService.
     * @param userRepository Il repository per la gestione degli utenti.
     * @param userMapper Il mapper per la conversione di utente in utente dto.
     */
    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }



    /**
     * Recupera tutti gli utenti.
     * @return La lista di tutti gli utenti
     */
    @Transactional(readOnly = true)
    public List<UserDto> getAllUtenti() {
        // return the users ordered by id
        return userRepository.findAll()
                .stream()
                .sorted((u1, u2) -> (int) (u1.getId() - u2.getId()))
                .map(userMapper::userToUserDto)
                .collect(Collectors.toList());
    }


    /**
     * Recupera un utente per id.
     * @param id: l'id dell'utente da recuperare
     * @return L'utente recuperato
     */
    @Transactional(readOnly = true)
    public UserDto getUtenteById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utente not found with id " + id));
        UserDto utenteDto = userMapper.userToUserDto(user);
        return utenteDto;
    }

    /**
     * Crea un nuovo utente.
     * @param utenteDto: l'utente da creare
     * @return L'utente creato
     */
    @Transactional
    public UserDto createUtente(UserDto utenteDto) {
        User utente = userMapper.userDtoToUser(utenteDto);
        User createdUtente = userRepository.save(utente);
        UserDto createdUtenteDto = userMapper.userToUserDto(createdUtente);
        return createdUtenteDto;
    }

    /**
     * Aggiorna un utente.
     * @param utenteToUpdateDto: l'utente da aggiornare
     * @return L'utente aggiornato
     */
    @Transactional
    public UserDto updateUtente(UserDto utenteToUpdateDto) {
        // check if utente with the same id already exists, if not throw an exception
//        if (!userRepository.existsById(utenteToUpdateDto.getId())) {
//            throw new RuntimeException("Utente with id " + utenteToUpdateDto.getId() + " not found!");
//        }
        User utenteToUpdate = userMapper.userDtoToUser(utenteToUpdateDto);
        User updatedUtente = userRepository.save(utenteToUpdate);
        UserDto  updatedUtenteDto = userMapper.userToUserDto(updatedUtente);
        return updatedUtenteDto;
    }

    /**
     * Elimina un utente.
     * @param id: l'id dell'utente da eliminare
     */
    @Transactional
    public void deleteUtente(Long id) {
        // check if utente with the same id already exists, if not throw an exception
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Utente with id " + id + " not found!");
        }
        userRepository.deleteById(id);
    }



}