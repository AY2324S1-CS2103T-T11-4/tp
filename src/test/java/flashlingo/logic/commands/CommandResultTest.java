package flashlingo.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.flashlingo.logic.commands.CommandResult;

/**
 * Represents the result of a command execution.
 */
public class CommandResultTest {

    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("some result");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("some result")));
        assertTrue(commandResult.equals(new CommandResult("some result",
                false, false, false)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("some other result")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("some result",
                true, false, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("some result", false,
                true, false)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("some result");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("some result").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("some other result").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("some result", true,
                false, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("some result", false,
                true, false).hashCode());
    }

    @Test
    public void toStringMethod() {
        CommandResult commandResult = new CommandResult("feedback");
        String expected = CommandResult.class.getCanonicalName() + "{feedbackToUser="
                + commandResult.getFeedbackToUser() + ", showHelp=" + commandResult.isShowHelp()
                + ", exit=" + commandResult.isExit() + "}";
        assertEquals(expected, commandResult.toString());
    }
}
