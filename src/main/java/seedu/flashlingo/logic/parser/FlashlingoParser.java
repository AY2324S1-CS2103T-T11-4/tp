package seedu.flashlingo.logic.parser;

import static seedu.flashlingo.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashlingo.logic.Messages.MESSAGE_IN_REVIEW_SESSION;
import static seedu.flashlingo.logic.Messages.MESSAGE_NOT_IN_REVIEW_SESSION;
import static seedu.flashlingo.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.flashlingo.commons.core.LogsCenter;
import seedu.flashlingo.logic.commands.AddCommand;
import seedu.flashlingo.logic.commands.Command;
import seedu.flashlingo.logic.commands.DeleteCommand;
import seedu.flashlingo.logic.commands.EditCommand;
import seedu.flashlingo.logic.commands.EndCommand;
import seedu.flashlingo.logic.commands.ExitCommand;
import seedu.flashlingo.logic.commands.FindCommand;
import seedu.flashlingo.logic.commands.HelpCommand;
import seedu.flashlingo.logic.commands.LanguageCommand;
import seedu.flashlingo.logic.commands.ListCommand;
import seedu.flashlingo.logic.commands.NoCommand;
import seedu.flashlingo.logic.commands.ReviewCommand;
import seedu.flashlingo.logic.commands.StartCommand;
import seedu.flashlingo.logic.commands.StatsCommand;
import seedu.flashlingo.logic.commands.YesCommand;
import seedu.flashlingo.logic.parser.exceptions.ParseException;


/**
 * Parses user input.
 */
public class FlashlingoParser {
    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Logger logger = LogsCenter.getLogger(FlashlingoParser.class);
    private static boolean isReviewSession = false;
    public static void setReviewSession(boolean edited) {
        isReviewSession = edited;
    }
    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        // Note to developers: Change the log level in config.json to enable lower level (i.e., FINE, FINER and lower)
        // log messages such as the one below.
        // Lower level log messages are used sparingly to minimize noise in the code.
        logger.fine("Command word: " + commandWord + "; Arguments: " + arguments);
        if (isReviewSession) {
            switch (commandWord) {
            case StartCommand.COMMAND_WORD:
                throw new ParseException(MESSAGE_IN_REVIEW_SESSION);
            case EndCommand.COMMAND_WORD:
                isReviewSession = false;
                return new EndCommand();
            case YesCommand.COMMAND_WORD:
                return new YesCommand();
            case NoCommand.COMMAND_WORD:
                return new NoCommand();
            default:
                logger.finer("This user input caused a ParseException: " + userInput);
                throw new ParseException(MESSAGE_IN_REVIEW_SESSION);
            }
        }
        switch (commandWord) {
        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);
        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);
        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);
        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);
        case NoCommand.COMMAND_WORD:
            throw new ParseException(MESSAGE_NOT_IN_REVIEW_SESSION);
        case LanguageCommand.COMMAND_WORD:
            return new LanguageCommandParser().parse(arguments);
        case YesCommand.COMMAND_WORD:
            throw new ParseException(MESSAGE_NOT_IN_REVIEW_SESSION);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case ReviewCommand.COMMAND_WORD:
            return new ReviewCommand();
        case StatsCommand.COMMAND_WORD:
            return new StatsCommand();
        case StartCommand.COMMAND_WORD:
            isReviewSession = true;
            return new StartCommand();
        case EndCommand.COMMAND_WORD:
            throw new ParseException(MESSAGE_NOT_IN_REVIEW_SESSION);
        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
