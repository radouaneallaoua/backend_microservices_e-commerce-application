package com.allaoua.commentreactionservice;

import com.allaoua.commentreactionservice.repository.CommentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CommentReactionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommentReactionServiceApplication.class, args);
    }


}
