package jellyfish.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a start and end date or time.
 * Accepts dates in yyyy-MM-dd format and displays them as MMM dd yyyy.
 * Plain text dates are stored and displayed as-is.
 */
public class Event extends Task {
    
    protected String from;
    protected String to;
    protected LocalDate fromDate;
    protected LocalDate toDate;
    
    /**
     * Creates an event task with the given description, start, and end.
     * Attempts to parse from and to as dates in yyyy-MM-dd format.
     *
     * @param description The task description.
     * @param from        The start date or time, either yyyy-MM-dd or plain text.
     * @param to          The end date or time, either yyyy-MM-dd or plain text.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        try {
            this.fromDate = LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            this.fromDate = null;
        }
        try {
            this.toDate = LocalDate.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            this.toDate = null;
        }
    }
    
    /**
     * Returns the event task in save file format.
     * Saves the raw date strings to ensure correct reloading regardless of format.
     *
     * @return A pipe-delimited string with type, status, description, start, and end.
     */
    @Override
    public String toSaveFormat() {
        return "E | " + super.toSaveFormat() + " | " + from + " | " + to;
    }
    
    /**
     * Returns a string representation of the event task.
     * Valid dates for from and to are displayed as MMM dd yyyy.
     * Otherwise the original strings are displayed as-is.
     *
     * @return Formatted string including task type, status, description, start, and end.
     */
    @Override
    public String toString() {
        String formattedFrom = (fromDate != null)
                ? fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) : from;
        String formattedTo = (toDate != null)
                ? toDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) : to;
        return "[E]" + super.toString() + " (from: " + formattedFrom + " to: " + formattedTo + ")";
    }
}