package seedu.flashlingo.model.flashcard.words;

import static java.util.Objects.requireNonNull;

/**
 * Encapsulates an input word
 */
public abstract class Word {
    /*
     * The first character of the language must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alpha} ]*";
    /** The input word **/
    private final String word;
    /** The input word's language **/
    private final String language;

    /**
     * Constructs a new Word
     * @param word The input word
     * @param language The input Word's language
     */
    public Word(String word, String language) {
        requireNonNull(language);
        this.word = word.trim();
        this.language = language.trim();
    }

    /**
     * Returns true if a given string is a valid language.
     */
    public static boolean isValidLanguage(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public abstract Word editWord(String newWord, String newLanguage);

    /**
     * Evaluates and returns this word
     * @return The word encapsulated by this
     */
    public String getWord() {
        return word;
    }

    /**
     * Evaluates and returns this word's language
     * @return Language of this word
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Checks whether this word contains a subpart
     * @param subpart The phrase to check for
     * @return True or False depending on whether this word contains subpart
     */
    public boolean hasSubpart(String subpart) {
        return this.word.toLowerCase().contains(subpart.toLowerCase());
    }

    /**
     * Checks whether this word is in the same language as the passed language
     * @param language The language to check against
     * @return True or False depending on whether this word is in the same language as language
     */
    public boolean isSameLanguage(String language) {
        return this.language.equalsIgnoreCase(language);
    }

    /**
     * Evaluates and returns String representation of this Word
     * @return String representation of this Word
     */
    @Override
    public String toString() {
        return this.word + " (" + this.language + ")";
    }
}

