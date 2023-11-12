package seedu.flashlingo.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.flashlingo.logic.Messages.MESSAGE_DUPLICATE_FLASHCARD;
import static seedu.flashlingo.logic.Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX;
import static seedu.flashlingo.logic.parser.CliSyntax.PREFIX_ORIGINAL_WORD;
import static seedu.flashlingo.logic.parser.CliSyntax.PREFIX_ORIGINAL_WORD_LANGUAGE;
import static seedu.flashlingo.logic.parser.CliSyntax.PREFIX_TRANSLATED_WORD;
import static seedu.flashlingo.logic.parser.CliSyntax.PREFIX_TRANSLATED_WORD_LANGUAGE;
import static seedu.flashlingo.model.Model.PREDICATE_SHOW_ALL_FLASHCARDS;

import java.util.Arrays;
import java.util.List;

import seedu.flashlingo.commons.core.index.Index;
import seedu.flashlingo.commons.util.ToStringBuilder;
import seedu.flashlingo.logic.Messages;
import seedu.flashlingo.logic.commands.exceptions.CommandException;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.flashcard.FlashCard;

/**
 * Edits the details of an existing flash card in Flashlingo.
 */
public class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";
    public static final String MESSAGE_EDIT_FLASHCARD_SUCCESS = "Edited Flashcard: %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the flashcard identified "
            + "by the index number used in the displayed flashcard list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_ORIGINAL_WORD + "WORD] "
            + "[" + PREFIX_TRANSLATED_WORD + "TRANSLATION] "
            + "[" + PREFIX_ORIGINAL_WORD_LANGUAGE + "WORD_LANGUAGE] "
            + "[" + PREFIX_TRANSLATED_WORD_LANGUAGE + "TRANSLATION_LANGUAGE] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_ORIGINAL_WORD + "bye "
            + PREFIX_TRANSLATED_WORD + "再见";

    private final Index index;
    private final String[] changes;
    /**
     * @param index of the flashcard in the list to edit
     * @param changes details to edit the flashcard with
     */
    public EditCommand(Index index, String[] changes) {
        requireNonNull(index);
        this.index = index;
        this.changes = changes;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<FlashCard> lastShownList = model.getFilteredFlashCardList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
        }

        FlashCard flashCardToEdit = lastShownList.get(index.getZeroBased());
        FlashCard editedFlashCard;
        try {
            editedFlashCard = flashCardToEdit.editFlashCard(changes);
        } catch (IllegalArgumentException e) {
            throw new CommandException(e.getMessage());
        }

        if ((changes.length > 0 && model.hasFlashCard(editedFlashCard)) || flashCardToEdit.equals(editedFlashCard)) {
            throw new CommandException(MESSAGE_DUPLICATE_FLASHCARD);
        }
        model.setFlashCard(flashCardToEdit, editedFlashCard);
        model.updateFilteredFlashCardList(PREDICATE_SHOW_ALL_FLASHCARDS);
        return new CommandResult(String.format(MESSAGE_EDIT_FLASHCARD_SUCCESS, Messages.format(editedFlashCard)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        EditCommand otherEditCommand = (EditCommand) other;
        return index.equals(otherEditCommand.index)
                && changes.equals(otherEditCommand.changes);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("changes", Arrays.toString(changes))
                .toString();
    }
}
