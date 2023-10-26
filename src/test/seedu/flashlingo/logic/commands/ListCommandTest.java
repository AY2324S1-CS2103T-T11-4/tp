package flashlingo.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.flashlingo.logic.commands.ListCommand;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.ModelManager;
import seedu.flashlingo.model.UserPrefs;

import static flashlingo.logic.commands.CommandTestUtil.assertCommandSuccess;
import static flashlingo.logic.commands.CommandTestUtil.showFlashCardAtIndex;
import static flashlingo.testutil.TypicalFlashCards.getTypicalFlashlingo;
import static flashlingo.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;
import static java.util.Objects.requireNonNull;
import static seedu.flashlingo.model.Model.PREDICATE_SHOW_ALL_FLASHCARDS;

/**
 * Lists all flashcards in Flashlingo to the user.
 */
public class ListCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFlashlingo(), new UserPrefs());
        expectedModel = new ModelManager(model.getFlashlingo(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showFlashCardAtIndex(model, INDEX_FIRST_FLASHCARD);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

//    public static final String COMMAND_WORD = "list";
//
//    public static final String MESSAGE_SUCCESS = "Listed all flashcards";
//
//
//    @Override
//    public CommandResultTest execute(Model model) {
//        requireNonNull(model);
//        model.updateFilteredFlashCardList(PREDICATE_SHOW_ALL_FLASHCARDS);
//        return new CommandResultTest(MESSAGE_SUCCESS + "\n" + model.getFilteredFlashCardList());
//    }
}
