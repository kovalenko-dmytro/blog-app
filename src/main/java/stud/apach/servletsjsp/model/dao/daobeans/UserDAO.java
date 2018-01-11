package stud.apach.servletsjsp.model.dao.daobeans;

import java.sql.*;
import java.util.*;

import stud.apach.servletsjsp.model.dao.entities.*;
import stud.apach.servletsjsp.model.dao.factory.*;

public class UserDAO implements EntityDAO<User> {
	
	public int insert(User entity) {
		int result = 0;
		Connection conn;
		PreparedStatement insertPstmt;
		String insertString = "INSERT INTO users (alias_name, first_name, last_name, email, password, avatar) VALUES(?, ?, ?, ?, ?, ?)";
		
		try {
			conn = DBDAOFactory.getInstance().createConnection();
			insertPstmt = conn.prepareStatement(insertString);
			
			insertPstmt.setString(1, entity.getAlias());
			insertPstmt.setString(2, entity.getFirstName());
			insertPstmt.setString(3, entity.getLastName());
			insertPstmt.setString(4, entity.getEmail());
			insertPstmt.setString(5, entity.getPassword());
			insertPstmt.setString(6, entity.getAvatar());
			result = insertPstmt.executeUpdate();
			
			insertPstmt.close();
			insertPstmt = null;			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int delete(User entity) {
		int result = 0;
		Connection conn;
		PreparedStatement deletePstmt;
		String deleteString = "DELETE FROM users WHERE user_id = ?";
		
		try {
			conn = DBDAOFactory.getInstance().createConnection();
			deletePstmt = conn.prepareStatement(deleteString);
			
			deletePstmt.setLong(1, entity.getId());						
			result = deletePstmt.executeUpdate();
			
			deletePstmt.close();
			deletePstmt = null;			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int update(User entity) {
		int result = 0;
		Connection conn;
		PreparedStatement updatePstmt;
		String updateString = "UPDATE users SET alias_name = ?, first_name = ?, last_name = ?, email = ?, avatar = ? WHERE user_id = ?";
		
		try {
			conn = DBDAOFactory.getInstance().createConnection();
			updatePstmt = conn.prepareStatement(updateString);

			updatePstmt.setString(1, entity.getAlias());
			updatePstmt.setString(2, entity.getFirstName());
			updatePstmt.setString(3, entity.getLastName());
			updatePstmt.setString(4, entity.getEmail());
			updatePstmt.setString(5, entity.getAvatar());
			updatePstmt.setLong(6, entity.getId());
			result = updatePstmt.executeUpdate();

			updatePstmt.close();
			updatePstmt = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public User findById(long id) {
		User user = null;
		Connection conn;
		PreparedStatement findPstmt;
		ResultSet rs;
		String findString = "SELECT user_id, alias_name, first_name, last_name, email, password FROM users WHERE user_id = ?";
		
		try {
			conn = DBDAOFactory.getInstance().createConnection();
			findPstmt = conn.prepareStatement(findString);		
			
			findPstmt.setLong(1, id);			
			rs = findPstmt.executeQuery();
			
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("user_id"));
				user.setAlias(rs.getString("alias_name"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
			}
			
			rs.close();
			rs = null;
			findPstmt.close();
			findPstmt = null;			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}	
	
	public Set<User> select() {
		Set<User> users = new HashSet<>();
		Connection conn;
		PreparedStatement selectPstmt;
		ResultSet rs;
		String selectString = "SELECT user_id, alias_name, first_name, last_name, email, password FROM users";
		
		try {
			conn = DBDAOFactory.getInstance().createConnection();
			selectPstmt = conn.prepareStatement(selectString);
			
			rs = selectPstmt.executeQuery();			
			
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("user_id"));
				user.setAlias(rs.getString("alias_name"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				users.add(user);
			}
			
			rs.close();
			rs = null;
			selectPstmt.close();
			selectPstmt = null;			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public Set<User> selectByParam(String param) {
		Set<User> users = new LinkedHashSet<>();
		Connection conn;
		PreparedStatement selectPstmt;
		ResultSet rs;
		String selectString = "SELECT users.user_id, users.alias_name, users.first_name, users.last_name, users.email, users.avatar, (SELECT count(*) FROM articles WHERE articles.user_id = users.user_id) AS count FROM users " + param;

		try {
			conn = DBDAOFactory.getInstance().createConnection();
			selectPstmt = conn.prepareStatement(selectString);

			rs = selectPstmt.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("user_id"));
				user.setAlias(rs.getString("alias_name"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setAvatar(rs.getString("avatar"));
				user.setArticleCount(rs.getInt("count"));
				users.add(user);
			}

			rs.close();
			rs = null;
			selectPstmt.close();
			selectPstmt = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public int count(String param) {
		int result = 0;
		Connection conn;
		PreparedStatement countPstmt;
		ResultSet rs;
		String countString = "SELECT count(*) FROM users" + param;

		try {
			conn = DBDAOFactory.getInstance().createConnection();
			countPstmt = conn.prepareStatement(countString);
			rs = countPstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);

			}

			countPstmt.close();
			countPstmt = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String findColumnByParam(String column, String param) {
		return null;
	}

	@Override
	public User findByParam(String param) {
		User user = null;
		Connection conn;
		Statement stmt;
		ResultSet rs;
		String query = "SELECT user_id, alias_name, first_name, last_name, email, avatar, (SELECT count(*) FROM articles WHERE articles.user_id = users.user_id) AS count FROM users WHERE " + param;

		try {
			conn = DBDAOFactory.getInstance().createConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("user_id"));
				user.setAlias(rs.getString("alias_name"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setAvatar(rs.getString("avatar"));
				user.setArticleCount(rs.getInt("count"));
			}

			rs.close();
			rs = null;
			stmt.close();
			stmt = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
}