package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.util.DateUtil;
import seedu.address.model.appointment.Appointment;

/**
 * A UI component that displays information of an {@code Appointment}.
 */
public class AppointmentCard extends UiPart<Region> {

    private static final String FXML = "AppointmentListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on PatientList level 4</a>
     */

    public final Appointment appointment;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label appointmentId;
    @FXML
    private Label appointmentDateTime;
    @FXML
    private Label appointmentDescription;
    @FXML
    private FlowPane details;

    /**
     * Creates a {@code AppointmentCode} with the given {@code Appointment} to display.
     */
    public AppointmentCard(Appointment appointment, String patientName) {
        super(FXML);
        this.appointment = appointment;

        appointmentId.setText(appointment.getAppointmentId() + ". ");
        name.setText(patientName);

        String formattedStartDateTime = DateUtil.formatDateTime(appointment.getStartDateTime().getDateTimeValue());
        String formattedEndDateTime = DateUtil.formatDateTime(appointment.getEndDateTime().getDateTimeValue());
        appointmentDateTime.setText(formattedStartDateTime + " - "
                + formattedEndDateTime);

        if (appointment.getAttendedStatus() != null
                && appointment.getAttendedStatus().hasAttended != null
                && appointment.getAttendedStatus().hasAttended) {
            details.getChildren().add(new Label("Attended"));
        }

        appointmentDescription.managedProperty().bind(appointmentDescription.visibleProperty());
        if (appointment.getAppointmentDescription() != null
                && appointment.getAppointmentDescription().appointmentDescription != null) {
            appointmentDescription.setText(appointment.getAppointmentDescription().appointmentDescription);
        } else {
            appointmentDescription.setVisible(false);
        }

        if (appointment.getFeedbackScore() == null || appointment.getFeedbackScore().feedbackScore == null) {
            details.getChildren().add(new Label("N/A"));
        } else {
            details.getChildren().add(new Label(appointment.getFeedbackScore().toString()));
        }
    }
}
