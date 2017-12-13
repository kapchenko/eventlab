import classes.Event_Participant;
import classes.LiquibaseUtil;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertTrue;

public class TestEventParticipant {
    @Before
    public void init() {

        LiquibaseUtil.createLQBConnection();
        LiquibaseUtil.updateTo("v0.1.3");
    }

    @Test
    public  void testEvent_Participant() {
        Event_Participant executeEvent_Participant  = new Event_Participant(3,1);
        Event_Participant testEvent_Participant = Event_Participant.getEvent_Participant(3);
        assertTrue(testEvent_Participant.equals(executeEvent_Participant));
    }


}
