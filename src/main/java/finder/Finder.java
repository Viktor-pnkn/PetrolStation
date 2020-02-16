package finder;

import transaction.Transaction;

public abstract class Finder<T> {
    public abstract boolean check(Transaction transaction, T... obj);
}
