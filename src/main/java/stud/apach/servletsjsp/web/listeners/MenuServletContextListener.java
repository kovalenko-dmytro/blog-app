package stud.apach.servletsjsp.web.listeners;

import stud.apach.servletsjsp.model.beans.CategoryBean;
import stud.apach.servletsjsp.model.beans.MenuBean;
import stud.apach.servletsjsp.model.dao.entities.Item;

import javax.servlet.*;
import java.util.*;

public class MenuServletContextListener implements ServletContextListener {
	
	public void contextInitialized (ServletContextEvent event) {

		ServletContext context = event.getServletContext();

		MenuBean menuBean = new MenuBean();
		List<Item> menu = menuBean.createMenuList();

		CategoryBean categoryBean = new CategoryBean();
		List<Item> categories = categoryBean.createCategoryList();

		for (Item item : menu) {
			if ("Categories".equals(item.getName())) {
				item.setSubItems(categories);
			}
		}

		context.setAttribute("menu", menu);
	}
	
	public void contextDestroyed (ServletContextEvent event) {
		
	}
}
