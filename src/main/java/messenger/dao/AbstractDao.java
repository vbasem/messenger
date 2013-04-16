package messenger.dao;

import java.util.List;

import messenger.storage.EntityManager;

public abstract class AbstractDao<T> {

	public abstract EntityManager<T> getEntityManager();
	
	public boolean persist(T model) {
		return getEntityManager().persist(model);
	}
	
	public List<T> fetchAll() {
		return getEntityManager().findAll();
	}
}
