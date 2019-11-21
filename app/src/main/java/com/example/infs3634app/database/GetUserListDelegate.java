package com.example.infs3634app.database;

import com.example.infs3634app.model.User;

import java.util.List;

public interface GetUserListDelegate {
    void handleUserResult(List<User> userList);
}
