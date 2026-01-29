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
                for (int i = 1; i <= listsTracker; i++ ) {
                    System.out.println(Integer.toString(i) + ". " + lists[i-1]);
                }
                System.out.println("\n" + Jellyfish.space);
            } else if (line.contains(("mark"))) {
                int markedPosition;
                String text = line.trim();
                if (line.contains("unmark")) {
                    markedPosition = Integer.parseInt(line.substring(7));
                    lists[markedPosition-1] = "[ ] " + lists[markedPosition].substring(4);
                    System.out.println("not done :(\n" + Integer.toString(markedPosition) + ". " + lists[markedPosition-1]);
                } else {
                    markedPosition = Integer.parseInt(line.substring(5));
                    lists[markedPosition-1] = "[X] " + lists[markedPosition-1].substring(4);
                    System.out.println("done :)\n" + Integer.toString(markedPosition) + ". " + lists[markedPosition-1]);
                } //case where thrs no integer after mark/unmark in progress

            } else { //main tracker
                    lists[listsTracker] = "[ ] " + line;
                    listsTracker++;
                    System.out.println("added: " + line + "\n" + Jellyfish.space);
            }
        }
    }
}
