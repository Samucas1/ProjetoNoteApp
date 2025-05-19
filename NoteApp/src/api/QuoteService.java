package api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class QuoteService {

    public static String getRandomQuote() {
        String apiUrl = "https://zenquotes.io/api/random";
        try {
        	URI uri = URI.create(apiUrl);
        	URL url = uri.toURL();
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            JSONArray jsonArr = new JSONArray(content.toString());
            JSONObject obj = jsonArr.getJSONObject(0);
            String quote = obj.getString("q");
            String author = obj.getString("a");

            return "\"" + quote + "\" - " + author;
        } catch (Exception e) {
            return "Erro ao obter citação.";
        }
    }
}