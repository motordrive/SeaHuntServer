package seahunt.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import seahunt.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByName(String name);

    List<User> findById(Long id);
}
