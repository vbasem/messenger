package messenger.storage;

import java.util.List;

public interface EntityManager<T> {
	
	public T find(int id);
	public boolean persist(T model);
	public List<T> findAll();

}
