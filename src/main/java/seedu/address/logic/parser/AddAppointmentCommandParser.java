package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTEND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;

import java.time.LocalDateTime;

import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;

/**
 * Parses input arguments and creates a new AddAppointmentCommand object
 */
public class AddAppointmentCommandParser implements Parser<AddAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddAppointmentCommand
     * and returns an AddAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddAppointmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STUDENT_ID, PREFIX_DATETIME,
                        PREFIX_ATTEND, PREFIX_APPOINTMENT_DESCRIPTION);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_STUDENT_ID, PREFIX_DATETIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppointmentCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(
                PREFIX_STUDENT_ID, PREFIX_DATETIME, PREFIX_ATTEND, PREFIX_APPOINTMENT_DESCRIPTION);
        int studentId = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_STUDENT_ID).get()).getOneBased();
        LocalDateTime appointmentDateTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_DATETIME).get());
        boolean hasAttended = ParserUtil.parseHasAttended(argMultimap.getAllValues(PREFIX_ATTEND));
        //TODO: remove after case log is implemented
        String appointmentDescription = ParserUtil.parseDescription(
                argMultimap.getAllValues(PREFIX_APPOINTMENT_DESCRIPTION));

        Appointment appointment = new Appointment(appointmentDateTime, studentId, appointmentDescription, hasAttended);
        return new AddAppointmentCommand(appointment);
    }

}