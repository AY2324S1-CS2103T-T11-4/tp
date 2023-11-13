package seedu.flashlingo.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.flashlingo.commons.util.ToStringBuilder;
import seedu.flashlingo.logic.Messages;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.flashcard.WordLanguagePredicate;

/**
 * Finds and lists all flash cards in flashlingo whose original words or translations are in a certain language.
 */
public class LanguageCommand extends Command {

    public static final String COMMAND_WORD = "language";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all flashcards whose language is "
            + "the specified language and displays them as a list with index numbers.\n"
            + "Parameters: LANGUAGE\n"
            + "Example: " + COMMAND_WORD + " English";

    /**
     * The predicate used to filter the flash card list with specified language.
     */
    private final WordLanguagePredicate predicate;

    public LanguageCommand(WordLanguagePredicate predicate) {
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
        if (!(other instanceof LanguageCommand)) {
            return false;
        }

        LanguageCommand otherLanguageCommand = (LanguageCommand) other;
        return predicate.equals(otherLanguageCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
