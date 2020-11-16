package com.github.JuiceEye.models;

import com.j256.ormlite.field.DatabaseField;

import java.util.Objects;

public class User {

    @DatabaseField(generatedId = true)
    private int userId;
    @DatabaseField(columnName = "username")
    private String username;
    @DatabaseField(columnName = "password")
    private String password;
    @DatabaseField(columnName = "role")
    private Role userRole;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }

    public User() {
    }

    public User(int userId, String username, String password, Role userRole) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(userRole, user.userRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, password, userRole);
    }

    @Override
    public String toString() {
        return "com.github.JuiceEye.models.User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}


