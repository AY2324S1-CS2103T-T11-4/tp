//@@author A1WAYSD-reused
//Reused from AB-3 JsonAddressBookStorage.java with minor modifications
package seedu.flashlingo.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.flashlingo.commons.core.LogsCenter;
import seedu.flashlingo.commons.exceptions.DataLoadingException;
import seedu.flashlingo.commons.exceptions.IllegalValueException;
import seedu.flashlingo.commons.util.FileUtil;
import seedu.flashlingo.commons.util.JsonUtil;
import seedu.flashlingo.model.ReadOnlyFlashlingo;

/**
 * Represents a storage for {@link seedu.flashlingo.model.Flashlingo}.
 */
public class JsonFlashlingoStorage implements FlashlingoStorage {
    private static final Logger logger = LogsCenter.getLogger(JsonFlashlingoStorage.class);
    private Path filePath;

    public JsonFlashlingoStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getFlashlingoFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyFlashlingo> readFlashlingo() throws DataLoadingException {
        return readFlashlingo(filePath);
    }

    /**
     * Functions similarly to {@link #readFlashlingo()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyFlashlingo> readFlashlingo(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableFlashlingo> jsonFlashlingo = JsonUtil.readJsonFile(
            filePath, JsonSerializableFlashlingo.class);
        if (!jsonFlashlingo.isPresent()) {
            return Optional.empty();
        }

        try {
            ReadOnlyFlashlingo flashlingo = jsonFlashlingo.get().toModelType();
            return Optional.of(flashlingo);
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveFlashlingo(ReadOnlyFlashlingo flashlingo) throws IOException {
        saveFlashlingo(flashlingo, filePath);
    }

    /**
     * Functions similarly to {@link #saveFlashlingo(ReadOnlyFlashlingo)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveFlashlingo(ReadOnlyFlashlingo flashlingo, Path filePath) throws IOException {
        requireNonNull(flashlingo);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableFlashlingo(flashlingo), filePath);
    }

}
