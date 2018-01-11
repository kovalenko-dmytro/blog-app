package stud.apach.servletsjsp.web.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ContactController extends HttpServlet {

    public void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html;charset=utf-8");

        request.getRequestDispatcher("/WEB-INF/view/pages/contacts/indexContact.jsp").forward(request,response);
    }

    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");

        String from = request.getParameter("inputEmail");
        String to = getServletConfig().getInitParameter("adminEmail");
        String subject = request.getParameter("inputSubject");
        String emailContent = request.getParameter("inputText");
        String login = request.getParameter("inputLogin");
        String password = request.getParameter("inputPassword");

        response.sendRedirect(request.getServletPath());
    }
}
