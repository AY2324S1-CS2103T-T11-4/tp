package seedu.flashlingo.model.flashcard.words;

import static java.util.Objects.requireNonNull;
import static seedu.flashlingo.commons.util.AppUtil.checkArgument;

/**
 * Encapsulates an input word
 */
abstract class Word {
    public static final String MESSAGE_CONSTRAINTS =
            "Languages should only contain alphanumeric characters and spaces, and it should not be blank";
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
        checkArgument(isValidLanguage(language), MESSAGE_CONSTRAINTS);
        this.word = word.trim();
        this.language = language.trim();
    }

    /**
     * Returns true if a given string is a valid language.
     */
    public static boolean isValidLanguage(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Evaluates whether this word is an original word
     * @return True or False depending on whether this is an original word
     */
    abstract boolean isOriginalWord();

    /**
     * Evaluates whether this word is a translated word
     * @return True or False depending on whether this is a translated word
     */
    abstract boolean isTranslatedWord();

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
        return this.language.toLowerCase().equals(language.toLowerCase());
    }

    /**
     * Checks whether this word is equal to the passed object
     * @param other Passed object to check equality against
     * @return True or False depending on whether this and other are equal
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Word)) {
            return false;
        }
        Word otherWord = (Word) other;
        return otherWord.word.equals(this.word) && otherWord.language.equals(this.language);
    }

    /**
     * Evaluates and returns String representation of this Word
     * @return String representation of this Word
     */
    @Override
    public String toString() {
        return this.word;
    }
}

