package stud.apach.servletsjsp.model.dao.daobeans;

import stud.apach.servletsjsp.model.dao.entities.*;
import stud.apach.servletsjsp.model.dao.factory.*;

import java.sql.*;
import java.util.*;

public class MenuDAO implements EntityDAO<Menu> {

    @Override
    public int insert(Menu entity) {
        return 0;
    }

    @Override
    public int delete(Menu entity) {
        return 0;
    }

    @Override
    public int update(Menu entity) {
        return 0;
    }

    @Override
    public Menu findById(long id) {
        return null;
    }

    @Override
    public Set<Menu> select() {
        Set<Menu> menu = new HashSet<>();
        Connection conn;
        PreparedStatement selectPstmt;
        ResultSet rs;

        String selectString = "SELECT menu_id, name, position, parent_id, path FROM menu";

        try {
            conn = DBDAOFactory.getInstance().createConnection();
            selectPstmt = conn.prepareStatement(selectString);

            rs = selectPstmt.executeQuery();

            while (rs.next()) {
                Menu menuItem = new Menu();

                menuItem.setId(rs.getInt("menu_id"));
                menuItem.setName(rs.getString("name"));
                menuItem.setPosition(rs.getInt("position"));
                menuItem.setParentId(rs.getInt("parent_id"));
                menuItem.setPath(rs.getString("path"));

                menu.add(menuItem);
            }

            rs.close();
            rs = null;
            selectPstmt.close();
            selectPstmt = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menu;
    }

    @Override
    public Set<Menu> selectByParam(String param) {
        return null;
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
    public Menu findByParam(String param) {
        return null;
    }
}
