import java.util.Scanner;
import java.util.Arrays;

public class Echo {

    public static void startEcho() {
        String line;
        Scanner in = new Scanner(System.in);

        boolean isBye = false;
        String[] lists = new String[100];
        Integer listsTracker = 0;

        while (!isBye) {
            //System.out.print("Type something: ");
            line = in.nextLine();
            if (line.equalsIgnoreCase("bye")) {
                isBye = true;
                System.out.println(Jellyfish.space);

            } else if (listsTracker>=99) {
                isBye = true;
                System.out.println(Jellyfish.space);
                break;

            } else if (line.equalsIgnoreCase("list")) {
                for (int i = 0; i < listsTracker; i++ ) {
                    System.out.println(Integer.toString(i+1) + ". " + lists[i]);
                }
                System.out.println("\n" + Jellyfish.space);
            } else {
                    lists[listsTracker] = line;
                    listsTracker++;
                    System.out.println("added: " + line + "\n" + Jellyfish.space);
            }
        }
    }
}
