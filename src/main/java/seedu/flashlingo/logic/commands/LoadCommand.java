package seedu.flashlingo.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.flashlingo.logic.Messages.MESSAGE_OPEN_FILE_FAIL;
import static seedu.flashlingo.logic.Messages.MESSAGE_READ_FILE_FAIL;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import seedu.flashlingo.commons.util.ToStringBuilder;
import seedu.flashlingo.logic.commands.exceptions.CommandException;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.flashcard.FlashCard;
import seedu.flashlingo.model.flashcard.ProficiencyLevel;
import seedu.flashlingo.model.flashcard.words.OriginalWord;
import seedu.flashlingo.model.flashcard.words.TranslatedWord;

/**
 * Loads a xlsx file to add flash cards to Flashlingo.
 */
public class LoadCommand extends Command {
    public static final String COMMAND_WORD = "load";
    public static final String MESSAGE_SUCCESS = "You have successfully loaded file: ";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Loads the xlsx file from specified path.\n"
            + "Example: " + COMMAND_WORD + " words.xlsx";
    public static final String MESSAGE_DUPLICATE_FLASHCARD = " flash card already exists!";
    public static final String MESSAGE_EMPTY_WORDS = "Word/translation cannot be empty!";
    public final String fileName;

    public LoadCommand(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Tries to access and open the file.
        FileInputStream fis;
        try {
            fis = new FileInputStream(fileName);
        } catch (Exception e) {
            throw new CommandException(MESSAGE_OPEN_FILE_FAIL);
        }

        // Tries to read the file.
        ArrayList<FlashCard> flashCards;
        try {
            flashCards = readFromExcel(fis);
        } catch (CommandException e) {
            throw e;
        } catch (Exception e) {
            throw new CommandException(MESSAGE_READ_FILE_FAIL);
        }

        addFlashCardsToModel(flashCards, model);

        return new CommandResult(MESSAGE_SUCCESS + fileName, false, false, false);
    }

    private ArrayList<FlashCard> readFromExcel(FileInputStream fis) throws Exception {
        ArrayList<FlashCard> flashCards = new ArrayList<>();

        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            String originalWordValue = row.getCell(0) == null ? "" : row.getCell(0).getStringCellValue();
            String translatedWordValue = row.getCell(1) == null ? "" : row.getCell(1).getStringCellValue();
            ArrayList<String> validWords = trimAndVerifyWords(originalWordValue, translatedWordValue);
            String originalWord = validWords.get(0);
            String translatedWord = validWords.get(1);
            FlashCard flashCard = new FlashCard(new OriginalWord(originalWord, ""),
                    new TranslatedWord(translatedWord, ""), new Date(), new ProficiencyLevel(1));
            flashCards.add(flashCard);
        }

        return flashCards;
    }

    private ArrayList<String> trimAndVerifyWords(String originalWordValue, String translatedWordValue)
            throws CommandException {
        ArrayList<String> validWords = new ArrayList<>();
        originalWordValue = originalWordValue.trim();
        translatedWordValue = translatedWordValue.trim();
        if (originalWordValue.equals("") || translatedWordValue.equals("")) {
            throw new CommandException(MESSAGE_EMPTY_WORDS);
        }
        validWords.add(originalWordValue);
        validWords.add(translatedWordValue);
        return validWords;
    }

    private void addFlashCardsToModel(ArrayList<FlashCard> flashCards, Model model) throws CommandException {
        // Checks for duplicated flash cards with the app.
        for (FlashCard flashCard : flashCards) {
            if (model.hasFlashCard(flashCard)) {
                throw new CommandException(flashCard.getOriginalWord().getWord() + "-"
                        + flashCard.getTranslatedWord().getWord() + MESSAGE_DUPLICATE_FLASHCARD);
            }
        }
        model.addFlashCards(flashCards);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LoadCommand)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }
}
