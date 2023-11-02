package flashlingo.storage;

import static flashlingo.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.flashlingo.commons.exceptions.IllegalValueException;
import seedu.flashlingo.commons.util.JsonUtil;
import seedu.flashlingo.storage.JsonSerializableFlashlingo;

public class JsonSerializableFlashlingoTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableFlashlingoTest");
    private static final Path TYPICAL_FLASHCARDS_FILE = TEST_DATA_FOLDER.resolve("typicalFlashCardsFlashlingo.json");
    private static final Path INVALID_FLASHCARD_FILE = TEST_DATA_FOLDER.resolve("invalidFlashCardFlashlingo.json");
    private static final Path DUPLICATE_FLASHCARD_FILE = TEST_DATA_FOLDER.resolve("duplicateFlashCardFlashlingo.json");

    @Test
    public void toModelType_invalidFlashCardsFile_throwsIllegalValueException() throws Exception {
        JsonSerializableFlashlingo dataFromFile = JsonUtil.readJsonFile(INVALID_FLASHCARD_FILE,
                JsonSerializableFlashlingo.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateFlashCards_throwsIllegalValueException() throws Exception {
        JsonSerializableFlashlingo dataFromFile = JsonUtil.readJsonFile(DUPLICATE_FLASHCARD_FILE,
                JsonSerializableFlashlingo.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableFlashlingo.MESSAGE_DUPLICATE_FLASHCARD,
                dataFromFile::toModelType);
    }
}
