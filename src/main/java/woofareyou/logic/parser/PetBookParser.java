package woofareyou.logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import woofareyou.commons.core.Messages;
import woofareyou.logic.commands.AbsentAttendanceCommand;
import woofareyou.logic.commands.AddCommand;
import woofareyou.logic.commands.AppointmentCommand;
import woofareyou.logic.commands.ChargeCommand;
import woofareyou.logic.commands.ClearCommand;
import woofareyou.logic.commands.Command;
import woofareyou.logic.commands.DeleteCommand;
import woofareyou.logic.commands.DietCommand;
import woofareyou.logic.commands.EditCommand;
import woofareyou.logic.commands.ExitCommand;
import woofareyou.logic.commands.FilterCommand;
import woofareyou.logic.commands.FindCommand;
import woofareyou.logic.commands.HelpCommand;
import woofareyou.logic.commands.ListCommand;
import woofareyou.logic.commands.PresentAttendanceCommand;
import woofareyou.logic.commands.SortCommand;
import woofareyou.logic.commands.UndoCommand;
import woofareyou.logic.parser.exceptions.ParseException;


/**
 * Parses user input.
 */
public class PetBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {
        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case DietCommand.COMMAND_WORD:
            return new DietCommandParser().parse(arguments);

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        case AppointmentCommand.COMMAND_WORD:
            return new AppointmentCommandParser().parse(arguments);

        case PresentAttendanceCommand.COMMAND_WORD:
            return new PresentAttendanceCommandParser().parse(arguments);

        case AbsentAttendanceCommand.COMMAND_WORD:
            return new AbsentAttendanceCommandParser().parse(arguments);

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        case ChargeCommand.COMMAND_WORD:
            return new ChargeCommandParser().parse(arguments);

        case FilterCommand.COMMAND_WORD:
            return new FilterCommandParser().parse(arguments);

        default:
            throw new ParseException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
