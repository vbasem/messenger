package messenger.messege;

import java.io.Serializable;

public class Message implements Serializable{
	
	private String messageReceiver;
	private String messageSender;
	private String messageBody;
	
	
	public Message() {

	}
	
	public String getMessageReceiver() {
		return messageReceiver;
	}
	public void setMessageReceiver(String messageReceiver) {
		this.messageReceiver = messageReceiver;
	}
	public String getMessageSender() {
		return messageSender;
	}
	public void setMessageSender(String messageSender) {
		this.messageSender = messageSender;
	}
	public String getMessageBody() {
		return messageBody;
	}
	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}
	
	

}
