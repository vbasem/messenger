/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package messenger.statistics;

import java.util.EnumMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.inject.Named;
import javax.inject.Singleton;
import messenger.annotations.SenderTypes;


@Singleton
@Named
class MessageStatistic {
    
    private Map<SenderTypes, Integer> counters;
    
    @PostConstruct 
    public void init() {
        counters = new EnumMap<SenderTypes, Integer>(SenderTypes.class);
        for (SenderTypes type : SenderTypes.values()) {
            counters.put(type, 0);
        }
    }

    public void messageSent(@Observes SenderTypes senderType) {
        int currentCount = counters.get(senderType);
        int incrementedCounter = currentCount + 1;
        counters.put(senderType, incrementedCounter);
    }

    public int getCounter(SenderTypes senderType) {
        return counters.get(senderType);
    }
}
