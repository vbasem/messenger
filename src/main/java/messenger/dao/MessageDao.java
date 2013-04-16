package messenger.dao;

import javax.inject.Inject;

import messenger.messege.Message;
import messenger.storage.EntityManager;


public class MessageDao extends AbstractDao<Message>{
	
	@Inject
	EntityManager<Message> em;
	
	@Override
	public EntityManager<Message> getEntityManager() {
		return em;
	}
}
