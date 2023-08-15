package com.seb45_pre_036.stackoverflow.comment.controller;

import com.seb45_pre_036.stackoverflow.comment.dto.CommentDto;
import com.seb45_pre_036.stackoverflow.comment.entity.Comment;
import com.seb45_pre_036.stackoverflow.comment.mapper.CommentMapper;
import com.seb45_pre_036.stackoverflow.comment.service.CommentService;
import com.seb45_pre_036.stackoverflow.dto.MultiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/comments")
@Validated
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper mapper;

    // 댓글 생성
    @PostMapping
    public ResponseEntity postComment(@Valid @RequestBody CommentDto.PostDto commentPostDto) {
        Comment comment = mapper.commentPostDtoToComment(commentPostDto);
        Comment savedComment = commentService.createComment(comment);

        CommentDto.ResponseDto responseDto = mapper.commentToCommentResponseDto(savedComment);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }


    // 댓글 수정
    @PatchMapping("/{comment-id}")
    public ResponseEntity patchComment(@PathVariable("comment-id") long commentId,
                                       @Valid @RequestBody CommentDto.PatchDto commentPatchDto) {
//        commentPatchDto.setCommentId(commentId);
        Comment comment = mapper.commentPatchDtoToComment(commentPatchDto);
        Comment updateComment = commentService.updateComment(comment);

        CommentDto.ResponseDto responseDto = mapper.commentToCommentResponseDto(updateComment);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 댓글 조회
    @GetMapping("/{comment-id}")
    public ResponseEntity getComment(@PathVariable("comment-id") long commentId) {
        Comment comment = commentService.findComment(commentId);

        if (comment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        CommentDto.ResponseDto responseDto = mapper.commentToCommentResponseDto(comment);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 댓글 리스트 조회
    @GetMapping
    public ResponseEntity getComments(@RequestParam(required = false, defaultValue = "1") int page,
                                      @RequestParam(required = false, defaultValue = "10") int size) {

        Page<Comment> pageComments = commentService.findComments(page -1, size);
        List<Comment> comments = pageComments.getContent();

        // 리스트 비었을 때 처리 추가하기

        return new ResponseEntity<>(new MultiResponseDto<>(mapper.commentsToCommentResponseDtos(comments), pageComments), HttpStatus.OK);
    }

    // 댓글 삭제
    @DeleteMapping("/{comment-id}")
    public ResponseEntity deleteComment(@PathVariable("comment-id") long commentId) {
        commentService.deleteComment(commentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
