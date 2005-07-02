package javabot.operations;

import java.io.IOException;

import javabot.ApplicationException;
import javabot.BotEvent;
import javabot.JDBCDatabase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created Jun 28, 2005
 *
 * @author <a href="mailto:javabot@cheeseronline.org">Justin Lee</a>
 */
@Test(groups = {"operations"})
public class AddFactoidOperationTest extends BaseOperationTest {
    private static Log log = LogFactory.getLog(AddFactoidOperationTest.class);

    public AddFactoidOperationTest() {
        super();
    }

    public AddFactoidOperationTest(String name) {
        super(name);
    }

    public void factoidAdd() throws IOException {
        String errorMessage = "Should have added the factoid";
        testOperation(System.currentTimeMillis() + "test pong is pong", OKAY, errorMessage);
        testOperation(System.currentTimeMillis() + "ping $1 is <action>sends some radar to $1, "
            + "awaits a response then forgets how long it took", OKAY,
            errorMessage);
        testOperation(System.currentTimeMillis() + "what? is a question", OKAY, errorMessage);
        testOperation(System.currentTimeMillis() + "what up? is <see>what?", OKAY, errorMessage);
    }

    @Test(dependsOnMethods = {"factoidAdd"})
    public void duplicateAdd() throws IOException {
        String errorMessage = "Should not have added the factoid";
        String message = System.currentTimeMillis() + "test pong is pong";
        testOperation(message, OKAY, errorMessage);
        testOperation(message, ALREADY_HAVE_FACTOID, errorMessage);
    }

    public void blankFactoid() throws IOException {
        AddFactoidOperation operation = getOperation();
        String response = "Invalid factoid name";
        String errorMessage = "Should not have added the factoid";
        testOperation("is pong", response, errorMessage);
    }

    public void blankValue() throws IOException {
        String response = "Invalid factoid value";
        String errorMessage = "Should not have added the factoid";
        testOperation("pong is", response, errorMessage);
    }

    public void channelMessage() throws IOException {
        BotEvent event = new BotEvent("#test", SENDER, "", "localhost",
            "pong is");
        Assert.assertEquals(getOperation().handleChannelMessage(event).size(), 0,
            "Should be an empty list");
    }

    protected AddFactoidOperation getOperation() {
        return new AddFactoidOperation(getDatabase());
    }

}