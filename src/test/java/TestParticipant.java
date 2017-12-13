import classes.Participant;
import classes.LiquibaseUtil;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertTrue;

public class TestParticipant {

    @Before
    public void init() {

        LiquibaseUtil.createLQBConnection();
        LiquibaseUtil.updateTo("v0.1.3");
    }

    @Test
    public  void testParticipant() {
        Participant executeParticipant  = new Participant(2,"Kasabian");
        Participant testParticipant = Participant.getParticipant(2);
        assertTrue(testParticipant.equals(executeParticipant));
    }


}
