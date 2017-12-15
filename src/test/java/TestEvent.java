import classes.Events;
import classes.LiquibaseUtil;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;



public class TestEvent {

    @Before
    public void init() {

        LiquibaseUtil.createLQBConnection();
        LiquibaseUtil.updateTo("v0.1.3");
    }

    @Test
    public void TestEvents() {
    Events executeEvents  = new Events(1,"Atlas Weekend",new java.sql.Date(2018,6,18), 3);
    Events testEvents = Events.getEvents(1);
    assertEquals(testEvents.toString(),executeEvents.toString());
    }
}
