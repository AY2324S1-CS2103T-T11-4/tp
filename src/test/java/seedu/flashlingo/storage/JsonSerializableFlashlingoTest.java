//@@author A1WAYSD
//Adapted from AB-3 JsonSerializableAddressBookTest
package seedu.flashlingo.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.flashlingo.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.flashlingo.commons.exceptions.IllegalValueException;
import seedu.flashlingo.commons.util.JsonUtil;
import seedu.flashlingo.model.Flashlingo;
import seedu.flashlingo.testutil.TypicalFlashCards;

public class JsonSerializableFlashlingoTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableFlashlingoTest");
    private static final Path TYPICAL_FLASH_CARDS_FILE = TEST_DATA_FOLDER.resolve("typicalFlashCardsFlashlingo.json");
    private static final Path INVALID_FLASH_CARD_FILE = TEST_DATA_FOLDER.resolve("invalidFlashCardFlashlingo.json");
    private static final Path DUPLICATE_FLASH_CARD_FILE = TEST_DATA_FOLDER.resolve("duplicateFlashCardFlashlingo.json");

    @Test
    public void toModelType_invalidFlashCardsFile_throwsIllegalValueException() throws Exception {
        JsonSerializableFlashlingo dataFromFile = JsonUtil.readJsonFile(INVALID_FLASH_CARD_FILE,
                JsonSerializableFlashlingo.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateFlashCards_throwsIllegalValueException() throws Exception {
        JsonSerializableFlashlingo dataFromFile = JsonUtil.readJsonFile(DUPLICATE_FLASH_CARD_FILE,
                JsonSerializableFlashlingo.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableFlashlingo.MESSAGE_DUPLICATE_FLASHCARD,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_typicalFlashCardsFile_success() throws Exception {
        JsonSerializableFlashlingo dataFromFile = JsonUtil.readJsonFile(TYPICAL_FLASH_CARDS_FILE,
                JsonSerializableFlashlingo.class).get();
        Flashlingo flashlingoFromFile = dataFromFile.toModelType();
        Flashlingo typicalFlashCardsFlashlingo = TypicalFlashCards.getTypicalFlashlingo();
        assertEquals(flashlingoFromFile, typicalFlashCardsFlashlingo);
    }
}
