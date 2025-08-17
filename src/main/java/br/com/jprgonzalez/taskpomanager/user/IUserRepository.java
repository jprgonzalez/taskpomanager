package br.com.jprgonzalez.taskpomanager.user;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface IUserRepository extends JpaRepository<UserModel, Long> {
    List<UserModel> findByUsername(String username);
    List<UserModel> findByEmail(String email);
    Optional<UserModel> findById(Long id);
}
