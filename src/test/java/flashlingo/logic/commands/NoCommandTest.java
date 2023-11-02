package flashlingo.logic.commands;

import static flashlingo.testutil.Assert.assertThrows;
import static flashlingo.testutil.TypicalFlashCards.getTypicalFlashlingo;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.flashlingo.logic.commands.NoCommand;
import seedu.flashlingo.logic.commands.exceptions.CommandException;
import seedu.flashlingo.model.Flashlingo;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.ModelManager;
import seedu.flashlingo.model.UserPrefs;

/**
 * Indicates user has not yet memorized the word.
 */
public class NoCommandTest {
    private Model model = new ModelManager(getTypicalFlashlingo(), new UserPrefs());
    @Test
    public void execute_no_success() {

        Model expectedModel = new ModelManager(new Flashlingo(model.getFlashlingo()), new UserPrefs());

        assertThrows(CommandException.class, () -> new NoCommand().execute(model));

    }

    @Test
    public void equals() {
        NoCommand noFirstCommand = new NoCommand();
        NoCommand noSecondCommand = new NoCommand();


        // same object -> returns true
        assertTrue(noFirstCommand.equals(noFirstCommand));

        // same values -> returns true
        NoCommand noFirstCommandCopy = new NoCommand();
        assertTrue(noFirstCommand.equals(noFirstCommandCopy));

        // different types -> returns false
        assertFalse(noFirstCommand.equals(1));

        // null -> returns false
        assertFalse(noFirstCommand.equals(null));

    }
}
