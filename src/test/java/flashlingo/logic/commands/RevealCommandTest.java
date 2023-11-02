package flashlingo.logic.commands;

import static flashlingo.logic.commands.CommandTestUtil.assertCommandSuccess;
import static flashlingo.testutil.TypicalFlashCards.getTypicalFlashlingo;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.flashlingo.logic.commands.RevealCommand;
import seedu.flashlingo.model.Flashlingo;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.ModelManager;
import seedu.flashlingo.model.UserPrefs;
import seedu.flashlingo.model.flashcard.FlashCard;

/**
 * Indicates user has not yet memorized the word.
 */
public class RevealCommandTest {
    private Model model = new ModelManager(getTypicalFlashlingo(), new UserPrefs());
    @Test
    public void execute_reveal_success() {
        Model expectedModel = new ModelManager(new Flashlingo(model.getFlashlingo()), new UserPrefs());
        FlashCard currentCard = model.getFilteredFlashCardList().get(0);
        assertCommandSuccess(new RevealCommand(), model,
                RevealCommand.MESSAGE_SUCCESS + currentCard.getTranslatedWord(), expectedModel);
    }
    @Test
    public void equals() {
        RevealCommand revealFirstCommand = new RevealCommand();

        // same object -> returns true
        assertTrue(revealFirstCommand.equals(revealFirstCommand));

        // same values -> returns true
        RevealCommand revealFirstCommandCopy = new RevealCommand();
        assertTrue(revealFirstCommand.equals(revealFirstCommandCopy));

        // different types -> returns false
        assertFalse(revealFirstCommand.equals(1));

        // null -> returns false
        assertFalse(revealFirstCommand.equals(null));

    }
}
