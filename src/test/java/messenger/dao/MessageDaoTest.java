package messenger.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;

import messenger.dao.MessageDao;
import messenger.messege.Message;
import messenger.storage.EntityManager;

import org.junit.Test;
import org.mockito.Mockito;



public class MessageDaoTest {

	
	@Test
	public void testPersist() {
		MessageDao dao = new MessageDao();
		Message msg1 = new Message();
		msg1.setMessageBody("test");
		
		EntityManager em = Mockito.mock(EntityManager.class);
		dao.em = em;
		
		dao.persist(msg1);
		
		Mockito.verify(em).persist(msg1);
		
	}
	
	@Test
	public void testFetchAll() {
		MessageDao dao = new MessageDao();
		ArrayList<Message> msgs = new ArrayList<Message>();
		Message msg1 = new Message();
		Message msg2 = new Message();
		msgs.add(msg1);
		msgs.add(msg2);
		msg1.setMessageBody("test");
		msg2.setMessageBody("test2");
		
		EntityManager em = Mockito.mock(EntityManager.class);
		Mockito.when(em.findAll(Message.class)).thenReturn(msgs);
		dao.em = em;
		
		assertEquals(msgs, dao.fetchAll());
	}
}
