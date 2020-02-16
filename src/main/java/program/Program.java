package program;

import station.PetrolStation;
import transaction.Transaction;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public class Program {
    public static void main(String[] args) throws InterruptedException, ParseException, IOException, PetrolStation.MyException {
        PetrolStation.work("gbf", 100000);
    }
}
