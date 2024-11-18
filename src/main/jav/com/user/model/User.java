package com.user.model;

import java.util.Objects;

/**
 * Represents a user in the system.
 */
public class User {

    private int userId;           // Unique identifier for the user
    private String username;      // Username for login
    private String email;         // Email address of the user
    private String password;      // Password for authentication
    private String role;          // Role of the user: student, instructor, or admin
    private String createdAt;     // Timestamp of when the user was created

    // Default constructor
    public User() {
    }

    /**
     * Parameterized constructor to initialize User object.
     * 
     * @param userId    Unique ID of the user
     * @param username  Username for the user
     * @param email     Email address of the user
     * @param password  Encrypted password
     * @param role      Role of the user
     * @param createdAt Creation timestamp
     */
    public User(int userId, String username, String email, String password, String role, String createdAt) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.createdAt = createdAt;
    }

    // Getters and Setters

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    // Overriding toString() method
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }

    // Overriding equals() and hashCode() for proper comparison

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                Objects.equals(username, user.username) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(role, user.role) &&
                Objects.equals(createdAt, user.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, email, password, role, createdAt);
    }
}
