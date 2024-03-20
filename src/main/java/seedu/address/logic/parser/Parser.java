package seedu.address.logic.parser;

import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;

import java.util.List;


/**
 * Represents a Parser that is able to parse user input into a {@code Command} of type {@code T}.
 */
public interface Parser<T extends Command> {

    /**
     * Parses {@code userInput} into a command and returns it.
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    T parse(String userInput) throws ParseException;

    /**
     * Parses {@code userInput} into a command and returns it. Takes in a list of people to help with querying and
     * parsing validation, such as "no such person exists" errors.
     * Declared as a default method to avoid overwriting methods for other unrelated classes
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    default T parse(String userInput, List<Person> patients) throws ParseException {
        throw new UnsupportedOperationException("This method is not implemented.");
    }
}
