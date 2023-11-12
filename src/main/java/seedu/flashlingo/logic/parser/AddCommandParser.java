package seedu.flashlingo.logic.parser;

import static seedu.flashlingo.logic.Messages.MESSAGE_EMPTY_VALUE;
import static seedu.flashlingo.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashlingo.logic.parser.CliSyntax.PREFIX_ORIGINAL_WORD;
import static seedu.flashlingo.logic.parser.CliSyntax.PREFIX_ORIGINAL_WORD_LANGUAGE;
import static seedu.flashlingo.logic.parser.CliSyntax.PREFIX_TRANSLATED_WORD;
import static seedu.flashlingo.logic.parser.CliSyntax.PREFIX_TRANSLATED_WORD_LANGUAGE;

import java.util.stream.Stream;

import seedu.flashlingo.logic.commands.AddCommand;
import seedu.flashlingo.logic.parser.exceptions.ParseException;
import seedu.flashlingo.model.flashcard.words.OriginalWord;
import seedu.flashlingo.model.flashcard.words.TranslatedWord;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_ORIGINAL_WORD, PREFIX_ORIGINAL_WORD_LANGUAGE,
                    PREFIX_TRANSLATED_WORD, PREFIX_TRANSLATED_WORD_LANGUAGE);

        if (!arePrefixesPresent(argMultimap, PREFIX_ORIGINAL_WORD, PREFIX_TRANSLATED_WORD)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ORIGINAL_WORD, PREFIX_ORIGINAL_WORD_LANGUAGE,
                PREFIX_TRANSLATED_WORD, PREFIX_TRANSLATED_WORD_LANGUAGE);
        return new AddCommand(getOriginalWord(argMultimap), getTranslationWord(argMultimap));
    }
    private OriginalWord getOriginalWord(ArgumentMultimap argMultimap) throws ParseException {
        String originalWord = argMultimap.getValue(PREFIX_ORIGINAL_WORD).get().trim();
        if (originalWord.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_EMPTY_VALUE, AddCommand.MESSAGE_USAGE));
        }
        if (arePrefixesPresent(argMultimap, PREFIX_ORIGINAL_WORD_LANGUAGE)) {
            String language = argMultimap.getValue(PREFIX_ORIGINAL_WORD_LANGUAGE).get();
            return new OriginalWord(originalWord, language);
        } else {
            return new OriginalWord(originalWord);
        }
    }

    private TranslatedWord getTranslationWord(ArgumentMultimap argMultimap) throws ParseException {
        String translationWord = argMultimap.getValue(PREFIX_TRANSLATED_WORD).get().trim();
        if (translationWord.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_EMPTY_VALUE, AddCommand.MESSAGE_USAGE));
        }
        if (arePrefixesPresent(argMultimap, PREFIX_TRANSLATED_WORD_LANGUAGE)) {
            String language = argMultimap.getValue(PREFIX_TRANSLATED_WORD_LANGUAGE).get();
            return new TranslatedWord(translationWord, language);
        } else {
            return new TranslatedWord(translationWord);
        }
    }
    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
