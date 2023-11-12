//@@author itsNatTan
package seedu.flashlingo.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.flashlingo.commons.core.LogsCenter;
import seedu.flashlingo.model.flashcard.FlashCard;
import seedu.flashlingo.session.SessionManager;

/**
 * Panel containing the list of flash cards.
 */
public class FlashcardListPanel extends UiPart<Region> {
    private static final String FXML = "FlashcardListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(FlashcardListPanel.class);

    @FXML
    private ListView<FlashCard> flashcardListView;
    private MainWindow mw;

    /**
     * Creates a {@code FlashCardsListPanel} with the given {@code ObservableList}.
     */
    public FlashcardListPanel(ObservableList<FlashCard> flashcardList, MainWindow mw) {
        super(FXML);
        this.mw = mw;
        flashcardListView.setItems(flashcardList);
        flashcardListView.setCellFactory(listView -> new FlashCardListViewCell());
    }

    public void update() {
        flashcardListView.setCellFactory(listView -> new FlashCardListViewCell());
    }
    /**
     * Custom {@code ListCell} that displays the graphics of a {@code flash card} using a {@code FlashCard}.
     */
    class FlashCardListViewCell extends ListCell<FlashCard> {
        @Override
        protected void updateItem(FlashCard fc, boolean empty) {
            super.updateItem(fc, empty);

            if (empty || fc == null) {
                setGraphic(null);
                setText(null);
            } else {
                if (SessionManager.getInstance().isReviewSession()) {
                    setGraphic(new FlashcardBox(fc, getIndex() + 1, mw).getRoot());
                } else {
                    setGraphic(new FlashcardBoxNoButton(fc, getIndex() + 1, mw).getRoot());
                }
            }
        }
    }

}
