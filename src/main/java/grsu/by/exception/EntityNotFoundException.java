package grsu.by.exception;

import java.util.Map;

public class EntityNotFoundException extends AbstractException {
    private static final String MESSAGE = " Can not find specified entity ";

    public EntityNotFoundException(Map<String, String> details) {
        super(MESSAGE, details);
    }
}
