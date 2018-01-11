package stud.apach.servletsjsp.model.beans;

import stud.apach.servletsjsp.model.dao.daobeans.EntityDAO;
import stud.apach.servletsjsp.model.dao.entities.Article;
import stud.apach.servletsjsp.model.dao.factory.DAOFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

public class ArticleBean {

    public int getQuantityOfArticles(String uri) {

        String[] partsURI = getPartsURI(uri);
        String query = "";

        switch (partsURI[partsURI.length-2]) {
            case "byCategory":
                query = " WHERE category_id IN(SELECT category_id FROM categories WHERE name = '" + partsURI[partsURI.length-1] + "')";
                break;
            case "byUser":
                query = " WHERE user_id IN(SELECT user_id FROM users WHERE alias_name = '" + partsURI[partsURI.length-1] + "')";
                break;
            default:
                query = " WHERE date LIKE '%-" + getCurrentMonth() + "-%'";
        }

        DAOFactory dbFactory = DAOFactory.getDAOFactory(DAOFactory.DB);

        EntityDAO<Article> articleDAO;
        int quantityOfArticles = 0;

        if (dbFactory != null) {
            articleDAO = dbFactory.getArticleDAO();
            quantityOfArticles = articleDAO.count(query);
        }

        return quantityOfArticles;
    }

    public Set<Article> getArticlesInCurrentMonthPerPage(int limit, int offset) {

        String query = "MONTH(a.date) = MONTH(NOW()) AND YEAR(a.date) = YEAR(NOW()) ORDER BY a.date DESC LIMIT " + limit + " OFFSET " + offset;

        DAOFactory dbFactory = DAOFactory.getDAOFactory(DAOFactory.DB);
        EntityDAO<Article> articleDAO;
        Set<Article> articles = null;

        if (dbFactory != null) {
            articleDAO = dbFactory.getArticleDAO();
            articles = articleDAO.selectByParam(query);
        }

        return articles;
    }

    public Article getArticleById(long id) {

        DAOFactory dbFactory = DAOFactory.getDAOFactory(DAOFactory.DB);

        EntityDAO<Article> articleDAO;
        Article article = null;

        if (dbFactory != null) {
            articleDAO = dbFactory.getArticleDAO();
            article = articleDAO.findById(id);
        }

        return article;
    }

    public Set<Article> getArticlesByUri(String uri, int limit, int offset) {

        String[] partsURI = getPartsURI(uri);
        String query = "";

        switch (partsURI[partsURI.length-2]) {
            case "byCategory":
                query = "c.name = '" + partsURI[partsURI.length-1] + "' ORDER BY a.date DESC LIMIT " + limit + " OFFSET " + offset;
                break;
            case "byUser":
                query = "u.alias_name = '" + partsURI[partsURI.length-1] + "' ORDER BY a.date DESC LIMIT " + limit + " OFFSET " + offset;
                break;
        }

        DAOFactory dbFactory = DAOFactory.getDAOFactory(DAOFactory.DB);
        EntityDAO<Article> articleDAO;
        Set<Article> articles = null;

        if (dbFactory != null) {
            articleDAO = dbFactory.getArticleDAO();
            articles = articleDAO.selectByParam(query);
        }

        return articles;
    }

    private String[] getPartsURI(String uri) {
        return uri.split("/");
    }

    private String getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return String.valueOf(calendar.get(Calendar.MONTH) + 1);
    }

    public int getQuantityOfArticlesInCurrentMonth() {

        String query = " WHERE MONTH(date) = MONTH(NOW()) AND YEAR(date) = YEAR(NOW())";

        DAOFactory dbFactory = DAOFactory.getDAOFactory(DAOFactory.DB);

        EntityDAO<Article> articleDAO;
        int quantityOfArticles = 0;

        if (dbFactory != null) {
            articleDAO = dbFactory.getArticleDAO();
            quantityOfArticles = articleDAO.count(query);

        }

        return quantityOfArticles;
    }

    public Set<Article> getArticlesByUser(String userAlias) {

        String query = "u.alias_name = '" + userAlias + "' ORDER BY a.date DESC";

        DAOFactory dbFactory = DAOFactory.getDAOFactory(DAOFactory.DB);
        EntityDAO<Article> articleDAO;
        Set<Article> articles = null;

        if (dbFactory != null) {
            articleDAO = dbFactory.getArticleDAO();
            articles = articleDAO.selectByParam(query);
        }

        return articles;
    }

    public Set<Article> getArticlesBySearch(String search, int limit, int offset) {

        String query = "c.name IN(SELECT c.name FROM categories c WHERE c.category_id = a.category_id) AND u.alias_name IN(SELECT u.alias_name FROM users u WHERE u.user_id = a.user_id) AND a.title LIKE '%" + search + "%' ORDER BY a.date DESC LIMIT " + limit + " OFFSET " + offset;

        DAOFactory dbFactory = DAOFactory.getDAOFactory(DAOFactory.DB);
        EntityDAO<Article> articleDAO;
        Set<Article> articles = null;

        if (dbFactory != null) {
            articleDAO = dbFactory.getArticleDAO();
            articles = articleDAO.selectByParam(query);
        }

        return articles;
    }

    public int getQuantityOfArticlesBySearch(String search) {

        String query = " WHERE title LIKE '%" + search + "%'";

        DAOFactory dbFactory = DAOFactory.getDAOFactory(DAOFactory.DB);

        EntityDAO<Article> articleDAO;
        int quantityOfArticles = 0;

        if (dbFactory != null) {
            articleDAO = dbFactory.getArticleDAO();
            quantityOfArticles = articleDAO.count(query);
        }

        return quantityOfArticles;
    }

    public int createArticle(Article article) {

        DAOFactory dbFactory = DAOFactory.getDAOFactory(DAOFactory.DB);
        EntityDAO<Article> articleDAO;
        int newArticleNo = 0;

        if (dbFactory != null) {
            articleDAO = dbFactory.getArticleDAO();
            newArticleNo = articleDAO.insert(article);
        }

        return newArticleNo;
    }

    public String getImageByParam(long inputCategory) {

        String query = ", categories c WHERE a.category_id = " + inputCategory;

        DAOFactory dbFactory = DAOFactory.getDAOFactory(DAOFactory.DB);
        EntityDAO<Article> articleDAO;
        String image = null;

        if (dbFactory != null) {
            articleDAO = dbFactory.getArticleDAO();
            image = articleDAO.findColumnByParam("image", query);
        }

        return image;
    }

    public int updateArticle(Article article) {

        DAOFactory dbFactory = DAOFactory.getDAOFactory(DAOFactory.DB);
        EntityDAO<Article> articleDAO;
        int updArticleNo = 0;

        if (dbFactory != null) {
            articleDAO = dbFactory.getArticleDAO();
            updArticleNo = articleDAO.update(article);
        }

        return updArticleNo;
    }

    public int deleteArticle(Article article) {

        DAOFactory dbFactory = DAOFactory.getDAOFactory(DAOFactory.DB);
        EntityDAO<Article> articleDAO;
        int delArticleNo = 0;

        if (dbFactory != null) {
            articleDAO = dbFactory.getArticleDAO();
            delArticleNo = articleDAO.delete(article);
        }

        return delArticleNo;
    }
}
