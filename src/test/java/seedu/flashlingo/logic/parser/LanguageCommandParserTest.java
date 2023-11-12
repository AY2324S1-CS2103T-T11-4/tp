package seedu.flashlingo.logic.parser;

import static seedu.flashlingo.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.flashlingo.logic.commands.LanguageCommand;
import seedu.flashlingo.model.flashcard.WordLanguagePredicate;

public class LanguageCommandParserTest {

    private LanguageCommandParser parser = new LanguageCommandParser();

    @Test
    public void parse_emptyArg_returnLanguageCommand() {
        LanguageCommand expectedLanguageCommand =
                new LanguageCommand(new WordLanguagePredicate(""));

        // no parameters
        assertParseSuccess(parser, "", expectedLanguageCommand);

        // only spaces
        assertParseSuccess(parser, "     ", expectedLanguageCommand);
    }

    @Test
    public void parse_validArgs_returnsLanguageCommand() {
        // no leading and trailing whitespaces
        LanguageCommand expectedLanguageCommand =
                new LanguageCommand(new WordLanguagePredicate("English"));
        assertParseSuccess(parser, "English", expectedLanguageCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n English \n", expectedLanguageCommand);
    }
}
