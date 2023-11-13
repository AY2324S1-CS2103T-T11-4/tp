//@@author A1WAYSD-reused
//Reused from AB-3 ReadOnlyAddressBook.java with minor modifications
package seedu.flashlingo.model;

import javafx.collections.ObservableList;
import seedu.flashlingo.model.flashcard.FlashCard;

/**
 * Unmodifiable view of a flashlingo.
 */
public interface ReadOnlyFlashlingo {

    /**
     * Returns an unmodifiable view of the flash cards list.
     * This list will not contain any duplicate flash cards.
     */
    ObservableList<FlashCard> getFlashCardList();

}
