//@@author A1WAYSD-reused
//Reused from ModelManagerTest.java with modifications
//Added test for switchTheme method
package seedu.flashlingo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashlingo.model.Model.PREDICATE_SHOW_ALL_FLASHCARDS;
import static seedu.flashlingo.testutil.Assert.assertThrows;
import static seedu.flashlingo.testutil.TypicalFlashCards.ALICE;
import static seedu.flashlingo.testutil.TypicalFlashCards.WORD;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.flashlingo.commons.core.GuiSettings;
import seedu.flashlingo.model.flashcard.FlashCard;
import seedu.flashlingo.model.flashcard.WordContainsKeywordsPredicate;
import seedu.flashlingo.testutil.FlashlingoBuilder;

public class ModelManagerTest {
    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new Flashlingo(), new Flashlingo(modelManager.getFlashlingo()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setFlashlingoFilePath(Paths.get("seedu/flashlingo/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setFlashlingoFilePath(Paths.get("new/flashlingo/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setFlashlingoFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setFlashlingoFilePath(null));
    }

    @Test
    public void setFlashlingoFilePath_validPath_setsFlashlingoFilePath() {
        Path path = Paths.get("seedu/flashlingo/file/path");
        modelManager.setFlashlingoFilePath(path);
        assertEquals(path, modelManager.getFlashlingoFilePath());
    }

    @Test
    public void hasFlashCard_nullFlashCard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasFlashCard(null));
    }

    @Test
    public void hasFlashCard_flashcardNotInFlashlingo_returnsFalse() {
        assertFalse(modelManager.hasFlashCard(WORD));
    }

    @Test
    public void hasFlashCard_flashCardInFlashlingo_returnsTrue() {
        modelManager.addFlashCard(WORD);
        assertTrue(modelManager.hasFlashCard(WORD));
    }

    @Test
    public void getFilteredFlashCardList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredFlashCardList().remove(0));
    }

    @Test
    public void addFlashCards_nullFlashCard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.addFlashCards(null));
    }

    @Test
    public void addFlashCards_success() {
        modelManager.addFlashCards(new ArrayList<FlashCard>(Arrays.asList(WORD, ALICE)));
        assertTrue(modelManager.hasFlashCard(WORD));
    }

    @Test
    public void equals() {
        Flashlingo flashlingo = new FlashlingoBuilder().withFlashCard(WORD).withFlashCard(ALICE).build();
        Flashlingo differentFlashlingo = new Flashlingo();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(flashlingo, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(flashlingo, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different flashlingo -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentFlashlingo, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getOriginalWord().getWord().split("\\s+");
        modelManager.updateFilteredFlashCardList(new WordContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(flashlingo, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredFlashCardList(PREDICATE_SHOW_ALL_FLASHCARDS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setFlashlingoFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(flashlingo, differentUserPrefs)));
    }

    @Test
    public void switchTheme_success() {
        modelManager.switchTheme();
        assertEquals("Dark", modelManager.getTheme());
        modelManager.switchTheme();
        assertEquals("Default", modelManager.getTheme());
    }
}
