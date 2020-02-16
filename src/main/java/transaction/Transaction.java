package transaction;

import converter.Converter;
import petrol.Petrol;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Transaction {
    private String number; // генерируется с помощью метода numberGeneration
    private Date date;
    private Map<Petrol, Integer> values;

    private Transaction(Date date) {
        this.date = date;
        this.values = new HashMap<Petrol, Integer>();
        this.number = numberGeneration();
    }

    public Map<Petrol, Integer> getValues() {
        return values;
    }

    private void add(Petrol p, Integer a) {
        if (!this.values.containsKey(p)) {
            this.values.put(p, a);
        } else {
            this.values.put(p, this.values.get(p) + a);
        }
    }

    public static Transaction loadData(File file) throws IOException, ParseException {
        BufferedReader bf = new BufferedReader(new FileReader(file));
        String date = bf.readLine();
        Transaction transaction = new Transaction(new SimpleDateFormat("dd.MM.yyyy").parse(date));
        while (bf.ready()) {
            String s = bf.readLine();
            String[] str = s.split(" ");
            Petrol p = new Petrol(str[0], Converter.simple(str[1]), "₽");
            transaction.add(p, Integer.valueOf(str[2]));
        }
        bf.close();
        return transaction;
    }

    public double getCost() {
        double cost = 0;
        for (Map.Entry<Petrol, Integer> entry : this.getValues().entrySet()) {
            cost += entry.getKey().getPrice();
        }
        return cost;
    }

    public Date getDate() {
        return date;
    }

    private String numberGeneration() {
        Random random = new Random();
        return String.valueOf(random.nextInt(100000)) + '-' + String.valueOf(random.nextInt(100000));
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "number='" + number + '\'' +
                ", date=" + new SimpleDateFormat("dd.MM.yyyy").format(date) +
                ", values=" + values +
                '}';
    }
}
