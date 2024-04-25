import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        Gson gson = new Gson();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://v6.exchangerate-api.com/v6/2df7a50257c7ddb5ce0fb4da/latest/BRL"))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        CurrencyResponse result = gson.fromJson(json, CurrencyResponse.class);

        UserOptions selectedOptions = ask();
        calc(new double[]{
                result.conversion_rates.USD,
                result.conversion_rates.EUR,
                result.conversion_rates.JPY
        }, selectedOptions);
    }

    public static void calc(double[] rates, UserOptions options) {
        double calculation = rates[options.secondaryCurrency] * options.amount;
        System.out.println(calculation);
    }

    public static UserOptions ask() {
        Scanner askCurrency = new Scanner(System.in);
        UserOptions selectedOptions = new UserOptions();
        String[] options = {"Real", "Dolar", "Iene"};

        // Primeira pergunta
        System.out.println("""
                        -----------------------
                        Olá, escreva o número da opção:
                        """);

        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ") " + options[i] + " para outras moedas");
        }

        System.out.println("-----------------------");
        selectedOptions.firstCurrency = (askCurrency.nextInt() - 1);

        // Segunda pergunta
        System.out.printf("""
                        -----------------------
                        Selecione para qual moeda deseja converter o seu %s:
                        """, options[selectedOptions.firstCurrency]);

        for (int i = 0; i < options.length; i++) {
            if (i != selectedOptions.firstCurrency) {
                System.out.println((i + 1) + ") " + options[i]);
            }
        }

        System.out.println("-----------------------");
        selectedOptions.secondaryCurrency = askCurrency.nextInt();

        // Pergunta do valor
        System.out.printf("Digite a quantidade de %s: %n", (options[selectedOptions.firstCurrency]));
        selectedOptions.amount = askCurrency.nextInt();

        return selectedOptions;
    }

    static class UserOptions {
        int firstCurrency;
        int secondaryCurrency;
        double amount;
    }

    static class CurrencyResponse {
        String result;
        ConversionRates conversion_rates;

        static class ConversionRates {
            double USD;
            double EUR;
            double JPY;
        }
    }

}