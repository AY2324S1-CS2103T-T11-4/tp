//@@author A1WAYSD-reused
//Reused from AB-3 StorageManager.java with minor modifications
package seedu.flashlingo.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.flashlingo.commons.core.LogsCenter;
import seedu.flashlingo.commons.exceptions.DataLoadingException;
import seedu.flashlingo.model.ReadOnlyFlashlingo;
import seedu.flashlingo.model.ReadOnlyUserPrefs;
import seedu.flashlingo.model.UserPrefs;

/**
 * Manages storage of Flashlingo data in local storage.
 */
public class StorageManager implements Storage {
    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private FlashlingoStorage flashlingoStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code FlashlingoStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(FlashlingoStorage flashlingoStorage, UserPrefsStorage userPrefsStorage) {
        this.flashlingoStorage = flashlingoStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }

    // ================ Flashlingo methods ==============================

    @Override
    public Path getFlashlingoFilePath() {
        return flashlingoStorage.getFlashlingoFilePath();
    }

    @Override
    public Optional<ReadOnlyFlashlingo> readFlashlingo() throws DataLoadingException {
        return readFlashlingo(flashlingoStorage.getFlashlingoFilePath());
    }

    @Override
    public Optional<ReadOnlyFlashlingo> readFlashlingo(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return flashlingoStorage.readFlashlingo(filePath);
    }

    @Override
    public void saveFlashlingo(ReadOnlyFlashlingo flashlingo) throws IOException {
        saveFlashlingo(flashlingo, flashlingoStorage.getFlashlingoFilePath());
    }

    @Override
    public void saveFlashlingo(ReadOnlyFlashlingo flashlingo, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        flashlingoStorage.saveFlashlingo(flashlingo, filePath);
    }

}
