package com.seb45_pre_036.stackoverflow.comment.controller;

import com.seb45_pre_036.stackoverflow.comment.dto.CommentDto;
import com.seb45_pre_036.stackoverflow.comment.entity.Comment;
import com.seb45_pre_036.stackoverflow.comment.mapper.CommentMapper;
import com.seb45_pre_036.stackoverflow.comment.service.CommentService;
import com.seb45_pre_036.stackoverflow.dto.MultiResponseDto;
import com.seb45_pre_036.stackoverflow.dto.SingleResponseDto;
import com.seb45_pre_036.stackoverflow.utils.UriCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/comments")
@Validated
public class CommentController {

    private final CommentService commentService;
    private final CommentMapper mapper;
    private final static String COMMENT_DEFAULT_URL = "/comments";

    public CommentController(CommentService commentService, CommentMapper mapper) {
        this.commentService = commentService;
        this.mapper = mapper;
    }

    // 댓글 생성
    @PostMapping
    public ResponseEntity postComment(@Valid @RequestBody CommentDto.PostDto commentPostDto) {

        Comment comment = mapper.commentPostDtoToComment(commentPostDto);

        Comment savedComment = commentService.createComment(comment);

        URI location = UriCreator.createUri(COMMENT_DEFAULT_URL, savedComment.getCommentId());

        return ResponseEntity.created(location).build();

    }

    // 댓글 수정
    @PatchMapping("/{comment-id}")
    public ResponseEntity patchComment(@PathVariable("comment-id") @Positive long commentId,
                                       @Valid @RequestBody CommentDto.PatchDto commentPatchDto,
                                       @RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken) {

        commentPatchDto.setCommentId(commentId);

        Comment comment = mapper.commentPatchDtoToComment(commentPatchDto);

        Comment updateComment = commentService.updateComment(comment, accessToken);

        CommentDto.ResponseDto responseDto = mapper.commentToCommentResponseDto(updateComment);

        return new ResponseEntity<>(new SingleResponseDto<>(responseDto), HttpStatus.OK);
    }


    // 댓글 삭제
    @DeleteMapping("/{comment-id}")
    public ResponseEntity deleteComment(@PathVariable("comment-id") @Positive long commentId,
                                        @RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken) {
        commentService.deleteComment(commentId, accessToken);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
