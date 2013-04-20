package messenger.sender;

import messenger.messege.Message;
import messenger.sender.EmailSender;

import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;

public class EmailSenderTest {

	@Test
	public void testEmailSender() {
		EmailSender emailSender = new EmailSender();
		Message msg = new Message();
		msg.setMessageBody("test message");
		msg.setMessageReceiver("you@you.you");
		msg.setMessageSender("me@me.me");

		Logger mockLogger = Mockito.mock(Logger.class);
		emailSender.logger = mockLogger;
		
		emailSender.send(msg);
		
		Mockito.verify(mockLogger, Mockito.times(1)).info(
				"sending email message to: {} | from: {} | message: {}",
				msg.getMessageReceiver(), 
				msg.getMessageSender(),
				msg.getMessageBody());

	}
}
