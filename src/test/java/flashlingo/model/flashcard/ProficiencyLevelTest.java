package flashlingo.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.flashlingo.model.flashcard.ProficiencyLevel;


class ProficiencyLevelTest {

    @Test
    void calculateNextReviewInterval() {
    }

    @Test
    void upgradeLevel() {
        ProficiencyLevel pl = new ProficiencyLevel(1);
        pl.upgradeLevel();
        assertEquals(pl.getLevel(), 2);
    }

    @Test
    void downgradeLevelWhenBase() {
        ProficiencyLevel pl = new ProficiencyLevel(1);
        pl.downgradeLevel();
        assertEquals(pl.getLevel(), 1);
    }

    @Test
    void downgradeLevelWhenNotBase() {
        ProficiencyLevel pl = new ProficiencyLevel(3);
        pl.downgradeLevel();
        assertEquals(pl.getLevel(), 2);
    }

    @Test
    void getLevel() {
        ProficiencyLevel pl1 = new ProficiencyLevel(4);
        assertEquals(pl1.getLevel(), 4);
    }

    @Test
    void testToString() {
        ProficiencyLevel pl1 = new ProficiencyLevel(4);
        assertEquals(pl1.toString(), "4");
    }

    @Test
    void setLevel() {
        ProficiencyLevel pl1 = new ProficiencyLevel(4);
        pl1.setLevel(2);
        assertEquals(pl1.getLevel(), 2);
        pl1.setLevel(6);
        assertEquals(pl1.getLevel(), 6);
    }
}
