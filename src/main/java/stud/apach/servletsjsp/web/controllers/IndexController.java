package stud.apach.servletsjsp.web.controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;

import stud.apach.servletsjsp.model.beans.ArticleBean;
import stud.apach.servletsjsp.model.beans.UserBean;
import stud.apach.servletsjsp.model.dao.entities.Article;
import stud.apach.servletsjsp.model.dao.entities.User;

public class IndexController extends HttpServlet {
		
	public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html;charset=utf-8");

		int limit = Integer.parseInt(getServletConfig().getInitParameter("pageLimit"));
		int page = 1;
		String param = request.getParameter("page");
		if (param != null) {
			page = Integer.parseInt(param);
		}
		int offset = limit * (page - 1);

		ArticleBean articleBean = new ArticleBean();
		Set<Article> articles = articleBean.getArticlesInCurrentMonthPerPage(limit, offset);
		int pageQuantity = (int) Math.ceil(articleBean.getQuantityOfArticlesInCurrentMonth() * 1.0 / limit);

		UserBean userBean = new UserBean();
		Set<User> users = userBean.getPopularUsers();

		request.setAttribute("articles", articles);
		request.setAttribute("pageQuantity", pageQuantity);
		request.setAttribute("currentPage", page);
		request.setAttribute("users", users);

		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request,response);				
	}
}
