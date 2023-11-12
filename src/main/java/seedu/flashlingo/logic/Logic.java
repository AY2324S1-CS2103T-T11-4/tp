package seedu.flashlingo.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.flashlingo.commons.core.GuiSettings;
import seedu.flashlingo.logic.commands.CommandResult;
import seedu.flashlingo.logic.commands.exceptions.CommandException;
import seedu.flashlingo.logic.parser.exceptions.ParseException;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.ReadOnlyFlashlingo;
import seedu.flashlingo.model.flashcard.FlashCard;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the Flashlingo.
     *
     * @see Model#getFlashlingo()
     */
    ReadOnlyFlashlingo getFlashlingo();

    /** Returns an unmodifiable view of the filtered list of flashcards */
    ObservableList<FlashCard> getFilteredFlashCardList();

    /**
     * Returns the user prefs' Flashlingo file path.
     */
    Path getFlashlingoFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' theme.
     */
    String getTheme();
}
