package seedu.flashlingo.logic.parser;

import static seedu.flashlingo.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.flashlingo.commons.core.index.Index;
import seedu.flashlingo.logic.commands.RevealCommand;
import seedu.flashlingo.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new RevealCommand object
 */
public class RevealCommandParser implements Parser<RevealCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RevealCommand
     * and returns a RevealCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RevealCommand parse(String args) throws ParseException {
        try {
            Index index;
            if (args.trim().isBlank()) {
                index = ParserUtil.parseIndex("1");
            } else {
                index = ParserUtil.parseIndex(args);
            }
            return new RevealCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
              String.format(MESSAGE_INVALID_COMMAND_FORMAT, RevealCommand.MESSAGE_USAGE), pe);
        }
    }

}
