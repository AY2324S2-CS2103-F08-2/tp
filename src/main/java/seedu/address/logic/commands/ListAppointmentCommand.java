package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;

import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Lists all persons in the address book to the user.
 */
public class ListAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "queryappointments";

    public static final String MESSAGE_SUCCESS = "Listed all appointments";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all appointments that meet the condition. "
            + "Optional Parameters: "
            + PREFIX_STUDENT_ID + "PATIENT_ID "
            + PREFIX_NAME + "PATIENT_NAME "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STUDENT_ID + "1"
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
