package messenger.dao;

import javax.inject.Inject;

import messenger.messege.Message;
import messenger.storage.EntityManager;


public class MessageDao extends AbstractDao<Message>{
	
	public MessageDao() {
		super(Message.class);
	}

	@Inject
	EntityManager em;
	
	@Override
	public EntityManager getEntityManager() {
		return em;
	}
}
