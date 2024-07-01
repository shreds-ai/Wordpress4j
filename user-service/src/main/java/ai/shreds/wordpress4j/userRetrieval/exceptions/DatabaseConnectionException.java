package ai.shreds.wordpress4j.userRetrieval.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.ObjectOutputStream;

@Getter
@Setter
public class DatabaseConnectionException extends Exception {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConnectionException.class);

    public DatabaseConnectionException(String message) {
        super(message);
        logger.info("DatabaseConnectionException initialized with message only.");
    }

    public DatabaseConnectionException(String message, Throwable cause) {
        super(message, cause);
        logger.debug("DatabaseConnectionException initialized with message and cause.", cause);
    }

    public DatabaseConnectionException(Throwable cause) {
        super(cause);
        logger.info("DatabaseConnectionException initialized with cause only.", cause);
    }

    public Throwable getRootCause() {
        return getCause();
    }

    public void logExceptionDetails() {
        logger.error("Exception occurred: " + getMessage());
        if (getCause() != null) {
            logger.error("Caused by: ", getCause());
        }
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeObject(getMessage());
        oos.writeObject(getRootCause());
    }
}