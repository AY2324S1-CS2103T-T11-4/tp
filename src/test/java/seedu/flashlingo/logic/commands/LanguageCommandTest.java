package seedu.flashlingo.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.flashlingo.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.flashlingo.testutil.TypicalFlashCards.FIONA;
import static seedu.flashlingo.testutil.TypicalFlashCards.GEORGE;
import static seedu.flashlingo.testutil.TypicalFlashCards.getTypicalFlashlingo;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.flashlingo.logic.Messages;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.ModelManager;
import seedu.flashlingo.model.UserPrefs;
import seedu.flashlingo.model.flashcard.WordLanguagePredicate;

public class LanguageCommandTest {
    private Model model;
    private Model expectedModel;
    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFlashlingo(), new UserPrefs());
        expectedModel = new ModelManager(model.getFlashlingo(), new UserPrefs());
    }

    @Test
    public void execute_zeroKeywords_oneFlashCardFound() {
        String expectedMessage = String.format(Messages.MESSAGE_FLASHCARDS_LISTED_OVERVIEW, 1);
        WordLanguagePredicate predicate = new WordLanguagePredicate("");
        LanguageCommand command = new LanguageCommand(predicate);

        expectedModel.updateFilteredFlashCardList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(GEORGE), model.getFilteredFlashCardList());
    }

    @Test
    public void execute_specificLanguage_multipleFlashCardsFound() {
        String expectedMessage = String.format(Messages.MESSAGE_FLASHCARDS_LISTED_OVERVIEW, 7);
        WordLanguagePredicate predicate = new WordLanguagePredicate("English");
        LanguageCommand command = new LanguageCommand(predicate);

        expectedModel.updateFilteredFlashCardList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(getTypicalFlashlingo(), model.getFlashlingo());
    }

    @Test
    public void execute_specificLanguageWithDifferentCase_multipleFlashCardsFound() {
        String expectedMessage = String.format(Messages.MESSAGE_FLASHCARDS_LISTED_OVERVIEW, 7);
        WordLanguagePredicate predicate = new WordLanguagePredicate("ENGLISH");
        LanguageCommand command = new LanguageCommand(predicate);

        expectedModel.updateFilteredFlashCardList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(getTypicalFlashlingo(), model.getFlashlingo());
    }

    @Test
    public void execute_notAccurateLanguage_oneFlashCardFound() {
        String expectedMessage = String.format(Messages.MESSAGE_FLASHCARDS_LISTED_OVERVIEW, 1);
        WordLanguagePredicate predicate = new WordLanguagePredicate("Chinese");
        LanguageCommand command = new LanguageCommand(predicate);

        expectedModel.updateFilteredFlashCardList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(FIONA), model.getFilteredFlashCardList());
    }

    @Test
    public void execute_notExistLanguage_noFlashCardFound() {
        String expectedMessage = String.format(Messages.MESSAGE_FLASHCARDS_LISTED_OVERVIEW, 0);
        WordLanguagePredicate predicate = new WordLanguagePredicate("French");
        LanguageCommand command = new LanguageCommand(predicate);

        expectedModel.updateFilteredFlashCardList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredFlashCardList());
    }

}
