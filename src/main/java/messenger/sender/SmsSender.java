package messenger.sender;

import javax.inject.Inject;

import org.slf4j.Logger;

import messenger.annotations.SenderType;
import messenger.annotations.SenderTypes;
import messenger.messege.Message;

@SenderType(SenderTypes.SMS)
public class SmsSender implements Sender {

	@Inject
	Logger logger;

	@Override
	public void send(Message message) {
		logger.info("sending sms message to: {0} | from: {1} | message: {2}",
				message.getMessageReceiver(),
				message.getMessageSender(),
				message.getMessageBody());
	}

}
