package jellyfish;

import jellyfish.task.Task;
import java.util.ArrayList;

/**
 * Manages the list of tasks, providing operations to add, delete, mark, and retrieve tasks.
 */
public class TaskList {
	
	private final ArrayList<Task> tasks;
	
	/** Creates an empty task list. */
	public TaskList() {
		this.tasks = new ArrayList<>();
	}
	
	/**
	 * Creates a task list pre-populated with the given tasks.
	 *
	 * @param tasks An existing list of tasks to manage.
	 */
	public TaskList(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}
	
	/**
	 * Adds a task to the list.
	 *
	 * @param task The task to add.
	 */
	public void addTask(Task task) {
		tasks.add(task);
	}
	
	/**
	 * Removes and returns the task at the given index.
	 *
	 * @param index Zero-based index of the task to delete.
	 * @return The removed task.
	 */
	public Task deleteTask(int index) {
		return tasks.remove(index);
	}
	
	/**
	 * Marks the task at the given index as done.
	 *
	 * @param index Zero-based index of the task.
	 */
	public void markTask(int index) {
		tasks.get(index).markDone();
	}
	
	/**
	 * Marks the task at the given index as not done.
	 *
	 * @param index Zero-based index of the task.
	 */
	public void unmarkTask(int index) {
		tasks.get(index).markNotDone();
	}
	
	/**
	 * Returns the task at the given index.
	 *
	 * @param index Zero-based index of the task.
	 * @return The task at that index.
	 */
	public Task getTask(int index) {
		return tasks.get(index);
	}
	
	/**
	 * Returns the number of tasks in the list.
	 *
	 * @return The task count.
	 */
	public int size() {
		return tasks.size();
	}
	
	/**
	 * Returns true if there are no tasks in the list.
	 *
	 * @return True if empty, false otherwise.
	 */
	public boolean isEmpty() {
		return tasks.isEmpty();
	}
	
	/**
	 * Returns the underlying ArrayList of tasks.
	 *
	 * @return The task list.
	 */
	public ArrayList<Task> getTasks() {
		return tasks;
	}
}