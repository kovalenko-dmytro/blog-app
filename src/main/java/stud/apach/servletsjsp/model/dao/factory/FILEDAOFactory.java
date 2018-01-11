package stud.apach.servletsjsp.model.dao.factory;

import stud.apach.servletsjsp.model.dao.daobeans.EntityDAO;
import stud.apach.servletsjsp.model.dao.entities.Article;
import stud.apach.servletsjsp.model.dao.entities.Category;
import stud.apach.servletsjsp.model.dao.entities.Menu;
import stud.apach.servletsjsp.model.dao.entities.User;

public class FILEDAOFactory extends DAOFactory {
	
	private static FILEDAOFactory instance;		
	
	private FILEDAOFactory() {}
	
	
	
	public static synchronized FILEDAOFactory getInstance() {
		if (instance == null) {
			instance = new FILEDAOFactory();
		}
		return instance;
	}

	@Override
	public EntityDAO<User> getUserDAO() {
		return null;
	}

	@Override
	public EntityDAO<Menu> getMenuDAO() {
		return null;
	}

	@Override
	public EntityDAO<Category> getCategoryDAO() {
		return null;
	}

	@Override
	public EntityDAO<Article> getArticleDAO() {
		return null;
	}
}
