package stud.apach.servletsjsp.web.controllers;

import stud.apach.servletsjsp.model.beans.ArticleBean;
import stud.apach.servletsjsp.model.dao.entities.Article;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;

public class ArticleController extends HttpServlet {

    public void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html;charset=utf-8");

        int limit = Integer.parseInt(getServletConfig().getInitParameter("pageLimit"));
        int page = 1;
        String param = request.getParameter("page");
        if (param != null) {
            page = Integer.parseInt(param);
        }
        int offset = limit * (page - 1);

        ArticleBean articleBean = new ArticleBean();
        Set<Article> articles;
        Article article;
        RequestDispatcher rd;

        String uri = request.getRequestURI();
        String search = request.getParameter("search");
        String[] parts = uri.split("/");

        if (search != null) {
            articles = articleBean.getArticlesBySearch(search, limit, offset);
            int pageQuantity = (int) Math.ceil(articleBean.getQuantityOfArticlesBySearch(search) * 1.0 / limit);

            request.setAttribute("articles", articles);
            request.setAttribute("pageQuantity", pageQuantity);
            request.setAttribute("currentPage", page);
            request.setAttribute("search", "?search="+search);

            rd = request.getRequestDispatcher("/WEB-INF/view/pages/articles/indexArticles.jsp");
            rd.forward(request, response);
        }

        if ("byId".equals(parts[parts.length-2]) || isDigit(parts[parts.length-1])) {
            article = articleBean.getArticleById(Long.parseLong(parts[parts.length-1]));
            request.setAttribute("article", article);

            rd = request.getRequestDispatcher("/WEB-INF/view/pages/articles/indexArticle.jsp");
            rd.forward(request, response);
        } else if ("byCategory".equals(parts[parts.length-2]) || "byUser".equals(parts[parts.length-2])){

            articles = articleBean.getArticlesByUri(uri, limit, offset);
            int pageQuantity = (int) Math.ceil(articleBean.getQuantityOfArticles(uri) * 1.0 / limit);

            request.setAttribute("articles", articles);
            request.setAttribute("pageQuantity", pageQuantity);
            request.setAttribute("currentPage", page);

            rd = request.getRequestDispatcher("/WEB-INF/view/pages/articles/indexArticles.jsp");
            rd.forward(request, response);
        }
    }

    public void doPost (HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("text/html;charset=utf-8");

    }

    private boolean isDigit(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
