package psichiatria.be.repository;

import org.springframework.data.repository.ListCrudRepository;
import psichiatria.be.model.domain.User;

public interface UserRepository extends ListCrudRepository<User, Long> {
}