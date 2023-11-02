package flashlingo.logic.parser;

import static flashlingo.logic.parser.CommandParserTestUtil.assertParseFailure;
import static flashlingo.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.flashlingo.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import org.junit.jupiter.api.Test;

import seedu.flashlingo.logic.commands.LoadCommand;
import seedu.flashlingo.logic.parser.LoadCommandParser;

public class LoadCommandParserTest {
    private LoadCommandParser parser = new LoadCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, LoadCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsLoadCommand() {
        // no leading and trailing whitespaces
        LoadCommand expectedLoadCommand =
                new LoadCommand("test.xlsx");
        assertParseSuccess(parser, "test.xlsx", expectedLoadCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n test \n \t .xlsx  \t", expectedLoadCommand);
    }
}
