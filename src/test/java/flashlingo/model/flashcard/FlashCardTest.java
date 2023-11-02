package flashlingo.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

import seedu.flashlingo.model.flashcard.FlashCard;
import seedu.flashlingo.model.flashcard.ProficiencyLevel;
import seedu.flashlingo.model.flashcard.words.OriginalWord;
import seedu.flashlingo.model.flashcard.words.TranslatedWord;

class FlashCardTest {
    private OriginalWord originalWord = new OriginalWord("Hello", "eng");
    private TranslatedWord translatedWord = new TranslatedWord("Ni Hao", "chi");
    @Test
    void getOriginalWord() {
        FlashCard fc = new FlashCard(new OriginalWord("Hello", "eng"),
                new TranslatedWord("Ni Hao", "chi"),
                new Date(),
                new ProficiencyLevel(1));

        assertEquals(fc.getOriginalWord(), originalWord);
    }

    @Test
    void getTranslatedWord() {
        FlashCard fc = new FlashCard(new OriginalWord("Hello", "eng"),
                new TranslatedWord("Ni Hao", "chi"),
                new Date(),
                new ProficiencyLevel(1));

        assertEquals(fc.getTranslatedWord(), translatedWord);
    }

    @Test
    void getWhenToReview() {
        Date now = new Date();
        FlashCard fc = new FlashCard(new OriginalWord("Hello", "eng"),
                new TranslatedWord("Ni Hao", "chi"),
                now,
                new ProficiencyLevel(1));
        assertEquals(fc.getWhenToReview(), now);
    }

    @Test
    void getProficiencyLevel() {
        FlashCard fc = new FlashCard(new OriginalWord("Hello", "eng"),
                new TranslatedWord("Ni Hao", "chi"),
                new Date(),
                new ProficiencyLevel(1));
        assertEquals(fc.getProficiencyLevel().getLevel(), 1);
    }

    @Test
    void isSameFlashCardEquality() {
        FlashCard fc = new FlashCard(new OriginalWord("Hello", "eng"),
                new TranslatedWord("Ni Hao", "chi"),
                new Date(),
                new ProficiencyLevel(1));
        assert(fc.isSameFlashCard(fc));
    }

    @Test
    void isSameFlashCardByWord() {
        FlashCard fc1 = new FlashCard(new OriginalWord("Hello", "eng"),
                new TranslatedWord("Ni Hao", "chi"),
                new Date(),
                new ProficiencyLevel(1));
        FlashCard fc2 = new FlashCard(new OriginalWord("Hello", "eng"),
                new TranslatedWord("Ni Hao", "chi"),
                new Date(),
                new ProficiencyLevel(1));
        assert(fc1.isSameFlashCard(fc2));
    }

    @Test
    void isSameFlashCardBySameWordDifferentTranslation() {
        FlashCard fc1 = new FlashCard(new OriginalWord("Hello", "eng"),
                new TranslatedWord("Ni Hao", "chi"),
                new Date(),
                new ProficiencyLevel(1));
        FlashCard fc2 = new FlashCard(new OriginalWord("Hello", "eng"),
                new TranslatedWord("你好", "chi"),
                new Date(),
                new ProficiencyLevel(1));
        assert(!fc1.isSameFlashCard(fc2));
    }

    @Test
    void hasKeyword() {
    }

    @Test
    void isOverdue() {
        FlashCard fc = new FlashCard(new OriginalWord("Hello", "eng"),
                new TranslatedWord("Ni Hao", "chi"),
                new Date(new Date().getTime() - 1000),
                new ProficiencyLevel(1));
        assert(fc.isOverdue());
    }

    @Test
    void isNotOverdue() {
        FlashCard fc = new FlashCard(new OriginalWord("Hello", "eng"),
                new TranslatedWord("Ni Hao", "chi"),
                new Date(new Date().getTime() + 100000),
                new ProficiencyLevel(1));
        assert(!fc.isOverdue());
    }

    @Test
    void isSameLanguageByOriginalWord() {
        FlashCard fc1 = new FlashCard(new OriginalWord("Hello", "eng"),
                new TranslatedWord("Ni Hao", "chi"),
                new Date(),
                new ProficiencyLevel(1));
        assert(fc1.isSameLanguage("eng"));
    }

    @Test
    void isSameLanguageByTranslatedWord() {
        FlashCard fc1 = new FlashCard(new OriginalWord("Hello", "eng"),
                new TranslatedWord("Ni Hao", "chi"),
                new Date(),
                new ProficiencyLevel(1));
        assert(fc1.isSameLanguage("chi"));
    }

    @Test
    void isNotSameLanguage() {
        FlashCard fc1 = new FlashCard(new OriginalWord("Hello", "eng"),
                new TranslatedWord("Ni Hao", "chi"),
                new Date(),
                new ProficiencyLevel(1));
        assert(!fc1.isSameLanguage("jap"));
    }

    @Test
    void testToString() {
        ProficiencyLevel level = new ProficiencyLevel(1);
        Date date = new Date(new Date().getTime() + 100000);
        FlashCard fc1 = new FlashCard(originalWord,
                translatedWord,
                date,
                level);
        assertEquals(fc1.toString(), originalWord + " | "
                + originalWord.getLanguage() + " | " + translatedWord + " | "
                + originalWord.getLanguage() + " | " + date.toString() + " | " + level + "\n");
    }

    @Test
    void handleUserInputSuccess() {
        Date date = new Date();
        FlashCard fc1 = new FlashCard(new OriginalWord("Hello", "eng"),
                new TranslatedWord("Ni Hao", "chi"),
                date,
                new ProficiencyLevel(1));
        fc1.updateLevel(true);
        assertEquals(fc1.getProficiencyLevel().getLevel(), 2);
        assert(!fc1.isDeletedFromReview());
        assert(!fc1.getWhenToReview().equals(date));
    }

    @Test
    void handleUserInputFailure() {
        Date date = new Date();
        FlashCard fc1 = new FlashCard(new OriginalWord("Hello", "eng"),
                new TranslatedWord("Ni Hao", "chi"),
                date,
                new ProficiencyLevel(2));
        fc1.updateLevel(false);
        assertEquals(fc1.getProficiencyLevel().getLevel(), 1);
        assert(!fc1.isDeletedFromReview());
        assert(!fc1.getWhenToReview().equals(date));
    }

    @Test
    void handleUserInputFailureWhenLevelIsBase() {
        Date date = new Date();
        FlashCard fc1 = new FlashCard(new OriginalWord("Hello", "eng"),
                new TranslatedWord("Ni Hao", "chi"),
                date,
                new ProficiencyLevel(1));
        fc1.updateLevel(false);
        assertEquals(fc1.getProficiencyLevel().getLevel(), 1);
        assert(!fc1.isDeletedFromReview());
        assert(!fc1.getWhenToReview().equals(date));
    }

    @Test
    void handleUserInputSuccessWhenAtMaxLevel() {
        Date date = new Date();
        FlashCard fc1 = new FlashCard(new OriginalWord("Hello", "eng"),
                new TranslatedWord("Ni Hao", "chi"),
                date,
                new ProficiencyLevel(5));
        fc1.updateLevel(true);
        assertEquals(fc1.getProficiencyLevel().getLevel(), 6);
        assert(fc1.isDeletedFromReview());
        assert(!fc1.getWhenToReview().equals(date));
    }
}
