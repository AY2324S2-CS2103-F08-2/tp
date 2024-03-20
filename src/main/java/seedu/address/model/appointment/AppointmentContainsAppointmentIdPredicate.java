package seedu.address.model.appointment;

import seedu.address.commons.util.ToStringBuilder;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Appointment}'s id matches any of the keywords given.
 */
public class AppointmentContainsAppointmentIdPredicate implements Predicate<Appointment> {
    private final List<String> keywords;

    public AppointmentContainsAppointmentIdPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Appointment appointment) {
        // We check for equality here instead of containsIn because otherwise, student 1 would match with 100
        return keywords.stream()
                .anyMatch(keyword -> String.valueOf(appointment.getAppointmentId()).equals(keyword)
                );
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AppointmentContainsAppointmentIdPredicate)) {
            return false;
        }

        AppointmentContainsAppointmentIdPredicate otherNameContainsKeywordsPredicate =
                (AppointmentContainsAppointmentIdPredicate) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
