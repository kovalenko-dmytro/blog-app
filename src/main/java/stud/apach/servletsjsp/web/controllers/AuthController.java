package stud.apach.servletsjsp.web.controllers;

import stud.apach.servletsjsp.model.beans.UserBean;
import stud.apach.servletsjsp.model.dao.entities.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class AuthController extends HttpServlet {
    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.setProperty("java.security.auth.login.config", "C:\\Java\\Tomcat\\webapps\\blog-app\\WEB-INF\\classes\\stud\\apach\\servletsjsp\\web\\auth\\jaas.config");

        response.setContentType("text/html;charset=utf-8");

        String action = request.getServletPath();

        UserBean userBean = new UserBean();

        switch (action) {
            case "/login":

                boolean loginSuccess = userBean.checkLogin(request.getParameter("loginEmail"), request.getParameter("loginPassword"));
                if (loginSuccess) {
                    User user = userBean.getUserByParam("email = " + "'" + request.getParameter("loginEmail") + "'");
                    HttpSession session = request.getSession();
                    session.setAttribute("userAlias",user.getAlias());
                    response.sendRedirect(request.getContextPath());
                } else {
                    String errorMessage = "Email or password are incorrect";
                    request.setAttribute("errorMessage", errorMessage);
                    RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/pages/register/register.jsp");
                    rd.forward(request,response);
                }

                break;
            case "/register":

                User user = new User();
                user.setFirstName(request.getParameter("inputFirstName"));
                user.setLastName(request.getParameter("inputLastName"));
                user.setAlias(request.getParameter("inputUserAlias"));
                user.setEmail(request.getParameter("inputEmail"));
                user.setPassword(request.getParameter("inputPassword"));
                user.setAvatar("noimage.png");

                int res = userBean.registerUser(user);
                if (res != 0) {
                    HttpSession session = request.getSession(false);
                    if (session !=null) {
                        session.setAttribute("userAlias", user.getAlias());
                    }
                }
                response.sendRedirect(request.getContextPath());
        }
    }

    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

        String action = request.getServletPath();
        switch (action) {
            case "/register":
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/pages/register/register.jsp");
                rd.forward(request,response);
                break;
            case "/logout":
                HttpSession session = request.getSession(false);
                if (session != null) {
                    session.setAttribute("userAlias", "");
                    session.invalidate();
                }
                response.sendRedirect(request.getContextPath());
        }
    }
}
