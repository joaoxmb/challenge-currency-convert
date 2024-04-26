import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Api {
    static String url = "https://v6.exchangerate-api.com/v6/2df7a50257c7ddb5ce0fb4da/pair/";

    public static double calc(String from, String to, double amount) {
        try {
            Gson gson = new Gson();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url + "/" + from + "/" + to + "/" + amount))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            CalcResponse data = gson.fromJson(response.body(), CalcResponse.class);

            if (!data.result.equals("success")) {
                return 0;
            }

            return data.conversion_result;

        } catch (IOException | InterruptedException e) {
            System.out.println("Aconteceu um erro ao executar: " + e.getMessage());
            return 0;
        }
    }

    static class CalcResponse {
        String result;
        double conversion_result;
    }
}
