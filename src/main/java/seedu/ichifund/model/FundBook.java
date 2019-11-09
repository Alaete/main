package seedu.ichifund.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.ichifund.model.analytics.Data;
import seedu.ichifund.model.analytics.DataList;
import seedu.ichifund.model.budget.Budget;
import seedu.ichifund.model.budget.UniqueBudgetList;
import seedu.ichifund.model.loan.Loan;
import seedu.ichifund.model.loan.LoanId;
import seedu.ichifund.model.loan.LoanList;
import seedu.ichifund.model.repeater.Repeater;
import seedu.ichifund.model.repeater.RepeaterUniqueId;
import seedu.ichifund.model.repeater.UniqueRepeaterList;
import seedu.ichifund.model.transaction.Transaction;
import seedu.ichifund.model.transaction.TransactionList;

/**
 * Wraps all data at the fund book level
 * Duplicates are not allowed (by .isSameRepeater and .isSameBudget comparison)
 */
public class FundBook implements ReadOnlyFundBook {

    private RepeaterUniqueId currentRepeaterUniqueId;
    private LoanId currentLoanId;
    private final UniqueRepeaterList repeaters;
    private final UniqueBudgetList budgets;
    private final TransactionList transactions;
    private final LoanList loans;
    private final DataList datas;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        currentRepeaterUniqueId = new RepeaterUniqueId("0");
        repeaters = new UniqueRepeaterList();
        budgets = new UniqueBudgetList();
        transactions = new TransactionList();
        datas = new DataList();
        loans = new LoanList();
    }

    public FundBook(){}

    /**
     * Creates an FundBook using the items in the {@code toBeCopied}
     */
    public FundBook(ReadOnlyFundBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the current unique id with {@code uniqueId}.
     */
    public void setCurrentRepeaterUniqueId(RepeaterUniqueId uniqueId) {
        requireNonNull(uniqueId);

        this.currentRepeaterUniqueId = uniqueId;
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the repeater list with {@code repeaters}.
     * {@code repeaters} must not contain duplicate repeaters.
     */
    public void setRepeaters(List<Repeater> repeaters) {
        this.repeaters.setRepeaters(repeaters);
    }

    /**
     * Replaces the contents of the budget list with {@code budgets}.
     * {@code budgets} must not contain duplicate budgets.
     */
    public void setBudgets(List<Budget> budgets) {
        this.budgets.setBudgets(budgets);
    }

    /**
     * Replaces the contents of the transaction list with {@code transactions}.
     */
    public void setTransactions(List<Transaction> transactions) {
        this.transactions.setTransactions(transactions);
    }

    /**
     * Replaces the contents of the data list with {@code data}.
     */
    public void setData(List<Data> data) {
        datas.setData(data);
    }

    /**
     * Resets the existing data of this {@code FundBook} with {@code newData}.
     */
    public void resetData(ReadOnlyFundBook newData) {
        requireNonNull(newData);

        setRepeaters(newData.getRepeaterList());
        setBudgets(newData.getBudgetList());
        setTransactions(newData.getTransactionList());
        setData(newData.getDataList());
    }

    //// transaction-level operations

    /**
     * Adds a transaction to the fund book.
     */
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    /**
     * Replaces the given transaction {@code target} in the list with {@code editedTransaction}.
     * {@code target} must exist in the fund book.
     */
    public void setTransaction(Transaction target, Transaction editedTransaction) {
        requireNonNull(editedTransaction);

        transactions.setTransaction(target, editedTransaction);
    }

    /**
     * Removes transaction {@code key} from this {@code FundBook}.
     * {@code key} must exist in the fund book.
     */
    public void removeTransaction(Transaction key) {
        transactions.remove(key);
    }

    public Optional<Transaction> getLatestTransaction() {
        return transactions.getLatestTransaction();
    }

    //// repeater-level operations

    /**
     * Returns true if a repeater with the same identity as {@code repeater} exists in the fund book.
     */
    public boolean hasRepeater(Repeater repeater) {
        requireNonNull(repeater);
        return repeaters.contains(repeater);
    }

    /**
     * Adds a repeater to the fund book.
     * The repeater must not already exist in the fund book.
     */
    public void addRepeater(Repeater repeater) {
        repeaters.add(repeater);
    }

    /**
     * Replaces the given repeater {@code target} in the list with {@code editedRepeater}.
     * {@code target} must exist in the fund book.
     * The repeater identity of {@code editedRepeater} must not be the same as another existing repeater in the fund
     * book.
     */
    public void setRepeater(Repeater target, Repeater editedRepeater) {
        requireNonNull(editedRepeater);

        repeaters.setRepeater(target, editedRepeater);
    }

    /**
     * Removes repeater {@code key} from this {@code FundBook}.
     * {@code key} must exist in the fund book.
     */
    public void removeRepeater(Repeater key) {
        repeaters.remove(key);
    }

    /// loan-level operations

    /**
     * Checks boolean value for if loan exists in Loans.
     * @param loan
     * @return
     */
    public boolean hasLoan(Loan loan) {
        requireNonNull(loan);
        return loans.contains(loan);
    }

    /**
     * Adds new loan with loan parameters to loans list.
     * @param loan
     */
    public void addLoan(Loan loan) {
        requireNonNull(loan);
        loans.add(loan);
    }

    /**
     * Replaces the current unique id with {@code uniqueId}.
     */
    public void setCurrentLoanId(LoanId loanId) {
        requireNonNull(loanId);
        this.currentLoanId = loanId;
    }

    //// budget-level operations

    /**
     * Returns true if a budget with the same identity as {@code budget} exists in the fund book.
     */
    public boolean hasBudget(Budget budget) {
        requireNonNull(budget);
        return budgets.contains(budget);
    }

    /**
     * Adds a budget to the fund book.
     * The budget must not already exist in the fund book.
     */
    public void addBudget(Budget budget) {
        budgets.add(budget);
    }

    /**
     * Replaces the given budget {@code target} in the list with {@code editedBudget}.
     * {@code target} must exist in the fund book.
     * The budget identity of {@code editedBudget} must not be the same as another existing budget in the fund book.
     */
    public void setBudget(Budget target, Budget editedBudget) {
        requireNonNull(editedBudget);

        budgets.setBudget(target, editedBudget);
    }

    /**
     * Removes budget {@code key} from this {@code FundBook}.
     * {@code key} must exist in the fund book.
     */
    public void removeBudget(Budget key) {
        budgets.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return transactions.asUnmodifiableObservableList().size() + " transactions, "
                + repeaters.asUnmodifiableObservableList().size() + " repeaters, "
                + budgets.asUnmodifiableObservableList().size() + " budgets, "
                + loans.asUnmodifiableObservableList().size() + " loans, "
                + datas.asUnmodifiableObservableList() + " datas";
    }

    @Override
    public RepeaterUniqueId getCurrentRepeaterUniqueId() {
        return currentRepeaterUniqueId;
    }

    @Override
    public LoanId getCurrentLoanId() {
        return currentLoanId;
    }

    @Override
    public ObservableList<Repeater> getRepeaterList() {
        return repeaters.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Budget> getBudgetList() {
        return budgets.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Transaction> getTransactionList() {
        return transactions.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Loan> getLoanList() {
        return loans.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Data> getDataList() {
        return datas.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FundBook // instanceof handles nulls
                && currentRepeaterUniqueId.equals(((FundBook) other).currentRepeaterUniqueId)
                && transactions.equals(((FundBook) other).transactions)
                && repeaters.equals(((FundBook) other).repeaters)
                && budgets.equals(((FundBook) other).budgets)
                && datas.equals(((FundBook) other).datas));
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentRepeaterUniqueId, transactions, repeaters, budgets, datas);
    }
}
