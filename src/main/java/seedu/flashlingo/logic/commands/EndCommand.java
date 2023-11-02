package seedu.flashlingo.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.flashlingo.commons.util.ToStringBuilder;
import seedu.flashlingo.logic.commands.exceptions.CommandException;
import seedu.flashlingo.model.Model;


/**
 * Ends the session of reviewing.
 */
public class EndCommand extends Command {

    public static final String COMMAND_WORD = "end";

    // For help function
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Ends reviewing session.\n"
        + "Example: " + COMMAND_WORD + " ";

    public static final String MESSAGE_SUCCESS = "Review Session has ended.";
    public static final String MESSAGE_STATE_REPEATED = "Review session hasn't been started!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.endSession();
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EndCommand)) {
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
