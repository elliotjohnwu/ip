package jellyfish;

import jellyfish.task.Deadline;
import jellyfish.task.Event;
import jellyfish.task.Task;
import jellyfish.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
	
	public static void save(Task[] tasks, int taskCounter) throws JellyfishException {
		try {
			File file = new File("./data/jellyfish.txt");
			File parentDir = file.getParentFile();
			if (!parentDir.exists() && !parentDir.mkdirs()) {
				throw new JellyfishException("Could not create data directory.");
			}
			FileWriter fw = new FileWriter(file);
			for (int i = 0; i < taskCounter; i++) {
				fw.write(tasks[i].toSaveFormat() + System.lineSeparator());
			}
			fw.close();
		} catch (IOException e) {
			throw new JellyfishException("Could not save tasks to file: " + e.getMessage());
		}
	}
	
	public static int load(Task[] tasks) throws JellyfishException {
		int taskCounter = 0;
		File file = new File("./data/jellyfish.txt");
		if (!file.exists()) {
			return 0;
		}
		try {
			Scanner s = new Scanner(file);
			while (s.hasNextLine()) {
				String line = s.nextLine();
				String[] parts = line.split(" \\| ");
				String type = parts[0].trim();
				boolean done = parts[1].trim().equals("1");
				if (type.equals("T")) {
					tasks[taskCounter] = new Todo(parts[2].trim());
				} else if (type.equals("D")) {
					tasks[taskCounter] = new Deadline(parts[2].trim(), parts[3].trim());
				} else if (type.equals("E")) {
					tasks[taskCounter] = new Event(parts[2].trim(), parts[3].trim(), parts[4].trim());
				}
				if (done) tasks[taskCounter].markDone();
				taskCounter++;
			}
		} catch (IOException e) {
			throw new JellyfishException("Could not load tasks from file: " + e.getMessage());
		}
		return taskCounter;
	}
}