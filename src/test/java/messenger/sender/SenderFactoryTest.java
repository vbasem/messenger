package messenger.sender;

import static org.junit.Assert.*;
import messenger.annotations.SenderTypes;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

public class SenderFactoryTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testGetSenderForSms() {
		SenderFactory factory = new SenderFactory();

		Sender sender = factory.getSender(Mockito.mock(SmsSender.class),
				Mockito.mock(EmailSender.class), SenderTypes.SMS);

		assertTrue(sender instanceof SmsSender);
	}

	@Test
	public void testGetSenderForEmail() {
		SenderFactory factory = new SenderFactory();

		Sender sender = factory.getSender(Mockito.mock(SmsSender.class),
				Mockito.mock(EmailSender.class), SenderTypes.EMAIL);

		assertTrue(sender instanceof EmailSender);
	}

	@Test
	public void testGetSenderForUnknown() {
		thrown.expect(RuntimeException.class);

		SenderFactory factory = new SenderFactory();

		Sender sender = factory.getSender(Mockito.mock(SmsSender.class),
				Mockito.mock(EmailSender.class),
				SenderTypes.valueOf("not defined"));
	}
}
