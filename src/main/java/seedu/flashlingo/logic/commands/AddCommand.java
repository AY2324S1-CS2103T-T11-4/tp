package seedu.flashlingo.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.flashlingo.logic.parser.CliSyntax.PREFIX_ORIGINAL_WORD;
import static seedu.flashlingo.logic.parser.CliSyntax.PREFIX_ORIGINAL_WORD_LANGUAGE;
import static seedu.flashlingo.logic.parser.CliSyntax.PREFIX_TRANSLATED_WORD;
import static seedu.flashlingo.logic.parser.CliSyntax.PREFIX_TRANSLATED_WORD_LANGUAGE;

import java.util.Date;

import seedu.flashlingo.commons.util.ToStringBuilder;
import seedu.flashlingo.logic.commands.exceptions.CommandException;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.flashcard.FlashCard;
import seedu.flashlingo.model.flashcard.ProficiencyLevel;
import seedu.flashlingo.model.flashcard.words.OriginalWord;
import seedu.flashlingo.model.flashcard.words.TranslatedWord;

/**
 * Adds a flash card to Flashlingo.
 */
public class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";

    // For help function
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a flashcard to Flashlingo.\n"
            + "Parameters: "
            + PREFIX_ORIGINAL_WORD + "ORIGINAL WORD "
            + PREFIX_ORIGINAL_WORD_LANGUAGE + "ORIGINAL WORD LANGUAGE "
            + PREFIX_TRANSLATED_WORD + "TRANSLATION "
            + PREFIX_TRANSLATED_WORD_LANGUAGE + "TRANSLATED WORD LANGUAGE \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ORIGINAL_WORD + "hello "
            + PREFIX_ORIGINAL_WORD_LANGUAGE + "English "
            + PREFIX_TRANSLATED_WORD + "你好 "
            + PREFIX_TRANSLATED_WORD_LANGUAGE + "Chinese";


    public static final String MESSAGE_SUCCESS = "New flashcard added: %s - %s";
    public static final String MESSAGE_DUPLICATE_CARD = "This flashcard already exists";
    private FlashCard toAdd;
    private OriginalWord original;
    private TranslatedWord translated;

    /**
     * Creates an AddCommand to add the specified {@code FlashCard}
     */
    public AddCommand(OriginalWord original, TranslatedWord translated) {
        requireNonNull(original);
        requireNonNull(translated);
        this.original = original;
        this.translated = translated;
    }

    /**
     * Creates an AddCommand to add the specified {@code FlashCard}
     */
    public AddCommand(FlashCard flashCard) {
        requireNonNull(flashCard);
        this.original = flashCard.getOriginalWord();
        this.translated = flashCard.getTranslatedWord();
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            this.toAdd = new FlashCard(original, translated, new Date(), new ProficiencyLevel(1));
        } catch (IllegalArgumentException iae) {
            throw new CommandException(iae.getMessage());
        }
        if (model.hasFlashCard(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CARD);
        }
        model.addFlashCard(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, this.original.getWord(), this.translated.getWord()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddCommand)) {
            return false;
        }

        AddCommand otherAddCommand = (AddCommand) other;
        return original.equals(otherAddCommand.original) && translated.equals(otherAddCommand.translated);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
