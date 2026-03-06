package jellyfish;

/**
 * Represents errors specific to the Jellyfish application,
 * such as invalid commands or file I/O failures.
 */
public class JellyfishException extends Exception {
    
    /**
     * Creates a JellyfishException with the given message.
     *
     * @param message Description of the error.
     */
    public JellyfishException(String message) {
        super(message);
    }
}