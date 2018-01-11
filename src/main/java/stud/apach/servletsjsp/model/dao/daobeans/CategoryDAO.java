package stud.apach.servletsjsp.model.dao.daobeans;

import stud.apach.servletsjsp.model.dao.entities.*;
import stud.apach.servletsjsp.model.dao.factory.*;

import java.sql.*;
import java.util.*;

public class CategoryDAO implements EntityDAO<Category> {

    @Override
    public int insert(Category entity) {
        return 0;
    }

    @Override
    public int delete(Category entity) {
        return 0;
    }

    @Override
    public int update(Category entity) {
        return 0;
    }

    @Override
    public Category findById(long id) {
        return null;
    }

    @Override
    public Set<Category> select() {
        Set<Category> categories = new TreeSet<>(Comparator.comparing(Category::getId));
        Connection conn;
        PreparedStatement selectPstmt;
        ResultSet rs;

        String selectString = "SELECT category_id, name, position, parent_id, path FROM categories";

        try {
            conn = DBDAOFactory.getInstance().createConnection();
            selectPstmt = conn.prepareStatement(selectString);

            rs = selectPstmt.executeQuery();

            while (rs.next()) {
                Category category = new Category();

                category.setId(rs.getInt("category_id"));
                category.setName(rs.getString("name"));
                category.setPosition(rs.getInt("position"));
                category.setParentId(rs.getInt("parent_id"));
                category.setPath(rs.getString("path"));

                categories.add(category);
            }

            rs.close();
            rs = null;
            selectPstmt.close();
            selectPstmt = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public Set<Category> selectByParam(String param) {
        Set<Category> categories = new TreeSet<>(Comparator.comparing(Category::getId));
        Connection conn;
        PreparedStatement selectPstmt;
        ResultSet rs;

        String selectString = "SELECT category_id, name, position, parent_id, path FROM categories " + param;

        try {
            conn = DBDAOFactory.getInstance().createConnection();
            selectPstmt = conn.prepareStatement(selectString);

            rs = selectPstmt.executeQuery();

            while (rs.next()) {
                Category category = new Category();

                category.setId(rs.getInt("category_id"));
                category.setName(rs.getString("name"));
                category.setPosition(rs.getInt("position"));
                category.setParentId(rs.getInt("parent_id"));
                category.setPath(rs.getString("path"));

                categories.add(category);
            }

            rs.close();
            rs = null;
            selectPstmt.close();
            selectPstmt = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public int count(String param) {
        return 0;
    }

    @Override
    public String findColumnByParam(String column, String param) {
        return null;
    }

    @Override
    public Category findByParam(String param) {
        Category category = null;
        Connection conn;
        Statement stmt;
        ResultSet rs;
        String query = "SELECT category_id, name, parent_id, position, path FROM categories" + param;

        try {
            conn = DBDAOFactory.getInstance().createConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                category = new Category();
                category.setId(rs.getInt("category_id"));
                category.setName(rs.getString("name"));
                category.setParentId(rs.getInt("parent_id"));
                category.setPosition(rs.getInt("position"));
                category.setPath(rs.getString("path"));
            }

            rs.close();
            rs = null;
            stmt.close();
            stmt = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }
}
