package jellyfish;

import jellyfish.task.Task;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles all user-facing input and output for the Jellyfish application.
 */
public class Ui {
	
	private static final Scanner in = new Scanner(System.in);
	
	/** Displays the welcome message on startup. */
	public static void showWelcome() {
		String name = "Jellyfish, ";
		String greeting = "at your service.\n";
		System.out.println("Hello, I'm " + name + greeting + Jellyfish.space);
	}
	
	/** Displays the farewell message on exit. */
	public static void showFarewell() {
		System.out.println("Bye bye. Dont die\n" + Jellyfish.space);
	}
	
	/** Prints the divider line. */
	public static void showLine() {
		System.out.println(Jellyfish.space);
	}
	
	/**
	 * Reads and returns the next line of user input.
	 *
	 * @return The input string entered by the user.
	 */
	public static String readCommand() {
		return in.nextLine();
	}
	
	/**
	 * Displays an error message to the user.
	 *
	 * @param message The error message to display.
	 */
	public static void showError(String message) {
		System.out.println(message);
	}
	
	/** Displays a warning that tasks could not be loaded from file. */
	public static void showLoadingError() {
		System.out.println("Warning: could not load tasks from file.");
	}
	
	/**
	 * Displays a confirmation that a task was added.
	 *
	 * @param task The task that was added.
	 */
	public static void showTaskAdded(Task task) {
		System.out.println("added: " + task.toString() + "\n" + Jellyfish.space);
	}
	
	/**
	 * Displays a confirmation that a task was deleted.
	 *
	 * @param position The 1-based position of the deleted task.
	 * @param task     The task that was deleted.
	 */
	public static void showTaskDeleted(int position, Task task) {
		System.out.println("deleted " + position + ". " + task.toString() + "\n" + Jellyfish.space);
	}
	
	/**
	 * Displays a confirmation that a task was marked as done.
	 *
	 * @param position The 1-based position of the task.
	 * @param task     The task that was marked.
	 */
	public static void showTaskMarked(int position, Task task) {
		System.out.println("done :)\n" + position + ". " + task.toString() + "\n" + Jellyfish.space);
	}
	
	/**
	 * Displays a confirmation that a task was marked as not done.
	 *
	 * @param position The 1-based position of the task.
	 * @param task     The task that was unmarked.
	 */
	public static void showTaskUnmarked(int position, Task task) {
		System.out.println("not done :(\n" + position + ". " + task.toString() + "\n" + Jellyfish.space);
	}
	
	/**
	 * Displays all tasks in the list.
	 *
	 * @param tasks The list of tasks to display.
	 */
	public static void showTaskList(ArrayList<Task> tasks) {
		if (tasks.isEmpty()) {
			System.out.println("Empty List");
		}
		for (int i = 1; i <= tasks.size(); i++) {
			System.out.println(i + "." + tasks.get(i - 1).toString());
		}
		System.out.println("\n" + Jellyfish.space);
	}
	
	/**
	 * Displays the results of a find command.
	 * Shows a message if no matches were found.
	 *
	 * @param results The list of matching tasks.
	 */
	public static void showFindResults(ArrayList<Task> results) {
		if (results.isEmpty()) {
			System.out.println("No matching tasks found.\n" + Jellyfish.space);
			return;
		}
		System.out.println("Here are the matching tasks in your list:");
		for (int i = 1; i <= results.size(); i++) {
			System.out.println(" " + i + "." + results.get(i - 1).toString());
		}
		System.out.println(Jellyfish.space);
	}
}