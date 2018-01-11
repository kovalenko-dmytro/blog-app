package stud.apach.servletsjsp.model.dao.factory;

import stud.apach.servletsjsp.model.dao.daobeans.EntityDAO;
import stud.apach.servletsjsp.model.dao.daobeans.UserDAO;
import stud.apach.servletsjsp.model.dao.entities.Article;
import stud.apach.servletsjsp.model.dao.entities.Category;
import stud.apach.servletsjsp.model.dao.entities.Menu;
import stud.apach.servletsjsp.model.dao.entities.User;

public abstract class DAOFactory {

	// List of DAO types supported by the factory
	public static final int DB = 1;
	public static final int FILE = 2;


	  
	public static DAOFactory getDAOFactory(int typeFactory) {  
		switch (typeFactory) {
		  case 1: 
			  return DBDAOFactory.getInstance();
		  case 2:
			  return FILEDAOFactory.getInstance();
			default:
				return null;
		}
	}

	public abstract EntityDAO<User> getUserDAO();
    public abstract EntityDAO<Menu> getMenuDAO();
    public abstract EntityDAO<Category> getCategoryDAO();
    public abstract EntityDAO<Article> getArticleDAO();
}
