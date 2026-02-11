package myPack;

import java.io.*;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Controller extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        control(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        control(request, response);
    }

    protected void control(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserDAO dao = new UserDAO();
        PostDAO pDao = new PostDAO();
        String action = request.getParameter("action");

        if (action.equals("signup")) {
            UserInfo user = new UserInfo();
            user.setName(request.getParameter("name"));
            user.setEmail(request.getParameter("email"));
            user.setRole(request.getParameter("role"));
            user.setPassword(request.getParameter("password"));
            dao.saveUser(user);
            response.sendRedirect("/vouch/?message=success");
        }

        else if (action.equals("login")) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            UserInfo user = dao.authenticate(email, password);
            if (!user.getEmail().isEmpty()) {
                HttpSession session = request.getSession(true);
                session.setAttribute("name", user.getName());
                session.setAttribute("email", user.getEmail());
                session.setAttribute("role", user.getRole());

                ArrayList<PostInfo> posts = pDao.showFeed();
                request.setAttribute("posts", posts);

                if (session.getAttribute("role").equals("admin")) {
                    request.getRequestDispatcher("adminDashboard/Dashboard.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("userDashboard/Dashboard.jsp").forward(request, response);
                }
            } else {
                response.sendRedirect("/vouch/?message=failed");
            }
        } else if (action.equals("logout")) {
            HttpSession session = request.getSession(false);
            session.invalidate();
            response.sendRedirect("/vouch/?message=logout");
        }

        else if (action.equals("showUsers")) {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("role").equals("user")) {
                response.sendRedirect("index.jsp");
            }
            ArrayList<UserInfo> users = dao.showUsers();
            request.setAttribute("users", users);
            request.getRequestDispatcher("adminDashboard/ShowUsers.jsp").forward(request, response);
        }

        else if (action.equals("deleteUser")) {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("role").equals("user")) {
                response.sendRedirect("index.jsp");
            }
            String id = (String) request.getParameter("id");
            dao.deleteUser(id);
            ArrayList<UserInfo> users = dao.showUsers();
            request.setAttribute("users", users);
            request.getRequestDispatcher("adminDashboard/ShowUsers.jsp").forward(request, response);
        }

        else if (action.equals("feed")) {
            HttpSession session = request.getSession(false);
            if (session == null) {
                response.sendRedirect("index.jsp");
            }
            ArrayList<PostInfo> posts = pDao.showFeed();
            request.setAttribute("posts", posts);

            if (session.getAttribute("role").equals("admin")) {
                request.getRequestDispatcher("adminDashboard/Dashboard.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("userDashboard/Dashboard.jsp").forward(request, response);
            }
        }

        else if (action.equals("createPost")) {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("role").equals("admin")) {
                response.sendRedirect("index.jsp");
            }
            String email = (String) session.getAttribute("email");
            PostInfo post = new PostInfo();
            String content = (String) request.getParameter("content");
            String title = (String) request.getParameter("title");
            String submitType = (String) request.getParameter("submitType");

            post.setEmail(email);
            post.setContent(content);
            post.setTitle(title);

            if ("Draft".equals(submitType)) {
                post.setStatus("draft");
                request.setAttribute("message", "Post Saved as Draft");
            } else {
                post.setStatus("pending");
                request.setAttribute("message", "Post Submitted for Review");
            }

            pDao.createPost(post);
            request.getRequestDispatcher("userDashboard/CreatePost.jsp").forward(request, response);
        }

        else if (action.equals("editPost")) {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("role").equals("admin")) {
                response.sendRedirect("index.jsp");
            }
            int id = Integer.parseInt(request.getParameter("id"));
            PostInfo post = pDao.getPostById(id);
            request.setAttribute("post", post);
            request.getRequestDispatcher("userDashboard/EditPost.jsp").forward(request, response);
        }

        else if (action.equals("updatePost")) {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("role").equals("admin")) {
                response.sendRedirect("index.jsp");
            }
            int id = Integer.parseInt(request.getParameter("id"));
            String content = (String) request.getParameter("content");
            String title = (String) request.getParameter("title");
            String submitType = (String) request.getParameter("submitType");

            PostInfo post = new PostInfo();
            post.setId(id);
            post.setTitle(title);
            post.setContent(content);

            if ("Draft".equals(submitType)) {
                post.setStatus("draft");
            } else {
                post.setStatus("pending");
            }

            pDao.updatePost(post);
            response.sendRedirect("controller?action=showUserPosts");
        }

        else if (action.equals("showUserPosts")) {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("role").equals("admin")) {
                response.sendRedirect("index.jsp");
            }
            String email = (String) session.getAttribute("email");
            ArrayList<PostInfo> posts = pDao.showUserPosts(email);
            request.setAttribute("posts", posts);
            request.getRequestDispatcher("userDashboard/ShowPosts.jsp").forward(request, response);
        }

        else if (action.equals("showPendingPosts")) {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("role").equals("user")) {
                response.sendRedirect("index.jsp");
            }
            ArrayList<PostInfo> posts = pDao.showPendingPosts();
            request.setAttribute("posts", posts);
            request.getRequestDispatcher("adminDashboard/ShowPendingPosts.jsp").forward(request, response);

        }

        else if (action.equals("approve")) {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("role").equals("user")) {
                response.sendRedirect("index.jsp");
            }

            int id = Integer.parseInt(request.getParameter("id"));
            pDao.approve(id);
            request.getRequestDispatcher("adminDashboard/ShowPendingPosts.jsp").forward(request, response);
        }

        else if (action.equals("reject")) {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("role").equals("user")) {
                response.sendRedirect("index.jsp");
            }

            int id = Integer.parseInt(request.getParameter("id"));
            pDao.decline(id);
            request.getRequestDispatcher("adminDashboard/ShowPendingPosts.jsp").forward(request, response);
        }

        else if (action.equals("deletePost")) {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("role").equals("admin")) {
                response.sendRedirect("index.jsp");
            }
            String email = (String) request.getParameter("email");
            int id = Integer.parseInt(request.getParameter("id"));
            pDao.deletePost(id);
            ArrayList<PostInfo> posts = pDao.showUserPosts(email);
            request.setAttribute("posts", posts);
            request.getRequestDispatcher("userDashboard/ShowPosts.jsp").forward(request, response);
        }
    }

}