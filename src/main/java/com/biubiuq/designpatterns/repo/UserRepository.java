package com.biubiuq.designpatterns.repo;

import com.biubiuq.designpatterns.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author biubiuQ
 * @description UserRepository
 * @date 2023/10/29
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);
}
