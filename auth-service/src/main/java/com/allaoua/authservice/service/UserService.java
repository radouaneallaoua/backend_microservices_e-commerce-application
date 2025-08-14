package com.allaoua.authservice.service;


import com.allaoua.authservice.dto.UserRequestDto;
import com.allaoua.authservice.dto.UserResponseDto;
import com.allaoua.authservice.entity.User;
import com.allaoua.authservice.repository.RoleRepository;
import com.allaoua.authservice.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    final private RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll().stream().map(User::toDto).toList());
    }



    public ResponseEntity<UserResponseDto> getUserByEmailAndPassword(String email, String password) {
        return ResponseEntity.ok(userRepository.findByEmailAndPassword(email, password).toDto());
    }

    public ResponseEntity<UserResponseDto> createUser(UserRequestDto userRequestDto) throws IOException {
        Path path = Path.of(System.getProperty("user.home"), "e-comm-final", "users_images");
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        String imageId = UUID.randomUUID().toString();
        Path imagePath = Path.of(System.getProperty("user.home"), "e-comm-final", "users_images", imageId + ".webp");
        Files.copy(userRequestDto.getImageURL().getInputStream(), imagePath);

        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .email(userRequestDto.getEmail())
                .password(userRequestDto.getPassword())
                .roles(List.of())
                .address(userRequestDto.getAddress())
                .notifications(new ArrayList<>())
                .username(userRequestDto.getUsername())
                .phoneNumber(userRequestDto.getPhoneNumber())
                .imageURL(imagePath.toUri().toString())
                .build();
        return ResponseEntity.ok(userRepository.save(user).toDto());
    }

    public ResponseEntity<UserResponseDto> updateUser(String userId,String email, String username, String phoneNumber, String password, String address, MultipartFile imageURL) throws IOException {
        User foundUser=userRepository.findById(userId).get();
        if(!Objects.equals(email, foundUser.getEmail())) {
            foundUser.setEmail(email);
        }
        if(!Objects.equals(username, foundUser.getUsername())) {
            foundUser.setUsername(username);
        }
        if(!Objects.equals(phoneNumber, foundUser.getPhoneNumber())) {
            foundUser.setPhoneNumber(phoneNumber);
        }
        if(!Objects.equals(password, foundUser.getPassword())) {
            foundUser.setPassword(password);
        }
        if(!Objects.equals(address, foundUser.getAddress())) {
            foundUser.setAddress(address);
        }

        if(imageURL!=null){
            Path oldImagePath=Path.of(URI.create(foundUser.getImageURL()));
            Files.delete(oldImagePath);
            String newImageId = UUID.randomUUID().toString();
            Path newImagePath = Path.of(System.getProperty("user.home"), "e-comm-final", "users_images", newImageId + ".webp");
            Files.copy(imageURL.getInputStream(), newImagePath);
            foundUser.setImageURL(newImagePath.toUri().toString());
        }
        return ResponseEntity.ok(userRepository.save(foundUser).toDto());
    }

    public ResponseEntity<String> deleteUser(String userId) {
        userRepository.deleteById(userId);
        return ResponseEntity.ok("User deleted successfully");
    }

    public ResponseEntity<byte[]> getUserProfileImageWithUserId(String userId) throws IOException {
        User user=userRepository.findById(userId).get();
        return ResponseEntity.ok(Files.readAllBytes(Path.of(URI.create(user.getImageURL()))));

    }

    public ResponseEntity<UserResponseDto> getUserById(String userId) {
        User user=userRepository.findById(userId).get();
        return ResponseEntity.ok(user.toDto());
    }
}
