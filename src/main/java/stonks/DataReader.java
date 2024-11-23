package main.java.stonks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
    public static String[] readData() {
        String fileName = "src/main/java/stonks/tickers.dat";
        List<String> tickers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                tickers.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert List to Array
        String[] tickerArray = tickers.toArray(new String[0]);

        // Print the tickers
        System.out.println(tickerArray.length);
        
        return tickerArray;
    }
}
