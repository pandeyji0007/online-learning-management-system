package com.user.DAO;

import com.user.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) class for performing operations on the User entity.
 */
public class UserDAO {

    private final Connection connection; // Database connection object

    /**
     * Constructor to initialize the DAO with a database connection.
     *
     * @param connection Database connection object
     */
    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Adds a new user to the database.
     *
     * @param user The User object containing details of the user to add
     * @throws SQLException If an SQL error occurs
     */
    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO users (username, email, password, role, created_at) VALUES (?, ?, ?, ?, NOW())";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole());
            stmt.executeUpdate();
        }
    }

    /**
     * Retrieves a user by their ID from the database.
     *
     * @param userId The ID of the user to retrieve
     * @return The User object representing the retrieved user, or null if not found
     * @throws SQLException If an SQL error occurs
     */
    public User getUserById(int userId) throws SQLException {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToUser(rs);
                }
            }
        }
        return null;
    }

    /**
     * Retrieves all users from the database.
     *
     * @return A list of User objects representing all users
     * @throws SQLException If an SQL error occurs
     */
    public List<User> getAllUsers() throws SQLException {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                users.add(mapRowToUser(rs));
            }
        }
        return users;
    }

    /**
     * Updates the details of an existing user in the database.
     *
     * @param user The User object containing updated details
     * @throws SQLException If an SQL error occurs
     */
    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE users SET username = ?, email = ?, password = ?, role = ? WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole());
            stmt.setInt(5, user.getUserId());
            stmt.executeUpdate();
        }
    }

    /**
     * Deletes a user from the database by their ID.
     *
     * @param userId The ID of the user to delete
     * @throws SQLException If an SQL error occurs
     */
    public void deleteUserById(int userId) throws SQLException {
        String sql = "DELETE FROM users WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        }
    }

    /**
     * Maps a row from the ResultSet to a User object.
     *
     * @param rs The ResultSet containing user data
     * @return A User object populated with data from the ResultSet
     * @throws SQLException If an SQL error occurs while accessing the ResultSet
     */
    private User mapRowToUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setRole(rs.getString("role"));
        user.setCreatedAt(rs.getString("created_at"));
        return user;
    }
}
