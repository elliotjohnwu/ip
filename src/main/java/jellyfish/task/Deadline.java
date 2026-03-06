package jellyfish.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 * Accepts dates in yyyy-MM-dd format and displays them as MMM dd yyyy.
 * Plain text dates are stored and displayed as-is.
 */
public class Deadline extends Task {
    
    protected String by;
    protected LocalDate byDate;
    
    /**
     * Creates a deadline task with the given description and deadline string.
     * Attempts to parse the deadline as a date in yyyy-MM-dd format.
     *
     * @param description The task description.
     * @param by          The deadline, either in yyyy-MM-dd format or plain text.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        try {
            this.byDate = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            this.byDate = null;
        }
    }
    
    /**
     * Returns the deadline task in save file format.
     * Saves the raw date string to ensure correct reloading regardless of format.
     *
     * @return A pipe-delimited string with type, status, description, and deadline.
     */
    @Override
    public String toSaveFormat() {
        return "D | " + super.toSaveFormat() + " | " + by;
    }
    
    /**
     * Returns a string representation of the deadline task.
     * If the deadline is a valid date, it is displayed as MMM dd yyyy.
     * Otherwise the original string is displayed as-is.
     *
     * @return Formatted string including task type, status, description, and deadline.
     */
    @Override
    public String toString() {
        if (byDate != null) {
            return "[D]" + super.toString() + " (by: "
                    + byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        }
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}