package seedu.address.model.appointment;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Appointment}'s patientId matches any of the keywords given.
 */
public class AppointmentContainsPatientIdPredicate implements Predicate<Appointment> {
    private final List<String> keywords;

    public AppointmentContainsPatientIdPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Appointment appointment) {
        // We check for equality here instead of containsIn because otherwise, student 1 would match with 100
        return keywords.stream()
                .anyMatch(keyword -> String.valueOf(appointment.getStudentId()).equals(keyword)
                );
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AppointmentContainsPatientIdPredicate)) {
            return false;
        }

        AppointmentContainsPatientIdPredicate otherNameContainsKeywordsPredicate =
                (AppointmentContainsPatientIdPredicate) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
