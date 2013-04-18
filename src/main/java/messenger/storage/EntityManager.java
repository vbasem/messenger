package messenger.storage;

import java.io.Serializable;
import java.util.List;

public interface EntityManager {
	
	public <T> T find(Class<T> entityClass, int id);
	public boolean persist(Serializable model);
	public <T> List<T> findAll(Class<T> entityClass);

}
