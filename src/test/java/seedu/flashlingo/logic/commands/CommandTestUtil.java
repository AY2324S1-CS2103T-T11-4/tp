package seedu.flashlingo.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashlingo.logic.parser.CliSyntax.PREFIX_ORIGINAL_WORD;
import static seedu.flashlingo.logic.parser.CliSyntax.PREFIX_ORIGINAL_WORD_LANGUAGE;
import static seedu.flashlingo.logic.parser.CliSyntax.PREFIX_TRANSLATED_WORD;
import static seedu.flashlingo.logic.parser.CliSyntax.PREFIX_TRANSLATED_WORD_LANGUAGE;
import static seedu.flashlingo.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.flashlingo.commons.core.index.Index;
import seedu.flashlingo.logic.commands.exceptions.CommandException;
import seedu.flashlingo.model.Flashlingo;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.flashcard.FlashCard;
import seedu.flashlingo.model.flashcard.WordContainsKeywordsPredicate;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_ORIGINAL_WORD_AMY = "Amy";
    public static final String VALID_ORIGINAL_WORD_BOB = "Bob";
    public static final String VALID_ORIGINAL_WORD_LANGUAGE = "English";
    public static final String VALID_TRANSLATION_AMY = "艾米";
    public static final String VALID_TRANSLATION_BOB = "鲍勃";
    public static final String VALID_TRANSLATION_LANGUAGE = "Mandarin";

    public static final String WORD_DESC_AMY = " " + PREFIX_ORIGINAL_WORD + VALID_ORIGINAL_WORD_AMY;
    public static final String WORD_DESC_BOB = " " + PREFIX_ORIGINAL_WORD + VALID_ORIGINAL_WORD_BOB;
    public static final String TRANSLATION_DESC_AMY = " " + PREFIX_TRANSLATED_WORD + VALID_TRANSLATION_AMY;
    public static final String TRANSLATION_DESC_BOB = " " + PREFIX_TRANSLATED_WORD + VALID_TRANSLATION_BOB;
    public static final String WORD_LANGUAGE_DESC = " " + PREFIX_ORIGINAL_WORD_LANGUAGE
            + VALID_ORIGINAL_WORD_LANGUAGE;
    public static final String TRANSLATION_LANGUAGE_DESC = " " + PREFIX_TRANSLATED_WORD_LANGUAGE
            + VALID_TRANSLATION_LANGUAGE;

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";


    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the flashlingo, filtered flash card list and selected flash card in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Flashlingo expectedFlashlingo = new Flashlingo(actualModel.getFlashlingo());
        List<FlashCard> expectedFilteredList = new ArrayList<>(actualModel.getFilteredFlashCardList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedFlashlingo, actualModel.getFlashlingo());
        assertEquals(expectedFilteredList, actualModel.getFilteredFlashCardList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the flash card at the given {@code targetIndex} in the
     * {@code model}'s flashlingo.
     */
    public static void showFlashCardAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredFlashCardList().size());

        FlashCard flashCard = model.getFilteredFlashCardList().get(targetIndex.getZeroBased());
        final String[] splitName = flashCard.getOriginalWord().getWord().split("\\s+");
        model.updateFilteredFlashCardList(new WordContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredFlashCardList().size());
    }

}
