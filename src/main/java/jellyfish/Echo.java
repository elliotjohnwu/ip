package jellyfish;

import jellyfish.task.Deadline;
import jellyfish.task.Event;
import jellyfish.task.Task;
import jellyfish.task.Todo;

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
                System.out.println("jellyfish.task.Task List Full");
                break;
            }

            //call list
            if (line.equalsIgnoreCase("list")) {
                for (int i = 1; i <= taskCounter; i++ ) {
                    System.out.println(i + "." + tasks[i-1].toString());
                }
                System.out.println("\n" + Jellyfish.space);
                continue;
            }

            //marking // added ERROR CASE FOR OUT OF BOUNDS (NO INTEGER AFTER (UN)MARK) || int exceeds task array size
            if (line.toLowerCase().startsWith("mark") || line.toLowerCase().startsWith("unmark")) {
                String text = line.trim();
                String[] words = text.split(" ");

                try {
                    //error handling

                    //invalid number of strings
                    if (words.length != 2) {
                        throw new JellyfishException("invalid number of words for mark");
                    }

                    //has non-digit character
                    for (char digit : words[1].toCharArray()) {
                        if (!Character.isDigit(digit)) {
                            throw new JellyfishException("invalid character for mark");
                        }
                    }


                    int markedPosition = Integer.parseInt(words[1]) - 1;

                    //invalid digit
                    if (markedPosition + 1 > taskCounter||markedPosition < 0) {
                        throw new JellyfishException("invalid digit for mark");
                    }

                    //mark/unmark logic
                    if (words[0].equalsIgnoreCase("unmark")) {
                        tasks[markedPosition].markNotDone();
                        System.out.println("not done :(\n" + (markedPosition + 1) + ". "
                                                   + tasks[markedPosition].toString() + "\n" + Jellyfish.space);

                    } else if (words[0].equalsIgnoreCase("mark")) {
                        tasks[markedPosition].markDone();
                        System.out.println("done :)\n" + (markedPosition + 1) + ". "
                                                   + tasks[markedPosition].toString() + "\n" + Jellyfish.space);

                    } else {
                        throw new JellyfishException("invalid mark");
                    }
                    //success, no errors
                    continue;

                } catch (JellyfishException e) {
                    System.out.println(e.getMessage());

                } continue;

            }

            if (line.toLowerCase().startsWith("todo")||line.toLowerCase().startsWith("deadline")||
                        line.toLowerCase().startsWith("event")) {
                if (line.toLowerCase().startsWith("todo")) {
                    try {
                        String[] descriptions = line.split(" ", 2);

                        if (descriptions.length < 2) {
                            throw new JellyfishException("todo missing following word");

                        } else {
                            String description = descriptions[1];
                            if (description.isBlank()) {
                                throw new JellyfishException("todo missing following word");

                            } else if (!descriptions[0].equals("todo")) {
                                throw new JellyfishException("invalid todo spelling");

                            } else {
                                tasks[taskCounter] = new Todo(description);
                            }
                        }

                    } catch (JellyfishException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }

                } else if (line.toLowerCase().startsWith("deadline")) {
                    try {
                        String text = line.substring(8).trim();

                        if (text.isEmpty()) {
                            throw new IndexOutOfBoundsException();
                        }
                        String[] split = line.substring(9).split(" /by ", 2);
                        String description = split[0];
                        String by = split[1];
                        tasks[taskCounter] = new Deadline(description, by);

                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Invalid deadline format. Use: deadline <description> /by <time>");
                        continue;
                    }

                } else if (line.toLowerCase().startsWith("event")) {
                    try {
                        String text = line.substring(5).trim();

                        if (text.isEmpty()) {
                            throw new IndexOutOfBoundsException();
                        }
                        String[] split = line.substring(6).split(" /from | /to ");
                        String description = split[0];
                        String from = split[1];
                        String to = split[2];
                        tasks[taskCounter] = new Event(description, from, to);

                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Invalid event format. Use: event <description> /from <start> /to <end>");
                        continue;
                    }
                }
                System.out.println("added: " + tasks[taskCounter].toString() + "\n" + Jellyfish.space);
                taskCounter++;

            } else {
                System.out.println("Invalid command, please try bye, list, (un)mark, todo, event, deadline");

            }
        }
    }
}
