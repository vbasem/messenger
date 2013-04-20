/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package messenger.sender;

import messenger.messege.Message;
import messenger.sender.SmsSender;

import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;

public class SmsSenderTest {

	@Test
	public void testSendMessage() {
		SmsSender smsSender = new SmsSender();
		Logger logger = Mockito.mock(Logger.class);
		smsSender.logger = logger;

		Message message = new Message();
		message.setMessageBody("test message");
		message.setMessageSender("me");
		message.setMessageReceiver("you");

		smsSender.send(message);

		Mockito.verify(logger, Mockito.times(1)).info(
				"sending sms message to: {} | from: {} | message: {}", 
				message.getMessageReceiver(),
				message.getMessageSender(),
				message.getMessageBody());
	}
}