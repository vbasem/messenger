package messenger.statistics;

import messenger.annotations.SenderTypes;
import org.junit.Test;
import static org.junit.Assert.*;

public class MessageStatisticTest {

    @Test
    public void messageSent_smsType_smsCounterIncreases() {
        MessageStatistic statistic = new MessageStatistic();
        statistic.init();
        
        statistic.messageSent(SenderTypes.SMS);
        
        assertEquals(1, statistic.getCounter(SenderTypes.SMS));
        
        statistic.messageSent(SenderTypes.SMS);
        
        assertEquals(2, statistic.getCounter(SenderTypes.SMS));
        assertEquals(0, statistic.getCounter(SenderTypes.EMAIL));
    }
    
    @Test
    public void messageSent_emailType_emailCounterIncreases() {
        MessageStatistic statistic = new MessageStatistic();
        statistic.init();
        
        statistic.messageSent(SenderTypes.EMAIL);
        
        assertEquals(1, statistic.getCounter(SenderTypes.EMAIL));
        
        statistic.messageSent(SenderTypes.EMAIL);
        
        assertEquals(2, statistic.getCounter(SenderTypes.EMAIL));
        assertEquals(0, statistic.getCounter(SenderTypes.SMS));
    }
}
