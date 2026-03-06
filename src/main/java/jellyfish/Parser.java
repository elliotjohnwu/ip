package jellyfish;

import jellyfish.task.Deadline;
import jellyfish.task.Event;
import jellyfish.task.Task;
import jellyfish.task.Todo;
import java.util.ArrayList;

/**
 * Parses user input and delegates to the appropriate command handler.
 */
public class Parser {
	
	/**
	 * Parses the given input line and executes the corresponding command.
	 *
	 * @param line  The full input string entered by the user.
	 * @param tasks The current task list to operate on.
	 */
	public static void parse(String line, TaskList tasks) {
		
		if (line.equalsIgnoreCase("list")) {
			Ui.showTaskList(tasks.getTasks());
			return;
		}
		
		if (line.toLowerCase().startsWith("delete")) {
			parseDelete(line, tasks);
			return;
		}
		
		if (line.toLowerCase().startsWith("mark") || line.toLowerCase().startsWith("unmark")) {
			parseMark(line, tasks);
			return;
		}
		
		if (line.toLowerCase().startsWith("find")) {
			parseFind(line, tasks);
			return;
		}
		
		if (line.toLowerCase().startsWith("todo") || line.toLowerCase().startsWith("deadline")
				|| line.toLowerCase().startsWith("event")) {
			parseAdd(line, tasks);
			return;
		}
		
		Ui.showError("Invalid command, please try bye, list, (un)mark, todo, event, deadline, delete");
	}
	
	private static void parseDelete(String line, TaskList tasks) {
		String[] words = line.trim().split(" ");
		try {
			if (words.length != 2) {
				throw new JellyfishException("invalid number of words for delete");
			}
			for (char digit : words[1].toCharArray()) {
				if (!Character.isDigit(digit)) {
					throw new JellyfishException("invalid character for delete");
				}
			}
			int index = Integer.parseInt(words[1]) - 1;
			if (index < 0 || index >= tasks.size()) {
				throw new JellyfishException("invalid digit for delete");
			}
			Task removed = tasks.deleteTask(index);
			Ui.showTaskDeleted(index + 1, removed);
			Storage.save(tasks.getTasks());
		} catch (JellyfishException e) {
			Ui.showError(e.getMessage());
		}
	}
	
	private static void parseMark(String line, TaskList tasks) {
		String[] words = line.trim().split(" ");
		try {
			if (words.length != 2) {
				throw new JellyfishException("invalid number of words for mark");
			}
			for (char digit : words[1].toCharArray()) {
				if (!Character.isDigit(digit)) {
					throw new JellyfishException("invalid character for mark");
				}
			}
			int index = Integer.parseInt(words[1]) - 1;
			if (index < 0 || index >= tasks.size()) {
				throw new JellyfishException("invalid digit for mark");
			}
			if (words[0].equalsIgnoreCase("unmark")) {
				tasks.unmarkTask(index);
				Storage.save(tasks.getTasks());
				Ui.showTaskUnmarked(index + 1, tasks.getTask(index));
				return;
				
			}
			if (words[0].equalsIgnoreCase("mark")) {
				tasks.markTask(index);
				Storage.save(tasks.getTasks());
				Ui.showTaskMarked(index + 1, tasks.getTask(index));
				return;
				
			}
			throw new JellyfishException("invalid mark");
		} catch (JellyfishException e) {
			Ui.showError(e.getMessage());
		}
	}
	
	private static void parseFind(String line, TaskList tasks) {
		try {
			String[] words = line.split(" ", 2);
			if (words.length < 2 || words[1].isBlank()) {
				throw new JellyfishException("find missing following word");
			} else if (!words[0].equals("find")) {
				throw new JellyfishException("invalid find spelling");
			}
			String keyword = words[1].trim().toLowerCase();
			ArrayList<Task> results = new ArrayList<>();
			for (int i = 0; i < tasks.size(); i++) {
				if (tasks.getTask(i).getDescription().toLowerCase().contains(keyword)) {
					results.add(tasks.getTask(i));
				}
			}
			Ui.showFindResults(results);
		} catch (JellyfishException e) {
			Ui.showError(e.getMessage());
		}
	}
	
	private static void parseAdd(String line, TaskList tasks) {
		Task newTask = null;
		try {
			if (line.toLowerCase().startsWith("todo")) {
				String[] descriptions = line.split(" ", 2);
				if (descriptions.length < 2 || descriptions[1].isBlank()) {
					throw new JellyfishException("todo missing following word");
				} else if (!descriptions[0].equals("todo")) {
					throw new JellyfishException("invalid todo spelling");
				}
				newTask = new Todo(descriptions[1]);
				
			}
			if (line.toLowerCase().startsWith("deadline")) {
				String[] text = line.split(" ", 2);
				if (text.length < 2 || text[1].isEmpty() || !text[0].equalsIgnoreCase("deadline")) {
					throw new JellyfishException("Invalid deadline format. Use: "
							+ "deadline <description> /by <time>");
				}
				String[] split = text[1].split(" /by ", 2);
				if (split.length < 2) {
					throw new JellyfishException("Invalid deadline format. "
							+ "Use: deadline <description> /by <time>");
				}
				newTask = new Deadline(split[0], split[1]);
				
			}
			if (line.toLowerCase().startsWith("event")) {
				String[] text = line.split(" ", 2);
				if (text.length < 2 || text[1].isEmpty() || !text[0].equalsIgnoreCase("event")) {
					throw new JellyfishException("Invalid event format. "
							+ "Use: event <description> /from <start> /to <end>");
				}
				String[] split = text[1].split(" /from | /to ");
				if (split.length < 3) {
					throw new JellyfishException("Invalid event format. "
							+ "Use: event <description> /from <start> /to <end>");
				}
				newTask = new Event(split[0], split[1], split[2]);
			}
		} catch (JellyfishException e) {
			Ui.showError(e.getMessage());
			return;
		}
		
		if (newTask != null) {
			tasks.addTask(newTask);
			Ui.showTaskAdded(newTask);
			try {
				Storage.save(tasks.getTasks());
			} catch (JellyfishException e) {
				Ui.showError(e.getMessage());
			}
		} else {
			Ui.showError("Invalid task add");
		}
	}
}