package com.example.infs3634app.database;

import com.example.infs3634app.model.User;

public interface GetUserDelegate {
    void handleUserResult(User user);
}
