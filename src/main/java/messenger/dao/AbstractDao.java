package messenger.dao;

import java.io.Serializable;
import java.util.List;

import messenger.storage.EntityManager;

public abstract class AbstractDao<T extends Serializable> {

	private Class<T> modelClass;
	
	public AbstractDao(Class<T> modelClass) {
		this.modelClass = modelClass;
	}
	public abstract EntityManager getEntityManager();
	
	public boolean persist(T model) {
		return getEntityManager().persist(model);
	}
	
	public List<T> fetchAll() {
		return getEntityManager().findAll(modelClass);
	}
}
