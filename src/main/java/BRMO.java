import java.util.Scanner;

public class BRMO {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm BetaRMinusOne; a.k.a. BRMO.");

        String list[] = new String[100];
        int listN = 0;

        for(String input = scanner.nextLine(); !input.equals("bye"); input = scanner.nextLine()) {
            
            if(input.equals("list")) {
                for(int i = 0; i < listN; ++i)
                    System.out.println(i + 1 + ". " + list[i]);
            } else {
                list[listN++] = input;
                System.out.println("added: " + input);
            }
        }

        System.out.println("We shall meet again.");
        scanner.close();
    }
}
