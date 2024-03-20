package seedu.address.model.appointment;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;


/**
 * Tests that a {@code Name} matches the {@code Person} that the appointment is associated with
 */
public class AppointmentContainsPatientNamePredicate implements Predicate<Appointment> {
    private final List<String> keywords;
    private final List<Person> patients;

    /**
     * Constructor for AppointmentContainsPatientNamePredicate
     * @param keywords
     * @param patients The current observable list of patients
     */
    public AppointmentContainsPatientNamePredicate(List<String> keywords, List<Person> patients) {
        this.keywords = keywords;
        this.patients = patients;
    }

    @Override
    public boolean test(Appointment appointment) {
        // 1. Map names to patient IDs
        // 2. Check if the appointment's patient name matches any of the keywords
        for (String keyword: keywords) {
            List<Integer> patientIdsMatched = patients.stream()
                    .filter(patient -> StringUtil.containsStringIgnoreCase(patient.getName().fullName, keyword))
                    .map(patient -> patient.getSid())
                    .collect(Collectors.toList());
            for (int patientId: patientIdsMatched) {
                if (appointment.getStudentId() == patientId) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AppointmentContainsPatientNamePredicate)) {
            return false;
        }

        AppointmentContainsPatientNamePredicate otherNameContainsKeywordsPredicate =
                (AppointmentContainsPatientNamePredicate) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
