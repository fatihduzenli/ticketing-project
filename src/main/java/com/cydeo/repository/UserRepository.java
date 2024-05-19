package com.cydeo.repository;

import com.cydeo.entity.Role;
import com.cydeo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String username);

    @Transactional  // if there is any issue while executing the method, it will roll back
    void deleteByUserName(String username);// we created another drived query to be able to delete by username

    List<User>findByRoleDescriptionIgnoreCase(String role);

}
