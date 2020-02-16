package finder;

import petrol.Petrol;
import transaction.Transaction;

import java.util.Map;

public class FuelFinder<T> extends Finder<T> {
    public boolean check(Transaction transaction, T... obj) {
        for (Map.Entry<Petrol, Integer> entry : transaction.getValues().entrySet()) {
            if (entry.getKey().getType().equals(obj[0]) && entry.getValue() >= (Integer) obj[1]) {
                return true;
            }
        }
        return false;
    }
}
