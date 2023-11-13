//@@author A1WAYSD-reused
//Reused from AB-3 JsonSerializableAddressBook.java with minor modifications
package seedu.flashlingo.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.flashlingo.commons.exceptions.IllegalValueException;
import seedu.flashlingo.model.Flashlingo;
import seedu.flashlingo.model.ReadOnlyFlashlingo;
import seedu.flashlingo.model.flashcard.FlashCard;

/**
 * An Immutable Flashlingo that is serializable to JSON format.
 */
@JsonRootName(value = "flashlingo")
class JsonSerializableFlashlingo {
    public static final String MESSAGE_DUPLICATE_FLASHCARD = "Flashcards list contains duplicate flashcard(s).";
    private final List<JsonAdaptedFlashCard> flashcards = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableFlashlingo} with the given flash cards.
     */
    @JsonCreator
    public JsonSerializableFlashlingo(@JsonProperty("flashcards") List<JsonAdaptedFlashCard> flashcards) {
        this.flashcards.addAll(flashcards);
    }

    /**
     * Converts a given {@code ReadOnlyFlashlingo} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableFlashlingo}.
     */
    public JsonSerializableFlashlingo(ReadOnlyFlashlingo source) {
        flashcards.addAll(source.getFlashCardList().stream().map(JsonAdaptedFlashCard::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Flashlingo into the model's {@code Flashlingo} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Flashlingo toModelType() throws IllegalValueException {
        Flashlingo flashlingo = new Flashlingo();
        for (JsonAdaptedFlashCard jsonAdaptedFlashCard : flashcards) {
            FlashCard flashCard = jsonAdaptedFlashCard.toModelType();
            if (flashlingo.hasFlashCard(flashCard)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_FLASHCARD);
            }
            flashlingo.addFlashCard(flashCard);
        }
        return flashlingo;
    }

}
