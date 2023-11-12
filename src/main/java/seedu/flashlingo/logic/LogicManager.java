package seedu.flashlingo.logic;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.flashlingo.commons.core.GuiSettings;
import seedu.flashlingo.commons.core.LogsCenter;
import seedu.flashlingo.logic.commands.Command;
import seedu.flashlingo.logic.commands.CommandResult;
import seedu.flashlingo.logic.commands.exceptions.CommandException;
import seedu.flashlingo.logic.parser.FlashlingoParser;
import seedu.flashlingo.logic.parser.exceptions.ParseException;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.ReadOnlyFlashlingo;
import seedu.flashlingo.model.flashcard.FlashCard;
import seedu.flashlingo.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_FORMAT = "Could not save data due to the following error: %s";

    public static final String FILE_OPS_PERMISSION_ERROR_FORMAT =
            "Could not save data to file %s due to insufficient permissions to write to the file or the folder.";

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final FlashlingoParser flashlingoParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        flashlingoParser = new FlashlingoParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        Command command = flashlingoParser.parseCommand(commandText);
        CommandResult commandResult = command.execute(model);

        try {
            storage.saveFlashlingo(model.getFlashlingo());
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (AccessDeniedException e) {
            throw new CommandException(String.format(FILE_OPS_PERMISSION_ERROR_FORMAT, e.getMessage()), e);
        } catch (IOException ioe) {
            throw new CommandException(String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyFlashlingo getFlashlingo() {
        return model.getFlashlingo();
    }

    @Override
    public ObservableList<FlashCard> getFilteredFlashCardList() {
        return model.getFilteredFlashCardList();
    }

    @Override
    public Path getFlashlingoFilePath() {
        return model.getFlashlingoFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public String getTheme() {
        return model.getTheme();
    }
}
