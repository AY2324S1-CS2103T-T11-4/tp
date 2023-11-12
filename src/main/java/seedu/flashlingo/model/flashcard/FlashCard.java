//@@author itsNatTan

package seedu.flashlingo.model.flashcard;

import static seedu.flashlingo.commons.util.AppUtil.checkArgument;
import static seedu.flashlingo.logic.Messages.MESSAGE_SAME_WORD;

import java.util.Date;

import seedu.flashlingo.model.flashcard.words.OriginalWord;
import seedu.flashlingo.model.flashcard.words.TranslatedWord;

/**
 * Represents each flashcard
 *
 * @author Nathanael M. Tan
 * @version 1.0
 * @since 1.0
 */
public class FlashCard {
    private final OriginalWord originalWord;
    private final TranslatedWord translatedWord;
    private Date whenToReview; // Date the flashcard was needs to be reviewed
    private ProficiencyLevel currentLevel; // How many times successfully remembered
    private boolean isRemembered; //if successfully remembers word
    private boolean isRevealed = false;
    /**
     * Constructor for Flashcard
     *
     * @param originalWord   The word in the original language
     * @param translatedWord The word in the language you are learning
     * @param whenToReview   The date of when you need to review this word
     * @param level          The level of familiarity with the word
     */
    public FlashCard(OriginalWord originalWord, TranslatedWord translatedWord, Date whenToReview,
                     ProficiencyLevel level) {
        this.currentLevel = level;
        this.whenToReview = whenToReview;
        this.translatedWord = translatedWord;
        this.originalWord = originalWord;
        checkArgument(isValidWord(originalWord, translatedWord), MESSAGE_SAME_WORD);
    }

    /**
     * Constructor for Flashcard
     *
     * @param originalWord   The word in the original language
     * @param translatedWord The word in the language you are learning
     * @param whenToReview   The date of when you need to review this word
     * @param level          The level of familiarity with the word
     * @param isRemembered   Whether the word was remembered
     */
    public FlashCard(OriginalWord originalWord, TranslatedWord translatedWord, Date whenToReview,
                     ProficiencyLevel level, boolean isRemembered) {
        this.currentLevel = level;
        this.whenToReview = whenToReview;
        this.translatedWord = translatedWord;
        this.originalWord = originalWord;
        this.isRemembered = isRemembered;
    }
    public OriginalWord getOriginalWord() {
        return originalWord;
    }

    public TranslatedWord getTranslatedWord() {
        return translatedWord;
    }

    public Date getWhenToReview() {
        return whenToReview;
    }

    public ProficiencyLevel getProficiencyLevel() {
        return currentLevel;
    }

    public boolean isDeletedFromReview() {
        return this.currentLevel.isDeletedFromReview();
    }

    public void setIsRevealed(Boolean isRevealed) {
        this.isRevealed = isRevealed;
    }
    public boolean getIsRevealed() {
        return this.isRevealed;
    }

    //@@author Song-Mengfei
    private boolean isValidWord(OriginalWord word, TranslatedWord translate) {
        return !word.getWord().equalsIgnoreCase(translate.getWord());
    }
    /**
     * Edits the flashCard
     * @param changes The new word to replace the old word
     * @return The new flashcard
     */
    public FlashCard editFlashCard(String[] changes) {
        OriginalWord originalWord = this.originalWord.editWord(changes[0], changes[1]);
        TranslatedWord translatedWord = this.translatedWord.editWord(changes[2], changes[3]);
        return new FlashCard(originalWord, translatedWord, whenToReview, currentLevel);
    }

    /**
     * Returns true if both flashcards have the same originalWord and translatedWord.
     * This defines a weaker notion of equality between two flashcards.
     */
    public boolean isSameFlashCard(FlashCard otherFlashCard) {
        if (otherFlashCard == this) {
            return true;
        }

        return otherFlashCard != null
                && otherFlashCard.getOriginalWord().equals(getOriginalWord())
                && otherFlashCard.getTranslatedWord().equals(getTranslatedWord());
    }

    //@@author D-Limiter
    /**
     * Returns true if the original word or the translation contains the keyword.
     * @param inputWord The keyword to check for
     * @return True or False depending on whether the keyword is found
     */
    public boolean hasKeyword(String inputWord) {
        return this.originalWord.hasSubpart(inputWord) || this.translatedWord.hasSubpart(inputWord);
    }

    /**
     * Sets this FlashCard as remembered
     */
    public void recallFlashCard() {
        this.isRemembered = true;
    }

    /**
     * Sets this FlashCard as forgotten
     */
    public void forgetFlashCard() {
        this.isRemembered = false;
    }

    /**
     * Evaluates and returns if this FlashCard is remembered
     */
    public boolean isRecalled() {
        return this.isRemembered;
    }

    /**
     * Returns true if the original word or the translation is of the language.
     * @param language The language to check for
     * @return True or False depending on whether the language is found
     */
    public boolean isSameLanguage(String language) {
        return this.originalWord.isSameLanguage(language) || this.translatedWord.isSameLanguage(language);
    }

    //@@author itsNatTan
    /**
     * Returns true if the review date is before the current date.
     * @return True or False depending on whether the review date is before the current date
     */
    public boolean isOverdue() {
        return this.whenToReview.before(new Date());
    }

    /**
     * Update the flash card to next level
     */
    public void updateLevel(boolean isSuccess) {
        if (isSuccess) {
            getProficiencyLevel().upgradeLevel();
            updateReviewDate(getProficiencyLevel().calculateNextReviewInterval());
        } else {
            getProficiencyLevel().downgradeLevel();
            updateReviewDate(getProficiencyLevel().calculateNextReviewInterval());
        }
    }

    /**
     * Formats Flashcard for writing to textFile
     *
     * @return Tab separated String formatted for writing
     */
    @Override
    public String toString() {
        String sb = originalWord + " | " + originalWord.getLanguage() + " | " + translatedWord + " | "
                + translatedWord.getLanguage() + " | " + whenToReview.toString() + " | " + currentLevel + "\n";
        return sb;
    }

    public void updateReviewDate(long timeInMs) {
        this.whenToReview = new Date(new Date().getTime() + timeInMs);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FlashCard)) {
            return false;
        }

        FlashCard otherFlashCard = (FlashCard) other;
        return originalWord.equals(otherFlashCard.originalWord)
                && translatedWord.equals(otherFlashCard.translatedWord);
    }
}
