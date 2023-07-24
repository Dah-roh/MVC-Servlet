package com.example.demofb.Controller;

import java.io.*;

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

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("This is your login information: "+req.getParameter("email")+ " "
                +req.getParameter("password"));

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        RequestDispatcher requestDispatcher;
        if (email.equals("darogadibia@yahoo.com") && password.equals("password")){
         requestDispatcher = req.getRequestDispatcher("welcome.jsp");
         req.setAttribute("email", email);
         requestDispatcher.forward(req, resp);
        }
        else{
            requestDispatcher = req.getRequestDispatcher("index.jsp");
            req.setAttribute("error", "email or password not correct");
            requestDispatcher.forward(req, resp);
        }
    }

    public void destroy() {
    }
}