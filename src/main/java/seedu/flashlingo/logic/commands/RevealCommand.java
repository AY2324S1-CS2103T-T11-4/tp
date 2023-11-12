package seedu.flashlingo.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.flashlingo.commons.core.index.Index;
import seedu.flashlingo.commons.util.ToStringBuilder;
import seedu.flashlingo.logic.Messages;
import seedu.flashlingo.logic.commands.exceptions.CommandException;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.flashcard.FlashCard;
import seedu.flashlingo.model.flashcard.words.TranslatedWord;

/**
 * Reveals the other side of the flash card.
 */
public class RevealCommand extends Command {
    public static final String COMMAND_WORD = "reveal";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Reveals the flashcard identified by the index number "
        + "used\nParameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Flashcard has been revealed!";
    private final Index targetIndex;
    /**
     * Creates an RevealCommand.
     */
    public RevealCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<FlashCard> lastShownList = model.getFilteredFlashCardList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
        }

        FlashCard toBeRevealed = lastShownList.get(targetIndex.getZeroBased());
        TranslatedWord translatedWord = model.reveal(toBeRevealed);

        if (!toBeRevealed.getIsRevealed()) { // If already revealed -> Hide
            return new CommandResult("Translation has been hidden");
        }
        return new CommandResult(MESSAGE_SUCCESS + translatedWord.getWord());

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
          .add("targetIndex", targetIndex)
          .toString();
    }
}
