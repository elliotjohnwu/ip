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
                    System.out.println(Integer.toString(i) + "." + tasks[i-1].toString());
                }
                System.out.println("\n" + Jellyfish.space);
                continue;
            }

            //marking // add ERROR CASE FOR OUT OF BOUNDS (NO INTEGER AFTER (UN)MARK) || int exceeds task array size
            if (line.contains(("mark"))) { //change to line.substring(0,5)  start with mark/unmark
                int markedPosition = 0;
                String text = line.trim().toLowerCase();
                if (text.startsWith("unmark")) {
                    markedPosition = Integer.parseInt(line.substring(7)) - 1;
                    tasks[markedPosition].markNotDone();
                    System.out.println("not done :(\n" + Integer.toString(markedPosition+1) + ". "
                            + tasks[markedPosition].toString() + "\n"  + Jellyfish.space);
                    continue;

                } else if (text.startsWith("mark")) {
                    markedPosition = Integer.parseInt(line.substring(5)) - 1;
                    tasks[markedPosition].markDone();
                    System.out.println("done :)\n" + Integer.toString(markedPosition+1) + ". "
                            + tasks[markedPosition].toString() + "\n"  + Jellyfish.space);
                    continue;

                } else {
                    tasks[taskCounter] = new Task(line);
                }

            }

            if (line.startsWith("todo ")) {
                String description = line.substring(5);
                tasks[taskCounter] = new Todo(description);

            } else if (line.startsWith("deadline ")) {
                String[] split = line.substring(9).split(" /by ", 2);
                String description = split[0];
                String by = split[1];
                tasks[taskCounter] = new Deadline(description, by);

            } else if (line.startsWith("event ")) {
                String[] split = line.substring(6).split(" /from | /to ");
                String description = split[0];
                String from = split[1];
                String to = split[2];
                tasks[taskCounter] = new Event(description, from, to);

            } else {
                tasks[taskCounter] = new Task(line);
            }

            System.out.println("added: " + tasks[taskCounter].toString() + "\n" + Jellyfish.space);
            taskCounter++;
        }
    }
}
