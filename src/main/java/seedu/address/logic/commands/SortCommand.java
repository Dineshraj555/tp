package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_SUCCESS = "Sorted all pets!";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the pet list by the input field specified.\n"
            + "Note the input fields are case sensitive.\n"
            + "The following are the 5 valid input fields:\n"
            + "1. owner\n 2. name\n 3. app\n 4. pick up\n 5. drop off\n"
            + "Parameters: FIELD_TO_SORT_BY\n"
            + "Example: " + COMMAND_WORD + "owner";

    private final String field;

    /**
     * @param field first letter of the column to sort the pet list by
     */
    public SortCommand(String field) {
        this.field = field;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortPetList(field);
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
