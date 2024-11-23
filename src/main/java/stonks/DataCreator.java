package main.java.stonks;

import java.io.BufferedWriter; 
import java.io.File;
import java.io.FileWriter; 
import java.io.IOException;
import javax.imageio.ImageIO;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;


public class DataCreator {
	private static final String API_KEY = "T2C3CE9HNOIRB9SG";
	
	public static void saveData() {
		try {
			String[] tickers = DataReader.readData();			
			
			for (int i = 0; i < tickers.length; i++) {
				String filename = "src/data/" + tickers[i] + ".json";
				String url = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&outputsize=full&symbol=";
				url = url + tickers[i];
				url = url + "&apikey=" + API_KEY;
				URI uri = new URI(url);
				HttpClient client = HttpClient.newHttpClient();
				HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();
				HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
				JSONObject json = new JSONObject(response.body());
				
				try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
		            writer.write(json.toString(4));
		            System.out.println("Data has been written to the file.");
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
			}
			
		
		} catch (Exception e) {
			
		}
	}
	
}

