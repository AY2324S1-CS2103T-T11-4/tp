package flashlingo.model.flashcard.word;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.flashlingo.model.flashcard.words.TranslatedWord;

class TranslatedWordTest {

    @Test
    void getWord() {
        TranslatedWord translation = new TranslatedWord("Ni Hao ", "chi");
        assertEquals(translation.getWord(), "Ni Hao");
    }

    @Test
    void getLanguage() {
        TranslatedWord translation = new TranslatedWord("Ni Hao ", "chi");
        assertEquals(translation.getLanguage(), "chi");
    }

    @Test
    void isSameLanguage() {
        TranslatedWord translation = new TranslatedWord("Ni Hao ", "chi");
        assert(translation.isSameLanguage("chi"));
    }

    @Test
    void testEqualsSameObject() {
        TranslatedWord translatedWord = new TranslatedWord("Hello", " eng ");
        assert(translatedWord.equals(translatedWord));
    }

    @Test
    void testEqualsSameWord() {
        TranslatedWord translatedWord1 = new TranslatedWord("Hello", " eng ");
        TranslatedWord translatedWord2 = new TranslatedWord("Hello", "eng ");
        assert(translatedWord1.equals(translatedWord2));
    }

    @Test
    void testEqualsNotSameWord() {
        TranslatedWord translatedWord1 = new TranslatedWord("Hello", " eng ");
        TranslatedWord translatedWord2 = new TranslatedWord("Hello", "jap ");
        assert(!translatedWord1.equals(translatedWord2));

        TranslatedWord translatedWord3 = new TranslatedWord("Hello", " eng ");
        TranslatedWord translatedWord4 = new TranslatedWord("Hell", "eng ");
        assert(!translatedWord3.equals(translatedWord4));
    }

    @Test
    void testToString() {
        TranslatedWord translatedWord = new TranslatedWord("Hello", " eng ");
        assertEquals(translatedWord.toString(), "Hello");
    }

    @Test
    void isOriginalWord() {
        TranslatedWord translatedWord = new TranslatedWord("Hello", " eng ");
        assert(!translatedWord.isOriginalWord());
    }

    @Test
    void isTranslatedWord() {
        TranslatedWord translatedWord = new TranslatedWord("Hello", " eng ");
        assert(translatedWord.isTranslatedWord());
    }
}
