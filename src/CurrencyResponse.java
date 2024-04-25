public class CurrencyResponse {
    String result;
    Rates conversion_rates;

    static class Rates {
        double USD;
        double EUR;
        double JPY;
        double BRL;

    }
}