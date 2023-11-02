package flashlingo.logic.commands;

import static flashlingo.logic.commands.CommandTestUtil.assertCommandSuccess;
import static flashlingo.testutil.TypicalFlashCards.getTypicalFlashlingo;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.flashlingo.logic.commands.ReviewCommand;
import seedu.flashlingo.model.Flashlingo;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.ModelManager;
import seedu.flashlingo.model.UserPrefs;

/**
 * Finds and lists all flashcards in flashlingo who is overdue.
 */
public class ReviewCommandTest {

    private Model model = new ModelManager(getTypicalFlashlingo(), new UserPrefs());
    @Test
    public void execute_review_success() {

        Model expectedModel = new ModelManager(new Flashlingo(model.getFlashlingo()), new UserPrefs());
        String expectedMessage = ReviewCommand.MESSAGE_SUCCESS + "\n" + "0 flashcards listed!" + "\n[]";
        assertCommandSuccess(new ReviewCommand(), model, expectedMessage, expectedModel);
    }
    @Test
    public void equals() {
        ReviewCommand reviewFirstCommand = new ReviewCommand();

        // same object -> returns true
        assertTrue(reviewFirstCommand.equals(reviewFirstCommand));

        // same values -> returns true
        ReviewCommand reviewFirstCommandCopy = new ReviewCommand();
        assertTrue(reviewFirstCommand.equals(reviewFirstCommandCopy));

        // different types -> returns false
        assertFalse(reviewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(reviewFirstCommand.equals(null));
    }
}
