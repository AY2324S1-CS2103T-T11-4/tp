package seedu.flashlingo.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.flashlingo.commons.util.ToStringBuilder;
import seedu.flashlingo.logic.commands.exceptions.CommandException;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.flashcard.FlashCard;

/**
 * Indicates user has not yet memorized the word.
 */
public class RevealCommand extends Command {

    public static final String COMMAND_WORD = "reveal";

    // For help function
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Reveals the translation.\n"
        + "Example: " + COMMAND_WORD + " ";

    public static final String MESSAGE_SUCCESS = "The translation is: ";


    /**
     * Creates an NoCommand.
     */
    public RevealCommand() {};

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        FlashCard currentCard = model.getFilteredFlashCardList().get(0);
        return new CommandResult(MESSAGE_SUCCESS + currentCard.getTranslatedWord());
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RevealCommand)) {
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
