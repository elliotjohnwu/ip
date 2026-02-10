import java.util.Scanner;

public class Jellyfish {
    public static final String space = "____________________________________________________________\n";

    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";*/
        //System.out.println("Hello from\n" + logo);
        String name = "Jellyfish\n";
        String greeting = "Hi, need help?\n";
        System.out.println("Hello, I'm " + name + greeting + space);

        Echo.startEcho();

        String farewell = "Bye bye. Dont die\n";
        System.out.println(farewell + space);

        //rubbish code for branch
        System.out.println(farewell + space);
    }
}
