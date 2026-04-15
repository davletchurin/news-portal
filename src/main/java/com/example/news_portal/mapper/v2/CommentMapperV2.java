package com.example.news_portal.mapper.v2;

import com.example.news_portal.entity.Comment;
import com.example.news_portal.web.model.CommentListResponse;
import com.example.news_portal.web.model.CommentResponse;
import com.example.news_portal.web.model.UpsertCommentRequest;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@DecoratedWith(CommentMapperDelegateV2.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapperV2 {
    Comment requestToComment(UpsertCommentRequest request);
    @Mapping(source = "commentId", target = "id")
    Comment requestToComment(Long commentId, UpsertCommentRequest request);
    CommentResponse commentToResponse(Comment comment);
    List<CommentResponse> commentListToResponseList(List<Comment> comments);
    default CommentListResponse commentListToCommentListResponse(List<Comment> comments) {
        CommentListResponse response = new CommentListResponse();
        response.setComments(commentListToResponseList(comments));
        return response;
    }
}
