package messenger.storage;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.List;

import messenger.messege.Message;
import messenger.storage.FileEntityManager;

import org.junit.Test;

public class FileEntityManagerTest {
	
	@Test
	public void testSave() throws IOException {
		Path createFile = Files.createTempFile(null, "test");
		
		FileEntityManager<Message> em = new FileEntityManager<Message>(createFile);
		
		Message msg = new Message();
		msg.setMessageBody("test");
		
		em.persist(msg);
		Message persistedMessage = em.find(1);
		
		assertEquals(msg.getMessageBody(), persistedMessage.getMessageBody());
	}
	
	@Test
	public void testFindAll() throws IOException {
		Path createFile = Files.createTempFile(null, "test");
		
		FileEntityManager<Message> em = new FileEntityManager<Message>(createFile);
		
		Message msg1 = new Message();
		Message msg2 = new Message();
		msg1.setMessageBody("test1");
		msg2.setMessageBody("test2");
		
		em.persist(msg1);
		em.persist(msg2);
		List<Message> allMessages = em.findAll();
		
		assertEquals(msg1.getMessageBody(), allMessages.get(0).getMessageBody());
		assertEquals(msg2.getMessageBody(), allMessages.get(1).getMessageBody());
	}

}
