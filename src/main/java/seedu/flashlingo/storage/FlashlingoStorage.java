//@@author A1WAYSD-reused
//Reused from AB-3 AddressBookStorage.java with minor modifications
package seedu.flashlingo.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.flashlingo.commons.exceptions.DataLoadingException;
import seedu.flashlingo.model.Flashlingo;
import seedu.flashlingo.model.ReadOnlyFlashlingo;

/**
 * Represents a storage for {@link Flashlingo}.
 */
public interface FlashlingoStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getFlashlingoFilePath();

    /**
     * Returns Flashlingo data as a {@link ReadOnlyFlashlingo}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyFlashlingo> readFlashlingo() throws DataLoadingException;

    /**
     * @see #getFlashlingoFilePath()
     */
    Optional<ReadOnlyFlashlingo> readFlashlingo(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyFlashlingo} to the storage.
     * @param flashlingo cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveFlashlingo(ReadOnlyFlashlingo flashlingo) throws IOException;

    /**
     * @see #saveFlashlingo(ReadOnlyFlashlingo)
     */
    void saveFlashlingo(ReadOnlyFlashlingo flashlingo, Path filePath) throws IOException;

}
