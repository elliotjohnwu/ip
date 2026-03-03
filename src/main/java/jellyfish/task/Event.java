package jellyfish.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected String from;
    protected String to;
    protected LocalDate fromDate;
    protected LocalDate toDate;

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
    
    @Override
    public String toSaveFormat() {
        return "E | " + super.toSaveFormat() + " | " + from + " | " + to;
    }
    
    @Override
    public String toString() {
        String formattedFrom;
        if (fromDate != null) {
            formattedFrom = fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } else {
            formattedFrom = from;
        }
        
        String formattedTo;
        if (toDate != null) {
            formattedTo = toDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } else {
            formattedTo = to;
        }
        return "[E]" + super.toString() + " (from: " + formattedFrom + " to: " + formattedTo + ")";
    }
    
}

