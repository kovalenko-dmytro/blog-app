package stud.apach.servletsjsp.model.dao.entities;

import java.util.List;

public interface Item extends Entity {

    public long getId();
    public void setId(long id);

    public String getName();
    public void setName(String name);

    public int getPosition();
    public void setPosition(int position);

    public int getParentId();
    public void setParentId(int parentId);

    public String getPath();
    public void setPath(String path);

    public List<Item> getSubItems();
    public void setSubItems(List<Item> subItems);
}
