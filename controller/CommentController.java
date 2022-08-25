package com.sparta.controller;


import com.sparta.dto.CommentRequestDto;
import com.sparta.dto.UserResponseDto;
import com.sparta.model.Comment;
import com.sparta.service.CommentService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth/comment")
public class CommentController {

    private final CommentService commentService;


    @Secured("ROLE_USER")
    @PostMapping("/{id}")
    public UserResponseDto<?> addComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto) {
        UserResponseDto userResponseDto=UserResponseDto.success(commentService.addComment(id, commentRequestDto));
        return userResponseDto;
    }

    @Secured("ROLE_USER")
    @PutMapping("/{id}/{commentId}")
    public Comment updateComment(@PathVariable Long id, @PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto) {
        return commentService.updateComment(id, commentId, commentRequestDto);
    }
    @Secured("ROLE_USER")
    @DeleteMapping("/{id}/{commentId}")
    public Boolean deleteComment(@PathVariable Long id, @PathVariable Long commentId) {
        return commentService.deleteComment(id, commentId);
    }

}
