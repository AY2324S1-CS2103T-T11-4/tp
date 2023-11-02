package flashlingo.logic.parser;

import static flashlingo.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static flashlingo.logic.commands.CommandTestUtil.VALID_ORIGINAL_WORD_BOB;
import static flashlingo.logic.commands.CommandTestUtil.VALID_TRANSLATION_BOB;
import static flashlingo.logic.commands.CommandTestUtil.WORD_DESC_AMY;
import static flashlingo.logic.commands.CommandTestUtil.WORD_DESC_BOB;
import static flashlingo.logic.parser.CommandParserTestUtil.assertParseFailure;
import static flashlingo.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static flashlingo.testutil.TypicalFlashCards.BOB;
import static flashlingo.testutil.TypicalFlashCards.TRANSLATION_DESC_BOB;
import static seedu.flashlingo.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashlingo.logic.parser.CliSyntax.PREFIX_ORIGINAL_WORD;

import org.junit.jupiter.api.Test;

import flashlingo.testutil.FlashCardBuilder;
import seedu.flashlingo.logic.Messages;
import seedu.flashlingo.logic.commands.AddCommand;
import seedu.flashlingo.logic.commands.Command;
import seedu.flashlingo.logic.parser.AddCommandParser;
import seedu.flashlingo.logic.parser.Parser;
import seedu.flashlingo.model.flashcard.FlashCard;

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
        assertParseFailure((Parser<? extends Command>) parser, WORD_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ORIGINAL_WORD));

    }
    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing Word prefix
        assertParseFailure((Parser<? extends Command>) parser, VALID_ORIGINAL_WORD_BOB + VALID_TRANSLATION_BOB,
                expectedMessage);

        // missing Translation prefix
        assertParseFailure((Parser<? extends Command>) parser, WORD_DESC_BOB + VALID_TRANSLATION_BOB,
                expectedMessage);
    }
}
