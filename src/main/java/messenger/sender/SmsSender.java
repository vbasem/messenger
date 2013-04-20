package messenger.sender;

import javax.inject.Inject;

import org.slf4j.Logger;

import messenger.annotations.Chosen;
import messenger.annotations.SenderTypes;
import messenger.messege.Message;

public class SmsSender implements Sender {

	@Inject
	Logger logger;

	@Override
	public void send(Message message) {
		logger.info("sending sms message to: {} | from: {} | message: {}",
				message.getMessageReceiver(),
				message.getMessageSender(),
				message.getMessageBody());
	}

}
