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

public class Storage {
	
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