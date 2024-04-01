package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.patientfeedbackreport.PatientFeedbackReport;

/**
 * An UI component that displays information of a {@code PatientFeedbackReport}.
 */
public class PatientFeedbackReportCard extends UiPart<Region> {

    private static final String FXML = "PatientFeedbackReportCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on PatientList level 4</a>
     */

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label avgFeedbackScore;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PatientFeedbackReportCard(PatientFeedbackReport pfr) {
        super(FXML);
        id.setText(pfr.getPatientId() + ". ");
        name.setText("Name: " + pfr.getPatientName().fullName);
        avgFeedbackScore.managedProperty().bind(avgFeedbackScore.visibleProperty());
        if (pfr.getAvgFeedbackScore() != null) {
            avgFeedbackScore.setText("Avg Score: " + pfr.getAvgFeedbackScore().toString());
        } else {
            avgFeedbackScore.setVisible(false);
        }
    }
}
