package messenger.sender;

import javax.inject.Inject;


import messenger.messege.Message;
import org.slf4j.Logger;

public class EmailSender implements Sender {

	@Inject
	Logger logger;

	@Override
	public void send(Message message) {
		logger.info("sending email message to: {} | from: {} | message: {}",
				message.getMessageReceiver(),
				message.getMessageSender(),
				message.getMessageBody());

	}

}
