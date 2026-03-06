package jellyfish.task;

/**
 * Represents a task with a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    
    /**
     * Creates a task with the given description, marked as not done.
     *
     * @param description The task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    
    /** Marks this task as done. */
    public void markDone() {
        isDone = true;
    }
    
    /** Marks this task as not done. */
    public void markNotDone() {
        isDone = false;
    }
    
    /**
     * Returns the status icon for display.
     *
     * @return "X" if done, " " if not done.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }
    
    /**
     * Returns the task in save file format.
     *
     * @return A string formatted for writing to the save file.
     */
    public String toSaveFormat() {
        return (isDone ? "1" : "0") + " | " + description;
    }
    
    /**
     * Returns the task description.
     *
     * @return The description string.
     */
    public String getDescription() {
        return description;
    }
    
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

