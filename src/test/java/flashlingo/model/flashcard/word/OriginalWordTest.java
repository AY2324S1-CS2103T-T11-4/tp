package flashlingo.model.flashcard.word;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.flashlingo.model.flashcard.words.OriginalWord;

class OriginalWordTest {

    @Test
    void getWord() {
        OriginalWord ogWord = new OriginalWord("Hello ", "eng");
        assertEquals(ogWord.getWord(), "Hello");
    }

    @Test
    void getLanguage() {
        OriginalWord ogWord = new OriginalWord("Hello", " eng ");
        assertEquals(ogWord.getLanguage(), "eng");
    }

    @Test
    void isSameLanguage() {
        OriginalWord ogWord = new OriginalWord("Hello", " eng ");
        assert(ogWord.isSameLanguage("eng"));
    }

    @Test
    void testEqualsSameObject() {
        OriginalWord ogWord = new OriginalWord("Hello", " eng ");
        assert(ogWord.equals(ogWord));
    }

    @Test
    void testEqualsSameWord() {
        OriginalWord ogWord1 = new OriginalWord("Hello", " eng ");
        OriginalWord ogWord2 = new OriginalWord("Hello", "eng ");
        assert(ogWord1.equals(ogWord2));
    }

    @Test
    void testEqualsNotSameWord() {
        OriginalWord ogWord1 = new OriginalWord("Hello", " eng ");
        OriginalWord ogWord2 = new OriginalWord("Hello", "jap ");
        assert(!ogWord1.equals(ogWord2));

        OriginalWord ogWord3 = new OriginalWord("Hello", " eng ");
        OriginalWord ogWord4 = new OriginalWord("Hell", "eng ");
        assert(!ogWord3.equals(ogWord4));
    }

    @Test
    void testToString() {
        OriginalWord ogWord = new OriginalWord("Hello", " eng ");
        assertEquals(ogWord.toString(), "Hello");
    }

    @Test
    void isOriginalWord() {
        OriginalWord ogWord = new OriginalWord("Hello", " eng ");
        assert(ogWord.isOriginalWord());
    }

    @Test
    void isTranslatedWord() {
        OriginalWord ogWord = new OriginalWord("Hello", " eng ");
        assert(!ogWord.isTranslatedWord());
    }
}
