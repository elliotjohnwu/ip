package jellyfish;

import java.util.ArrayList;

public class Jellyfish {
    
    public static final String space = "____________________________________________________________\n";
    
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
    /*dummy code as i forgot to branch out first for level 9 ack*/
}