package seedu.flashlingo.model.flashcard.words;

import static seedu.flashlingo.logic.Messages.MESSAGE_EMPTY_VALUE;

/**
 * Represents the original word
 *
 * @author Nathanael M. Tan, Taanish Bhardwaj
 * @version 1.2
 * @since 1.2
 */
public class OriginalWord extends Word {
    /**
     * Constructs a new Original Word
     * @param word String to be encapsulated by this Original Word
     * @param language Language of the encapsulated word
     */
    public OriginalWord(String word, String language) {
        super(word, language);
    }

    /**
     * Constructs a new Original Word
     * @param word String to be encapsulated by this Original Word
     */
    public OriginalWord(String word) {
        super(word, "");
    }

    /**
     * Edits this word
     * @param newWord The new word to replace this word
     * @return The new word
     */
    @Override
    public OriginalWord editWord(String newWord, String newLanguage) {
        if (newWord == null) {
            newWord = getWord();
        } else if (newWord.isEmpty()) {
            throw new IllegalArgumentException(MESSAGE_EMPTY_VALUE);
        }
        return new OriginalWord(
                newWord, newLanguage == null ? getLanguage() : newLanguage);
    }

    /**
     * Checks whether this Original Word is equal to the passed object
     * @param other Passed object to check equality against
     * @return True or False depending on whether this and other are equal
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof OriginalWord)) {
            return false;
        }

        OriginalWord otherOriginalWord = (OriginalWord) other;
        return getWord().equalsIgnoreCase(otherOriginalWord.getWord())
                && getLanguage().equalsIgnoreCase(otherOriginalWord.getLanguage());
    }
}
