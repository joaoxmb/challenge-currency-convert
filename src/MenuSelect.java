import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MenuSelect {
    String title;
    ArrayList<Config.Currency> items;
    int selected;

    public MenuSelect(String title, ArrayList<Config.Currency> items) {
        this.title = title;
        this.items = items;
        this.selected = select();
    }

    public int select() {

        Scanner scanner = new Scanner(System.in);
        ArrayList<String> codes = new ArrayList<>();

        System.out.println(title);

        int index = 1;
        for (Config.Currency item : this.items) {
            System.out.println(index + ". " + item.code + " - " + item.name);

            codes.add(item.code);
            index++;
        }

        System.out.print("""
                
                Escolha uma opção:""");

        int selected;
        while (true) {
            String response = scanner.nextLine();

            if (response.matches("\\d+")) {
                // se houver apenas numeros na string
                int responseToInt = Integer.parseInt(response);

                if (responseToInt >= 1 && responseToInt < index) {
                    selected = responseToInt - 1;
                    break;
                }
            }

            // se houver caracteres
            int selectByCode = codes.indexOf(response.toUpperCase());

            if (selectByCode >= 0) {
                selected = selectByCode;
                break;
            }

            System.out.println("""
                    
                    Opção inválida, tente novamente:""");
        }

        return selected;
    }
}
