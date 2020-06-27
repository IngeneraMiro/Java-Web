package web.exam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.exam.models.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

   Optional<User> findByUsernameAndPassword(String username, String password);

}
