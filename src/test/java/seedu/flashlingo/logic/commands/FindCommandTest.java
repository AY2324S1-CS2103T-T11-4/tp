package seedu.flashlingo.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashlingo.logic.Messages.MESSAGE_FLASHCARDS_LISTED_OVERVIEW;
import static seedu.flashlingo.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.flashlingo.testutil.TypicalFlashCards.BENSON;
import static seedu.flashlingo.testutil.TypicalFlashCards.DANIEL;
import static seedu.flashlingo.testutil.TypicalFlashCards.ELLE;
import static seedu.flashlingo.testutil.TypicalFlashCards.KEYWORD_MATCHING_MEIER;
import static seedu.flashlingo.testutil.TypicalFlashCards.KEYWORD_MATCHING_MEIER_OR_MEYER;
import static seedu.flashlingo.testutil.TypicalFlashCards.SUBSTRING_ME;
import static seedu.flashlingo.testutil.TypicalFlashCards.getTypicalFlashlingo;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.ModelManager;
import seedu.flashlingo.model.UserPrefs;
import seedu.flashlingo.model.flashcard.WordContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalFlashlingo(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalFlashlingo(), new UserPrefs());

    @Test
    public void equals() {
        WordContainsKeywordsPredicate firstPredicate =
                new WordContainsKeywordsPredicate(Collections.singletonList("first"));
        WordContainsKeywordsPredicate secondPredicate =
                new WordContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different flash card -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_oneKeywords_multipleFlashCardsFound() {
        String expectedMessage = String.format(MESSAGE_FLASHCARDS_LISTED_OVERVIEW, 2);
        WordContainsKeywordsPredicate predicate = preparePredicate(KEYWORD_MATCHING_MEIER);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredFlashCardList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, DANIEL), model.getFilteredFlashCardList());
    }

    @Test
    public void execute_multipleKeywords_multipleFlashCardsFound() {
        String expectedMessage = String.format(MESSAGE_FLASHCARDS_LISTED_OVERVIEW, 3);
        WordContainsKeywordsPredicate predicate = preparePredicate(KEYWORD_MATCHING_MEIER_OR_MEYER);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredFlashCardList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, DANIEL, ELLE), model.getFilteredFlashCardList());
    }

    @Test
    public void execute_multipleKeywordsWithNotExistingKeyWord_multipleFlashCardsFound() {
        String expectedMessage = String.format(MESSAGE_FLASHCARDS_LISTED_OVERVIEW, 2);
        WordContainsKeywordsPredicate predicate = preparePredicate(KEYWORD_MATCHING_MEIER + ",notExisting");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredFlashCardList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, DANIEL), model.getFilteredFlashCardList());
    }

    @Test
    public void execute_substringKeywords_multipleFlashCardsFound() {
        String expectedMessage = String.format(MESSAGE_FLASHCARDS_LISTED_OVERVIEW, 3);
        WordContainsKeywordsPredicate predicate = preparePredicate(SUBSTRING_ME);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredFlashCardList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, DANIEL, ELLE), model.getFilteredFlashCardList());
    }

    @Test
    public void toStringMethod() {
        WordContainsKeywordsPredicate predicate = new WordContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindCommand findCommand = new FindCommand(predicate);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private WordContainsKeywordsPredicate preparePredicate(String userInput) {
        return new WordContainsKeywordsPredicate(Arrays.asList(userInput.trim().split(",")));
    }
}
