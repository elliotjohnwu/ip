package jellyfish;

import jellyfish.task.Deadline;
import jellyfish.task.Event;
import jellyfish.task.Task;
import jellyfish.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class Echo {

    public static void startEcho() {
        String line;
        Scanner in = new Scanner(System.in);

        boolean isBye = false;
        ArrayList<Task> tasks = new ArrayList<>();
		try {
            Storage.load(tasks);
			
		} catch (JellyfishException e) {
			System.out.println(e.getMessage());
			
		}

        while (!isBye) {
            line = in.nextLine();
            //end echo
            if (line.equalsIgnoreCase("bye")) {
                isBye = true;
                System.out.println(Jellyfish.space);
                continue;

            }

            //call list
            if (line.equalsIgnoreCase("list")) {
                if (tasks.isEmpty()) {
                    System.out.println("Empty List");
                    
                }
                for (int i = 1; i <= tasks.size(); i++ ) {
                    System.out.println(i + "." + tasks.get(i - 1).toString());
                }
                System.out.println("\n" + Jellyfish.space);
                continue;
            }

            //delete
            if (line.toLowerCase().startsWith("delete")) {
                String text = line.trim();
                String[] words = text.split(" ");

                //error handling
                try {
                    //invalid number of strings
                    if (words.length != 2) {
                        throw new JellyfishException("invalid number of words for delete");
                    }

                    //has non-digit character
                    for (char digit : words[1].toCharArray()) {
                        if (!Character.isDigit(digit)) {
                            throw new JellyfishException("invalid character for delete");
                        }
                    }

                    int markedPosition = Integer.parseInt(words[1]) - 1;
                    //invalid digit
                    if (markedPosition + 1 > tasks.size()||markedPosition < 0) {
                        throw new JellyfishException("invalid digit for delete");
                    }

                    //delete logic
                    if (words[0].equalsIgnoreCase("delete")) {
                        System.out.println("deleted " + (markedPosition + 1) + ". "
                                + tasks.get(markedPosition).toString() + "\n" + Jellyfish.space);
                        tasks.remove(markedPosition);

                    } else {
                        throw new JellyfishException("invalid delete");
                    }
                    //success, no errors
                    continue;

                } catch (JellyfishException e) {
                    System.out.println(e.getMessage());

                } continue;

            }


            //marking // added ERROR CASE FOR OUT OF BOUNDS (NO INTEGER AFTER (UN)MARK) || int exceeds task array size
            if (line.toLowerCase().startsWith("mark") || line.toLowerCase().startsWith("unmark")) {
                String text = line.trim();
                String[] words = text.split(" ");

                //error handling
                try {
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
                    if (markedPosition + 1 > tasks.size()||markedPosition < 0) {
                        throw new JellyfishException("invalid digit for mark");
                    }

                    //mark/unmark logic
                    if (words[0].equalsIgnoreCase("unmark")) {
                        tasks.get(markedPosition).markNotDone();
                        Storage.save(tasks);
                        System.out.println("not done :(\n" + (markedPosition + 1) + ". "
                                + tasks.get(markedPosition).toString() + "\n" + Jellyfish.space);

                    } else if (words[0].equalsIgnoreCase("mark")) {
                        tasks.get(markedPosition).markDone();
                        Storage.save(tasks);
                        System.out.println("done :)\n" + (markedPosition + 1) + ". "
                                + tasks.get(markedPosition).toString() + "\n" + Jellyfish.space);

                    } else {
                        throw new JellyfishException("invalid mark");
                    }
                    //success, no errors
                    continue;

                } catch (JellyfishException e) {
                    System.out.println(e.getMessage());

                } continue;

            }

            if (line.toLowerCase().startsWith("todo")||line.toLowerCase().startsWith("deadline")
                    || line.toLowerCase().startsWith("event")) {
                
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
                                tasks.add(new Todo(description));
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
                        tasks.add(new Deadline(description, by));

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
                        tasks.add(new Event(description, from, to));

                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Invalid event format. Use: event <description> /from <start> /to <end>");
                        continue;
                    }
                }
                System.out.println("added: " + tasks.get(tasks.size() - 1).toString() + "\n" + Jellyfish.space);
                try {
                    Storage.save(tasks);
                } catch (JellyfishException e) {
                    System.out.println(e.getMessage());
                }
                
                
            } else {
                System.out.println("Invalid command, please try bye, list, (un)mark, todo, event, deadline, delete");

            }
        }
    }
}
