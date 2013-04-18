package messenger.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import messenger.dao.MessageDao;
import messenger.messege.Message;
import messenger.sender.Sender;

@Named
public class MessengerService {
	
	@Inject
	Sender sender;
	
	@Inject
	Message message;
	
	@Inject
	MessageDao messageDao;
	
	
	public void send() {
		sender.send(message);
	}

	public void saveMessage() {
		messageDao.persist(message);
	}

	public List<Message> fetchAllMessages() {
		return messageDao.fetchAll();
	}

	
}
