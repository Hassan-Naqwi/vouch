package myPack;

import java.sql.*;
import java.util.ArrayList;

public class PostDAO {

    public static Connection createConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "root");
    }

    public void createPost(PostInfo post) {
        try (Connection conn = createConnection()) {
            String sql = "Insert INTO post (`title`, `content`, `email`, `status`) values (?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, post.getTitle());
            stmt.setString(2, post.getContent());
            stmt.setString(3, post.getEmail());
            stmt.setString(4, post.getStatus());
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public PostInfo getPostById(int id) {
        PostInfo post = new PostInfo();
        try (Connection conn = createConnection()) {
            String sql = "Select * from post where id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                post.setId(rs.getInt("id"));
                post.setEmail(rs.getString("email"));
                post.setStatus(rs.getString("status"));
                post.setContent(rs.getString("content"));
                post.setTitle(rs.getString("title"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return post;
    }

    public void updatePost(PostInfo post) {
        try (Connection conn = createConnection()) {
            String sql = "UPDATE post SET title=?, content=?, status=? WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, post.getTitle());
            stmt.setString(2, post.getContent());
            stmt.setString(3, post.getStatus());
            stmt.setInt(4, post.getId());
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<PostInfo> showUserPosts(String id) {
        ArrayList<PostInfo> posts = new ArrayList<PostInfo>();
        try (Connection conn = createConnection()) {
            String sql = "Select * from post where email = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PostInfo post = new PostInfo();
                post.setId(rs.getInt("id"));
                post.setEmail(rs.getString("email"));
                post.setStatus(rs.getString("status"));
                post.setContent(rs.getString("content"));
                post.setTitle(rs.getString("title"));
                posts.add(post);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return posts;
    }

    public ArrayList<PostInfo> showPendingPosts() {
        ArrayList<PostInfo> posts = new ArrayList<PostInfo>();
        try (Connection conn = createConnection()) {
            String sql = "Select * from post where status = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "pending");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PostInfo post = new PostInfo();
                post.setId(rs.getInt("id"));
                post.setEmail(rs.getString("email"));
                post.setStatus(rs.getString("status"));
                post.setContent(rs.getString("content"));
                post.setTitle(rs.getString("title"));
                posts.add(post);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return posts;
    }

    public ArrayList<PostInfo> showFeed() {
        ArrayList<PostInfo> posts = new ArrayList<PostInfo>();
        try (Connection conn = createConnection()) {
            String sql = "Select * from post where status = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "approved");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PostInfo post = new PostInfo();
                post.setId(rs.getInt("id"));
                post.setEmail(rs.getString("email"));
                post.setStatus(rs.getString("status"));
                post.setContent(rs.getString("content"));
                post.setTitle(rs.getString("title"));
                posts.add(post);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return posts;
    }

    public void approve(int id) {
        try (Connection conn = createConnection()) {
            String sql = "update post set status=? where id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "approved");
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void decline(int id) {
        try (Connection conn = createConnection()) {
            String sql = "update post set status=? where id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "declined");
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deletePost(int id) {
        try (Connection conn = createConnection()) {
            String sql = "Delete from post where id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
