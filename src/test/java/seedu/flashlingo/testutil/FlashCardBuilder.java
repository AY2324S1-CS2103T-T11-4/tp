package seedu.flashlingo.testutil;

import java.time.Instant;
import java.util.Date;

import seedu.flashlingo.model.flashcard.FlashCard;
import seedu.flashlingo.model.flashcard.ProficiencyLevel;
import seedu.flashlingo.model.flashcard.words.OriginalWord;
import seedu.flashlingo.model.flashcard.words.TranslatedWord;

/**
 * A utility class to help with building FlashCard objects.
 */
public class FlashCardBuilder {
    public static final String DEFAULT_ORIGINAL_WORD = "Hello";
    public static final String DEFAULT_TRANSLATED_WORD = "你好";
    public static final String DEFAULT_ORIGINAL_WORD_LANGUAGE = "English";
    public static final String DEFAULT_TRANSLATED_WORD_LANGUAGE = "Mandarin";
    public static final int DEFAULT_LEVEL = 1;
    public static final String DEFAULT_WHEN_TO_REVIEW = "2023-01-01T00:00:00Z";

    private OriginalWord originalWord;
    private TranslatedWord translatedWord;
    private Date whenToReview;
    private ProficiencyLevel level;

    /**
     * Creates a {@code FlashCardBuilder} with the default details.
     */
    public FlashCardBuilder() {
        originalWord = new OriginalWord(DEFAULT_ORIGINAL_WORD, DEFAULT_ORIGINAL_WORD_LANGUAGE);
        translatedWord = new TranslatedWord(DEFAULT_TRANSLATED_WORD, DEFAULT_TRANSLATED_WORD_LANGUAGE);
        whenToReview = Date.from(Instant.parse(DEFAULT_WHEN_TO_REVIEW));
        level = new ProficiencyLevel(DEFAULT_LEVEL);
    }

    /**
     * Initializes the FlashCardBuilder with the data of {@code flashCardToCopy}.
     */
    public FlashCardBuilder(FlashCard flashCardToCopy) {
        originalWord = flashCardToCopy.getOriginalWord();
        translatedWord = flashCardToCopy.getTranslatedWord();
        whenToReview = flashCardToCopy.getWhenToReview();
        level = flashCardToCopy.getProficiencyLevel();
    }

    /**
     * Sets the {@code OriginalWord} of the {@code FlashCard} that we are building.
     */
    public FlashCardBuilder withOriginalWord(String word, String language) {
        this.originalWord = new OriginalWord(word, language);
        return this;
    }

    /**
     * Sets the {@code TranslatedWord} of the {@code FlashCard} that we are building.
     */
    public FlashCardBuilder withTranslatedWord(String word, String language) {
        this.translatedWord = new TranslatedWord(word, language);
        return this;
    }

    /**
     * Sets the {@code WhenToReview} of the {@code FlashCard} that we are building.
     */
    public FlashCardBuilder withWhenToReview(String date) {
        this.whenToReview = Date.from(Instant.parse(DEFAULT_WHEN_TO_REVIEW));
        System.out.println(whenToReview);
        return this;
    }

    /**
     * Sets the {@code Level} of the {@code FlashCard} that we are building.
     */
    public FlashCardBuilder withLevel(int level) {
        this.level = new ProficiencyLevel(level);
        return this;
    }

    public FlashCard build() {
        return new FlashCard(originalWord, translatedWord, whenToReview, level);
    }
}
