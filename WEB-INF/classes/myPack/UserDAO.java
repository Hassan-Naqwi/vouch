package myPack;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO {

    public static Connection createConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "root");
    }

    public void saveUser(UserInfo user) {
        try (Connection conn = createConnection()) {
            String sql = "Insert INTO user (name, email, role, password) values (?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getRole());
            stmt.setString(4, user.getPassword());
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public UserInfo authenticate(String email, String password) {
        UserInfo user = new UserInfo();
        try (Connection conn = createConnection()) {
            String sql = "Select * from user where email = ? and password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("name"));
                user.setRole(rs.getString("role"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }

    public ArrayList<UserInfo> showUsers() {
        ArrayList<UserInfo> users = new ArrayList<UserInfo>();
        try (Connection conn = createConnection()) {
            String sql = "Select * from user where role = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "user");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                UserInfo user = new UserInfo();
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("name"));
                user.setRole(rs.getString("role"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void deleteUser(String id) {
        try (Connection conn = createConnection()) {
            String sql = "delete from user where email = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}