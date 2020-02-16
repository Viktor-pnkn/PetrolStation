package finder;

import petrol.Petrol;
import transaction.Transaction;

import java.util.Map;

public class FuelMoreFinder<T> extends Finder<String> {
    public boolean check (Transaction transaction, String... str) {
        int x = 0, y = 0;
        for (Map.Entry<Petrol, Integer> entry : transaction.getValues().entrySet()) {
            if (entry.getKey().getType().equals(str[0])) {
                x = entry.getValue();
            }
            if (entry.getKey().getType().equals(str[1])) {
                y = entry.getValue();
            }
        }
        return x > y;
    }
}
