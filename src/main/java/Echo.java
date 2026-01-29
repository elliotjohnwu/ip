import java.util.Scanner;

public class Echo {

    public static void startEcho() {
        String line;
        Scanner in = new Scanner(System.in);

        boolean isBye = false;

        while (!isBye) {
            System.out.print("Type something: ");
            line = in.nextLine();
            if (line.equalsIgnoreCase("bye")) {
                isBye = true;
                System.out.println(Jellyfish.space);
            } else {
                System.out.println("You said: " + line+ "\n" + Jellyfish.space);
            }
        }
    }
}
