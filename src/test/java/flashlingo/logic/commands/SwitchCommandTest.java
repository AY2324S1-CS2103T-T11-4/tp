package flashlingo.logic.commands;

import static flashlingo.logic.commands.CommandTestUtil.assertCommandSuccess;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.flashlingo.logic.commands.SwitchCommand;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.ModelManager;

public class SwitchCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_switch_success() {
        String expectedCommandResult = SwitchCommand.MESSAGE_SUCCESS + "dark theme!";
        assertCommandSuccess(new SwitchCommand(), model, expectedCommandResult, expectedModel);
    }
    @Test
    public void equals() {
        SwitchCommand switchFirstCommand = new SwitchCommand();
        SwitchCommand switchSecondCommand = new SwitchCommand();


        // same object -> returns true
        assertTrue(switchFirstCommand.equals(switchFirstCommand));

        // same values -> returns true
        SwitchCommand switchFirstCommandCopy = new SwitchCommand();
        assertTrue(switchFirstCommand.equals(switchFirstCommandCopy));

        // different types -> returns false
        assertFalse(switchFirstCommand.equals(1));

        // null -> returns false
        assertFalse(switchFirstCommand.equals(null));
    }
}
