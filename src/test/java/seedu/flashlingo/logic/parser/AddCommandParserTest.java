package seedu.flashlingo.logic.parser;

import static seedu.flashlingo.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashlingo.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.flashlingo.logic.commands.CommandTestUtil.TRANSLATION_DESC_BOB;
import static seedu.flashlingo.logic.commands.CommandTestUtil.VALID_ORIGINAL_WORD_BOB;
import static seedu.flashlingo.logic.commands.CommandTestUtil.VALID_TRANSLATION_BOB;
import static seedu.flashlingo.logic.commands.CommandTestUtil.WORD_DESC_AMY;
import static seedu.flashlingo.logic.commands.CommandTestUtil.WORD_DESC_BOB;
import static seedu.flashlingo.logic.parser.CliSyntax.PREFIX_ORIGINAL_WORD;
import static seedu.flashlingo.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.flashlingo.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.flashlingo.testutil.TypicalFlashCards.BOB;

import org.junit.jupiter.api.Test;

import seedu.flashlingo.logic.Messages;
import seedu.flashlingo.logic.commands.AddCommand;
import seedu.flashlingo.model.flashcard.FlashCard;
import seedu.flashlingo.testutil.FlashCardBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        FlashCard expectedFlashCard = new FlashCardBuilder(BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + WORD_DESC_BOB + TRANSLATION_DESC_BOB,
                new AddCommand(expectedFlashCard));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedPersonString = WORD_DESC_BOB + TRANSLATION_DESC_BOB;

        // multiple Word
        assertParseFailure(parser, WORD_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ORIGINAL_WORD));

    }
    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing Word prefix
        assertParseFailure(parser, VALID_ORIGINAL_WORD_BOB + VALID_TRANSLATION_BOB,
                expectedMessage);

        // missing Translation prefix
        assertParseFailure(parser, WORD_DESC_BOB + VALID_TRANSLATION_BOB,
                expectedMessage);
    }
}
