package seedu.flashlingo.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.flashlingo.commons.util.ToStringBuilder;
import seedu.flashlingo.logic.commands.exceptions.CommandException;
import seedu.flashlingo.model.Model;


/**
 * Starts a new session of reviewing.
 */
public class StartCommand extends Command {

    public static final String COMMAND_WORD = "start";

    // For help function
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Starts reviewing session.\n"
        + "Example: " + COMMAND_WORD + " ";

    public static final String MESSAGE_SUCCESS = "Review Session has been started.";
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.startSession();
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StartCommand)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
          .add("start", "")
          .toString();
    }
}
