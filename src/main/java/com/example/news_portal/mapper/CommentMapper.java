package com.example.news_portal.mapper;

import com.example.news_portal.model.Comment;
import com.example.news_portal.web.model.CommentListResponse;
import com.example.news_portal.web.model.CommentResponse;
import com.example.news_portal.web.model.UpsertCommentRequest;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@DecoratedWith(CommentMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {
    Comment requestToComment(UpsertCommentRequest request);
    @Mapping(source = "newsId", target = "id")
    Comment requestToComment(Long commentId, UpsertCommentRequest request);
    List<CommentResponse> commentListToResponseList(List<Comment> comments);
    default CommentListResponse commentListToCommentListResponse(List<Comment> comments) {
        CommentListResponse response = new CommentListResponse();
        response.setComments(commentListToResponseList(comments));
        return response;
    }
}
