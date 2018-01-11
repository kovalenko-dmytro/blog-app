package stud.apach.servletsjsp.model.beans;

import stud.apach.servletsjsp.model.dao.factory.*;
import stud.apach.servletsjsp.model.dao.daobeans.*;
import stud.apach.servletsjsp.model.dao.entities.*;

import java.util.*;

public class MenuBean {

    public Set<Menu> getItems() {

        DAOFactory dbFactory = DAOFactory.getDAOFactory(DAOFactory.DB);

        EntityDAO<Menu> menuDAO;
        Set<Menu> menuSet = null;
        if (dbFactory != null) {
            menuDAO = dbFactory.getMenuDAO();
            menuSet = menuDAO.select();

        }

        return menuSet;
    }

    public List<Item> createMenuList() {
        List<Item> menuList = new ArrayList<>();

        DAOFactory dbFactory = DAOFactory.getDAOFactory(DAOFactory.DB);
        EntityDAO<Menu> menuDAO;

        if (dbFactory != null) {
            menuDAO = dbFactory.getMenuDAO();
            Set<Menu> menuSet = menuDAO.select();

            Iterator<Menu> iterator = menuSet.iterator();

            while (iterator.hasNext()) {
                Menu menuItem = iterator.next();
                if (menuItem.getParentId() == 0) {
                    menuList.add(menuItem);
                } else {
                    for (Menu item : menuSet) {
                        if (item.getId() == menuItem.getParentId()) {
                            item.getSubItems().add(menuItem);
                        }
                    }
                }

            }
        }
        menuList.sort(Comparator.comparing(Item::getPosition));
        return menuList;
    }
}
