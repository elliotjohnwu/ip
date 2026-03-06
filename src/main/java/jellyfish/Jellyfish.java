package jellyfish;

import java.util.ArrayList;

/**
 * Entry point of the Jellyfish chatbot application.
 * Initializes all components and runs the main command loop.
 */
public class Jellyfish {
    
    /** Divider line used throughout the UI. */
    public static final String space = "____________________________________________________________\n";
    
    /**
     * Starts the Jellyfish application.
     * Loads saved tasks, then repeatedly reads and processes user commands until bye is entered.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Ui.showWelcome();
        
        TaskList tasks;
        ArrayList<jellyfish.task.Task> loadedTasks = new ArrayList<>();
        try {
            Storage.load(loadedTasks);
            tasks = new TaskList(loadedTasks);
        } catch (JellyfishException e) {
            Ui.showLoadingError();
            tasks = new TaskList();
        }
        
        boolean isBye = false;
        while (!isBye) {
            String line = Ui.readCommand();
            if (line.equalsIgnoreCase("bye")) {
                isBye = true;
                Ui.showLine();
            } else {
                Parser.parse(line, tasks);
            }
        }
        
        Ui.showFarewell();
    }
}