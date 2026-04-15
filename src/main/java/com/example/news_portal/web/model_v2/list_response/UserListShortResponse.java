package com.example.news_portal.web.model_v2.list_response;

import com.example.news_portal.web.model_v2.response.UserShortResponse;
import lombok.Data;

import java.util.List;

@Data
public class UserListShortResponse {
    private List<UserShortResponse> users;
}
