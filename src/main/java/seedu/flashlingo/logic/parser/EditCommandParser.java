package seedu.flashlingo.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.flashlingo.logic.Messages.MESSAGE_EMPTY_VALUE;
import static seedu.flashlingo.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashlingo.logic.parser.CliSyntax.PREFIX_ORIGINAL_WORD;
import static seedu.flashlingo.logic.parser.CliSyntax.PREFIX_ORIGINAL_WORD_LANGUAGE;
import static seedu.flashlingo.logic.parser.CliSyntax.PREFIX_TRANSLATED_WORD;
import static seedu.flashlingo.logic.parser.CliSyntax.PREFIX_TRANSLATED_WORD_LANGUAGE;

import java.util.Arrays;
import java.util.Optional;

import seedu.flashlingo.commons.core.index.Index;
import seedu.flashlingo.logic.commands.EditCommand;
import seedu.flashlingo.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {
    private final Prefix[] prefixes = new Prefix[] {
        PREFIX_ORIGINAL_WORD, PREFIX_ORIGINAL_WORD_LANGUAGE, PREFIX_TRANSLATED_WORD, PREFIX_TRANSLATED_WORD_LANGUAGE};

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, prefixes);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ORIGINAL_WORD, PREFIX_ORIGINAL_WORD_LANGUAGE,
                PREFIX_TRANSLATED_WORD, PREFIX_TRANSLATED_WORD_LANGUAGE);

        String[] changes = getChanges(argMultimap);
        if (Arrays.equals(changes, new String[4])) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }

        return new EditCommand(index, changes);
    }
    private String[] getChanges(ArgumentMultimap argMultimap) throws ParseException {
        String[] changes = new String[4];
        for (int i = 0; i < prefixes.length; i++) {
            Optional<String> temp = argMultimap.getValue(prefixes[i]);
            changes[i] = temp.orElse(null);
        }
        if (isInvalidWord(changes[0]) || isInvalidWord(changes[2])) {
            throw new ParseException(MESSAGE_EMPTY_VALUE);
        }
        return changes;
    }
    private boolean isInvalidWord(String change) {
        return change != null && change.isEmpty();
    }
}
