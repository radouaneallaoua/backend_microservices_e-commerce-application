package com.allaoua.authservice.repository;

import com.allaoua.authservice.entity.Notification;
import com.allaoua.authservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
