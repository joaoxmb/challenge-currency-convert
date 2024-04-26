import java.util.Scanner;

public class Main {
    static Config config;

    public static void main(String[] args) {
        askConfig();
        askCommand();
    }

    public static void askCommand() {
        Scanner scanner = new Scanner(System.in);

        convertMessage();

        while (true) {
            System.out.print("/ ");
            String choices = scanner.nextLine();


            switch (choices) {
                case "config":
                    askConfig();
                    convertMessage();
                    break;
                case "invert":
                    Config.Currency currentFrom = config.fromCurrency;
                    config.fromCurrency = config.toCurrency;
                    config.toCurrency = currentFrom;

                    convertMessage();
                    break;
                default:
                    try {
                        // Tentamos transformar string em double
                        choices = choices.replaceAll("[^0-9.]", "");
                        double amount = Double.parseDouble(choices);

                        if (amount > 0) {
                            double calculated = Api.calc(config.fromCurrency.code, config.toCurrency.code, amount);
                            System.out.printf("""
                                %s %.2f = %s %.2f
                                
                                """, config.fromCurrency.symbol, amount, config.toCurrency.symbol, calculated);
                            break;
                        }

                        System.out.println("""
                            
                            0 não é um valor válido para se fazer o calculo de cotação:
                            """);

                    } catch (NumberFormatException e) {
                        // Caso o entrada passada seja inválida
                        System.out.println("""
                            
                            Esse comando não exites, tente novamente:
                            """);
                    }
            }

        }
    }

    public static void askConfig() {
        Config currentConfig = new Config();

        MenuSelect first = new MenuSelect("""
                
                =====================================================
                                 Conversor de Moedas
                =====================================================
                
                Moeda de Origem:
                """, currentConfig.options);

        currentConfig.fromCurrency = currentConfig.options.get(first.selected);


        // Remove a opcao selecionada anteriormente para não se repetir
        currentConfig.options.remove(first.selected);

        MenuSelect secondary = new MenuSelect("""
                
                Moeda de Destino:
                """, currentConfig.options);

        currentConfig.toCurrency = currentConfig.options.get(secondary.selected);

        // Inserindo valores de configuracao ao finalizar configuracao
        config = currentConfig;
    }

    private static void convertMessage() {
        System.out.printf("""
       
                    =====================================================
                         Você está convertendo %s para %s
                    =====================================================
                    
                    """, config.fromCurrency.name, config.toCurrency.name, config.fromCurrency.code, config.fromCurrency.name);
    }
}