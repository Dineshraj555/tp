package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AttendanceCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

public class AttendanceCommandParser implements Parser<AttendanceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AttendanceCommand
     * and returns an AttendanceCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AttendanceCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ATTENDANCE_DATE, PREFIX_PICK_UP, PREFIX_DROP_OFF);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AttendanceCommand.MESSAGE_USAGE), pe);
        }

        AttendanceCommand.PetAttendanceDescriptor petAttendanceDescriptor = new AttendanceCommand.PetAttendanceDescriptor();
        if (argMultimap.getValue(PREFIX_ATTENDANCE_DATE).isPresent()) {
            petAttendanceDescriptor.setAttendanceDate(ParserUtil.parseAttendanceDate(argMultimap.getValue(PREFIX_ATTENDANCE_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_PICK_UP).isPresent()) {
            petAttendanceDescriptor.setPickUpTime(ParserUtil.parsePickUpTime(argMultimap.getValue(PREFIX_PICK_UP).get()));
        }
        if (argMultimap.getValue(PREFIX_DROP_OFF).isPresent()) {
            petAttendanceDescriptor.setDropOffTime(ParserUtil.parseDropOffTime(argMultimap.getValue(PREFIX_DROP_OFF).get()));
        }

        return new AttendanceCommand(index, petAttendanceDescriptor);
    }
}
