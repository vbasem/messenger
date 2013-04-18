package messenger.storage;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import messenger.messege.Message;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class FileEntityManagerTest {
	
	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();
	
	@Test
	public void testSave() throws IOException {
		//Path createFile = Files.createTempFile(null, "test");
		
		FileEntityManager em = new FileEntityManager(tempFolder.newFile().toPath());
		
		Message msg = new Message();
		msg.setMessageBody("test");
		
		em.persist(msg);
		Message persistedMessage = em.find(Message.class, 1);
		
		assertEquals(msg.getMessageBody(), persistedMessage.getMessageBody());
	}
	
	@Test
	public void testFindAll() throws IOException {
		
		FileEntityManager em = new FileEntityManager(tempFolder.newFile().toPath());
		
		Message msg1 = new Message();
		Message msg2 = new Message();
		msg1.setMessageBody("test1");
		msg2.setMessageBody("test2");
		
		em.persist(msg1);
		em.persist(msg2);
		List<Message> allMessages = em.findAll(Message.class);
		
		assertEquals(msg1.getMessageBody(), allMessages.get(0).getMessageBody());
		assertEquals(msg2.getMessageBody(), allMessages.get(1).getMessageBody());
	}

}
