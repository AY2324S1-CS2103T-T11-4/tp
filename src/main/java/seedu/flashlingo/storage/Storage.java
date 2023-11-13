//@@author A1WAYSD-reused
//Reused from AB-3 Storage.java with minor modifications
package seedu.flashlingo.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.flashlingo.commons.exceptions.DataLoadingException;
import seedu.flashlingo.model.ReadOnlyFlashlingo;
import seedu.flashlingo.model.ReadOnlyUserPrefs;
import seedu.flashlingo.model.UserPrefs;

/**
 * API of the Storage component.
 */
public interface Storage extends FlashlingoStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataLoadingException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getFlashlingoFilePath();

    @Override
    Optional<ReadOnlyFlashlingo> readFlashlingo() throws DataLoadingException;

    @Override
    void saveFlashlingo(ReadOnlyFlashlingo flashlingo) throws IOException;

}
