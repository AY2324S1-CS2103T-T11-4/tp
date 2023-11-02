package flashlingo.logic.commands;

import static flashlingo.testutil.Assert.assertThrows;
import static flashlingo.testutil.TypicalFlashCards.getTypicalFlashlingo;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.flashlingo.logic.commands.YesCommand;
import seedu.flashlingo.logic.commands.exceptions.CommandException;
import seedu.flashlingo.model.Flashlingo;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.ModelManager;
import seedu.flashlingo.model.UserPrefs;

/**
 * Indicates user has memorized the word.
 */
public class YesCommandTest {

    private Model model = new ModelManager(getTypicalFlashlingo(), new UserPrefs());
    @Test
    public void execute_yes_success() {

        Model expectedModel = new ModelManager(new Flashlingo(model.getFlashlingo()), new UserPrefs());

        assertThrows(CommandException.class, () -> new YesCommand().execute(model));
    }
    @Test
    public void equals() {
        YesCommand yesFirstCommand = new YesCommand();

        // same object -> returns true
        assertTrue(yesFirstCommand.equals(yesFirstCommand));

        // same values -> returns true
        YesCommand yesFirstCommandCopy = new YesCommand();
        assertTrue(yesFirstCommand.equals(yesFirstCommandCopy));

        // different types -> returns false
        assertFalse(yesFirstCommand.equals(1));

        // null -> returns false
        assertFalse(yesFirstCommand.equals(null));

    }
}
