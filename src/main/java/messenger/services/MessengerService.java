package messenger.services;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import messenger.annotations.*;
import messenger.dao.*;
import messenger.messege.*;
import messenger.sender.*;

@Named
@RequestScoped
public class MessengerService {

    private SenderTypes senderType = SenderTypes.SMS;
    
    @Inject
    @RequestScoped
    @Chosen
    Sender sender;
    
    @Inject
    MessageDao messageDao;
    Message message;

    public Message getMessage() {
        return message;
    }

    @PostConstruct
    public void init() {
        message = new Message();
    }
    
    public SenderTypes[] getSenderTypes() {
    
        return SenderTypes.values();
    }

    public void send() {
        sender.send(message);
        saveMessage();
    }

    public void saveMessage() {
        messageDao.persist(message);
    }

    public List<Message> fetchAllMessages() {
        return messageDao.fetchAll();
    }

    @Produces
    public SenderTypes getSenderType() {
        return senderType;
    }

    public void setSenderType(SenderTypes senderType) {
        this.senderType = senderType;
    }
}
