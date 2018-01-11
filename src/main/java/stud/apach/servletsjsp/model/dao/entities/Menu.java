package stud.apach.servletsjsp.model.dao.entities;

import java.util.ArrayList;
import java.util.List;

public class Menu implements Item, Comparable<Menu> {

    private long id;
    private String name;
    private int position;
    private int parentId;
    private String path;
    private List<Item> subItems = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Item> getSubItems() {
        return subItems;
    }

    public void setSubItems(List<Item> subItems) {
        this.subItems = subItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Menu menu = (Menu) o;

        if (id != menu.id) return false;
        if (position != menu.position) return false;
        if (parentId != menu.parentId) return false;
        if (name != null ? !name.equals(menu.name) : menu.name != null) return false;
        if (path != null ? !path.equals(menu.path) : menu.path != null) return false;
        return subItems != null ? subItems.equals(menu.subItems) : menu.subItems == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + position;
        result = 31 * result + parentId;
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + (subItems != null ? subItems.hashCode() : 0);
        return result;
    }



    @Override
    public int compareTo(Menu o) {
        return this.position - o.getPosition();
    }
}
