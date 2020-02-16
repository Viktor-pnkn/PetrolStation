package comparators;

import transaction.Transaction;

import java.util.Comparator;

public interface MyComparator extends Comparator<Transaction> {
    int compare(Transaction o1, Transaction o2);
}
