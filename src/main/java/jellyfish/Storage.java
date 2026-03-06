package jellyfish;

import jellyfish.task.Deadline;
import jellyfish.task.Event;
import jellyfish.task.Task;
import jellyfish.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles loading and saving tasks to a local file.
 * Tasks are stored in a pipe-delimited text format at ./data/jellyfish.txt.
 */
public class Storage {
	
	/**
	 * Saves all tasks to the data file, overwriting any existing content.
	 *
	 * @param tasks The list of tasks to save.
	 * @throws JellyfishException If the directory cannot be created or the file cannot be written.
	 */
	public static void save(ArrayList<Task> tasks) throws JellyfishException {
		try {
			File file = new File("./data/jellyfish.txt");
			File parentDir = file.getParentFile();
			if (!parentDir.exists() && !parentDir.mkdirs()) {
				throw new JellyfishException("Could not create data directory.");
			}
			FileWriter fw = new FileWriter(file);
			for (Task t : tasks) {
				fw.write(t.toSaveFormat() + System.lineSeparator());
			}
			fw.close();
		} catch (IOException e) {
			throw new JellyfishException("Could not save tasks to file: " + e.getMessage());
		}
	}
	
	/**
	 * Loads tasks from the data file into the given list.
	 * If the file does not exist, no tasks are loaded and no error is thrown.
	 *
	 * @param tasks The list to populate with loaded tasks.
	 * @throws JellyfishException If the file cannot be read.
	 */
	public static void load(ArrayList<Task> tasks) throws JellyfishException {
		File file = new File("./data/jellyfish.txt");
		if (!file.exists()) {
			return;
		}
		try {
			Scanner s = new Scanner(file);
			while (s.hasNextLine()) {
				String line = s.nextLine();
				String[] parts = line.split(" \\| ");
				String type = parts[0].trim();
				boolean done = parts[1].trim().equals("1");
				Task t = null;
				if (type.equals("T")) {
					t = new Todo(parts[2].trim());
				} else if (type.equals("D")) {
					t = new Deadline(parts[2].trim(), parts[3].trim());
				} else if (type.equals("E")) {
					t = new Event(parts[2].trim(), parts[3].trim(), parts[4].trim());
				}
				if (t != null) {
					if (done) t.markDone();
					tasks.add(t);
				}
			}
		} catch (IOException e) {
			throw new JellyfishException("Could not load tasks from file: " + e.getMessage());
		}
	}
}