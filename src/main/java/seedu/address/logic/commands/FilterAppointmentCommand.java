package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.parser.CliSyntax;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.EndDateTime;
import seedu.address.model.appointment.StartDateTime;


/**
 * Filter the appointment in the CogniCare appointment list which happen on the specified datetimes.
 */
public class FilterAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "filterappointment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filter the appointment which happens "
            + "between one datetime and another datetime. Parameters: "
            + CliSyntax.PREFIX_START_DATETIME + "START_DATETIME "
            + CliSyntax.PREFIX_END_DATETIME + "END_DATETIME\n"
            + "Example: " + COMMAND_WORD + " "
            + CliSyntax.PREFIX_START_DATETIME + "2024-03-18 09:00 "
            + CliSyntax.PREFIX_END_DATETIME + "2024-03-18 18:00";

    private final Predicate<Appointment> predicate;
    private final StartDateTime startDateTime;
    private final EndDateTime endDateTime;

    /**
     * Creates a FilterAppointmentCommand to filter the appointment list with the specified predicate.
     */
    public FilterAppointmentCommand(Predicate<Appointment> predicate,
                                    StartDateTime startDateTime, EndDateTime endDateTime) {
        this.predicate = predicate;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public CommandResult execute(Model model) {
        requireAllNonNull(model, startDateTime, endDateTime);
        // Check if startDateTime is before endDateTime
        if (startDateTime.compareTo(endDateTime) > 0) {
            return new CommandResult(Messages.MESSAGE_INVALID_START_END_DATETIME);
        }
        model.updateFilteredAppointmentList(predicate);
        return new CommandResult(String.format(Messages.MESSAGE_APPOINTMENTS_LISTED_OVERVIEW,
                model.getFilteredAppointmentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterAppointmentCommand)) {
            return false;
        }

        FilterAppointmentCommand otherAppointmentFindCommand = (FilterAppointmentCommand) other;
        return predicate.equals(otherAppointmentFindCommand.predicate)
                && startDateTime.equals(otherAppointmentFindCommand.startDateTime)
                && endDateTime.equals(otherAppointmentFindCommand.endDateTime);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
