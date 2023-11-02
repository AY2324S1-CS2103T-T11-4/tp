package flashlingo.logic.parser;

import static flashlingo.testutil.Assert.assertThrows;
import static flashlingo.testutil.TestUtil.SANDBOX_FOLDER;
import static flashlingo.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashlingo.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashlingo.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import org.junit.jupiter.api.Test;

import flashlingo.testutil.FlashCardBuilder;
import flashlingo.testutil.FlashCardUtil;
import seedu.flashlingo.logic.commands.AddCommand;
import seedu.flashlingo.logic.commands.DeleteCommand;
import seedu.flashlingo.logic.commands.ExitCommand;
import seedu.flashlingo.logic.commands.HelpCommand;
import seedu.flashlingo.logic.commands.ListCommand;
import seedu.flashlingo.logic.commands.LoadCommand;
import seedu.flashlingo.logic.parser.FlashlingoParser;
import seedu.flashlingo.logic.parser.exceptions.ParseException;
import seedu.flashlingo.model.flashcard.FlashCard;

public class FlashlingoParserTest {

    private final FlashlingoParser parser = new FlashlingoParser();

    @Test
    public void parseCommand_add() throws Exception {
        FlashCard flashCard = new FlashCardBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(FlashCardUtil.getAddCommand(flashCard));
        assertEquals(new AddCommand(flashCard), command);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_FLASHCARD.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_FLASHCARD), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_load() throws Exception {
        assertTrue(parser.parseCommand(LoadCommand.COMMAND_WORD + " " + SANDBOX_FOLDER) instanceof LoadCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
                -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
