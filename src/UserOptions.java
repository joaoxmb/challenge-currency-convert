import java.util.ArrayList;
import java.util.Arrays;

public class UserOptions {
    Currency fromCurrency;
    Currency toCurrency;

    ArrayList<Currency> options = new ArrayList<>(Arrays.asList(
        new Currency("BRL", "Real", "R$"),
        new Currency("USD", "Dolar", "$"),
        new Currency("EUR", "Euro", "€"),
        new Currency("JPY", "Iene", "¥")
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