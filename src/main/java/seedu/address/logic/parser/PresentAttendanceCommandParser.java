package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.PresentAttendanceCommand.PresentAttendanceDescriptor;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DROPOFF;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PICKUP;

import java.time.LocalDate;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.PresentAttendanceCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new PresentAttendanceCommand object.
 */
public class PresentAttendanceCommandParser implements Parser<PresentAttendanceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PresentAttendanceCommand
     * and returns a PresentAttendanceCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public PresentAttendanceCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_DATE, PREFIX_PICKUP, PREFIX_DROPOFF);

        if (onlyOneTimePresent(argMultimap)) {
            throw new ParseException("meme");
        }

        Index index;
        LocalDate attendanceDate;
        PresentAttendanceDescriptor presentAttendanceDescriptor =
            new PresentAttendanceDescriptor();

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
            attendanceDate = ParserUtil.parseAttendanceDate(argMultimap.getValue(PREFIX_DATE).get());
            presentAttendanceDescriptor.setAttendanceDate(attendanceDate);

            if (argMultimap.getValue(PREFIX_PICKUP).isPresent()) {
                presentAttendanceDescriptor.setPickUpTime(
                    ParserUtil.parsePickUpTime(argMultimap.getValue(PREFIX_PICKUP).get())
                );
            }

            if (argMultimap.getValue(PREFIX_DROPOFF).isPresent()) {
                presentAttendanceDescriptor.setDropOffTime(
                    ParserUtil.parseDropOffTime(argMultimap.getValue(PREFIX_DROPOFF).get())
                );
            }
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                PresentAttendanceCommand.MESSAGE_USAGE), pe);
        }

        return new PresentAttendanceCommand(index, presentAttendanceDescriptor);
    }

    /**
     * Checks to see if only pick up or only drop off time for the command is present.
     *
     * @param argumentMultimap the arguments of the command.
     * @return true if only one of the time arguments are present, false otherwise.
     */
    private boolean onlyOneTimePresent(ArgumentMultimap argumentMultimap) {
        return argumentMultimap.getValue(PREFIX_PICKUP).isPresent()
            ^ argumentMultimap.getValue(PREFIX_DROPOFF).isPresent(); // exclusive OR
    }
}

