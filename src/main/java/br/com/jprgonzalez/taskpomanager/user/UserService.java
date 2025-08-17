package br.com.jprgonzalez.taskpomanager.user;

import java.util.Objects;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.jprgonzalez.taskpomanager.exceptions.custom.UserAlreadyExistsException;
import br.com.jprgonzalez.taskpomanager.exceptions.custom.UserNotExistsException;
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

    public UserResponseDTO getUser(Long id, String username, String email) {
        long nonNullCount = Stream.of(id, username, email).filter(Objects::nonNull).count();
        if (nonNullCount == 0) {
            throw new UserNotExistsException("At least one parameter (id, username, or email) must be provided.");
        }else if (nonNullCount > 1) {
            throw new UserNotExistsException("Only one parameter (id, username, or email) should be provided.");
        }

        UserModel foundUser;

        if (id != null) {
            foundUser = userRepository.findById(id)
                    .orElseThrow(() -> new UserNotExistsException("User not found by id: " + id));
        } else if (username != null) {
            foundUser = userRepository.findByUsername(username)
                    .stream()
                    .findFirst()
                    .orElseThrow(() -> new UserNotExistsException("User not found by username: " + username));
        } else {
            foundUser = userRepository.findByEmail(email)
                    .stream()
                    .findFirst()
                    .orElseThrow(() -> new UserNotExistsException("User not found by email: " + email));
        }

        return new UserResponseDTO(
            foundUser.getId(),
            foundUser.getName(),
            foundUser.getUsername(),
            foundUser.getEmail(),
            foundUser.getRole(),
            foundUser.getTeamId(),
            foundUser.getActive(),
            foundUser.getCreatedAt(),
            foundUser.getCreatedByUserId(),
            foundUser.getLastModifiedAt(),
            foundUser.getLastModifiedByUserId()
        );
    }

}
