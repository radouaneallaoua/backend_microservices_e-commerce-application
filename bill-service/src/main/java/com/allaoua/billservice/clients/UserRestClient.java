package com.allaoua.billservice.clients;

import com.allaoua.billservice.models.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth-service")
public interface UserRestClient {
    @GetMapping("/users/{userId}")
    ResponseEntity<UserResponseDto> getUser(@PathVariable("userId") String userId);


}
