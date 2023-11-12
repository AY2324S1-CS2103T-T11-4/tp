package seedu.flashlingo.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.flashlingo.model.Model.PREDICATE_SHOW_ALL_FLASHCARDS;

import seedu.flashlingo.model.Model;

/**
 * Lists all flash cards in Flashlingo to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all flashcards";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredFlashCardList(PREDICATE_SHOW_ALL_FLASHCARDS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
