package jellyfish.task;

/**
 * Represents a todo task with no date or time attached.
 */
public class Todo extends Task {
    
    /**
     * Creates a todo task with the given description.
     *
     * @param description The task description.
     */
    public Todo(String description) {
        super(description);
    }
    
    /**
     * Returns the todo task in save file format.
     *
     * @return A pipe-delimited string with type, status, and description.
     */
    @Override
    public String toSaveFormat() {
        return "T | " + super.toSaveFormat();
    }
    
    /**
     * Returns a string representation of the todo task.
     *
     * @return Formatted string including task type, status, and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}