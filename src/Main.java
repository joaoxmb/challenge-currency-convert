import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Currency;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException, NoSuchFieldException, IllegalAccessException {
        UserOptions selectedOptions = askConfig();

        while (true) {
            double amount = askAmount(selectedOptions.fromCurrency.name, selectedOptions.toCurrency.name);

            if (amount <= 0) {
                break;
            }
            double calculated = calcRates(selectedOptions, amount);
            System.out.println(calculated);
        }

    }

    public static double calcRates(UserOptions options, double amount) throws IOException, InterruptedException {
        Gson gson = new Gson();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://v6.exchangerate-api.com/v6/2df7a50257c7ddb5ce0fb4da/pair/" + options.fromCurrency.code + "/" + options.toCurrency.code + "/" + amount))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        CalcResponse result = gson.fromJson(json, CalcResponse.class);

        return result.conversion_result;
    }

    class CalcResponse {
        String result;
        double conversion_result;
    }

    public static UserOptions askConfig() {
        Scanner askFor = new Scanner(System.in);
        UserOptions selectedOptions = new UserOptions();

        // Primeira pergunta
        System.out.println("""
                -----------------------
                Bem vindo! Selecione a moeda que você deseja converter,
                você precisa inserir o valor númerico da opção de sua escolha:
                
                """);

        int index = 1;
        for (UserOptions.Currency item : selectedOptions.options) {
            System.out.println(index + ") " + item.name);
            index++;
        }

        System.out.println("-----------------------");
        int firstSelected = askFor.nextInt() - 1;
        selectedOptions.fromCurrency = selectedOptions.options.get(firstSelected);

        // Segunda pergunta
            // Remove a opcao selecionada anteriormente para não se repetir
        selectedOptions.options.remove(firstSelected);

        System.out.printf("""
                -----------------------
                Bacana! Agora escolha a moeda para qual deseja
                converter o seu %s
                
                """, selectedOptions.fromCurrency.name);

        int indexDois = 1;
        for (UserOptions.Currency item : selectedOptions.options) {
            System.out.println(indexDois + ") " + item.name);
            indexDois++;
        }
        System.out.println("-----------------------");

        selectedOptions.toCurrency = selectedOptions.options.get(askFor.nextInt() - 1);

        System.out.println("Você está convertendendo " + selectedOptions.fromCurrency.name + " para " + selectedOptions.toCurrency.name);

        return selectedOptions;
    }

    public static double askAmount(String fromCurrencyName, String toCurrencyName) {
        Scanner askFor = new Scanner(System.in);
        System.out.printf("""
                    Digite o valor em %s que você deseja converter para %s
                    """, fromCurrencyName, toCurrencyName);

        return askFor.nextDouble();
    }
}