package web.exam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.exam.models.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User,String> {

     User getByUsernameAndPassword(String username,String password);


}
