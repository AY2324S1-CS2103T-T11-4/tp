package seedu.flashlingo.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.flashlingo.commons.util.ToStringBuilder;
import seedu.flashlingo.logic.commands.exceptions.CommandException;
import seedu.flashlingo.model.Model;

/**
 * Switches the color theme of the application.
 */
public class SwitchCommand extends Command {
    public static final String COMMAND_WORD = "switch";
    public static final String MESSAGE_SUCCESS = "You have switched to ";

    public SwitchCommand() {}

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.switchTheme();
        String currentTheme = model.getTheme().equals("Default") ? "light" : "dark";
        return new CommandResult(MESSAGE_SUCCESS + currentTheme + " theme!", false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SwitchCommand)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }
}
