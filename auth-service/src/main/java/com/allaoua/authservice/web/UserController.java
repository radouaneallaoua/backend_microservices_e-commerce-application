package com.allaoua.authservice.web;

import com.allaoua.authservice.dto.UserRequestDto;
import com.allaoua.authservice.dto.UserResponseDto;
import com.allaoua.authservice.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return userService.getAllUsers();
    }




    @PostMapping("/authenticate")
    public ResponseEntity<UserResponseDto> getUserByEmailAndPassword(@RequestParam String email, @RequestParam String password) {
        return userService.getUserByEmailAndPassword(email, password);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable String userId) {
        return userService.getUserById(userId);
    }

    @PostMapping(value = "",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserResponseDto> saveUser(@ModelAttribute UserRequestDto userRequestDto) throws IOException {
        return userService.createUser(userRequestDto);
    }


    @PutMapping(value = "/{userId}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable String userId,
                                                      @RequestParam(required = false) String email,
                                                      @RequestParam(required = false) String username,
                                                      @RequestParam(required = false) String phoneNumber,
                                                      @RequestParam(required = false) String password,
                                                      @RequestParam(required = false) MultipartFile imageURL,
                                                      @RequestParam(required = false) String address
                                                      ) throws IOException {
        return userService.updateUser(userId,email,username,phoneNumber,password,address,imageURL);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        return userService.deleteUser(userId);
    }

    @GetMapping(value = "{userId}/profile-image",produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> getUserProfileImage(@PathVariable String userId) throws IOException {
        return userService.getUserProfileImageWithUserId(userId);
    }





}
