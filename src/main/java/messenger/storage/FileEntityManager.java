package messenger.storage;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileEntityManager<T> implements EntityManager<T> {

	Path storage;
	boolean isNew = true;
	private static final String DEFAULT_STORAGE = "storage_file";

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
	public T find(int id) {
		List<T> allObjs = findAll();
		T obj = null;
		if (allObjs.size() <= id) {
			obj = allObjs.get(id - 1);
		}

		return obj;
	}

	@Override
	public boolean persist(T model) {
		try {

			List<T> currentObjects = readAllFromFile();

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
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return readAllFromFile();
	}

	protected List<T> readAllFromFile() {

		List<T> readItems = new ArrayList<T>();
		
		if (storage.toFile().length() == 0) {
			return readItems;
		}
		
		try {
			ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(storage));

			readItems = ((List<T>) ois.readObject());
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
