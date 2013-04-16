package messenger.services;

import java.util.ArrayList;
import java.util.List;

import messenger.dao.MessageDao;
import messenger.messege.Message;
import messenger.sender.Sender;
import messenger.services.MessengerService;

import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.*;


public class MessangerServiceTest {
	
	@Test
	public void testSending() {
		MessengerService msgService = new MessengerService();
		Message msgMock = Mockito.mock(Message.class);
		Sender senderMock = Mockito.mock(Sender.class);
		msgService.message = msgMock;
		msgService.sender = senderMock;
		
		msgService.send();
		
		Mockito.verify(senderMock, Mockito.times(1)).send(msgMock);
	}
	
	@Test
	public void listAllMessages() {
		MessengerService msgService = new MessengerService();

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