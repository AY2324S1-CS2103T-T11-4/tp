package seedu.flashlingo.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashlingo.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.flashlingo.logic.commands.CommandTestUtil.showFlashCardAtIndex;
import static seedu.flashlingo.testutil.TypicalFlashCards.getTypicalFlashlingo;
import static seedu.flashlingo.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;
import static seedu.flashlingo.testutil.TypicalIndexes.INDEX_SECOND_FLASHCARD;

import org.junit.jupiter.api.Test;

import seedu.flashlingo.commons.core.index.Index;
import seedu.flashlingo.logic.Messages;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.ModelManager;
import seedu.flashlingo.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code RevealCommand}.
 */
public class RevealCommandTest {

    private Model model = new ModelManager(getTypicalFlashlingo(), new UserPrefs());

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFlashCardList().size() + 1);
        RevealCommand revealCommand = new RevealCommand(outOfBoundIndex);

        assertCommandFailure(revealCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showFlashCardAtIndex(model, INDEX_FIRST_FLASHCARD);

        Index outOfBoundIndex = INDEX_SECOND_FLASHCARD;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFlashlingo().getFlashCardList().size());

        RevealCommand revealCommand = new RevealCommand(outOfBoundIndex);

        assertCommandFailure(revealCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }

}
