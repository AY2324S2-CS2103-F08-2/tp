package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;

/**
 * Represents a DateTimeField in the appointment.
 */
public class DateTimeField implements Comparable<DateTimeField> {
    public static final String MESSAGE_CONSTRAINTS =
            "Date should be in the format of yyyy-MM-dd HH:mm";
    private final LocalDateTime value;

    /**
     * Constructs a {@code DateTimeField}.
     * @param dateTimeValue A valid date
     */
    public DateTimeField(String dateTimeValue) {
        requireNonNull(dateTimeValue);
        checkArgument(isValidDateTimeField(dateTimeValue), MESSAGE_CONSTRAINTS);
        this.value = LocalDateTime.parse(dateTimeValue);
    }

    /**
     * Constructs a {@code DateTimeField}.
     * @param dateTimeValue A valid date
     */
    public DateTimeField(LocalDateTime dateTimeValue) {
        requireNonNull(dateTimeValue);
        this.value = dateTimeValue;
    }

    /**
     * Returns true if a given string is a valid start date.
     */
    public static boolean isValidDateTimeField(String test) {
        try {
            LocalDateTime.parse(test, Appointment.DATE_TIME_FORMATTER);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DateTimeField)) {
            return false;
        }

        DateTimeField otherDateTimeField = (DateTimeField) other;
        return value.equals(otherDateTimeField.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }


    @Override
    public int compareTo(DateTimeField other) {
        return value.compareTo(other.value);
    }

    public LocalDateTime getDateTimeValue() {
        return value;
    }

    public boolean isAfter(DateTimeField other) {
        return this.compareTo(other) > 0;
    }

    public boolean isBefore(DateTimeField other) {
        return this.compareTo(other) < 0;
    }
}
