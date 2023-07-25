package com.example.demofb.Controller;

import java.io.*;
import java.sql.SQLException;

import com.example.demofb.DAO.UserDAO;
import com.example.demofb.DTO.UserDTO;
import com.example.demofb.Enums.Role;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        System.out.println("This is your login information: "+request.getParameter("email")+ " "
                +request.getParameter("password"));
        System.out.println(request.getParameter("id"));
        // Hello
        if (request.getParameter("id")!=null) {
            request.setAttribute("id", request.getParameter("id"));
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request, response);
        }
        else {
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>" + message + "</h1>");
            out.println("</body></html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("This is your login information: " + req.getParameter("email") + " "
                + req.getParameter("password"));


        if (req.getParameter("signup")!=null) {
            UserDAO userDAO = new UserDAO();
            System.out.println(req.getParameter("signup"));
            try {
                userDAO.saveUser(
                        UserDTO.builder().email(req.getParameter("email"))
                        .password(req.getParameter("password"))
                        .lastname(req.getParameter("lastname"))
                        .firstname(req.getParameter("firstname"))
                        .role(Role.USER).build()
                );
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            try {
                System.out.println(userDAO.findUser(req.getParameter("email"),
                        req.getParameter("password")));
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
            req.setAttribute("id", 1);
            req.setAttribute("login", "Sign up successful, please log in.");
            requestDispatcher.forward(req, resp);

        } else {

            String email = req.getParameter("email");
            String password = req.getParameter("password");
            RequestDispatcher requestDispatcher;
            try {
                UserDTO userDTO = new UserDAO().findUser(email, password);
                System.out.println(userDTO);
                if (userDTO!=null) {
                    HttpSession session = req.getSession();
                    session.setAttribute("name", userDTO.getFirstname());
                    session.setAttribute("trackId", userDTO.getId());
                    requestDispatcher = req.getRequestDispatcher("welcome.jsp");
                    System.out.println("this is the name "+userDTO.getFirstname());
                    requestDispatcher.forward(req, resp);
                } else {
                    requestDispatcher = req.getRequestDispatcher("index.jsp");
                    req.setAttribute("error", "email or password not correct");
                    requestDispatcher.forward(req, resp);
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void destroy() {
    }
}