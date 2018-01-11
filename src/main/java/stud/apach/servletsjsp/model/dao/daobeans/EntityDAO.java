package stud.apach.servletsjsp.model.dao.daobeans;

import stud.apach.servletsjsp.model.dao.entities.Entity;
import java.util.*;

public interface EntityDAO<E extends Entity> {

	int insert(E entity);
	int delete(E entity);
	int update(E entity);
	E findById(long id);
    E findByParam(String param);
	Set<E> select();
	Set<E> selectByParam(String param);
	int count(String param);
	String findColumnByParam(String column, String param);
}
