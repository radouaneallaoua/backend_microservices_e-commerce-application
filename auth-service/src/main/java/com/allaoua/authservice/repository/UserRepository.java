package com.allaoua.authservice.repository;

import com.allaoua.authservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmailAndPassword(String email,String password);
    List<User> findByRoleId(Long roleId);

}
