package com.example.news_portal.mapper.v2;

import com.example.news_portal.entity.User;
import com.example.news_portal.web.model.UpsertUserRequest;
import com.example.news_portal.web.model.UserResponse;
import com.example.news_portal.web.model_v2.list_response.UserListShortResponse;
import com.example.news_portal.web.model_v2.response.UserShortResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {CommentMapperV2.class, NewsMapperV2.class})
public interface UserMapperV2 {
    User requestToUser(UpsertUserRequest request);
    @Mapping(source = "userId", target = "id")
    User requestToUser(Long userId, UpsertUserRequest request);

    UserResponse userToResponse(User user);

    UserShortResponse userToShortResponse(User user);

    List<UserShortResponse> userListToResponseList(List<User> users);
    default UserListShortResponse userListToUserListShortResponse(List<User> users) {
        UserListShortResponse response = new UserListShortResponse();
        response.setUsers(userListToResponseList(users));
        return response;
    }
}
