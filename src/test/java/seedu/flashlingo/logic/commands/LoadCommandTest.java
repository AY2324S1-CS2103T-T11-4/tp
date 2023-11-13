package seedu.flashlingo.logic.commands;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.ModelManager;
import seedu.flashlingo.testutil.TypicalFlashCards;

public class LoadCommandTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "LoadCommandTest");
    private static final Path INVALID_FORMAT_FILE = TEST_DATA_FOLDER.resolve("invalidFormatFile.xlsx");
    private static final Path INVALID_TYPE_FILE = TEST_DATA_FOLDER.resolve("invalidTypeFile.json");
    private static final Path DUPLICATE_FLASH_CARD_FILE = TEST_DATA_FOLDER.resolve("duplicateFlashCardFile.xlsx");
    private static final Path VALID_FLASH_CARD_FILE = TEST_DATA_FOLDER.resolve("validFlashCardFile.xlsx");
    private static final String MESSAGE_OPEN_FILE_FAIL = "File not found or accessible.";
    private static final String MESSAGE_READ_FILE_FAIL = "File cannot be read due to invalid content or format.";
    private static final String MESSAGE_EMPTY_WORD = "Word/translation cannot be empty!";
    private static final String MESSAGE_DUPLICATE_FLASH_CARD = " flashcard already exists!";
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_invalidFilePath_throwsCommandException() {
        CommandTestUtil.assertCommandFailure(new LoadCommand("invalidFilePath"), model,
                MESSAGE_OPEN_FILE_FAIL);
    }

    @Test
    public void execute_invalidFileType_throwsCommandException() {
        CommandTestUtil.assertCommandFailure(new LoadCommand(INVALID_TYPE_FILE.toString()), model,
                MESSAGE_READ_FILE_FAIL);
    }

    @Test
    public void execute_invalidFileFormat_throwsCommandException() {
        CommandTestUtil.assertCommandFailure(new LoadCommand(INVALID_FORMAT_FILE.toString()), model,
                MESSAGE_EMPTY_WORD);
    }

    @Test
    public void execute_duplicateFlashCard_throwsCommandException() {
        model.addFlashCard(TypicalFlashCards.AMY);
        CommandTestUtil.assertCommandFailure(new LoadCommand(DUPLICATE_FLASH_CARD_FILE.toString()), model,
                TypicalFlashCards.AMY.getOriginalWord().getWord() + "-"
                + TypicalFlashCards.AMY.getTranslatedWord().getWord() + MESSAGE_DUPLICATE_FLASH_CARD);
    }

    @Test
    public void execute_validFlashCardFile_success() {
        expectedModel.addFlashCard(TypicalFlashCards.AMY);
        CommandTestUtil.assertCommandSuccess(new LoadCommand(VALID_FLASH_CARD_FILE.toString()), model,
                String.format(LoadCommand.MESSAGE_SUCCESS + VALID_FLASH_CARD_FILE, 1, 0), expectedModel);
    }
}
