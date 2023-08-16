package com.seb45_pre_036.stackoverflow.comment.mapper;

import com.seb45_pre_036.stackoverflow.answer.entity.Answer;
import com.seb45_pre_036.stackoverflow.comment.dto.CommentDto;
import com.seb45_pre_036.stackoverflow.comment.entity.Comment;
import com.seb45_pre_036.stackoverflow.member.entity.Member;
import com.seb45_pre_036.stackoverflow.question.entity.Question;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    default Comment commentPostDtoToComment (CommentDto.PostDto commentPostDto) {

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

    Comment commentPatchDtoToComment (CommentDto.PatchDto commentPatchDto);

    default CommentDto.ResponseDto commentToCommentResponseDto(Comment comment) {

        CommentDto.ResponseDto commentResponseDto = new CommentDto.ResponseDto(
                comment.getCommentId(),
                comment.getContent(),
                comment.getAnswer().getAnswerId(),
                comment.getMember().getMemberId(),
                comment.getMember().getEmail(),
                comment.getMember().getNickName(),
                comment.getCreatedAt(),
                comment.getModifiedAt()
        );

        return commentResponseDto;

    }

    List<CommentDto.ResponseDto> commentsToCommentResponseDtos(List<Comment> comments);


}

