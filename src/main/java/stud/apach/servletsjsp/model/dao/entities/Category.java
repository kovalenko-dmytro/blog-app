package stud.apach.servletsjsp.model.dao.entities;

import java.util.ArrayList;
import java.util.List;

public class Category implements Item, Comparable<Category> {

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

        Category category = (Category) o;

        if (id != category.id) return false;
        if (position != category.position) return false;
        if (parentId != category.parentId) return false;
        if (name != null ? !name.equals(category.name) : category.name != null) return false;
        if (path != null ? !path.equals(category.path) : category.path != null) return false;
        return subItems != null ? subItems.equals(category.subItems) : category.subItems == null;
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
    public int compareTo(Category o) {
        return this.position - o.getPosition();
    }
}
