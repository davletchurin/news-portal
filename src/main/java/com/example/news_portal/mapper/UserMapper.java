package com.example.news_portal.mapper;

import com.example.news_portal.entity.User;
import com.example.news_portal.web.model.UpsertUserRequest;
import com.example.news_portal.web.model.UserListResponse;
import com.example.news_portal.web.model.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {CommentMapper.class, NewsMapper.class})
public interface UserMapper {
    User requestToUser(UpsertUserRequest request);
    @Mapping(source = "userId", target = "id")
    User requestToUser(Long userId, UpsertUserRequest request);
    @Mapping(target = "news", ignore = true)
    @Mapping(target = "comments", ignore = true)
    UserResponse userToResponse(User user);
    List<UserResponse> userListToResponseList(List<User> users);
    default UserListResponse userListToUserListResponse(List<User> users) {
        UserListResponse response = new UserListResponse();
        response.setUsers(userListToResponseList(users));
        return response;
    }
}
