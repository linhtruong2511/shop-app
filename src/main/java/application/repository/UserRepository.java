package application.repository;

import application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsernameContaining(String userName);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
