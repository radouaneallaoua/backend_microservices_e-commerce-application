package com.allaoua.commentreactionservice.web;


import com.allaoua.commentreactionservice.dto.CommentDto;
import com.allaoua.commentreactionservice.entity.Comment;
import com.allaoua.commentreactionservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("")
    public ResponseEntity<List<Comment>> getAllComments() {
        return commentService.getAllComments();
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Comment>> getAllCommentsOfProduct(@PathVariable("productId") String productId) {
        return commentService.getAllCommentWithProductId(productId);
    }

    @PostMapping("")
    public ResponseEntity<Comment> saveComment(@RequestBody CommentDto commentDto) {
        return commentService.saveComment(commentDto);
    }
}
