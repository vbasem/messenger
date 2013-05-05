package messenger.controller;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.event.Event;
import messenger.annotations.SenderTypes;

import messenger.dao.MessageDao;
import messenger.messege.*;
import messenger.sender.Sender;

import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.*;
import org.junit.Before;
import org.slf4j.Logger;

public class MessangerTest {

    private Messenger msgService;
    private Message msgMock;
    private Sender senderMock;
    private MessageDao daoMock;
    private Event<SenderTypes> eventMock;
    private Logger loggerMock;

    @Before
    public void createMessengerWithMocks() {
        msgService = new Messenger();
        msgMock = Mockito.mock(Message.class);
        senderMock = Mockito.mock(Sender.class);
        eventMock = (Event<SenderTypes>) Mockito.mock(Event.class);
        loggerMock = Mockito.mock(Logger.class);
        daoMock = Mockito.mock(MessageDao.class);

        msgService.messageDao = daoMock;
        msgService.message = msgMock;
        msgService.sender = senderMock;
        msgService.event = eventMock;
        msgService.logger = loggerMock;
    }

    @Test
    public void testSending() {

        msgService.send();

        Mockito.verify(senderMock, Mockito.times(1)).send(msgMock);
    }

    @Test
    public void listAllMessages() {
        Message msg1 = new Message();
        Message msg2 = new Message();
        msg1.setMessageBody("t1");
        msg2.setMessageBody("t2");

        List<Message> expectedMessages = new ArrayList<Message>();
        expectedMessages.add(msg1);
        expectedMessages.add(msg2);
        Mockito.when(daoMock.fetchAll()).thenReturn(expectedMessages);

        assertEquals(expectedMessages, msgService.fetchAllMessages());

    }

    @Test
    public void sendMessage_smsType_smsTypeEventIsCreated() {
        msgService.setSenderType(SenderTypes.SMS);

        msgService.send();

        Mockito.verify(eventMock)
                .fire(SenderTypes.SMS);
    }
}
