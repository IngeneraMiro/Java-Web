package presentation.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import presentation.demo.models.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

  Optional<User> findByUsername(String username);
  boolean existsByUsername(String username);
  Optional<User> findByUsernameAndPassword(String username, String password);

}
