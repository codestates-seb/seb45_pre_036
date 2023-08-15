package com.seb45_pre_036.stackoverflow.comment.mapper;

import com.seb45_pre_036.stackoverflow.answer.entity.Answer;
import com.seb45_pre_036.stackoverflow.comment.dto.CommentDto;
import com.seb45_pre_036.stackoverflow.comment.entity.Comment;
import com.seb45_pre_036.stackoverflow.member.entity.Member;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CommentMapper {


    //    Comment commentPostDtoToComment(CommentDto.PostDto commentPostDto);
    default Comment commenPostDtoToComment (CommentDto.PostDto commentPostDto) {
        Member member = new Member();
        member.setMemberId(commentPostDto.getMemberId());

        Answer answer = new Answer();
        answer.setAnswerId(commentPostDto.getAnswerId());

        Comment comment = new Comment();
        comment.setMember(member);
        comment.setAnswer(answer);
        comment.setContent(commentPostDto.getContent());

        return comment;
    }

    //    Comment commentPatchDtoToComment(CommentDto.PatchDto commentPatchDto);
    Comment commentPatchDtoToComment (CommentDto.PatchDto commentPatchDto);
//    {
//        Comment comment = new Comment();
//        comment.setCommentId(commentPatchDto.getCommentId());
//        comment.setContent(commentPatchDto.getContent());
//
//        return comment;
//    }

    //    CommentDto.ResponseDto commentToCommentResponseDto(Comment comment);

    CommentDto.ResponseDto commentToCommentResponseDto(Comment comment);
//    {
//        CommentDto.ResponseDto commentResponseDto = new CommentDto.ResponseDto(
//                comment.getCommentId(),
//                comment.getMember().getMemberId(),
//                comment.getAnswer().getAnswerId(),
//                comment.getContent(),
//                comment.getMember().getEmail(),
//                comment.getMember().getNickName(),
//                comment.getCreatedAt(),
//                comment.getModifiedAt()
//        );
//
//        commentResponseDto.setCommentId(comment.getCommentId());
//        commentResponseDto.setMemberId(comment.getMember().getMemberId());
//        commentResponseDto.setAnswerId(comment.getAnswer().getAnswerId());
//        commentResponseDto.setContent(comment.getContent());
//        commentResponseDto.setEmail(comment.getMember().getEmail());
//        commentResponseDto.setNickName(comment.getMember().getNickName());
//        commentResponseDto.setCreatedAt(comment.getCreatedAt());
//        commentResponseDto.setModifiedAt(comment.getModifiedAt());
//
//        return commentResponseDto;
//    }

    default List<CommentDto.ResponseDto> commentsToCommentResponseDtos(List<Comment> comments) {
        return comments.stream()
                .map(comment -> commentToCommentResponseDto(comment))
                .collect(Collectors.toList());
    }
}
