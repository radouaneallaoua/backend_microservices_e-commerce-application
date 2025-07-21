package com.allaoua.authservice.web;

import com.allaoua.authservice.dto.RoleRequestDto;
import com.allaoua.authservice.dto.RoleResponseDto;
import com.allaoua.authservice.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("")
    public ResponseEntity<RoleResponseDto> saveRole(@RequestBody RoleRequestDto roleRequestDto) {
        return roleService.saveRole(roleRequestDto);
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<RoleResponseDto> getRoleById(@PathVariable Long roleId) {
        return roleService.getRoleById(roleId);
    }


    @PutMapping("/{roleId}")
    public ResponseEntity<RoleResponseDto> updateRole(@PathVariable Long roleId,@RequestBody RoleRequestDto roleRequestDto) {
        return roleService.updateRole(roleId, roleRequestDto);
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<String> deleteRoleById(@PathVariable Long roleId) {
        return roleService.deleteRoleById(roleId);
    }

    @GetMapping("")
    public ResponseEntity<List<RoleResponseDto>> getAllRoles() {
        return roleService.getAllRoles();
    }


}
