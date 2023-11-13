package seedu.flashlingo.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.flashlingo.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {
    private GuiSettings guiSettings = new GuiSettings();
    private Path flashlingoFilePath = Paths.get("data" , "flashlingo.json");
    private String theme = "Default";

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setFlashlingoFilePath(newUserPrefs.getFlashlingoFilePath());
        setTheme(newUserPrefs.getTheme());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public void setTheme(String theme) {
        requireNonNull(theme);
        this.theme = theme;
    }

    public String getTheme() {
        return theme;
    }

    public Path getFlashlingoFilePath() {
        return flashlingoFilePath;
    }

    public void setFlashlingoFilePath(Path flashlingoFilePath) {
        requireNonNull(flashlingoFilePath);
        this.flashlingoFilePath = flashlingoFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UserPrefs)) {
            return false;
        }

        UserPrefs otherUserPrefs = (UserPrefs) other;
        return guiSettings.equals(otherUserPrefs.guiSettings)
                && flashlingoFilePath.equals(otherUserPrefs.flashlingoFilePath)
                && theme.equals(otherUserPrefs.theme);

    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, flashlingoFilePath, theme);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + flashlingoFilePath);
        sb.append("\nTheme : " + theme);
        return sb.toString();
    }

}
