package flashlingo.testutil;

import seedu.flashlingo.model.Flashlingo;
import seedu.flashlingo.model.flashcard.FlashCard;

/**
 * A utility class to help with building Flashlingo objects.
 * Example usage: <br>
 *     {@code Flashlingo fl = new FlashlingoBuilder().withTranslation("再见").build();}
 */
public class FlashlingoBuilder {

    private Flashlingo flashlingo;

    public FlashlingoBuilder() {
        flashlingo = new Flashlingo();
    }

    public FlashlingoBuilder(Flashlingo flashlingo) {
        this.flashlingo = flashlingo;
    }

    /**
     * Adds a new {@code Flashcard} to the {@code Flashlingo} that we are building.
     */
    public FlashlingoBuilder withFlashCard(FlashCard flashCard) {
        flashlingo.addFlashCard(flashCard);
        return this;
    }

    public Flashlingo build() {
        return flashlingo;
    }
}
