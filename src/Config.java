import java.util.ArrayList;
import java.util.Arrays;

public class Config {
    Currency fromCurrency;
    Currency toCurrency;

    ArrayList<Currency> options = new ArrayList<>(Arrays.asList(
        new Currency("BRL", "Real", "R$"),
        new Currency("USD", "Dólar Americano", "$"),
        new Currency("EUR", "Euro", "€"),
        new Currency("JPY", "Iene Japonês", "¥"),
        new Currency("GBP", "Libra Esterlina", "£"),
        new Currency("AUD", "Dólar Australiano", "$"),
        new Currency("CAD", "Dólar Canadense", "$"),
        new Currency("CHF", "Franco Suíço", "CHF"),
        new Currency("CNY", "Yuan Chinês", "¥"),
        new Currency("SEK", "Coroa Sueca", "kr"),
        new Currency("NZD", "Dólar da Nova Zelândia", "$")
    ));

    static class Currency {
        String code;
        String name;
        String symbol;

        public Currency(String code, String name, String symbol) {
            this.code = code;
            this.name = name;
            this.symbol = symbol;
        }
    }
}