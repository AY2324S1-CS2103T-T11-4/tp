package seedu.flashlingo.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.flashlingo.logic.Messages.MESSAGE_SAME_WORD;
import static seedu.flashlingo.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.flashlingo.commons.core.GuiSettings;
import seedu.flashlingo.logic.commands.exceptions.CommandException;
import seedu.flashlingo.model.Flashlingo;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.ReadOnlyFlashlingo;
import seedu.flashlingo.model.ReadOnlyUserPrefs;
import seedu.flashlingo.model.flashcard.FlashCard;
import seedu.flashlingo.model.flashcard.words.OriginalWord;
import seedu.flashlingo.model.flashcard.words.TranslatedWord;
import seedu.flashlingo.testutil.FlashCardBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullFlashCard_throwsNullPointerException() {
        // Check that the constructor throws a NullPointerException when a null FlashCard is provided.
        OriginalWord original = new OriginalWord("hello", "English");
        TranslatedWord translation = new TranslatedWord("你好", "Mandarin");
        assertThrows(NullPointerException.class, () -> new AddCommand(null, translation));
        assertThrows(NullPointerException.class, () -> new AddCommand(original, null));
        assertThrows(NullPointerException.class, () -> new AddCommand(null, null));
    }

    @Test
    public void execute_flashCardAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingFlashCardAdded modelStub = new ModelStubAcceptingFlashCardAdded();
        FlashCard validFlashCard = new FlashCardBuilder().build();

        CommandResult commandResult = new AddCommand(validFlashCard.getOriginalWord(),
                validFlashCard.getTranslatedWord())
                .execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validFlashCard.getOriginalWord().getWord(),
                        validFlashCard.getTranslatedWord().getWord()),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validFlashCard), modelStub.flashCardsAdded);
    }

    @Test
    public void execute_duplicateFlashCard_throwsCommandException() {
        FlashCard validFlashCard = new FlashCardBuilder().build();
        AddCommand addCommand = new AddCommand(validFlashCard.getOriginalWord(), validFlashCard.getTranslatedWord());
        ModelStub modelStub = new ModelStubWithFlashCard(validFlashCard);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_CARD, () -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_sameWordAndTranslation_throwsCommandException() {
        FlashCard validFlashCard = new FlashCardBuilder().build();
        TranslatedWord sameWord = new TranslatedWord(validFlashCard.getOriginalWord().getWord());
        AddCommand addCommand = new AddCommand(validFlashCard.getOriginalWord(), sameWord);
        ModelStub modelStub = new ModelStubWithFlashCard(validFlashCard);

        assertThrows(CommandException.class, MESSAGE_SAME_WORD, () -> addCommand.execute(modelStub));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String getTheme() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTheme(String theme) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void switchTheme() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getFlashlingoFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFlashlingoFilePath(Path flashlingoFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFlashlingo(ReadOnlyFlashlingo flashlingo) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyFlashlingo getFlashlingo() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasFlashCard(FlashCard flashCard) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteFlashCard(FlashCard target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addFlashCard(FlashCard flashCard) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addFlashCards(ArrayList<FlashCard> flashCards) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFlashCard(FlashCard target, FlashCard editedFlashCard) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<FlashCard> getFilteredFlashCardList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredFlashCardList(Predicate<FlashCard> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int getNumberOfFlashCards() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int getNumberOfRememberedWords() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public FlashCard nextReviewWord() throws CommandException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void startSession() throws CommandException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void endSession() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasNextRound() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public TranslatedWord reveal(FlashCard toBeRevealed) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single flash card.
     */
    private class ModelStubWithFlashCard extends ModelStub {
        private final FlashCard flashCard;

        ModelStubWithFlashCard(FlashCard flashCard) {
            requireNonNull(flashCard);
            this.flashCard = flashCard;
        }

        @Override
        public boolean hasFlashCard(FlashCard flashCard) {
            requireNonNull(flashCard);
            return this.flashCard.isSameFlashCard(flashCard);
        }
    }

    /**
     * A Model stub that always accept the flash card being added.
     */
    private class ModelStubAcceptingFlashCardAdded extends ModelStub {
        final ArrayList<FlashCard> flashCardsAdded = new ArrayList<>();

        @Override
        public boolean hasFlashCard(FlashCard flashCard) {
            requireNonNull(flashCard);
            return flashCardsAdded.stream().anyMatch(flashCard::isSameFlashCard);
        }

        @Override
        public void addFlashCard(FlashCard flashCard) {
            requireNonNull(flashCard);
            flashCardsAdded.add(flashCard);
        }

        @Override
        public ReadOnlyFlashlingo getFlashlingo() {
            return new Flashlingo();
        }
    }
}
