package seedu.flashlingo.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.flashlingo.model.Model.PREDICATE_SHOW_ALL_FLASHCARDS;

import seedu.flashlingo.commons.util.ToStringBuilder;
import seedu.flashlingo.logic.commands.exceptions.CommandException;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.flashcard.FlashCard;
import seedu.flashlingo.session.SessionManager;

/**
 * Indicates user has memorized the word.
 */
public class YesCommand extends Command {

    public static final String COMMAND_WORD = "yes";

    // For help function
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Indicates user has successfully memorized the word.\n"
            + "Example: " + COMMAND_WORD + " ";

    public static final String MESSAGE_SUCCESS = "Great Job! You have indicated that you have memorized the word!\n";
    /**
     * Creates an YesCommand.
     */
    public YesCommand() {}

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        FlashCard response = model.nextReviewWord();
        response.updateLevel(true);
        response.recallFlashCard();
        // Deals with the case where there's no more words to review
        if (!model.hasNextRound()) {
            SessionManager.getInstance().setSession(false);
            model.updateFilteredFlashCardList(PREDICATE_SHOW_ALL_FLASHCARDS);
            return new CommandResult(MESSAGE_SUCCESS + "\nYou have no more words to review!");
        }
        // Deals with the case where there's more words to review
        model.nextReviewWord();
        return new CommandResult(MESSAGE_SUCCESS + "\nThe next word is: ");
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof YesCommand)) {
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
