package stud.apach.servletsjsp.model.dao.daobeans;

import stud.apach.servletsjsp.model.dao.entities.Article;
import stud.apach.servletsjsp.model.dao.factory.DBDAOFactory;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class ArticleDAO implements EntityDAO<Article> {

    @Override
    public int insert(Article entity) {
        int result = 0;
        Connection conn;
        PreparedStatement insertPstmt;
        String insertString = "INSERT INTO articles (title, text, image, date, category_id, user_id) VALUES(?, ?, ?, ?, ?, ?)";

        try {
            conn = DBDAOFactory.getInstance().createConnection();
            insertPstmt = conn.prepareStatement(insertString);

            insertPstmt.setString(1, entity.getTitle());
            insertPstmt.setString(2, entity.getText());
            insertPstmt.setString(3, entity.getImage());
            insertPstmt.setString(4, entity.getDate());
            insertPstmt.setLong(5, entity.getCategoryId());
            insertPstmt.setLong(6, entity.getUserId());
            result = insertPstmt.executeUpdate();

            insertPstmt.close();
            insertPstmt = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int delete(Article entity) {
        int result = 0;
        Connection conn;
        PreparedStatement deletePstmt;
        String deleteString = "DELETE FROM articles WHERE article_id = ?";

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

    @Override
    public int update(Article entity) {
        int result = 0;
        Connection conn;
        PreparedStatement updatePstmt;
        String updateString = "UPDATE articles SET title = ?, text = ?, image = ?, date = ?, category_id = ?, user_id = ? WHERE article_id = ?";

        try {
            conn = DBDAOFactory.getInstance().createConnection();
            updatePstmt = conn.prepareStatement(updateString);

            updatePstmt.setString(1, entity.getTitle());
            updatePstmt.setString(2, entity.getText());
            updatePstmt.setString(3, entity.getImage());
            updatePstmt.setString(4, entity.getDate());
            updatePstmt.setLong(5, entity.getCategoryId());
            updatePstmt.setLong(6, entity.getUserId());
            updatePstmt.setLong(7, entity.getId());
            result = updatePstmt.executeUpdate();

            updatePstmt.close();
            updatePstmt = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Article findById(long id) {
        Article article = null;
        Connection conn;
        PreparedStatement findPstmt;
        ResultSet rs;
        String findString = "SELECT a.article_id, a.title, a.text, a.image, a.date, a.category_id, a.user_id, u.alias_name, c.name FROM articles a, users u, categories c WHERE a.user_id = u.user_id AND a.category_id = c.category_id AND a.article_id = ?";

        try {
            conn = DBDAOFactory.getInstance().createConnection();
            findPstmt = conn.prepareStatement(findString);

            findPstmt.setLong(1, id);
            rs = findPstmt.executeQuery();

            while (rs.next()) {
                article = new Article();
                article.setId(rs.getInt("article_id"));
                article.setTitle(rs.getString("title"));
                article.setText(rs.getString("text"));
                article.setImage(rs.getString("image"));
                article.setDate(rs.getString("date"));
                article.setCategoryId(rs.getInt("category_id"));
                article.setUserId(rs.getInt("user_id"));
                article.setUserAlias(rs.getString("alias_name"));
                article.setCategoryName(rs.getString("name"));
            }

            rs.close();
            rs = null;
            findPstmt.close();
            findPstmt = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }

    @Override
    public Set<Article> select() {
        TreeSet<Article> articles = new TreeSet<>(Comparator.comparing(Article::getDate).reversed());
        Connection conn;
        PreparedStatement selectPstmt;
        ResultSet rs;
        String selectString = "SELECT a.article_id, a.title, a.text, a.image, a.date, a.category_id, a.user_id, u.alias_name FROM articles a, users u WHERE a.user_id = u.user_id";

        try {
            conn = DBDAOFactory.getInstance().createConnection();
            selectPstmt = conn.prepareStatement(selectString);

            rs = selectPstmt.executeQuery();

            while (rs.next()) {
                Article article = new Article();
                article.setId(rs.getInt("article_id"));
                article.setTitle(rs.getString("title"));
                article.setText(rs.getString("text"));
                article.setImage(rs.getString("image"));
                article.setDate(rs.getString("date"));
                article.setCategoryId(rs.getInt("category_id"));
                article.setUserId(rs.getInt("user_id"));
                article.setUserAlias(rs.getString("alias_name"));
                articles.add(article);
            }

            rs.close();
            rs = null;
            selectPstmt.close();
            selectPstmt = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }

    @Override
    public Set<Article> selectByParam(String param) {
        Set<Article> articles = new LinkedHashSet<>();
        Connection conn;
        PreparedStatement selectPstmt;
        ResultSet rs;
        String selectString = "SELECT a.article_id, a.title, a.text, a.image, a.date, a.category_id, a.user_id, u.alias_name, c.name FROM articles a, users u, categories c WHERE a.user_id = u.user_id AND a.category_id = c.category_id AND " + param;

        try {
            conn = DBDAOFactory.getInstance().createConnection();
            selectPstmt = conn.prepareStatement(selectString);

            rs = selectPstmt.executeQuery();

            while (rs.next()) {
                Article article = new Article();
                article.setId(rs.getInt("article_id"));
                article.setTitle(rs.getString("title"));
                article.setText(rs.getString("text"));
                article.setImage(rs.getString("image"));
                article.setDate(rs.getString("date"));
                article.setCategoryId(rs.getInt("category_id"));
                article.setUserId(rs.getInt("user_id"));
                article.setUserAlias(rs.getString("alias_name"));
                article.setCategoryName(rs.getString("name"));
                articles.add(article);
            }

            rs.close();
            rs = null;
            selectPstmt.close();
            selectPstmt = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }

    @Override
    public int count(String param) {
        int result = 0;
        Connection conn;
        PreparedStatement countPstmt;
        ResultSet rs;
        String countString = "SELECT count(*) FROM articles" + param;

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
        String result = null;
        Connection conn;
        Statement stmt;
        ResultSet rs;
        String query = "SELECT a." + column + " FROM articles a" + param;

        try {
            conn = DBDAOFactory.getInstance().createConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            if (rs.next()) {
                result = rs.getString(1);

            }

            rs.close();
            rs = null;
            stmt.close();
            stmt = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Article findByParam(String param) {
        Article article = null;
        Connection conn;
        Statement stmt;
        ResultSet rs;
        String query = "SELECT article-id, title, text, image, date, category_id, user_id FROM articles WHERE " + param;

        try {
            conn = DBDAOFactory.getInstance().createConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                article = new Article();
                article.setId(rs.getInt("article_id"));
                article.setTitle(rs.getString("title"));
                article.setText(rs.getString("text"));
                article.setImage(rs.getString("image"));
                article.setDate(rs.getString("date"));
                article.setCategoryId(rs.getInt("category_id"));
                article.setUserId(rs.getInt("user_id"));
            }

            rs.close();
            rs = null;
            stmt.close();
            stmt = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }


}
