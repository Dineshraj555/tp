package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.SORT_BY_APPOINTMENT;
import static seedu.address.logic.parser.CliSyntax.SORT_BY_NAME;
import static seedu.address.logic.parser.CliSyntax.SORT_BY_OWNER;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        Boolean isValidSortParameter =
                trimmedArgs.equals(SORT_BY_NAME)
                        || trimmedArgs.equals(SORT_BY_OWNER)
                        || trimmedArgs.equals(SORT_BY_APPOINTMENT);
        if (!isValidSortParameter) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
        return new SortCommand(trimmedArgs);
    }
}
