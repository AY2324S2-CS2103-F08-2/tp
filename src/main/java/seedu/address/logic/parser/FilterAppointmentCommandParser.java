package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATETIME;

import seedu.address.logic.commands.FilterAppointmentCommand;
import seedu.address.logic.commands.ReportFeedbackCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.EndDateTime;
import seedu.address.model.appointment.FilterAppointmentPredicate;
import seedu.address.model.appointment.StartDateTime;

import java.time.LocalDateTime;

/**
 * Parses input arguments and creates a new FindAppointmentCommand object
 */
public class FilterAppointmentCommandParser implements Parser<FilterAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindAppointmentCommand
     * and returns a FindAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterAppointmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_START_DATETIME, PREFIX_END_DATETIME);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterAppointmentCommand.MESSAGE_USAGE));
        }


        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_START_DATETIME, PREFIX_END_DATETIME);
        StartDateTime startDateTime;
        EndDateTime endDateTime;
        if (argMultimap.getValue(PREFIX_START_DATETIME).isEmpty()) {
            startDateTime = new StartDateTime(LocalDateTime.MIN);
        } else {
            startDateTime = ParserUtil.parseStartDateTime(argMultimap.getValue(PREFIX_START_DATETIME).get());
        }
        if (argMultimap.getValue(PREFIX_END_DATETIME).isEmpty()) {
            endDateTime = new EndDateTime(LocalDateTime.MAX);
        } else {
            endDateTime = ParserUtil.parseEndDateTime(argMultimap.getValue(PREFIX_END_DATETIME).get());
        }
        
        return new FilterAppointmentCommand(new FilterAppointmentPredicate(startDateTime, endDateTime),
                startDateTime, endDateTime);
    }

}
