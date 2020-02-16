package station;

import comparators.KuboMetrComparator;
import finder.Finder;
import finder.FuelFinder;
import finder.FuelMoreFinder;
import petrol.Petrol;
import transaction.Transaction;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class PetrolStation {
    private String name;
    private List<Transaction> history;
    private Map<Petrol, Integer> values;
    private double budget;

    public PetrolStation(String name, double budget) {
        this.name = name;
        this.budget = budget;
        this.history = new ArrayList<Transaction>();
        this.values = new HashMap<Petrol, Integer>();
    }

    public static void work(String name, double budget) throws InterruptedException, IOException, ParseException, MyException {
        PetrolStation station = new PetrolStation(name, budget);
        while (true) {
            File dir = new File("C:\\Users\\BOSS\\IdeaProjects\\PetrolStation\\Stations");
            for (File file : dir.listFiles()) {
                Transaction transaction = Transaction.loadData(file);
                if (transaction.getCost() > station.budget) {
                    //System.out.println(station.findFuel(new FuelFinder<>(), "oil", 100));
                    //System.out.println(station.findMoreFuel(new FuelMoreFinder<>(), "oil", "gas"));
                    //System.out.println(station.finalBill());
                    station.sort(new KuboMetrComparator());
                    System.out.println("vdc");
                    for (Transaction transaction1 : station.history) {
                        System.out.println(transaction1.getDate());
                    }
                    throw new MyException();
                }
                station.add(transaction);
                file.delete();
            }
            Thread.sleep(5000);
        }
    }

    private void add(Transaction transaction) {
        this.history.add(transaction);
        for (Map.Entry<Petrol, Integer> entry : transaction.getValues().entrySet()) {
            if (!this.values.containsKey(entry.getKey())) {
                this.values.put(entry.getKey(), entry.getValue());
            } else {
                this.values.put(entry.getKey(), this.values.get(entry.getKey()) + entry.getValue());
            }
        }
        this.budget -= transaction.getCost();
    }

    /**
     * Метод возвращает список тех транзакий, в которых количество кубометров искомого топлива ("oil") больше,
     * чем в заданном значении второго параметра (100).
     */
    private <T> List<Transaction> findFuel(Finder<T> f, T... obj) {
        List<Transaction> list = new ArrayList<Transaction>();
        for (Transaction transaction : this.history) {
            if (f.check(transaction, obj)) {
                list.add(transaction);
            }
        }
        return list;
    }

    /**
     * Метод возвращает список тех транзакий, в которых количество кубометров первого указанного топлива ("oil") больше,
     * чем количество кубометров второго указанного топлива ("gas").
     */
    private <T> List<Transaction> findMoreFuel(Finder<T> f, T... obj) {
        List<Transaction> list = new ArrayList<>();
        for (Transaction transaction : this.history) {
            if (f.check(transaction, obj)) {
                list.add(transaction);
            }
        }
        return list;
    }

    /**
     * Метод считает суммарный чек за все транзакции на станции.
     */
    private double finalBill() {
        double bill = 0;
        for (Transaction transaction : this.history) {
            bill += transaction.getCost();
        }
        return bill;
    }

    private void sort(Comparator f) {
        this.history.sort(f);
    }

    public static class MyException extends Exception {
        public MyException() {
            System.out.println("Station has not got enough money to pay the transaction");

        }
    }
}
