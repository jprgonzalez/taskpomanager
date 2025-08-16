package br.com.jprgonzalez.taskpomanager.user;

import org.springframework.stereotype.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.jprgonzalez.taskpomanager.exceptions.custom.UserAlreadyExistsException;
import br.com.jprgonzalez.taskpomanager.user.dto.UserCreationRequestDTO;
import br.com.jprgonzalez.taskpomanager.user.dto.UserResponseDTO;

@Service
public class UserService {
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO createUser(UserCreationRequestDTO  userDto) {
        if (!userRepository.findByUsername(userDto.getUsername()).isEmpty()) {
            throw new UserAlreadyExistsException("User with username " + userDto.getUsername() + " already exists.");
        }

        if (!userRepository.findByEmail(userDto.getEmail()).isEmpty()) {
            throw new UserAlreadyExistsException("User with email " + userDto.getEmail() + " already exists.");
        }

        UserModel user = new UserModel();
        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());
        user.setTeamId(userDto.getTeamId());
        user.setActive(userDto.getActive());

        String hashedPassword = BCrypt.withDefaults().hashToString(12, userDto.getPassword().toCharArray());
        user.setPassword(hashedPassword);

        UserModel savedUser = userRepository.save(user);

        return new UserResponseDTO(
            savedUser.getId(),
            savedUser.getName(),
            savedUser.getUsername(),
            savedUser.getEmail(),
            savedUser.getRole(),
            savedUser.getTeamId(),
            savedUser.getActive(),
            savedUser.getCreatedAt(),
            savedUser.getCreatedByUserId(),
            savedUser.getLastModifiedAt(),
            savedUser.getLastModifiedByUserId()
        );
    }



}
