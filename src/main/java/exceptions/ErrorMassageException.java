package exceptions;

import java.util.ArrayList;
import java.util.List;

public class ErrorMassageException extends RuntimeException {
    private List<String> messages = new ArrayList<>();

    public ErrorMassageException() {
    }

    public ErrorMassageException(String message) {
        messages.add(message);
    }

    public List<String> getMessages() {
        return messages;
    }
}
