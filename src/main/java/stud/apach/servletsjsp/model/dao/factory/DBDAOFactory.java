package stud.apach.servletsjsp.model.dao.factory;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;

import stud.apach.servletsjsp.model.dao.daobeans.*;
import stud.apach.servletsjsp.model.dao.entities.*;

public class DBDAOFactory extends DAOFactory {
	
	private static DBDAOFactory instance;
	private DataSource dataSource;
	private Connection connection;	
	private UserDAO userDAO;
	private MenuDAO menuDAO;
	private CategoryDAO categoryDAO;
	private ArticleDAO articleDAO;
	
	private DBDAOFactory() {}
	
	public static synchronized DBDAOFactory getInstance() {
		if (instance == null) {
			instance = new DBDAOFactory();
		}
		return instance;
	}
	
	public Connection createConnection() {
		if(connection == null){
			try {
				connection = getDataSource().getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}

	@Override
	public EntityDAO<User> getUserDAO() {
		if(userDAO == null){
		  userDAO = new UserDAO();
		}
		return userDAO;
		
	}

	@Override
	public EntityDAO<Menu> getMenuDAO() {
		if(menuDAO == null){
			menuDAO = new MenuDAO();
		}
		return menuDAO;
	}

	@Override
	public EntityDAO<Category> getCategoryDAO() {
		if(categoryDAO == null){
			categoryDAO = new CategoryDAO();
		}
		return categoryDAO;
	}

	@Override
	public EntityDAO<Article> getArticleDAO() {
		if(articleDAO == null){
			articleDAO = new ArticleDAO();
		}
		return articleDAO;
	}

	private DataSource getDataSource() {
		if(dataSource == null){
			Context initContext;
			try {
				initContext = new InitialContext();
				dataSource = (DataSource) initContext.lookup("java:/comp/env/jdbc/BlogDB");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return dataSource;
	}
}
