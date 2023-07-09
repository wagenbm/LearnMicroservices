package microservices.book.multiplication.user;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    
    Optional<User> findByAlias(final String alias);

    List<User> findAllByIdIn(final List<Long> ids);

}
