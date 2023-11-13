//@@author
package seedu.flashlingo;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.flashlingo.commons.core.Config;
import seedu.flashlingo.commons.core.LogsCenter;
import seedu.flashlingo.commons.core.Version;
import seedu.flashlingo.commons.exceptions.DataLoadingException;
import seedu.flashlingo.commons.util.ConfigUtil;
import seedu.flashlingo.commons.util.StringUtil;
import seedu.flashlingo.logic.Logic;
import seedu.flashlingo.logic.LogicManager;
import seedu.flashlingo.model.Flashlingo;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.ModelManager;
import seedu.flashlingo.model.ReadOnlyFlashlingo;
import seedu.flashlingo.model.ReadOnlyUserPrefs;
import seedu.flashlingo.model.UserPrefs;
import seedu.flashlingo.model.util.SampleDataUtil;
import seedu.flashlingo.storage.FlashlingoStorage;
import seedu.flashlingo.storage.JsonFlashlingoStorage;
import seedu.flashlingo.storage.JsonUserPrefsStorage;
import seedu.flashlingo.storage.Storage;
import seedu.flashlingo.storage.StorageManager;
import seedu.flashlingo.storage.UserPrefsStorage;
import seedu.flashlingo.ui.Ui;
import seedu.flashlingo.ui.UiManager;

//@@author
/**
 * Runs the application.
 */
public class MainApp extends Application {
    public static final Version VERSION = new Version(0, 2, 2, true);
    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing Flashlingo ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());
        initLogging(config);

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        FlashlingoStorage flashlingoStorage = new JsonFlashlingoStorage(userPrefs.getFlashlingoFilePath());

        storage = new StorageManager(flashlingoStorage, userPrefsStorage);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic, model);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s Flashlingo and {@code userPrefs}. <br>
     * The data from the sample Flashlingo will be used instead if {@code storage}'s Flashlingo is not found,
     * or an empty Flashlingo will be used instead if errors occur when reading {@code storage}'s Flashlingo.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        logger.info("Using data file : " + storage.getFlashlingoFilePath());

        Optional<ReadOnlyFlashlingo> flashlingoOptional;
        ReadOnlyFlashlingo initialData;
        try {
            flashlingoOptional = storage.readFlashlingo();
            if (!flashlingoOptional.isPresent()) {
                logger.info("Creating a new data file " + storage.getFlashlingoFilePath()
                        + " populated with a sample Flashlingo.");
            }
            initialData = flashlingoOptional.orElseGet(SampleDataUtil::getSampleFlashlingo);
        } catch (DataLoadingException e) {
            logger.warning("Data file at " + storage.getFlashlingoFilePath() + " could not be loaded."
                    + " Will be starting with an empty Flashlingo.");
            initialData = new Flashlingo();
        }

        return new ModelManager(initialData, userPrefs);
    }

    private void initLogging(Config config) {
        LogsCenter.init(config);
    }

    /**
     * Returns a {@code Config} using the file at {@code configFilePath}. <br>
     * The default file path {@code Config#DEFAULT_CONFIG_FILE} will be used instead
     * if {@code configFilePath} is null.
     */
    protected Config initConfig(Path configFilePath) {
        Config initializedConfig;
        Path configFilePathUsed;

        configFilePathUsed = Config.DEFAULT_CONFIG_FILE;

        if (configFilePath != null) {
            logger.info("Custom Config file specified " + configFilePath);
            configFilePathUsed = configFilePath;
        }

        logger.info("Using config file : " + configFilePathUsed);

        try {
            Optional<Config> configOptional = ConfigUtil.readConfig(configFilePathUsed);
            if (!configOptional.isPresent()) {
                logger.info("Creating new config file " + configFilePathUsed);
            }
            initializedConfig = configOptional.orElse(new Config());
        } catch (DataLoadingException e) {
            logger.warning("Config file at " + configFilePathUsed + " could not be loaded."
                    + " Using default config properties.");
            initializedConfig = new Config();
        }

        //Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }
        return initializedConfig;
    }

    /**
     * Returns a {@code UserPrefs} using the file at {@code storage}'s user prefs file path,
     * or a new {@code UserPrefs} with default configuration if errors occur when
     * reading from the file.
     */
    protected UserPrefs initPrefs(UserPrefsStorage storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using preference file : " + prefsFilePath);

        UserPrefs initializedPrefs;
        try {
            Optional<UserPrefs> prefsOptional = storage.readUserPrefs();
            if (!prefsOptional.isPresent()) {
                logger.info("Creating a new preference file " + prefsFilePath);
            }
            initializedPrefs = prefsOptional.orElse(new UserPrefs());
        } catch (DataLoadingException e) {
            logger.warning("Preference file at " + prefsFilePath + " could not be loaded."
                    + " Using default preferences now.");
            initializedPrefs = new UserPrefs();
        }

        //Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting Flashlingo " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Flashlingo ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
