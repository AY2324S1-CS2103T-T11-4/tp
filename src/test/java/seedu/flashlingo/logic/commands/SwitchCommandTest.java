package seedu.flashlingo.logic.commands;

import static seedu.flashlingo.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.flashlingo.logic.commands.SwitchCommand.MESSAGE_SUCCESS;

import org.junit.jupiter.api.Test;

import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.ModelManager;

public class SwitchCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_switchToDarkTheme_success() {
        expectedModel.switchTheme();
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SUCCESS + "dark theme!", false, false, false);
        assertCommandSuccess(new SwitchCommand(), model, expectedCommandResult, expectedModel);
    }
}
