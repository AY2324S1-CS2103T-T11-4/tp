//@@author A1WAYSD-reused
//Reused from AB-3 AddressBook.java with minor modifications
package seedu.flashlingo.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.flashlingo.commons.util.ToStringBuilder;
import seedu.flashlingo.model.flashcard.FlashCard;
import seedu.flashlingo.model.flashcard.UniqueFlashCardList;

/**
 * Wraps all data at the flashlingo level
 * Duplicates are not allowed (by .isSameFlash comparison)
 */
public class Flashlingo implements ReadOnlyFlashlingo {

    private final UniqueFlashCardList flashCards;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     * among constructors.
     */
    {
        flashCards = new UniqueFlashCardList();
    }

    public Flashlingo() {}

    /**
     * Creates a Flashlingo using the FlashCards in the {@code toBeCopied}
     */
    public Flashlingo(ReadOnlyFlashlingo toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the flashcard list with {@code flashCards}.
     * {@code flashCards} must not contain duplicate flashCards.
     */
    public void setFlashCards(List<FlashCard> flashCards) {
        this.flashCards.setFlashCards(flashCards);
    }

    /**
     * Resets the existing data of this {@code Flashlingo} with {@code newData}.
     */
    public void resetData(ReadOnlyFlashlingo newData) {
        requireNonNull(newData);
        setFlashCards(newData.getFlashCardList());
    }

    //// flashcard-level operations

    /**
     * Returns true if a flashcard with the same identity as {@code flashcard} exists in the flashlingo.
     */
    public boolean hasFlashCard(FlashCard flashCard) {
        requireNonNull(flashCard);
        return flashCards.contains(flashCard);
    }

    /**
     * Adds a flashcard to Flashlingo.
     * The flashcard must not already exist in Flashlingo.
     */
    public void addFlashCard(FlashCard flashCard) {
        flashCards.add(flashCard);
    }

    /**
     * Replaces the given flashcard {@code target} in the list with {@code editedFlashCard}.
     * {@code target} must exist in flashlingo.
     * The flashcard identity of {@code editedFlashCard} must not be the same as another existing
     * flashcard in flashlingo.
     */
    public void setFlashCard(FlashCard target, FlashCard editedFlashCard) {
        requireNonNull(editedFlashCard);

        flashCards.setFlashCard(target, editedFlashCard);
    }

    /**
     * Removes {@code key} from this {@code Flashlingo}.
     * {@code key} must exist in Flashlingo.
     */
    public void removeFlashCard(FlashCard key) {
        flashCards.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
          .add("FlashCards", flashCards)
          .toString();
    }

    @Override
    public ObservableList<FlashCard> getFlashCardList() {
        return flashCards.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Flashlingo)) {
            return false;
        }

        Flashlingo otherFlashlingo = (Flashlingo) other;
        return flashCards.equals(otherFlashlingo.flashCards);
    }

    @Override
    public int hashCode() {
        return flashCards.hashCode();
    }
}
