package seedu.flashlingo.model;

import static java.util.Objects.requireNonNull;
import static seedu.flashlingo.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.flashlingo.commons.core.GuiSettings;
import seedu.flashlingo.commons.core.LogsCenter;
import seedu.flashlingo.logic.commands.exceptions.CommandException;
import seedu.flashlingo.model.flashcard.FlashCard;
import seedu.flashlingo.model.flashcard.NextReviewWordPredicate;
import seedu.flashlingo.model.flashcard.WordOverduePredicate;
import seedu.flashlingo.model.flashcard.words.TranslatedWord;
import seedu.flashlingo.session.SessionManager;

/**
 * Represents the in-memory model of the flashlingo data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);
    private final Flashlingo flashlingo;
    private final UserPrefs userPrefs;
    private final FilteredList<FlashCard> filteredFlashCards;

    /**
     * Initializes a ModelManager with the given flashlingo and userPrefs.
     */
    public ModelManager(ReadOnlyFlashlingo flashlingo, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(flashlingo, userPrefs);

        logger.fine("Initializing with Flashlingo: " + flashlingo + " and user prefs " + userPrefs);

        this.flashlingo = new Flashlingo(flashlingo);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredFlashCards = new FilteredList<>(this.flashlingo.getFlashCardList());
    }

    public ModelManager() {
        this(new Flashlingo(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    //@@author
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getFlashlingoFilePath() {
        return userPrefs.getFlashlingoFilePath();
    }

    @Override
    public void setFlashlingoFilePath(Path flashlingoFilePath) {
        requireNonNull(flashlingoFilePath);
        userPrefs.setFlashlingoFilePath(flashlingoFilePath);
    }

    @Override
    public String getTheme() {
        return userPrefs.getTheme();
    }

    @Override
    public void setTheme(String theme) {
        requireNonNull(theme);
        userPrefs.setTheme(theme);
    }

    @Override
    public void switchTheme() {
        if (getTheme().equals("Default")) {
            setTheme("Dark");
        } else {
            setTheme("Default");
        }
    }

    //=========== Flashlingo ================================================================================

    @Override
    public void setFlashlingo(ReadOnlyFlashlingo flashlingo) {
        this.flashlingo.resetData(flashlingo);
    }

    @Override
    public ReadOnlyFlashlingo getFlashlingo() {
        return flashlingo;
    }

    @Override
    public boolean hasFlashCard(FlashCard flashCard) {
        requireNonNull(flashCard);
        return flashlingo.hasFlashCard(flashCard);
    }

    @Override
    public void deleteFlashCard(FlashCard target) {
        flashlingo.removeFlashCard(target);
    }

    @Override
    public void addFlashCard(FlashCard flashCard) {
        flashlingo.addFlashCard(flashCard);
        updateFilteredFlashCardList(PREDICATE_SHOW_ALL_FLASHCARDS);
    }

    @Override
    public void addFlashCards(ArrayList<FlashCard> flashCards) {
        for (FlashCard flashCard : flashCards) {
            if (flashlingo.hasFlashCard(flashCard)) {
                continue;
            }
            flashlingo.addFlashCard(flashCard);
        }
        updateFilteredFlashCardList(PREDICATE_SHOW_ALL_FLASHCARDS);
    }

    @Override
    public void setFlashCard(FlashCard target, FlashCard editedFlashCard) {
        requireAllNonNull(target, editedFlashCard);
        flashlingo.setFlashCard(target, editedFlashCard);
    }
    @Override
    public int getNumberOfFlashCards() {
        return this.filteredFlashCards.size();
    }

    @Override
    public FlashCard nextReviewWord() throws CommandException {
        updateFilteredFlashCardList(new WordOverduePredicate());
        if (filteredFlashCards.size() == 0) {
            SessionManager.getInstance().setSession(false);
            updateFilteredFlashCardList(unused -> true);
            throw new CommandException("There's no FlashCards to review. Well done!");
        }
        FlashCard toBeReviewed = getFilteredFlashCardList().get(0);
        Predicate<FlashCard> t = new NextReviewWordPredicate(toBeReviewed);
        updateFilteredFlashCardList(t);
        return toBeReviewed;
    }

    //=========== Filtered Flashcard List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code FlashCard} backed by the internal list of
     * {@code versionedFlashlingo}
     */
    @Override
    public ObservableList<FlashCard> getFilteredFlashCardList() {
        return filteredFlashCards;
    }

    @Override
    public void updateFilteredFlashCardList(Predicate<FlashCard> predicate) {
        requireNonNull(predicate);
        filteredFlashCards.setPredicate(predicate);
    }

    @Override
    public int getNumberOfRememberedWords() {
        int numOfFlashCardsRemembered = 0;
        for (FlashCard flashCard : filteredFlashCards) {
            if (flashCard.isRecalled()) {
                numOfFlashCardsRemembered += 1;
            }
        }
        return numOfFlashCardsRemembered;
    }
    @Override
    public void startSession() throws CommandException {
        SessionManager.getInstance().setSession(true);
        updateFilteredFlashCardList(new WordOverduePredicate());
        if (getFilteredFlashCardList().size() == 0) {
            SessionManager.getInstance().setSession(false);
            updateFilteredFlashCardList(PREDICATE_SHOW_ALL_FLASHCARDS);
            throw new CommandException("You have no more words to review!");
        }
        updateFilteredFlashCardList(new NextReviewWordPredicate(getFilteredFlashCardList().get(0)));
    }
    @Override
    public void endSession() {
        SessionManager.getInstance().setSession(false);
        updateFilteredFlashCardList(PREDICATE_SHOW_ALL_FLASHCARDS);
    }

    @Override
    public boolean hasNextRound() {
        updateFilteredFlashCardList(new WordOverduePredicate());
        return getFilteredFlashCardList().size() != 0;
    }

    @Override
    public TranslatedWord reveal(FlashCard toBeRevealed) {
        if (toBeRevealed.getIsRevealed()) {
            toBeRevealed.setIsRevealed(false);
        } else {
            toBeRevealed.setIsRevealed(true);
        }
        return toBeRevealed.getTranslatedWord();
    }

    //@@author
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return flashlingo.equals(otherModelManager.flashlingo)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredFlashCards.equals(otherModelManager.filteredFlashCards);
    }
}
