package jellyfish.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected String by;
    protected LocalDate byDate;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        
        
        try {
            this.byDate = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            this.byDate = null;
        }
    }
    
    @Override
    public String toSaveFormat() {
        return "D | " + super.toSaveFormat() + " | " + by;
    }
    
    
    @Override
    public String toString() {
        if (byDate != null) {
            return "[D]" + super.toString() + " (by: "
                    + byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        }
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}


