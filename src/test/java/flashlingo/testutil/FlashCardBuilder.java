package flashlingo.testutil;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import seedu.flashlingo.model.flashcard.FlashCard;
import seedu.flashlingo.model.flashcard.ProficiencyLevel;
import seedu.flashlingo.model.flashcard.words.OriginalWord;
import seedu.flashlingo.model.flashcard.words.TranslatedWord;

/**
 * A utility class to help with building FlashCard objects.
 */
public class FlashCardBuilder {

    public static final String ORIGINAL_WORD = "伟大的";
    public static final String TRANSLATED_WORD = "great";
    public static final Date WHEN_TO_REVIEW = new GregorianCalendar(2023, Calendar.DECEMBER, 17).getTime();
    public static final int LEVEL = 1;

    private OriginalWord originalWord;
    private TranslatedWord translatedWord;
    private Date whenToReview; // Date the flashcard was needs to be reviewed
    private ProficiencyLevel level; // How many times successfully remembered

    private boolean isUpdated;
    private ProficiencyLevel originalLevel; // For undo function


    /**
     * Creates a {@code FlashcardBuilder} with the default details.
     */
    public FlashCardBuilder() {
        this.originalWord = new OriginalWord(ORIGINAL_WORD, "");
        this.translatedWord = new TranslatedWord(TRANSLATED_WORD, "");
        this.whenToReview = WHEN_TO_REVIEW;
        this.level = new ProficiencyLevel(LEVEL);
    }

    /**
     * Initializes the FlashCardBuilder with the data of {@code flashCardToCopy}.
     */
    public FlashCardBuilder(FlashCard flashCardToCopy) {
        this.originalWord = flashCardToCopy.getOriginalWord();
        this.translatedWord = flashCardToCopy.getTranslatedWord();
        this.whenToReview = flashCardToCopy.getWhenToReview();
        this.level = flashCardToCopy.getProficiencyLevel();

    }

    /**
     * Sets the {@code OriginalWord} of the {@code FlashCard} that we are building.
     */
    public FlashCardBuilder withOriginalWord(String originalWord, String language) {
        this.originalWord = new OriginalWord(originalWord, language);
        return this;
    }

    /**
     * Sets the {@code TranslatedWord} of the {@code FlashCard} that we are building.
     */
    public FlashCardBuilder withTranslatedWord(String translatedWord, String language) {
        this.translatedWord = new TranslatedWord(translatedWord, "");
        return this;
    }

    /**
     * Sets the review {@code Date} of the {@code FlashCard} that we are building.
     */
    public FlashCardBuilder withWhenToReview(Date whenToReview) {
        this.whenToReview = whenToReview;
        return this;
    }

    /**
     * Sets the {@code ProficiencyLevel} of the {@code FlashCard} that we are building.
     */
    public FlashCardBuilder withLevel(int level) {
        this.level = new ProficiencyLevel(level);
        return this;
    }

    public FlashCard build() {
        return new FlashCard(originalWord, translatedWord, whenToReview, level);
    }

}
