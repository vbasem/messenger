package messenger.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import messenger.annotations.*;
import messenger.dao.*;
import messenger.messege.*;
import messenger.sender.*;
import org.slf4j.Logger;

@Named
@RequestScoped
public class Messenger {

    private SenderTypes senderType = SenderTypes.SMS;
    
    @Inject
    @RequestScoped
    @Chosen
    Sender sender;
    
    @Inject
    MessageDao messageDao;
    
    Message message;
    
    @Inject
    Logger logger;
    
    @Inject
    Event<SenderTypes> event;

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
        event.fire(senderType);
        messageDao.persist(message);
    }

    public List<Message> fetchAllMessages() {
        logger.info("fetching all message");
        List<Message> messages = messageDao.fetchAll();
        
        
        for (Message msg : messages) {
            logger.info("" +msg.getMessageBody());
        }
                
                
        return messages;
    }

    @Produces
    public SenderTypes getSenderType() {
        return senderType;
    }

    public void setSenderType(SenderTypes senderType) {
        this.senderType = senderType;
    }
}
