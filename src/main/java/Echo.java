import java.util.Scanner;

public class Echo {

    public static void startEcho() {
        String line;
        Scanner in = new Scanner(System.in);

        //boolean isBye = false;
        Task[] tasks = new Task[100];
        int taskCounter = 0;

        while (true) {
            line = in.nextLine();
            //end echo
            if (line.equalsIgnoreCase("bye")) {
                //isBye = true;
                System.out.println(Jellyfish.space);
                break;

            }
            //full list
            if (taskCounter >=99) {
                //isBye = true;
                System.out.println(Jellyfish.space);
                break;
            }
            //call list
            if (line.equalsIgnoreCase("list")) {
                for (int i = 1; i <= taskCounter; i++ ) {
                    System.out.println(Integer.toString(i) + "." + tasks[i-1].toStringWithMarking());
                }
                System.out.println("\n" + Jellyfish.space);
                continue;
            }
            //marking
            if (line.contains(("mark"))) { //change to line.substring(0,5)  start with mark/unmark
                int markedPosition = 0;
                String text = line.trim().toLowerCase();
                if (text.startsWith("unmark")) {
                    markedPosition = Integer.parseInt(line.substring(7)) - 1;
                    tasks[markedPosition].markNotDone();
                    //tasks[markedPosition-1] = "[ ] " + tasks[markedPosition-1].substring(4);
                    System.out.println("not done :(\n" + Integer.toString(markedPosition+1) + ". " + tasks[markedPosition].toStringWithMarking());

                } else if (text.startsWith("mark")) {
                    markedPosition = Integer.parseInt(line.substring(5)) - 1;
                    tasks[markedPosition].markDone();
                    //tasks[markedPosition-1] = "[X] " + tasks[markedPosition].substring(4);
                    System.out.println("done :)\n" + Integer.toString(markedPosition+1) + ". " + tasks[markedPosition].toStringWithMarking());
                } //ERROR CASE FOR IF OUT OF BOUNDS (NO INTEGER AFTER (UN)MARK)
                continue;
            }
            tasks[taskCounter] = new Task(line);
            System.out.println("added: " + tasks[taskCounter].toStringWithMarking() + "\n" + Jellyfish.space);
            taskCounter++;
        }
    }
}
