package stud.apach.servletsjsp.web.controllers;

import stud.apach.servletsjsp.model.beans.ArticleBean;
import stud.apach.servletsjsp.model.beans.CategoryBean;
import stud.apach.servletsjsp.model.beans.UserBean;
import stud.apach.servletsjsp.model.dao.entities.Article;
import stud.apach.servletsjsp.model.dao.entities.Category;
import stud.apach.servletsjsp.model.dao.entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class UserController extends HttpServlet {

    public void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html;charset=utf-8");

        int limit = Integer.parseInt(getServletConfig().getInitParameter("pageLimit"));
        int page = 1;
        String param = request.getParameter("page");
        if (param != null) {
            page = Integer.parseInt(param);
        }
        int offset = limit * (page - 1);

        UserBean userBean = new UserBean();
        ArticleBean articleBean = new ArticleBean();
        Set<User> users;
        Set<Article> articles;
        User user;
        Article article;
        int pageQuantity;
        HttpSession session;
        CategoryBean categoryBean = new CategoryBean();
        Set<Category> categories;

        String path = request.getServletPath();
        String forward = "";

        switch (path) {
            case "/users":
                users = userBean.getAllUsers(limit, offset);
                pageQuantity = (int) Math.ceil(userBean.getQuantityOfUsers() * 1.0 / limit);

                request.setAttribute("users", users);
                request.setAttribute("pageQuantity", pageQuantity);
                request.setAttribute("currentPage", page);

                forward = "/WEB-INF/view/pages/users/indexUsers.jsp";
                break;

            case "/profile":
                String userAlias = (String) request.getSession().getAttribute("userAlias");
                user = userBean.getUserByAlias(userAlias);

                articles = articleBean.getArticlesByUser(userAlias);
                user.setArticles(articles);

                request.setAttribute("user", user);
                forward = "/WEB-INF/view/pages/users/indexProfile.jsp";
                break;

            case "/profile/user/update":
                session = request.getSession(false);
                if (session != null) {
                    userAlias = (String) session.getAttribute("userAlias");
                    user = userBean.getUserByAlias(userAlias);
                    session.setAttribute("user", user);
                }
                forward = "/WEB-INF/view/pages/users/form/indexFormUser.jsp";
                break;

            case "/profile/create-article":
                session = request.getSession(false);
                if (session != null) {
                    userAlias = (String) session.getAttribute("userAlias");
                    user = userBean.getUserByAlias(userAlias);
                    categories = categoryBean.getCategories();

                    request.setAttribute("user", user);
                    request.setAttribute("categories", categories);
                    request.setAttribute("uri", request.getRequestURI());
                }
                forward = "/WEB-INF/view/pages/articles/form/indexFormArticle.jsp";
                break;

            case "/profile/update-article":
                session = request.getSession(false);
                if (session != null) {
                    userAlias = (String) session.getAttribute("userAlias");
                    user = userBean.getUserByAlias(userAlias);
                    article = articleBean.getArticleById(Long.parseLong(request.getParameter("articleId")));
                    categories = categoryBean.getCategories();

                    request.setAttribute("user", user);
                    request.setAttribute("article", article);
                    request.setAttribute("categories", categories);
                    request.setAttribute("uri", request.getRequestURI());
                }
                forward = "/WEB-INF/view/pages/articles/form/indexFormArticle.jsp";
                break;

            case "/profile/delete-article":

                article = articleBean.getArticleById(Long.parseLong(request.getParameter("articleId")));
                int result = articleBean.deleteArticle(article);

                if (result != 0) {
                    response.sendRedirect(request.getContextPath()+"/profile");
                }
                break;
        }
        if (!"/profile/delete-article".equals(path)) {
            request.getRequestDispatcher(forward).forward(request, response);
        }
    }

    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");

        UserBean userBean = new UserBean();
        ArticleBean articleBean = new ArticleBean();
        Article article;
        User user;
        String image;
        int result;
        String path = request.getServletPath();

        switch (path) {
            case "/profile/user/update":
                HttpSession session = request.getSession(false);
                if (session != null) {
                    user = (User) request.getSession().getAttribute("user");
                    user.setFirstName(request.getParameter("inputFirstName"));
                    user.setLastName(request.getParameter("inputLastName"));
                    user.setAlias(request.getParameter("inputUserAlias"));

                    result = userBean.updateUser(user);

                    if (result != 0) {
                        request.getSession(false).setAttribute("userAlias", user.getAlias());
                    }

                }
                response.sendRedirect(request.getContextPath()+"/profile");
                break;

            case "/profile/create-article":
                    article = new Article();

                    image = articleBean.getImageByParam(Long.parseLong(request.getParameter("inputCategory")));

                    article.setTitle(request.getParameter("inputTitle"));
                    article.setText(request.getParameter("inputText"));
                    article.setImage(image);
                    article.setDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                    article.setCategoryId(Long.parseLong(request.getParameter("inputCategory")));
                    article.setUserId(Long.parseLong(request.getParameter("inputUserId")));

                    result = articleBean.createArticle(article);
                    if (result != 0) {
                        response.sendRedirect(request.getContextPath()+"/profile");
                    }
                break;

            case "/profile/update-article":

                article = articleBean.getArticleById(Long.parseLong(request.getParameter("inputArticleId")));

                image = articleBean.getImageByParam(Long.parseLong(request.getParameter("inputCategory")));

                article.setTitle(request.getParameter("inputTitle"));
                article.setText(request.getParameter("inputText"));
                article.setImage(image);
                article.setDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                article.setCategoryId(Long.parseLong(request.getParameter("inputCategory")));
                article.setUserId(Long.parseLong(request.getParameter("inputUserId")));

                result = articleBean.updateArticle(article);
                if (result != 0) {
                    response.sendRedirect(request.getContextPath()+"/profile");
                }
                break;
        }
    }
}
