package com.allaoua.authservice.service;

import com.allaoua.authservice.dto.RoleRequestDto;
import com.allaoua.authservice.dto.RoleResponseDto;
import com.allaoua.authservice.entity.Role;
import com.allaoua.authservice.repository.RoleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleService {
    final private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public ResponseEntity<RoleResponseDto> saveRole(RoleRequestDto roleRequestDto) {
         Role role= Role.builder()
                 .roleName(roleRequestDto.getRoleName())
                 .build();
         return ResponseEntity.ok(roleRepository.save(role).toDto());
    }

    public ResponseEntity<List<RoleResponseDto>> getAllRoles() {
        return ResponseEntity.ok(roleRepository.findAll().stream().map(Role::toDto).toList());
    }


    public ResponseEntity<RoleResponseDto> getRoleById(Long id) {
        return ResponseEntity.ok(roleRepository.findById(id).get().toDto());
    }

    public ResponseEntity<String> deleteRoleById(Long id) {
        roleRepository.deleteById(id);
        return ResponseEntity.ok("role deleted successfully with id "+id);
    }

    public ResponseEntity<RoleResponseDto> updateRole(Long id,RoleRequestDto roleRequestDto) {
        Role role = roleRepository.findById(id).get();
        role.setRoleName(roleRequestDto.getRoleName());
        return ResponseEntity.ok(roleRepository.save(role).toDto());
    }
}
