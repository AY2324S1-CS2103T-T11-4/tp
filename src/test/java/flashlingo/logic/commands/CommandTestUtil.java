package flashlingo.logic.commands;

import static flashlingo.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashlingo.logic.parser.CliSyntax.PREFIX_ORIGINAL_WORD;
import static seedu.flashlingo.logic.parser.CliSyntax.PREFIX_TRANSLATED_WORD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.flashlingo.commons.core.index.Index;
import seedu.flashlingo.logic.commands.Command;
import seedu.flashlingo.logic.commands.CommandResult;
import seedu.flashlingo.logic.commands.exceptions.CommandException;
import seedu.flashlingo.model.Flashlingo;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.flashcard.FlashCard;
import seedu.flashlingo.model.flashcard.WordContainsKeywordsPredicate;




/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_ORIGINAL_WORD_HELLO = "你好";
    public static final String VALID_ORIGINAL_WORD_WELCOME = "欢迎";
    public static final String VALID_ORIGINAL_WORD_THANKS = "ありがとう";
    public static final String VALID_ORIGINAL_WORD_PLEASE = "Bitte";
    public static final String VALID_ORIGINAL_WORD_HONEST = "honnête";
    public static final String VALID_TRANSLATED_WORD_HELLO = "hello";
    public static final String VALID_TRANSLATED_WORD_WELCOME = "welcome";
    public static final String VALID_TRANSLATED_WORD_THANKS = "thanks";
    public static final String VALID_TRANSLATED_WORD_PLEASE = "please";
    public static final String VALID_TRANSLATED_WORD_HONEST = "honest";
    public static final String VALID_ORIGINAL_WORD_AMY = "Amy";
    public static final String VALID_ORIGINAL_WORD_BOB = "Bob";
    public static final String VALID_ORIGINAL_WORD_LANGUAGE = "English";
    public static final String VALID_TRANSLATION_AMY = "艾米";
    public static final String VALID_TRANSLATION_BOB = "鲍勃";
    public static final String VALID_TRANSLATION_LANGUAGE = "Mandarin";

    public static final String ORIGINAL_WORD_DESC_HELLO = " " + PREFIX_ORIGINAL_WORD + VALID_ORIGINAL_WORD_HELLO;
    public static final String ORIGINAL_WORD_DESC_WELCOME = " " + PREFIX_ORIGINAL_WORD + VALID_ORIGINAL_WORD_WELCOME;
    public static final String TRANSLATED_WORD_DESC_HELLO = " " + PREFIX_TRANSLATED_WORD + VALID_TRANSLATED_WORD_HELLO;
    public static final String TRANSLATED_WORD_DESC_WELCOME = " "
            + PREFIX_TRANSLATED_WORD + VALID_TRANSLATED_WORD_HELLO;
    public static final String WORD_DESC_AMY = " " + PREFIX_ORIGINAL_WORD + VALID_ORIGINAL_WORD_AMY;
    public static final String WORD_DESC_BOB = " " + PREFIX_ORIGINAL_WORD + VALID_ORIGINAL_WORD_BOB;
    public static final String TRANSLATION_DESC_AMY = " " + PREFIX_TRANSLATED_WORD + VALID_TRANSLATION_AMY;
    public static final String TRANSLATION_DESC_BOB = " " + PREFIX_TRANSLATED_WORD + VALID_TRANSLATION_BOB;

    public static final String INVALID_ORIGINAL_WORD_DESC = " " + PREFIX_ORIGINAL_WORD; // '&' not allowed in names
    public static final String INVALID_TRANSLATED_WORD_DESC = " " + PREFIX_TRANSLATED_WORD; // 'a' not allowed in phones

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

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
     * - the Flashlingo, filtered FlashCard list and selected pFlashcard in {@code actualModel} remain unchanged
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
     * Updates {@code model}'s filtered list to show only the flashcard at the given {@code targetIndex} in the
     * {@code model}'s Flashlingo
     */
    public static void showFlashCardAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredFlashCardList().size());

        FlashCard flashCard = model.getFilteredFlashCardList().get(targetIndex.getZeroBased());
        final String[] splitName = flashCard.getOriginalWord().getWord().split("\\s+");
        model.updateFilteredFlashCardList(new WordContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredFlashCardList().size());
    }

}
