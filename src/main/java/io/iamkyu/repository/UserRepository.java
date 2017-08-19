package io.iamkyu.repository;

import io.iamkyu.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Kj Nam
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
