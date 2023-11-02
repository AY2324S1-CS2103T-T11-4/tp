package flashlingo.storage;

import static flashlingo.testutil.TypicalFlashCards.getTypicalFlashlingo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.flashlingo.commons.core.GuiSettings;
import seedu.flashlingo.model.Flashlingo;
import seedu.flashlingo.model.ReadOnlyFlashlingo;
import seedu.flashlingo.model.UserPrefs;
import seedu.flashlingo.storage.JsonFlashlingoStorage;
import seedu.flashlingo.storage.JsonUserPrefsStorage;
import seedu.flashlingo.storage.StorageManager;

public class StorageManagerTest {
    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonFlashlingoStorage flashlingoStorage = new JsonFlashlingoStorage(getTempFilePath("fl"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(flashlingoStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void flashlingoReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonFlashlingoStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonFlashlingoStorageTest} class.
         */
        Flashlingo original = getTypicalFlashlingo();
        storageManager.saveFlashlingo(original);
        ReadOnlyFlashlingo retrieved = storageManager.readFlashlingo().get();
        assertEquals(original, new Flashlingo(retrieved));
    }

    @Test
    public void getFlashlingoFilePath() {
        assertNotNull(storageManager.getFlashlingoFilePath());
    }
}
