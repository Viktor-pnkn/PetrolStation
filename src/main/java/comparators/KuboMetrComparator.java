package comparators;

import petrol.Petrol;
import transaction.Transaction;

import java.util.Map;

public class KuboMetrComparator implements MyComparator {
    @Override
    public int compare(Transaction o1, Transaction o2) {
        int x = 0, y = 0;
        for (Map.Entry<Petrol, Integer> entry : o1.getValues().entrySet()) {
            x += entry.getValue();
        }
        for (Map.Entry<Petrol, Integer> entry : o2.getValues().entrySet()) {
            y += entry.getValue();
        }
        return Integer.compare(x, y);
    }
}
