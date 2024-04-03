package seedu.address.model.appointment;

import java.util.function.Predicate;

/**
 * Tests that a {@code Appointment}'s {@code startDateTime} and {@code endDateTime} matches the date times given.
 */
public class FilterAppointmentPredicate implements Predicate<Appointment> {
    private final StartDateTime lowerBoundDateTime;
    private final EndDateTime upperBoundDateTime;

    /**
     * Finds appointment based on start and end date times.
     * @param lowerBoundDateTime target start date time.
     * @param upperBoundDateTime target end date time.
     */
    public FilterAppointmentPredicate(StartDateTime lowerBoundDateTime, EndDateTime upperBoundDateTime) {
        this.lowerBoundDateTime = lowerBoundDateTime;
        this.upperBoundDateTime = upperBoundDateTime;
    }

    private boolean isAppointmentFullyBeforeLowerBound(Appointment appointment) {
        return appointment.getEndDateTime().isBefore(lowerBoundDateTime);
    }

    private boolean isAppointmentFullyAfterUpperBound(Appointment appointment) {
        return appointment.getStartDateTime().isAfter(upperBoundDateTime);
    }

    @Override
    public boolean test(Appointment appointment) {
        // Notice the ! before the whole statement
        return !isAppointmentFullyBeforeLowerBound(appointment)
                && !isAppointmentFullyAfterUpperBound(appointment);
    }
}
