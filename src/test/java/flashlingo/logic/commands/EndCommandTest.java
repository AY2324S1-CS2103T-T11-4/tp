package flashlingo.logic.commands;

import static flashlingo.logic.commands.CommandTestUtil.assertCommandSuccess;
import static flashlingo.testutil.TypicalFlashCards.getTypicalFlashlingo;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.flashlingo.logic.commands.EndCommand;
import seedu.flashlingo.model.Flashlingo;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.ModelManager;
import seedu.flashlingo.model.UserPrefs;

/**
 * Ends a new session of reviewing.
 */
public class EndCommandTest {
    private Model model = new ModelManager(getTypicalFlashlingo(), new UserPrefs());
    @Test
    public void execute_end_success() {

        Model expectedModel = new ModelManager(new Flashlingo(model.getFlashlingo()), new UserPrefs());

        assertCommandSuccess(new EndCommand(), model, EndCommand.MESSAGE_SUCCESS, expectedModel);
    }
    @Test
    public void equals() {
        EndCommand endFirstCommand = new EndCommand();

        // same object -> returns true
        assertTrue(endFirstCommand.equals(endFirstCommand));

        // same values -> returns true
        EndCommand endFirstCommandCopy = new EndCommand();
        assertTrue(endFirstCommand.equals(endFirstCommandCopy));

        // different types -> returns false
        assertFalse(endFirstCommand.equals(1));

        // null -> returns false
        assertFalse(endFirstCommand.equals(null));

    }
}
