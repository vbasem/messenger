package messenger.storage;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileEntityManager implements EntityManager {

	private static final String DEFAULT_STORAGE = "C:\\Users\\Home\\storage_file";
	
	Path storage;

	public FileEntityManager() {
		this(Paths.get(DEFAULT_STORAGE));
	}

	public FileEntityManager(Path path) {

		try {
			if (Files.exists(path)) {
				storage = path;
			} else {
				storage = Files.createFile(path);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public <T> T find(Class<T> entityClass, int id) {
		List<T> allObjs = findAll(entityClass);
		T obj = null;
		if (allObjs.size() <= id) {
			obj = allObjs.get(id - 1);
		}

		return obj;
	}

	@Override
	public boolean persist(Serializable model) {
		try {

			List currentObjects = readAllFromFile();

			ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(storage));

			currentObjects.add(model);
			oos.writeObject(currentObjects);
			oos.flush();
			oos.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public <T> List<T> findAll(Class<T> entityClass) {
		// TODO Auto-generated method stub
		return readAllFromFile();
	}

	protected List readAllFromFile() {

		List readItems = new ArrayList();
		
		if (storage.toFile().length() == 0) {
			return readItems;
		}
		
		try {
			ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(storage));

			readItems = (List) ois.readObject();
			// ois.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return readItems;
	}

}
