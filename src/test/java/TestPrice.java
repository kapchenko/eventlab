import classes.Price;
import classes.LiquibaseUtil;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
public class TestPrice {

        @Before
        public void init() {

            LiquibaseUtil.createLQBConnection();
            LiquibaseUtil.updateTo("v0.1.3");
        }

        @Test
        public  void testPrice() {
            Price executePrice  = new Price(2,300);
            Price testPrice = Price.getPrice(2);
            assertTrue(testPrice.equals(executePrice));
        }


    }


