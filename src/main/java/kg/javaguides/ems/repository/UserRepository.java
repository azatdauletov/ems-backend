package kg.javaguides.ems.repository;

import kg.javaguides.ems.entity.Role;
import kg.javaguides.ems.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    User findByRole(Role role);
}
