package api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class QuoteService {
    public String getRandomQuote() {
        try {
            @SuppressWarnings("deprecation")
			URL url = new URL("https://api.quotable.io/random"); //Código original (desativado por falha de rede na máquina):
          //Motivo: bloqueio de rede acadêmica. Versão offline ativada provisoriamente para testes.
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Pega só a parte do conteúdo da citação
            String json = response.toString();
            int start = json.indexOf("\"content\":\"") + 11;
            int end = json.indexOf("\"", start);
            return json.substring(start, end);

        } catch (Exception e) {
            e.printStackTrace(); // isso mostra o erro no console
            return "Seja sua melhor versão."; // fallback
        }
    }
}


