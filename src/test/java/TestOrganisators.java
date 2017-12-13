import classes.Organisators;
import classes.LiquibaseUtil;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertTrue;

public class TestOrganisators {

    @Before
    public void init() {

        LiquibaseUtil.createLQBConnection();
        LiquibaseUtil.updateTo("v0.1.3");
    }

        @Test
        public  void testOrganisators() {
            Organisators executeOrganisators  = new Organisators(3,"Atlas","3333333333");
            Organisators testOrganisators = Organisators.getOrganisators(3);
            assertTrue(testOrganisators.equals(executeOrganisators));
        }


    }
