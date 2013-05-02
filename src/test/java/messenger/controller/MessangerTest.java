package messenger.controller;

import java.util.ArrayList;
import java.util.List;

import messenger.dao.AbstractDao;
import messenger.dao.MessageDao;
import messenger.messege.*;
import messenger.sender.Sender;
import messenger.controller.Messenger;
import messenger.storage.EntityManager;

import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.*;


public class MessangerTest {
	
	@Test
	public void testSending() {
		Messenger msgService = new Messenger();
		Message msgMock = Mockito.mock(Message.class);
		Sender senderMock = Mockito.mock(Sender.class);
		MessageDao daoMock = Mockito.mock(MessageDao.class);
		msgService.messageDao = daoMock;
		msgService.message = msgMock;
		msgService.sender = senderMock;
		
		msgService.send();
		
		Mockito.verify(senderMock, Mockito.times(1)).send(msgMock);
	}
	
	@Test
	public void listAllMessages() {
		Messenger msgService = new Messenger();

		Message msg1 = new Message();
		Message msg2 = new Message();
		msg1.setMessageBody("t1");
		msg2.setMessageBody("t2");
		
		List<Message> expectedMessages = new ArrayList<Message>();
		expectedMessages.add(msg1);
		expectedMessages.add(msg2);
		
		MessageDao messageDaoMock = Mockito.mock(MessageDao.class);
		Mockito.when(messageDaoMock.fetchAll()).thenReturn(expectedMessages);
		
		msgService.messageDao = messageDaoMock;
		
		assertEquals(expectedMessages, msgService.fetchAllMessages());
		
	}
}
