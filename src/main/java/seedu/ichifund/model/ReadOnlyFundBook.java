package seedu.ichifund.model;

import javafx.collections.ObservableList;
import seedu.ichifund.model.analytics.Data;
import seedu.ichifund.model.budget.Budget;
import seedu.ichifund.model.person.Person;
import seedu.ichifund.model.repeater.Repeater;
import seedu.ichifund.model.repeater.RepeaterUniqueId;
import seedu.ichifund.model.transaction.Transaction;

/**
 * Unmodifiable view of an fund book
 */
public interface ReadOnlyFundBook {

    /**
     * Returns an unmodifiable view of the current repeater unique id.
     */
    RepeaterUniqueId getCurrentRepeaterUniqueId();

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the repeaters list.
     * This list will not contain any duplicate repeaters.
     */
    ObservableList<Repeater> getRepeaterList();

    /**
     * Returns an unmodifiable view of the budgets list.
     * This list will not contain any duplicate budgets.
     */
    ObservableList<Budget> getBudgetList();

    /**
     * Returns an unmodifiable view of the transactions list.
     */
    ObservableList<Transaction> getTransactionList();

    /**
     * Returns an unmodifiable view of the data list.
     */
    ObservableList<Data> getDataList();

}
