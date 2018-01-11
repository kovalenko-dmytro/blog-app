package stud.apach.servletsjsp.model.beans;

import stud.apach.servletsjsp.model.dao.factory.*;
import stud.apach.servletsjsp.model.dao.daobeans.*;
import stud.apach.servletsjsp.model.dao.entities.*;

import java.util.*;

public class CategoryBean {

    public List<Item> createCategoryList() {
        List<Item> categoryList = new ArrayList<>();

        DAOFactory dbFactory = DAOFactory.getDAOFactory(DAOFactory.DB);
        EntityDAO<Category> categoryDAO;

        if (dbFactory != null) {
            categoryDAO = dbFactory.getCategoryDAO();
            Set<Category> categorySet = categoryDAO.select();

            Iterator<Category> iterator = categorySet.iterator();

            while (iterator.hasNext()) {
                Category categoryItem = iterator.next();
                if (categoryItem.getParentId() == 0) {
                    categoryList.add(categoryItem);
                } else {
                    for (Category item : categorySet) {
                        if (item.getId() == categoryItem.getParentId()) {
                            item.getSubItems().add(categoryItem);
                        }
                    }
                }

            }
        }
        categoryList.sort(Comparator.comparing(Item::getPosition));
        return categoryList;
    }

    public Set<Category> getCategories() {

        String query = "WHERE path <> ''";
        Set<Category> categories = new TreeSet<>();
        DAOFactory dbFactory = DAOFactory.getDAOFactory(DAOFactory.DB);
        EntityDAO<Category> categoryDAO;

        if (dbFactory != null) {
            categoryDAO = dbFactory.getCategoryDAO();
            categories = categoryDAO.selectByParam(query);
        }

        return categories;
    }
}
