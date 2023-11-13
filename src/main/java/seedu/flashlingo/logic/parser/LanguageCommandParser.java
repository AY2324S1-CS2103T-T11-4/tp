package seedu.flashlingo.logic.parser;

import seedu.flashlingo.logic.commands.LanguageCommand;
import seedu.flashlingo.logic.parser.exceptions.ParseException;
import seedu.flashlingo.model.flashcard.WordLanguagePredicate;

/**
 * Parses input arguments and creates a new LanguageCommand object
 */
public class LanguageCommandParser implements Parser<LanguageCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the LanguageCommand
     * and returns a LanguageCommand object for execution.
     */
    public LanguageCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        return new LanguageCommand(new WordLanguagePredicate(trimmedArgs));
    }

}
