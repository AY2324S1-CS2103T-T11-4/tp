package seedu.flashlingo.testutil;

import static seedu.flashlingo.logic.commands.CommandTestUtil.VALID_ORIGINAL_WORD_AMY;
import static seedu.flashlingo.logic.commands.CommandTestUtil.VALID_ORIGINAL_WORD_BOB;
import static seedu.flashlingo.logic.commands.CommandTestUtil.VALID_TRANSLATION_AMY;
import static seedu.flashlingo.logic.commands.CommandTestUtil.VALID_TRANSLATION_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.flashlingo.model.Flashlingo;
import seedu.flashlingo.model.flashcard.FlashCard;

/**
 * A utility class containing a list of {@code FlashCard} objects to be used in tests.
 */
public class TypicalFlashCards {

    public static final FlashCard ALICE = new FlashCardBuilder().withOriginalWord("Alice Pauline", "English")
            .withTranslatedWord("爱丽丝·宝琳", "Mandarin").build();
    public static final FlashCard BENSON = new FlashCardBuilder().withOriginalWord("Benson Meier",
                    "English")
            .withTranslatedWord("本森·梅尔", "Mandarin")
            .build();
    public static final FlashCard CARL = new FlashCardBuilder().withOriginalWord("Carl Kurz", "English")
            .withTranslatedWord("卡尔·库尔兹", "Mandarin")
            .build();
    public static final FlashCard DANIEL = new FlashCardBuilder().withOriginalWord("Daniel Meier",
                    "English")
            .withTranslatedWord("丹尼尔·梅尔", "Mandarin")
            .build();
    public static final FlashCard ELLE = new FlashCardBuilder().withOriginalWord("Elle Meyer",
                    "English")
            .withTranslatedWord("艾尔·迈耶", "Mandarin")
            .build();
    public static final FlashCard FIONA = new FlashCardBuilder().withOriginalWord("Fiona Kunz",
                    "English")
            .withTranslatedWord("菲奥娜昆兹", "Mandarin").build();

    public static final FlashCard GEORGE = new FlashCardBuilder().withOriginalWord("George Best",
                    "English")
            .withTranslatedWord("乔治·贝斯特", "Mandarin").build();

    // Manually added
    public static final FlashCard HOON = new FlashCardBuilder().withOriginalWord("Hoon Meier",
                    "English")
            .withTranslatedWord("胡恩·梅尔", "Mandarin")
            .build();
    public static final FlashCard IDA = new FlashCardBuilder().withOriginalWord("Ida Mueller",
                    "English")
            .withTranslatedWord("艾达·米勒", "Mandarin")
            .build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final FlashCard AMY = new FlashCardBuilder().withOriginalWord(VALID_ORIGINAL_WORD_AMY,
                    "")
            .withTranslatedWord(VALID_TRANSLATION_AMY, "")
            .build();
    public static final FlashCard BOB = new FlashCardBuilder().withOriginalWord(VALID_ORIGINAL_WORD_BOB,
                    "")
            .withTranslatedWord(VALID_TRANSLATION_BOB, "")
            .build();

    public static final FlashCard WORD = new FlashCardBuilder().withOriginalWord("你好", "Chinese")
            .withTranslatedWord("hi", "English").withLevel(2)
            .withWhenToReview("2023-12-12T23:59:59Z").build();

    private TypicalFlashCards() {} // prevents instantiation

    /**
     * Returns an {@code Flashlingo} with all the typical flashcards.
     */
    public static Flashlingo getTypicalFlashlingo() {
        Flashlingo fl = new Flashlingo();
        for (FlashCard flashCard : getTypicalFlashCards()) {
            fl.addFlashCard(flashCard);
        }
        return fl;
    }

    public static List<FlashCard> getTypicalFlashCards() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
