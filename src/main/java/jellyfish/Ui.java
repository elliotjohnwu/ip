package jellyfish;

import jellyfish.task.Task;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
	
	private static final Scanner in = new Scanner(System.in);
	
	public static void showWelcome() {
		String name = "jellyfish.Jellyfish\n";
		String greeting = "Hi, need help?\n";
		System.out.println("Hello, I'm " + name + greeting + Jellyfish.space);
	}
	
	public static void showFarewell() {
		System.out.println("Bye bye. Dont die\n" + Jellyfish.space);
	}
	
	public static void showLine() {
		System.out.println(Jellyfish.space);
	}
	
	public static String readCommand() {
		return in.nextLine();
	}
	
	public static void showError(String message) {
		System.out.println(message);
	}
	
	public static void showLoadingError() {
		System.out.println("Warning: could not load tasks from file.");
	}
	
	public static void showTaskAdded(Task task) {
		System.out.println("added: " + task.toString() + "\n" + Jellyfish.space);
	}
	
	public static void showTaskDeleted(int position, Task task) {
		System.out.println("deleted " + position + ". " + task.toString() + "\n" + Jellyfish.space);
	}
	
	public static void showTaskMarked(int position, Task task) {
		System.out.println("done :)\n" + position + ". " + task.toString() + "\n" + Jellyfish.space);
	}
	
	public static void showTaskUnmarked(int position, Task task) {
		System.out.println("not done :(\n" + position + ". " + task.toString() + "\n" + Jellyfish.space);
	}
	
	public static void showTaskList(ArrayList<Task> tasks) {
		if (tasks.isEmpty()) {
			System.out.println("Empty List");
		}
		for (int i = 1; i <= tasks.size(); i++) {
			System.out.println(i + "." + tasks.get(i - 1).toString());
		}
		System.out.println("\n" + Jellyfish.space);
	}
}