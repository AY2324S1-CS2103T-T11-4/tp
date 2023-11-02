package flashlingo.logic.commands;

import static flashlingo.logic.commands.CommandTestUtil.assertCommandFailure;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.flashlingo.logic.commands.LoadCommand;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.ModelManager;

public class LoadCommandTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "LoadCommandTest");
    private static final String MESSAGE_OPEN_FILE_FAIL = "File not found or accessible.";
    private static final String MESSAGE_READ_FILE_FAIL = "File cannot be read due to incorrect format.";
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    private Path addToTestDataPathIfNotNull(String dataFileInTestDataFolder) {
        return dataFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(dataFileInTestDataFolder)
                : null;
    }

    @Test
    public void execute_invalidFileName_throwsCommandException() {
        assertCommandFailure(new LoadCommand("invalidFile.json"), model,
                MESSAGE_OPEN_FILE_FAIL);
    }

}
