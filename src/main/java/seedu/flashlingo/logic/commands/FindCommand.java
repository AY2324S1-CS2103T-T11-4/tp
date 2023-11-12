package seedu.flashlingo.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.flashlingo.commons.util.ToStringBuilder;
import seedu.flashlingo.logic.Messages;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.flashcard.WordContainsKeywordsPredicate;

/**
 * Finds and lists all flash cards in flashlingo whose original words contains any of the argument keywords.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all flashcards whose words contain any of "
            + "the specified keywords and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " shark";

    /**
     * The predicate used to filter the flash card list with specified keywords.
     */
    private final WordContainsKeywordsPredicate predicate;

    public FindCommand(WordContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredFlashCardList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_FLASHCARDS_LISTED_OVERVIEW,
                        model.getFilteredFlashCardList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCommand)) {
            return false;
        }

        FindCommand otherFindCommand = (FindCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
