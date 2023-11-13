//@@author A1WAYSD-reused
//Reused from AB-3 UniquePersonList.java with minor modifications
package seedu.flashlingo.model.flashcard;

import static java.util.Objects.requireNonNull;
import static seedu.flashlingo.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.flashlingo.model.flashcard.exceptions.DuplicateFlashCardException;
import seedu.flashlingo.model.flashcard.exceptions.FlashCardNotFoundException;

/**
 * A list of flashcards that enforces uniqueness between its elements and does not allow nulls.
 * A flashcard is considered unique by comparing using {@code FlashCard#isSameFlashCard(FlashCard)}.
 * As such, adding and updating of
 * flashcards uses FlashCard#isSameFlashCard(FlashCard)
 * for equality so as to ensure that the flashcard being added or updated is
 * unique in terms of identity in the UniqueFlashCardList.
 * However, the removal of a flashcard uses FlashCard#equals(Object) so
 * as to ensure that the flashcard with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see FlashCard#isSameFlashCard(FlashCard)
 */
public class UniqueFlashCardList implements Iterable<FlashCard> {

    private final ObservableList<FlashCard> internalList = FXCollections.observableArrayList();
    private final ObservableList<FlashCard> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent flashcard as the given argument.
     */
    public boolean contains(FlashCard toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameFlashCard);
    }

    /**
     * Adds a flashcard to the list.
     * The flashcard must not already exist in the list.
     */
    public void add(FlashCard toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateFlashCardException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the flashcard {@code target} in the list with {@code editedFlashCard}.
     * {@code target} must exist in the list.
     * The flashcard identity of {@code editedFlashCard} must not be the same as another existing flashcard in the list.
     */
    public void setFlashCard(FlashCard target, FlashCard editedFlashCard) {
        requireAllNonNull(target, editedFlashCard);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new FlashCardNotFoundException();
        }

        if (!target.isSameFlashCard(editedFlashCard) && contains(editedFlashCard)) {
            throw new DuplicateFlashCardException();
        }

        internalList.set(index, editedFlashCard);
    }

    /**
     * Removes the equivalent flashcard from the list.
     * The flashcard must exist in the list.
     */
    public void remove(FlashCard toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new FlashCardNotFoundException();
        }
    }

    /**
     * Replaces the contents of this list with {@code flashCards}.
     * {@code flashCards} must not contain duplicate flashCards.
     */
    public void setFlashCards(List<FlashCard> flashCards) {
        requireAllNonNull(flashCards);
        if (!flashCardsAreUnique(flashCards)) {
            throw new DuplicateFlashCardException();
        }

        internalList.setAll(flashCards);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<FlashCard> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<FlashCard> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueFlashCardList)) {
            return false;
        }

        UniqueFlashCardList otherUniqueFlashCardList = (UniqueFlashCardList) other;
        return internalList.equals(otherUniqueFlashCardList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code flashCards} contains only unique flashCards.
     */
    private boolean flashCardsAreUnique(List<FlashCard> cardList) {
        for (int i = 0; i < cardList.size() - 1; i++) {
            for (int j = i + 1; j < cardList.size(); j++) {
                if (cardList.get(i).isSameFlashCard(cardList.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
