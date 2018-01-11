package stud.apach.servletsjsp.model.beans;


import stud.apach.servletsjsp.model.dao.factory.*;
import stud.apach.servletsjsp.model.dao.daobeans.*;
import stud.apach.servletsjsp.model.dao.entities.*;


import java.io.File;
import java.util.Set;

public class UserBean {
	
	
	public int createUser(User user) {		
		
		DAOFactory dbFactory = DAOFactory.getDAOFactory(DAOFactory.DB);

		EntityDAO<User> userDAO;
		int newUserNo = 0;
		if (dbFactory != null) {
			userDAO = dbFactory.getUserDAO();
			newUserNo = userDAO.insert(user);

		}
		
		return newUserNo;
	}
	
	public int updateUser(User user) {		
		
		DAOFactory dbFactory = DAOFactory.getDAOFactory(DAOFactory.DB);

		EntityDAO<User> userDAO;
		int updUserNo = 0;
		if (dbFactory != null) {
			userDAO = dbFactory.getUserDAO();
			updUserNo = userDAO.update(user);

		}

		return updUserNo;
	}
	
	public User getUserById(long id) {		
		
		DAOFactory dbFactory = DAOFactory.getDAOFactory(DAOFactory.DB);

		EntityDAO<User> userDAO;
		User user = null;
		if (dbFactory != null) {
			userDAO = dbFactory.getUserDAO();
			user = userDAO.findById(id);

		}

		return user;
	}

	public User getUserByParam(String param) {

		DAOFactory dbFactory = DAOFactory.getDAOFactory(DAOFactory.DB);
		EntityDAO<User> userDAO;
		User user = null;

		if (dbFactory != null) {
			userDAO = dbFactory.getUserDAO();
			user = userDAO.findByParam(param);

		}

		return user;
	}

	public User getUserByAlias(String alias) {

		String query = " alias_name = '" + alias + "'";

		DAOFactory dbFactory = DAOFactory.getDAOFactory(DAOFactory.DB);
		EntityDAO<User> userDAO;
		User user = null;

		if (dbFactory != null) {
			userDAO = dbFactory.getUserDAO();
			user = userDAO.findByParam(query);

		}

		return user;
	}

	public int registerUser(User user) {

		DAOFactory dbFactory = DAOFactory.getDAOFactory(DAOFactory.DB);

		EntityDAO<User> userDAO;
		int regUserNo = 0;
		if (dbFactory != null) {
			userDAO = dbFactory.getUserDAO();
			User findUser = userDAO.findByParam("email = " + "'" + user.getEmail() + "'");
			if (findUser == null) {
				regUserNo = createUser(user);
			}
		}
		return regUserNo;
	}

	public Set<User> getPopularUsers() {

		String query = "ORDER BY count DESC LIMIT 3";

		DAOFactory dbFactory = DAOFactory.getDAOFactory(DAOFactory.DB);
		EntityDAO<User> userDAO;
		Set<User> users = null;

		if (dbFactory != null) {
			userDAO = dbFactory.getUserDAO();
			users = userDAO.selectByParam(query);
		}

		return users;
	}

    public Set<User> getAllUsers(int limit, int offset) {

		String query = "ORDER BY count DESC LIMIT " + limit + " OFFSET " + offset;

		DAOFactory dbFactory = DAOFactory.getDAOFactory(DAOFactory.DB);
		EntityDAO<User> userDAO;
		Set<User> users = null;

		if (dbFactory != null) {
			userDAO = dbFactory.getUserDAO();
			users = userDAO.selectByParam(query);

		}

		return users;
    }

	public int getQuantityOfUsers() {

		DAOFactory dbFactory = DAOFactory.getDAOFactory(DAOFactory.DB);
		EntityDAO<User> userDAO;
		int quantityOfUsers = 0;

		if (dbFactory != null) {
			userDAO = dbFactory.getUserDAO();
			quantityOfUsers = userDAO.count("");
		}

		return quantityOfUsers;
	}

    public boolean checkLogin(String loginEmail, String loginPassword) {


		long emailResult;
		long passResult;

		User user = getUserByParam(" email = '" + loginEmail + "'");
		if (user == null) {
			return false;
		} else {
			emailResult = user.getId();
			user = getUserByParam(" password = '" + loginPassword + "'");
			if (user == null) {
				return false;
			} else {
				passResult = user.getId();
			}
		}

		return emailResult == passResult;
    }
}
