package messenger.sender;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import messenger.annotations.Chosen;
import messenger.annotations.SenderTypes;

public class SenderFactory {

	@RequestScoped
	@Produces
	@Chosen
	public Sender getSender(@New SmsSender smsSender, @New EmailSender emailSender, SenderTypes type) {
		switch (type) {
			case SMS:
				return smsSender;
			case EMAIL:
				return emailSender;
		}
		
		throw new RuntimeException("no sender for the type" + type.toString());
	}

}
