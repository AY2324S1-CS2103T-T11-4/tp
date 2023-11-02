package flashlingo.storage;

import static flashlingo.testutil.Assert.assertThrows;
import static flashlingo.testutil.TypicalFlashCards.WORD;
import static flashlingo.testutil.TypicalFlashCards.getTypicalFlashlingo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.flashlingo.commons.exceptions.DataLoadingException;
import seedu.flashlingo.model.Flashlingo;
import seedu.flashlingo.model.ReadOnlyFlashlingo;
import seedu.flashlingo.model.flashcard.exceptions.DuplicateFlashCardException;
import seedu.flashlingo.storage.JsonFlashlingoStorage;

public class JsonFlashlingoStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonFlashlingoStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readFlashlingo_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readFlashlingo(null));
    }

    private java.util.Optional<ReadOnlyFlashlingo> readFlashlingo(String filePath) throws Exception {
        return new JsonFlashlingoStorage(Paths.get(filePath)).readFlashlingo(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readFlashlingo("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readFlashlingo("notJsonFormatFlashlingo.json"));
    }

    @Test
    public void readFlashlingo_invalidFlashCardFlashlingo_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readFlashlingo("invalidFlashCardFlashlingo.json"));
    }

    @Test
    public void readFlashlingo_invalidAndValidFlashCardFlashlingo_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readFlashlingo("invalidAndValidFlashCardFlashlingo.json"));
    }

    @Test
    public void readAndSaveFlashlingo_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempFlashlingo.json");
        Flashlingo original = getTypicalFlashlingo();
        JsonFlashlingoStorage jsonFlashlingoStorage = new JsonFlashlingoStorage(filePath);

        // Save in new file and read back
        jsonFlashlingoStorage.saveFlashlingo(original, filePath);
        ReadOnlyFlashlingo readBack = jsonFlashlingoStorage.readFlashlingo(filePath).get();
        assertEquals(original, new Flashlingo(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addFlashCard(WORD);
        original.removeFlashCard(WORD);
        jsonFlashlingoStorage.saveFlashlingo(original, filePath);
        readBack = jsonFlashlingoStorage.readFlashlingo(filePath).get();
        assertEquals(original, new Flashlingo(readBack));

        // Save and read without specifying file path
        original.addFlashCard(WORD);
        jsonFlashlingoStorage.saveFlashlingo(original); // file path not specified
        readBack = jsonFlashlingoStorage.readFlashlingo().get(); // file path not specified
        assertEquals(original, new Flashlingo(readBack));

    }

    @Test
    public void saveFlashlingo_nullFlashlingo_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFlashlingo(null, "SomeFile.json"));
    }

    /**
     * Saves {@code flashlingo} at the specified {@code filePath}.
     */
    private void saveFlashlingo(ReadOnlyFlashlingo flashlingo, String filePath) {
        try {
            new JsonFlashlingoStorage(Paths.get(filePath))
                    .saveFlashlingo(flashlingo, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveFlashlingo_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFlashlingo(new Flashlingo(), null));
    }

    @Test
    public void saveFlashlingo_duplicateFlashCard_throwsDuplicateFlashCardException() {
        try {
            Flashlingo flashlingo = new Flashlingo();
            flashlingo.addFlashCard(WORD);
            flashlingo.addFlashCard(WORD);
            fail();
        } catch (DuplicateFlashCardException e) {
            assertEquals(e.getMessage(), "Operation would result in duplicate flashcards");
        }
    }
}
