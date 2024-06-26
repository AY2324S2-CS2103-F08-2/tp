package seedu.address.model.appointment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents an Appointment attached to a Person in PatientList
 */
public class Appointment implements Comparable<Appointment> {
    public static final String MESSAGE_DATETIME_ALREADY_TAKEN = "There is already an appointment at that time";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final HasAttended DEFAULT_ATTENDED_STATUS = new HasAttended(false);

    private static int idTracker = 1;

    public final int appointmentId;

    private final PatientId patientId;

    private final AppointmentDescription appointmentDescription;

    private final FeedbackScore feedbackScore;

    private HasAttended hasAttended;
    private final StartDateTime startDateTime;
    private final EndDateTime endDateTime;

    /**
     * Constructs a {@code Appointment}.
     *
     * @param appointmentId          unique id of the appointment.
     * @param startDateTime          start date and time of the appointment.
     * @param endDateTime            end date and time of the appointment.
     * @param patientId              unique id of the student.
     * @param appointmentDescription description of the appointment.
     * @param hasAttended            whether student has attended the appointment.
     */
    public Appointment(int appointmentId,
                       StartDateTime startDateTime,
                       EndDateTime endDateTime,
                       PatientId patientId,
                       AppointmentDescription appointmentDescription,
                       HasAttended hasAttended,
                       FeedbackScore feedbackScore) {
        requireAllNonNull(startDateTime, endDateTime);
        this.appointmentId = appointmentId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        // Student ID is the same as the person ID
        this.patientId = patientId;
        idTracker = appointmentId + 1;

        this.appointmentDescription = appointmentDescription;
        this.hasAttended = hasAttended;
        this.feedbackScore = feedbackScore;
    }

    /**
     * Constructs a {@code Appointment} with automatically generated id.
     *
     * @param startDateTime          start date and time of the appointment.
     * @param endDateTime            end date and time of the appointment.
     * @param patientId              unique id of the student.
     * @param appointmentDescription description of the appointment.
     */
    public Appointment(StartDateTime startDateTime, EndDateTime endDateTime,
                       PatientId patientId, AppointmentDescription appointmentDescription) {
        this(idTracker, startDateTime, endDateTime, patientId, appointmentDescription, DEFAULT_ATTENDED_STATUS, null);
    }

    /**
     * Constructs a {@code Appointment} with automatically generated id.
     *
     * @param startDateTime          start date and time of the appointment.
     * @param endDateTime            end date and time of the appointment.
     * @param patientId              unique id of the student.
     * @param appointmentDescription description of the appointment.
     * @param hasAttended            whether student has attended the appointment.
     */
    public Appointment(StartDateTime startDateTime, EndDateTime endDateTime, PatientId patientId,
                       AppointmentDescription appointmentDescription, HasAttended hasAttended) {
        this(idTracker, startDateTime, endDateTime, patientId, appointmentDescription, hasAttended, null);
    }

    /**
     * Constructs a {@code Appointment} with automatically generated id.
     *
     * @param startDateTime          start date and time of the appointment.
     * @param endDateTime            end date and time of the appointment.
     * @param patientId              unique id of the student.
     * @param appointmentDescription description of the appointment.
     * @param hasAttended            whether student has attended the appointment.
     * @param feedbackScore          student's rating of the counselling session
     */
    public Appointment(StartDateTime startDateTime,
                       EndDateTime endDateTime, PatientId patientId, AppointmentDescription appointmentDescription,
                       HasAttended hasAttended, FeedbackScore feedbackScore) {

        this(idTracker, startDateTime, endDateTime, patientId, appointmentDescription, hasAttended, feedbackScore);
    }

    public boolean isStartDateTimeAfterEndDateTime() {
        return startDateTime.compareTo(endDateTime) > 0;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("appointmentId", appointmentId)
                .add("startDateTime", startDateTime)
                .add("endDateTime", endDateTime)
                .add("studentId", patientId)
                .add("appointmentDescription", appointmentDescription)
                .add("hasAttended", hasAttended)
                .add("feedbackScore", feedbackScore)
                .toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Appointment)) {
            return false;
        }

        Appointment otherAppointment = (Appointment) other;
        return otherAppointment.appointmentId == this.appointmentId
                && otherAppointment.startDateTime.equals(this.startDateTime)
                && otherAppointment.endDateTime.equals(this.endDateTime)
                && otherAppointment.patientId.equals(this.patientId)
                && otherAppointment.appointmentDescription.equals(this.appointmentDescription)
                && otherAppointment.hasAttended.equals(this.hasAttended)
                && otherAppointment.feedbackScore.equals(this.feedbackScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appointmentId, startDateTime, endDateTime,
                patientId, appointmentDescription, hasAttended);
    }

    public HasAttended getAttendedStatus() {
        return hasAttended;
    }

    public void setAttendedStatus(HasAttended hasAttended) {
        this.hasAttended = hasAttended;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public StartDateTime getStartDateTime() {
        return startDateTime;
    }

    public EndDateTime getEndDateTime() {
        return endDateTime;
    }

    public PatientId getPatientId() {
        return patientId;
    }

    public AppointmentDescription getAppointmentDescription() {
        return appointmentDescription;
    }

    /**
     * This getter method is a bit special is it can return null. It is the job of the
     * caller to check fo null first before using the value.
     * @return A nullable integer
     */
    public FeedbackScore getFeedbackScore() {
        return feedbackScore;
    }

    public static int getIdTracker() {
        return idTracker;
    }

    /**
     * Compares the appointment id of this appointment with another appointment.
     *
     * @param other the other appointment to compare with.
     * @return a negative integer, zero, or a positive integer as this appointment id is less than, equal to,
     *         or greater than the other appointment id.
     */
    @Override
    public int compareTo(Appointment other) {
        return this.appointmentId - other.appointmentId;
    }

    /**
     * Returns true if both appointments have the same studentId and appointmentDateTime.
     * This defines a weaker notion of equality between two appointments.
     */
    public boolean isSameAppointment(Appointment otherAppointment) {
        if (otherAppointment == this) {
            return true;
        }

        return otherAppointment != null
                && this.patientId.equals(otherAppointment.patientId)
                && this.startDateTime.compareTo(otherAppointment.endDateTime) < 0
                && this.endDateTime.compareTo(otherAppointment.startDateTime) > 0;
    }

}
