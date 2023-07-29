package com.example.demofb.Controller;

import com.example.demofb.DAO.PostDAO;
import com.example.demofb.DTO.PostDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "home", value = "/homepage")
public class HomepageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<PostDTO> postDTOList;
        if (session.getAttribute("trackId") != null) {
            Long userId = (Long) session.getAttribute("trackId");
            if (req.getParameter("del") != null) {
                Long postId = Long.valueOf(req.getParameter("del"));
                try {
                    new PostDAO().deleteUserPost(postId);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (req.getParameter("upd") != null) {
                Long postId = Long.valueOf(req.getParameter("upd"));
                try {
                    PostDTO postDTO = new PostDAO().findById(postId);
                    req.setAttribute("update-post", postDTO.getContent());
                    req.setAttribute("post-id", postId);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                postDTOList = new PostDAO().findAllPostsByUser(userId);
                req.setAttribute("post", postDTOList);
                System.out.println("ALL OF THE USER'S POSTS: " + postDTOList);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            RequestDispatcher dispatcher = req.getRequestDispatcher("welcome.jsp");
            session.setAttribute("name", req.getAttribute("firstname"));
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<PostDTO> postDTOList;
        if (session.getAttribute("trackId") != null) {
            Long user_id = (Long) session.getAttribute("trackId");
            RequestDispatcher dispatcher = req.getRequestDispatcher("welcome.jsp");
            String post = req.getParameter("post");

            if (post != null && post.isEmpty()) {
                throw new RuntimeException("Post cannot be empty");
            }
             if (req.getParameter("update-post") != null) {
                try {
                    new PostDAO().updateUserPost(Long.valueOf(req.getParameter("post-id")),
                            req.getParameter("update-post"));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
             else if (req.getParameter("post") != null) {
                 try {
                     new PostDAO().savePost(post, user_id);
                 } catch (SQLException e) {
                     throw new RuntimeException(e);
                 }
             }
            try {
                postDTOList = new PostDAO().findAllPostsByUser(user_id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
                req.setAttribute("post", postDTOList);
                session.setAttribute("name", req.getAttribute("firstname"));
                dispatcher.forward(req, resp);
            } else {
                resp.sendRedirect("index.jsp");
            }
        }
    }
