//@@author
package seedu.flashlingo.model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.flashlingo.commons.core.GuiSettings;
import seedu.flashlingo.logic.commands.exceptions.CommandException;
import seedu.flashlingo.model.flashcard.FlashCard;
import seedu.flashlingo.model.flashcard.words.TranslatedWord;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<FlashCard> PREDICATE_SHOW_ALL_FLASHCARDS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    //@@author A1WAYSD
    /**
     * Returns the user prefs' theme.
     */
    String getTheme();

    /**
     * Sets the user prefs' theme.
     */
    void setTheme(String theme);

    /**
     * Switches the user prefs' theme between light and dark.
     */
    void switchTheme();

    //@@author WangCheng0116
    /**
     * Returns the user prefs' Flashlingo file path.
     */
    Path getFlashlingoFilePath();

    /**
     * Sets the user prefs' Flashlingo file path.
     */
    void setFlashlingoFilePath(Path flashlingoFilePath);

    /**
     * Replaces Flashlingo data with the data in {@code flashlingo}.
     */
    void setFlashlingo(ReadOnlyFlashlingo flashlingo);

    /**
     * Returns the Flashlingo
     */
    ReadOnlyFlashlingo getFlashlingo();

    /**
     * Returns true if a flashcard with the same identity as {@code flashcard} exists in the Flashlingo.
     */
    boolean hasFlashCard(FlashCard flashCard);

    /**
     * Deletes the given flashcard.
     * The flashcard must exist in the Flashlingo.
     */
    void deleteFlashCard(FlashCard target);

    /**
     * Adds the given FlashCard.
     * {@code flashCard} must not already exist in the Flashlingo.
     */
    void addFlashCard(FlashCard flashCard);

    //@@author A1WAYSD
    void addFlashCards(ArrayList<FlashCard> flashCards);

    //@@author WangCheng0116
    /**
     * Replaces the given flashcard {@code target} with {@code editedFlashCard}.
     * {@code target} must exist in the Flashlingo .
     * The flashcard identity of {@code editedFlashCard} must not be the same as another existing
     * FlashCard in the Flashlingo.
     */
    void setFlashCard(FlashCard target, FlashCard editedFlashCard);

    /**
     * Returns an unmodifiable view of the filtered flashcard list
     */
    ObservableList<FlashCard> getFilteredFlashCardList();

    /**
     * Updates the filter of the filtered flashcard list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredFlashCardList(Predicate<FlashCard> predicate);

    /**
     * Evaluates and returns the number of FlashCards
     * @return Number of FlashCards
     */
    int getNumberOfFlashCards();

    /**
     * Evaluated the number of FlashCards remebered in this session
     * @return Number of FlashCardsRemembered
     */
    int getNumberOfRememberedWords();
    FlashCard nextReviewWord() throws CommandException;
    void startSession() throws CommandException;
    void endSession();
    boolean hasNextRound();
    TranslatedWord reveal(FlashCard toBeRevealed);
}
