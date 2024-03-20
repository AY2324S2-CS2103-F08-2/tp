package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT_ID;

import java.util.function.Predicate;

import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;

/**
 * Lists all persons in the address book to the user.
 */
public class ListAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "queryappointments";

    public static final String MESSAGE_SUCCESS = "Listed all appointments";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all appointments that meet the condition. "
            + "Optional Parameters: "
            + PREFIX_APPOINTMENT_ID + "APPOINTMENT_ID "
            + PREFIX_PATIENT_ID + "PATIENT_ID "
            + PREFIX_NAME + "PATIENT_NAME "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_APPOINTMENT_ID + "1"
            + "Or: " + COMMAND_WORD + " "
            + PREFIX_PATIENT_ID + "1"
            + "Or: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe" + " "
            + "Note that you can only use one of the optional parameters at a time.";

    private final Predicate<Appointment> predicates;

    public ListAppointmentCommand(Predicate<Appointment> predicates) {
        this.predicates = predicates;
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.updateFilteredAppointmentList(predicates);

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
